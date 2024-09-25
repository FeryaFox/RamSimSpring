package ru.feryafox.os_lab1_spring.RamSimManager.ByteMapRamSim.Utils;

import java.util.ArrayList;

public class ByteUtils {
   public static byte invertBit(byte b, int bitNumber) {
       return (byte) (b ^ (1 << bitNumber));
   }

    public static String byteToBinaryString(byte b) {
        StringBuilder sb = new StringBuilder(8);
        for (int i = 7; i >= 0; i--) {
            sb.append((b >> i) & 1);
        }
        return sb.toString();
    }

    public static ArrayList<String> byteArrayToStringArray(byte[] b){
       ArrayList<String> list = new ArrayList<>();

       for (int i = 0; i < b.length; i++) {
           list.add(byteToBinaryString(b[i]));
       }

       return list;
    }

    public static int getBit(byte b, int bitNumber) {
       return (b >> bitNumber) & 1;
    }
}
