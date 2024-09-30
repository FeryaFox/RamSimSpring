package ru.feryafox.ram_sim_spring.RamSimManager.RamSimBase.RamInfo;

import java.util.ArrayList;

public class RamInfo {
    protected int ramSize;
    protected ArrayList<BlockInfo> ramInfo;
    protected int freeRam;
    protected int usedRam;

    public RamInfo(int ramSize, ArrayList<BlockInfo> ramInfo, int freeRam, int usedRam) {
        this.ramSize = ramSize;
        this.ramInfo = ramInfo;
        this.freeRam = freeRam;
        this.usedRam = usedRam;
    }

    public ArrayList<BlockInfo> getRamInfo() {
        return ramInfo;
    }

    public int getRamSize() {
        return ramSize;
    }

    public int getFreeRam() {
        return freeRam;
    }

    public int getUsedRam() {
        return usedRam;
    }

    public static class Builder {
        protected int ramSize;
        protected ArrayList<BlockInfo> ramInfo;
        protected int freeRam;
        protected int usedRam;

        public Builder setFreeRam(int freeRam) {
            this.freeRam = freeRam;
            return this;
        }

        public Builder setUsedRam(int usedRam) {
            this.usedRam = usedRam;
            return this;
        }

        public Builder setRamSize(int ramSize) {
            this.ramSize = ramSize;
            return this;
        }

        public Builder setRamInfo(ArrayList<BlockInfo> ramInfo) {
            this.ramInfo = ramInfo;
            return this;
        }

        public RamInfo build() {
            return new RamInfo(ramSize, ramInfo, freeRam, usedRam);
        }
    }
}
