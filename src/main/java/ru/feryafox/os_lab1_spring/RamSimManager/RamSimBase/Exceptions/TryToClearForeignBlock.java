package ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.Exceptions;

public class TryToClearForeignBlock extends RamSimException {
    public static String message = "Попытка очистить чужой блок данных";
    public TryToClearForeignBlock() {
        super(message);
    }
}
