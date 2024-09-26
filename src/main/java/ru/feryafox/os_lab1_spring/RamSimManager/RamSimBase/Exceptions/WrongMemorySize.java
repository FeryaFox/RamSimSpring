package ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.Exceptions;

public class WrongMemorySize extends RamSimException{
    public static String message = "Неправильный размер памяти для выделения";
    public WrongMemorySize() {
        super(message);
    }
}
