package ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.Exceptions;

public class NotEnoughMemoryToAllocate extends RamSimException {
    public NotEnoughMemoryToAllocate() {
        super("Недостаточно памяти для выделения");
    }
}
