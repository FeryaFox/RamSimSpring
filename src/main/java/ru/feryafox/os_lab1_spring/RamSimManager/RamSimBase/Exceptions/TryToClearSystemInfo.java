package ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.Exceptions;

public class TryToClearSystemInfo extends RamSimException{
    public TryToClearSystemInfo() {
        super("Попытка освободить системную информацию");
    }
}
