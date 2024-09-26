package ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.Exceptions;

public class TryToClearSystemInfo extends RamSimException{
    public static String message = "Попытка освободить системную информацию";
    public TryToClearSystemInfo() {
        super(message);
    }
}
