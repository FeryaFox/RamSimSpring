package ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.Exceptions;

public class IndexToClearIsOutOfRange extends RamSimException {
    public static String message = "Попытка очистить память за пределами памяти";
    public IndexToClearIsOutOfRange() {
        super(message);
    }
}
