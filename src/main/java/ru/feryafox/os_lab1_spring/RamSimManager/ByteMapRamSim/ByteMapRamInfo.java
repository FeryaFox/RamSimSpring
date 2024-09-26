package ru.feryafox.os_lab1_spring.RamSimManager.ByteMapRamSim;


import ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.RamInfo.BlockInfo;
import ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.RamInfo.RamInfo;

import java.util.ArrayList;


public class ByteMapRamInfo extends RamInfo {
    protected int byteMapSize;
    protected int availableMemory;
    protected int blockSize;

    public ByteMapRamInfo(int ramSize, ArrayList<BlockInfo> ramInfo, int freeRam, int usedRam, int byteMapSize, int availableMemory, int blockSize) {
        super(ramSize, ramInfo, freeRam, usedRam);
        this.byteMapSize = byteMapSize;
        this.availableMemory = availableMemory;
        this.blockSize = blockSize;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public int getBiteMapSize() {
        return byteMapSize;
    }

    public int getAvailableMemory() {
        return availableMemory;
    }

    public static class Builder extends RamInfo.Builder {
        protected int byteMapSize;
        protected int availableMemory;
        protected int blockSize;

        public Builder setAvailableMemory(int availableMemory) {
            this.availableMemory = availableMemory;
            return this;
        }

        public Builder setByteMapSize(int byteMapSize) {
            this.byteMapSize = byteMapSize;
            return this;
        }

        public Builder setBlockSize(int blockSize) {
            this.blockSize = blockSize;
            return this;
        }

        public ByteMapRamInfo build() {
            return new ByteMapRamInfo(ramSize, ramInfo, freeRam, usedRam, byteMapSize, availableMemory, blockSize);
        }
    }
}
