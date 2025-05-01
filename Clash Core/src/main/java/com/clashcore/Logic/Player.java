package Logic;

public class Player {
    public static int HighID = 0;
    public static int LowID = 0;
    public static String Token = null;

    private Device device;

    public Player(Device device) {
        this.device = device;
    }

    public void encode() {
    }
}
