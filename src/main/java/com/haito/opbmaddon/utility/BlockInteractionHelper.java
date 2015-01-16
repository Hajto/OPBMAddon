package com.haito.opbmaddon.utility;


public class BlockInteractionHelper {
    public static int[] modifyOutChordsBySide(double posX, double posY, double posZ, int side) {
        switch (side) {
            case 0:
                posY--;
                break;
            case 1:
                posY++;
                break;
            case 2:
                posZ--;
                break;
            case 3:
                posZ++;
                break;
            case 4:
                posX--;
                break;
            case 5:
                posX++;
                break;
        }
        int[] temp = {(int) posX, (int) posY, (int) posZ};
        return temp;
    }
}
