package ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase;

import ru.feryafox.os_lab1_spring.RamSimManager.ByteMapRamSim.ByteMapRamInfo;

public interface RamSimBase {
    int allocateMemory(byte sizeMemoryToAllocate, byte processId);
    void clearMemory(byte sizeMemoryToClear, byte processId, int startedAddress);
    byte[] getRam();
    ByteMapRamInfo getRamInfo();
}
