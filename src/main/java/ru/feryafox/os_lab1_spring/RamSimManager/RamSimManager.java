package ru.feryafox.os_lab1_spring.RamSimManager;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.RamSimBase;

import java.util.HashMap;


@Component
@ApplicationScope
public class RamSimManager {
    private HashMap<String, RamSimBase> ramSims = new HashMap<>();
}
