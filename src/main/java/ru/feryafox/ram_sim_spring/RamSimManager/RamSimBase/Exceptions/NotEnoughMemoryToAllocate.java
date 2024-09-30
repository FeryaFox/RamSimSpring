package ru.feryafox.ram_sim_spring.RamSimManager.RamSimBase.Exceptions;

public class NotEnoughMemoryToAllocate extends RamSimException {
    public static String message = "Недостаточно памяти для выделения";
    public NotEnoughMemoryToAllocate() {
        super(message);
    }
}
