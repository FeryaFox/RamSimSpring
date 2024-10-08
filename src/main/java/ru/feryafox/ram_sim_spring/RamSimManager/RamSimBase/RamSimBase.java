package ru.feryafox.ram_sim_spring.RamSimManager.RamSimBase;

import ru.feryafox.ram_sim_spring.RamSimManager.RamSimBase.RamInfo.RamInfo;

public interface RamSimBase {
    int allocateMemory(byte sizeMemoryToAllocate, byte processId);
    void clearMemory(byte sizeMemoryToClear, byte processId, int startedAddress);
    byte[] getRam();
    RamInfo getRamInfo();
}
