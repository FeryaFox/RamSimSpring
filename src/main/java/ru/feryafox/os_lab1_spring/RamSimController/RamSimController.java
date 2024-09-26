package ru.feryafox.os_lab1_spring.RamSimController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.feryafox.os_lab1_spring.RamSimManager.RamSimManager;

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

}
