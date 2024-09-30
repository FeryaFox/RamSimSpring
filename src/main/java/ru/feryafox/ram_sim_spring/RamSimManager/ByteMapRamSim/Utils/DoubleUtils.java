package ru.feryafox.ram_sim_spring.RamSimManager.ByteMapRamSim.Utils;

public class DoubleUtils {
    public static int divWithCeil (int a, int b){

        // Если кто-то это видит, простите... Мне было сложно что-то нормально придумать....

        double temp = (double) a / b;
        return (int) Math.ceil(temp);
    }
}