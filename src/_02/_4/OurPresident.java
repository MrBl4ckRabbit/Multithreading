package _02._4;

public class OurPresident {
    private static OurPresident president;

    static {
        synchronized (OurPresident.class) {//синхронизация статического блока.
            president = new OurPresident();
        }
    }

    private OurPresident() {
    }

    public static OurPresident getOurPresident() {
        return president;
    }
}

