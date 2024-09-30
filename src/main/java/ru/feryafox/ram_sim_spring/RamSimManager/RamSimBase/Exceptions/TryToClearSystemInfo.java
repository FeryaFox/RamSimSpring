package ru.feryafox.ram_sim_spring.RamSimManager.RamSimBase.Exceptions;

public class TryToClearSystemInfo extends RamSimException{
    public static String message = "Попытка освободить системную информацию";
    public TryToClearSystemInfo() {
        super(message);
    }
}
