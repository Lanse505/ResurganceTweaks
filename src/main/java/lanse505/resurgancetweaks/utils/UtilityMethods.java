package lanse505.resurgancetweaks.utils;

public class UtilityMethods {
    public static boolean tryProcentage(double percent) {
        return Math.random() < percent;
    }

    public static int nextIntInclusive(int min, int max) {
        return Constants.RANDOM.nextInt(max - min + 1) + min;
    }
}
