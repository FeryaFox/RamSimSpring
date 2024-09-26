package ru.feryafox.os_lab1_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.RamSimBase;
import ru.feryafox.os_lab1_spring.RamSimManager.RamSimManager;

import java.util.HashMap;

@Controller
public class RamSimController {

    final RamSimManager ramSimManager;

    public RamSimController(RamSimManager ramSimManager) {
        this.ramSimManager = ramSimManager;
    }

    @PostMapping("/ram_sim")
    @ResponseBody
    public String createRamSim(@RequestParam Integer ramSize, @RequestParam Integer blockSize) {
        String uuid = ramSimManager.createRamSim(ramSize, blockSize);
        return uuid;
    }

    @GetMapping("/ram_sim")
    @ResponseBody
    public HashMap<String, RamSimBase> getRamSim() {
        return ramSimManager.getRamSims();
    }

    @DeleteMapping("/ram_sim/{ramSimUUID}")
    @ResponseBody
    public void deleteRamSim(@PathVariable String ramSimUUID) {
        ramSimManager.deleteRamSim(ramSimUUID);
    }

    @GetMapping("/ram_sim/{ramSimUUID}")
    @ResponseBody
    public RamSimBase getRamSim(@PathVariable String ramSimUUID) {
        return ramSimManager.getRamSim(ramSimUUID);
    }

    @PostMapping("/ram_sim/{ramSimUUID}/allocate_memory")
    @ResponseBody
    public int allocateRamSim(@PathVariable String ramSimUUID, @RequestParam Integer sizeMemoryToAllocate, @RequestParam Integer processId) {
        return ramSimManager.allocateMemory(ramSimUUID, sizeMemoryToAllocate, processId);
    }
}
