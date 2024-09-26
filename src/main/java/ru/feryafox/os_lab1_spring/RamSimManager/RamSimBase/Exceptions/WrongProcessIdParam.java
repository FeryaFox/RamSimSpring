package ru.feryafox.os_lab1_spring.RamSimManager.RamSimBase.Exceptions;

public class WrongProcessIdParam extends RamSimException{
    public static String message = "Некоретный processId.";
    public WrongProcessIdParam() {
        super(message);
    }
}
