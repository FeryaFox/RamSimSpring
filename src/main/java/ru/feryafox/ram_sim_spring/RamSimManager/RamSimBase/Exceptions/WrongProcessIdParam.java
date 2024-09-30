package ru.feryafox.ram_sim_spring.RamSimManager.RamSimBase.Exceptions;

public class WrongProcessIdParam extends RamSimException{
    public static String message = "Некоретный processId.";
    public WrongProcessIdParam() {
        super(message);
    }
}
