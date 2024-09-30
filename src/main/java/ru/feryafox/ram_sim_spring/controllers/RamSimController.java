package ru.feryafox.ram_sim_spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.feryafox.ram_sim_spring.RamSimManager.RamSimBase.Exceptions.*;
import ru.feryafox.ram_sim_spring.RamSimManager.RamSimManager;

import java.util.HashMap;

@RestController
public class RamSimController {

    final RamSimManager ramSimManager;

    public RamSimController(RamSimManager ramSimManager) {
        this.ramSimManager = ramSimManager;
    }

    @PostMapping("/ram_sim")
    @ResponseBody
    public String createRamSim(@RequestParam Integer ramSize, @RequestParam Integer blockSize) {
        return ramSimManager.createRamSim(ramSize, blockSize);
    }

    @GetMapping("/ram_sim")
    @ResponseBody
    public HashMap<String, RamSimManager.ShortRamSimInfo> getRamSim() {
        return ramSimManager.getByteMapRamSims();
    }

    @DeleteMapping("/ram_sim/{ramSimUUID}")
    @ResponseBody
    public void deleteRamSim(@PathVariable String ramSimUUID) {
        ramSimManager.deleteRamSim(ramSimUUID);
    }

    @GetMapping("/ram_sim/{ramSimUUID}")
    @ResponseBody
    public RamSimManager.LongRamSimInfo getRamSim(@PathVariable String ramSimUUID) {
        return ramSimManager.getLongRamSimInfo(ramSimUUID);
    }

    @PostMapping("/ram_sim/{ramSimUUID}/allocate_memory")
    @ResponseBody
    public ResponseEntity<?> allocateMemory(
            @PathVariable String ramSimUUID, 
            @RequestParam Integer sizeMemoryToAllocate, 
            @RequestParam Integer processId
    ) {
        try {
            int allocatedAddress = ramSimManager.allocateMemory(ramSimUUID, sizeMemoryToAllocate, processId);
            return ResponseEntity.ok(allocatedAddress);
        } catch (WrongMemorySize e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(0, WrongMemorySize.message));
        }
        catch (WrongProcessIdParam e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(1, WrongProcessIdParam.message));
        }
        catch (NotEnoughMemoryToAllocate e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(2, NotEnoughMemoryToAllocate.message));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(-1, e.getMessage()));
        }
    }

    @PostMapping("/ram_sim/{ramSimUUID}/clear_memory")
    @ResponseBody
    public ResponseEntity<?> clearMemory(
            @PathVariable String ramSimUUID, 
            @RequestParam Integer sizeMemoryToClear, 
            @RequestParam Integer processId, 
            @RequestParam Integer startedAddress
    ) {
        try {
            ramSimManager.clearMemory(ramSimUUID, sizeMemoryToClear, processId, startedAddress);
            return ResponseEntity.ok("Success");
        }
        catch (WrongMemorySize e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(0, WrongMemorySize.message));
        }
        catch (WrongProcessIdParam e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(1, WrongProcessIdParam.message));
        }
        catch (IndexToClearIsOutOfRange e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(3, IndexToClearIsOutOfRange.message));
        }
        catch (TryToClearSystemInfo e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(4, TryToClearSystemInfo.message));
        }
        catch (TryToClearForeignBlock e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(5, TryToClearForeignBlock.message));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(-1, e.getMessage()));
        }
    }

    public static class ErrorResponse {
        private Integer code;
        private String message;

        public ErrorResponse(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
