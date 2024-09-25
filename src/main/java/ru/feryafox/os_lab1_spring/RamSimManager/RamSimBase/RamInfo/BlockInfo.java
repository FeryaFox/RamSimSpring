package ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.RamInfo;

public class BlockInfo {
    protected int processId;
    protected int size;
    protected int startedAddress;

    public BlockInfo(int processId, int size, int startedAddress) {
        this.processId = processId;
        this.size = size;
        this.startedAddress = startedAddress;
    }

    public int getProcessId() {
        return processId;
    }
    public int getSize() {
        return size;
    }
    public int getStartedAddress() {
        return startedAddress;
    }
}
