package ru.feryafox.os_lab1_spring.RamSimManager;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import ru.feryafox.os_lab1_spring.RamSimManager.ByteMapRamSim.ByteMapRamInfo;
import ru.feryafox.os_lab1_spring.RamSimManager.ByteMapRamSim.ByteMapRamSim;
import ru.feryafox.os_lab1_spring.RamSimManager.ByteMapRamSim.RamSimSettings.ByteMapRamSimSettings;
import ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.RamSimBase;

import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;


@Component
@ApplicationScope
public class RamSimManager {
    private HashMap<String, RamSimBase> ramSims = new HashMap<>();


    public String createRamSim(int ramSize, int biteMapSize) {
        ByteMapRamSimSettings ramSimSettings = new ByteMapRamSimSettings(ramSize, biteMapSize);

        String uuid = UUID.randomUUID().toString();
        ramSims.put(uuid, new ByteMapRamSim(ramSimSettings));

        return uuid;
    }

    public HashMap<String, RamSimBase> getRamSims() {
        return ramSims;
    }

    public void deleteRamSim(String uuid) {
        ramSims.remove(uuid);
    }

    public RamSimBase getRamSim(String uuid) {
        return ramSims.get(uuid);
    }

    public boolean isRamSimExist(String uuid) {
        return ramSims.containsKey(uuid);
    }

    public int allocateMemory(String uuid, int sizeMemoryToAllocate, int processId) {
        return ramSims.get(uuid).allocateMemory((byte) sizeMemoryToAllocate, (byte) processId);
    }

    public void clearMemory(String uuid, int sizeMemoryToClear, int processId, Integer startedAddress) {
        ramSims.get(uuid).clearMemory((byte) sizeMemoryToClear, (byte) processId, startedAddress);
    }

    public HashMap<String, ShortRamSimInfo> getByteMapRamSims() {
        HashMap<String, ShortRamSimInfo> ramSimInfos = new HashMap<>();
        for (String key : ramSims.keySet()) {
            ByteMapRamInfo ramInfo = (ByteMapRamInfo) ramSims.get(key).getRamInfo();
            ramSimInfos.put(key, new ShortRamSimInfo( ramInfo.getRamSize(), ramInfo.getBiteMapSize(), ramInfo.getFreeRam(), ramInfo.getUsedRam()));
        }
        return ramSimInfos;
    }

    public record ShortRamSimInfo(int ramSize, int biteMapSize, int freeMemory, int usedMemory) {}

}
