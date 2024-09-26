package ru.feryafox.os_lab1_spring.RamSimManager.ByteMapRamSim.Utils;

public class DoubleUtils {
    public static int divWithCeil (int a, int b){

        // Если кто-то это видит, простите... Мне было сложно что-то нормально придумать....

        double temp = (double) a / b;
        return (int) Math.ceil(temp);
    }
}