package ru.feryafox.ram_sim_spring.RamSimManager.ByteMapRamSim;

import ru.feryafox.ram_sim_spring.RamSimManager.ByteMapRamSim.RamSimSettings.ByteMapRamSimSettings;
import ru.feryafox.ram_sim_spring.RamSimManager.ByteMapRamSim.Utils.ByteUtils;
import ru.feryafox.ram_sim_spring.RamSimManager.ByteMapRamSim.Utils.DoubleUtils;
import ru.feryafox.ram_sim_spring.RamSimManager.RamSimBase.Exceptions.*;
import ru.feryafox.ram_sim_spring.RamSimManager.RamSimBase.RamInfo.BlockInfo;
import ru.feryafox.ram_sim_spring.RamSimManager.RamSimBase.RamSimBase;

import java.util.ArrayList;

public class ByteMapRamSim implements RamSimBase {

    private final int RAM_SIZE;
    private final int BLOCK_SIZE;
    private final byte[] ram;


    public ByteMapRamSim() {
        RAM_SIZE = 1024;
        BLOCK_SIZE = 8;
        ram = new byte[RAM_SIZE];
    }

    public ByteMapRamSim(ByteMapRamSimSettings settings) {
        RAM_SIZE = settings.RamSize();
        BLOCK_SIZE = settings.BlockSize();
        ram = new byte[RAM_SIZE];
    }

    public ByteMapRamSim(ByteMapRamSimSettings settings, byte[] ram) {
        RAM_SIZE = settings.RamSize();
        BLOCK_SIZE = settings.BlockSize();
        this.ram = ram;
    }

    public int allocateMemory(int sizeMemoryToAllocate, int processId) {
        return allocateMemory((byte) sizeMemoryToAllocate, (byte) processId);
    }

    public int allocateMemory(byte sizeMemoryToAllocate, byte processId) {


        if (sizeMemoryToAllocate <= 0) throw new WrongMemorySize();
        if (processId <= 0) throw new WrongProcessIdParam();

        int sizeMemoryToAllocateWithSystemInfo = sizeMemoryToAllocate + 8 * 2;

        int systemInfoSize = calculateByteMapSize();

        int availableMemoryAtMoment = 0;
        int availableMemoryAtMomentAddress = systemInfoSize;

        int neededBlocks = DoubleUtils.divWithCeil(sizeMemoryToAllocateWithSystemInfo, BLOCK_SIZE);
        int startedBlock = 0;

        for (int i = 0; i < systemInfoSize; i++) {
            for (int j = 7; j >= 0; j--) {
                int bit = ByteUtils.getBit(ram[i], j);

                if (bit == 1) {
                    availableMemoryAtMomentAddress += (availableMemoryAtMoment == 0) ? BLOCK_SIZE : availableMemoryAtMoment;
                    startedBlock += (availableMemoryAtMoment == 0) ? 1 : availableMemoryAtMoment / BLOCK_SIZE;
                    availableMemoryAtMoment = 0;

                    continue;
                }

                availableMemoryAtMoment += BLOCK_SIZE;


                if (availableMemoryAtMoment >= sizeMemoryToAllocateWithSystemInfo) {
                    allocateBlocks(startedBlock, neededBlocks);
                    setSystemInfoToBlock(sizeMemoryToAllocate, processId, availableMemoryAtMomentAddress);
                    return availableMemoryAtMomentAddress + 2;
                }
            }
        }

        throw new NotEnoughMemoryToAllocate();
    }

    public void clearMemory(int sizeMemoryToClear, int processId, int startedAddress) {
        clearMemory((byte) sizeMemoryToClear, (byte) processId, startedAddress);
    }

