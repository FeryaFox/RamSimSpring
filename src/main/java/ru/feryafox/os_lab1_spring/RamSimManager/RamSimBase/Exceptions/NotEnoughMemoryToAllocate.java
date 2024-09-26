package ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.Exceptions;

public class NotEnoughMemoryToAllocate extends RamSimException {
    public static String message = "Недостаточно памяти для выделения";
    public NotEnoughMemoryToAllocate() {
        super(message);
    }
}
