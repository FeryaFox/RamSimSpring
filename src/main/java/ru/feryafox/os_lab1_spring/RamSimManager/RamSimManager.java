package ru.feryafox.os_lab1_spring.RamSimManager;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import ru.feryafox.os_lab1_spring.RamSimManager.ByteMapRamSim.ByteMapRamSim;
import ru.feryafox.os_lab1_spring.RamSimManager.ByteMapRamSim.RamSimSettings.ByteMapRamSimSettings;
import ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.RamSimBase;
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
}
