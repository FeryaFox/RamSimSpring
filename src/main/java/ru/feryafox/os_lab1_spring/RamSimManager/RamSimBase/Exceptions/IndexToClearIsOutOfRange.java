package ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.Exceptions;

public class IndexToClearIsOutOfRange extends RamSimException {
    public IndexToClearIsOutOfRange() {
        super("Попытка очистить память за пределами памяти");
    }
}
