package ru.feryafox.os_lab1_spring.RamSimManager.ByteMapRamSim;


import ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.RamInfo.BlockInfo;
import ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.RamInfo.RamInfo;

import java.util.ArrayList;


public class ByteMapRamInfo extends RamInfo {
    protected int byteMapSize;
    protected int availableMemory;


    public ByteMapRamInfo(int ramSize, ArrayList<BlockInfo> ramInfo, int freeRam, int usedRam, int byteMapSize, int availableMemory) {
        super(ramSize, ramInfo, freeRam, usedRam);
        this.byteMapSize = byteMapSize;
        this.availableMemory = availableMemory;
    }

    public int getByteMapSize() {
        return byteMapSize;
    }

    public int getAvailableMemory() {
        return availableMemory;
    }

    public static class Builder extends RamInfo.Builder {
        protected int byteMapSize;
        protected int availableMemory;

        public Builder setAvailableMemory(int availableMemory) {
            this.availableMemory = availableMemory;
            return this;
        }

        public Builder setByteMapSize(int byteMapSize) {
            this.byteMapSize = byteMapSize;
            return this;
        }

        public ByteMapRamInfo build() {
            return new ByteMapRamInfo(ramSize, ramInfo, freeRam, usedRam, byteMapSize, availableMemory);
        }
    }
}
