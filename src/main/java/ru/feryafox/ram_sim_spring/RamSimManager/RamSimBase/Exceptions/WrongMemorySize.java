package ru.feryafox.ram_sim_spring.RamSimManager.RamSimBase.Exceptions;

public class WrongMemorySize extends RamSimException{
    public static String message = "Неправильный размер памяти для выделения";
    public WrongMemorySize() {
        super(message);
    }
}
