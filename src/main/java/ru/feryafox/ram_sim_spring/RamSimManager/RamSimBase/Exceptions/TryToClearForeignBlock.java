package ru.feryafox.ram_sim_spring.RamSimManager.RamSimBase.Exceptions;

public class TryToClearForeignBlock extends RamSimException {
    public static String message = "Попытка очистить чужой блок данных";
    public TryToClearForeignBlock() {
        super(message);
    }
}
