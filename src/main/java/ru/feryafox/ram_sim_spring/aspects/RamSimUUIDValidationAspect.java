package ru.feryafox.ram_sim_spring.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.feryafox.ram_sim_spring.RamSimManager.RamSimManager;
@Aspect
@Component
public class RamSimUUIDValidationAspect {

    private final RamSimManager ramSimManager;

    public RamSimUUIDValidationAspect(RamSimManager ramSimManager) {
        this.ramSimManager = ramSimManager;
    }

    @Around("execution(* ru.feryafox.ram_sim_spring.controllers.RamSimController.*(..)) && args(ramSimUUID, ..)")
    public Object checkRamSimUuid(ProceedingJoinPoint joinPoint, String ramSimUUID) throws Throwable {
        if (ramSimUUID == null || ramSimUUID.isEmpty() || !ramSimManager.isRamSimExist(ramSimUUID)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "RamSimUUID not found");
        }

        return joinPoint.proceed();
    }
}