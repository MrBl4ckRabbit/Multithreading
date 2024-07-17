package _02._5;

public class IMF {

    private static IMF imf;

    public static IMF getFund() {
        synchronized (IMF.class) {
            imf = new IMF();
        }
        return imf;
    }

    private IMF() {
    }
}

