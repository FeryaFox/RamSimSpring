package ru.feryafox.ram_sim_spring.RamSimManager.RamSimBase.Exceptions;

public class IndexToClearIsOutOfRange extends RamSimException {
    public static String message = "Попытка очистить память за пределами памяти";
    public IndexToClearIsOutOfRange() {
        super(message);
    }
}
