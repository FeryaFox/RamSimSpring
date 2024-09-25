package ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.Exceptions;

public class TryToClearForeignBlock extends RamSimException {
    public TryToClearForeignBlock() {
        super("Попытка очистить чужой блок данных");
    }
}
