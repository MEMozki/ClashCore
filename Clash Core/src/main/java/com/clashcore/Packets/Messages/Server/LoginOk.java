package Packets.Messages.Server;

import Utils.Writer;
import Logic.Player;
import Logic.Device;

public class LoginOk extends Writer {

    private Player player;

    public LoginOk(Device device, Player player) {
        super(device);
        this.device = device;
        this.player = player;
        this.id = 20104;
        this.version = 1; 
    }

    @Override
    public void encode() {
        writeInt(0);  
        writeInt(1);  
        writeInt(0); 
        writeInt(1); 
        writeString("a77bad4dc5241ccb44d5a541376396208f92af8");

        writeString(null); 
        writeString(null); 

        writeInt(0);
        writeInt(0);
        writeInt(0);

        writeString("dev");  // env

        writeInt(0);
        writeInt(0);
        writeInt(0);

        writeString(null);
        System.out.println("[DEBUG] Message LoginOk has been sent.");
    }
}
