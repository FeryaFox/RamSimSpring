package ru.feryafox.os_lab1_spring.RamSimManager;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.ApplicationScope;
import ru.feryafox.os_lab1_spring.RamSimManager.ByteMapRamSim.ByteMapRamInfo;
import ru.feryafox.os_lab1_spring.RamSimManager.ByteMapRamSim.ByteMapRamSim;
import ru.feryafox.os_lab1_spring.RamSimManager.ByteMapRamSim.RamSimSettings.ByteMapRamSimSettings;
import ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.RamInfo.BlockInfo;
import ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.RamSimBase;
import ru.feryafox.os_lab1_spring.models.RamSimEntity;
import ru.feryafox.os_lab1_spring.repositories.RamSimRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;


@Component
@ApplicationScope
@Transactional
public class RamSimManager {

    private final RamSimRepository ramSimRepository;

    public RamSimManager(RamSimRepository ramSimRepository) {
        this.ramSimRepository = ramSimRepository;
    }

    @PostConstruct
    private void loadRamSimsFromDatabase() {
        List<RamSimEntity> ramSimEntities = ramSimRepository.findAll();
        for (RamSimEntity entity : ramSimEntities) {
            ByteMapRamSimSettings settings = new ByteMapRamSimSettings(entity.getRamSize(), entity.getBlockSize());
            ByteMapRamSim ramSim = new ByteMapRamSim(settings, entity.getRam());
            ramSims.put(entity.getUuid(), ramSim);
        }
    }

    private HashMap<String, RamSimBase> ramSims = new HashMap<>();


    public String createRamSim(int ramSize, int biteMapSize) {
        ByteMapRamSimSettings ramSimSettings = new ByteMapRamSimSettings(ramSize, biteMapSize);

        String uuid = UUID.randomUUID().toString();

        ByteMapRamSim newByteMapSim = new ByteMapRamSim(ramSimSettings);
        ramSims.put(uuid, newByteMapSim);

        RamSimEntity ramSimEntity = new RamSimEntity();
        ramSimEntity.setUuid(uuid);
        ramSimEntity.setRamSize(ramSize);
        ramSimEntity.setBlockSize(biteMapSize);
        ramSimEntity.setRam(newByteMapSim.getRam());

        ramSimRepository.save(ramSimEntity);

        return uuid;
    }

    public HashMap<String, RamSimBase> getRamSims() {
        return ramSims;
    }

    public void deleteRamSim(String uuid) {
        ramSims.remove(uuid);
        ramSimRepository.deleteByUuid(uuid);
    }

    public RamSimBase getRamSim(String uuid) {
        return ramSims.get(uuid);
    }

    public LongRamSimInfo getLongRamSimInfo(String uuid) {
        ByteMapRamSim ramSim = (ByteMapRamSim) ramSims.get(uuid);
        ByteMapRamInfo ramInfo = ramSim.getRamInfo();

        return new LongRamSimInfo(
                ramInfo.getRamSize(),
                ramInfo.getRamInfo(),
                ramInfo.getFreeRam(),
                ramInfo.getUsedRam(),
                ramInfo.getBiteMapSize(),
                ramInfo.getAvailableMemory(),
                ramInfo.getBlockSize(),
                toByteList(ramSim.getRam())
        );
    }

    public boolean isRamSimExist(String uuid) {
        return ramSims.containsKey(uuid);
    }

    public int allocateMemory(String uuid, int sizeMemoryToAllocate, int processId) {

        ByteMapRamSim currentSim = (ByteMapRamSim) ramSims.get(uuid);
        int address = currentSim.allocateMemory((byte) sizeMemoryToAllocate, (byte) processId);

        ramSimRepository.findByUuid(uuid).ifPresent(ramSim -> {
            ramSim.setRam(currentSim.getRam());
            ramSimRepository.save(ramSim);
        });

        return address;
    }

    public void clearMemory(String uuid, int sizeMemoryToClear, int processId, Integer startedAddress) {

        ByteMapRamSim currentSim = (ByteMapRamSim) ramSims.get(uuid);
        currentSim.clearMemory((byte) sizeMemoryToClear, (byte) processId, startedAddress);

        ramSimRepository.findByUuid(uuid).ifPresent(ramSim -> {
            ramSim.setRam(currentSim.getRam());
            ramSimRepository.save(ramSim);
        });
    }

    public HashMap<String, ShortRamSimInfo> getByteMapRamSims() {
        HashMap<String, ShortRamSimInfo> ramSimInfos = new HashMap<>();
        for (String key : ramSims.keySet()) {
            ByteMapRamInfo ramInfo = (ByteMapRamInfo) ramSims.get(key).getRamInfo();
            ramSimInfos.put(key, new ShortRamSimInfo( ramInfo.getRamSize(), ramInfo.getBlockSize(), ramInfo.getFreeRam(), ramInfo.getUsedRam()));
        }
        return ramSimInfos;
    }

    public record ShortRamSimInfo(int ramSize, int blockSize, int freeMemory, int usedMemory) {}

    public record LongRamSimInfo(int ramSize, ArrayList<BlockInfo> processes, int freeRam, int usedRam, int byteMapSize, int availableMemory, int blockSize, ArrayList<Byte> ram) {};


    private static ArrayList<Byte> toByteList(byte[] byteArray) {
        ArrayList<Byte> byteList = new ArrayList<>();
        for (byte b : byteArray) {
            byteList.add(b);
        }
        return byteList;
    }

}