    public void clearMemory(byte sizeMemoryToClear, byte processId, int startedAddress) {
        if (sizeMemoryToClear <= 0) throw new WrongMemorySize();
        if (processId <= 0) throw new WrongProcessIdParam();

        if (startedAddress < 0 || startedAddress > RAM_SIZE ) throw new IndexToClearIsOutOfRange();
        if (startedAddress < calculateByteMapSize()) throw new TryToClearSystemInfo();
        if (ram[startedAddress - 1] != sizeMemoryToClear) throw new IndexToClearIsOutOfRange();
        if (ram[startedAddress - 2] != processId) throw new TryToClearForeignBlock();

        ram[startedAddress - 1] = 0;
        ram[startedAddress - 2] = 0;

        int c = 0;
        int startedClear = DoubleUtils.divWithCeil((startedAddress - 2), BLOCK_SIZE) - DoubleUtils.divWithCeil(calculateByteMapSize(), BLOCK_SIZE);
        int busyBlocks = DoubleUtils.divWithCeil((sizeMemoryToClear + 8 * 2 ), BLOCK_SIZE);
        int clearBlocks = 0;

        for (int i = 0; i < calculateByteMapSize(); i++) {
            for (int j = 7; j >= 0; j--) {
                if (c >= startedClear) {
                    if (clearBlocks == busyBlocks) return;

                    ram[i] = ByteUtils.invertBit(ram[i], j);

                    clearBlocks++;
                }
                c++;
            }
        }

    }

    private void allocateBlocks(int startedBlock, int blockCount) {
        int completedBlocks = 0;
        int changedBlocks = 0;

        for (int i = 0; i < calculateByteMapSize(); i++) {
            for (int j = 7; j >= 0; j--) {
                if (completedBlocks >= startedBlock) {
                    if (changedBlocks == blockCount) return;
                    ram[i] = ByteUtils.invertBit(ram[i], j);
                    changedBlocks++;
                }
                completedBlocks++;
            }
        }
    }

    public void setSystemInfoToBlock(byte sizeMemoryToAllocate, byte processId, int availableMemoryAtAddress) {
        ram[availableMemoryAtAddress] = processId;
        ram[availableMemoryAtAddress + 1] = sizeMemoryToAllocate;
    }

    public byte[] getRam() {
        return ram;
    }

    @Override
    public ByteMapRamInfo getRamInfo() {
        var builder = new ByteMapRamInfo.Builder();
        builder.setRamSize(RAM_SIZE);

        int freeRam = 0;
        int usedRam = 0;

        int realBlockCount = DoubleUtils.divWithCeil(RAM_SIZE - calculateByteMapSize(), BLOCK_SIZE);
        int stepedBlockCount = 0;

        for (int i = 0; i < calculateByteMapSize(); i++) {
            for (int j = 7; j >= 0; j--) {
                if (stepedBlockCount >= realBlockCount) break;
                if (ByteUtils.getBit(ram[i], j) == 0) freeRam += BLOCK_SIZE;
                if (ByteUtils.getBit(ram[i], j) == 1) usedRam += BLOCK_SIZE;
            }
        }
        freeRam -= calculateByteMapSize();

        builder.setFreeRam(freeRam);
        builder.setUsedRam(usedRam);
        builder.setByteMapSize(calculateByteMapSize());
        builder.setAvailableMemory(RAM_SIZE - calculateByteMapSize());
        builder.setBlockSize(BLOCK_SIZE);

        ArrayList<BlockInfo> blockInfos = new ArrayList<>();

        for (int i = calculateByteMapSize(); i < RAM_SIZE; i++) {
            if (ram[i] == 0) continue;
            blockInfos.add(new BlockInfo(ram[i], ram[i + 1], i + 2));

            i += DoubleUtils.divWithCeil( ram[i + 1] + 8 * 2 , BLOCK_SIZE * 8 ) - 1;
        }

        builder.setRamInfo(blockInfos);

        return builder.build();
    }

    public int calculateByteMapSize() {
        return calculateByteMapSize(RAM_SIZE, BLOCK_SIZE);
    }

    public static int calculateByteMapSize(int ramSize, int blockSize) {
        return (ramSize / blockSize + 7) / 8;
    }

}
