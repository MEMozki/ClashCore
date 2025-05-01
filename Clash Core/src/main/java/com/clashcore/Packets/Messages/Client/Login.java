package Packets.Messages.Client;

import Utils.Reader;
import Packets.Factory.Packet;
import Packets.Messages.Server.LoginOk;
import Packets.Messages.Server.OwnHomeData;
import Logic.Player;
import Logic.Device;

public class Login extends Reader implements Packet {
    private Device device;
    private Player player;

    public Login(byte[] data, Device device) {
        super(data); 
        this.device = device;
        this.player = new Player(device);
    }

    @Override
    public void decode() {
    	// Logic decode
    }

    @Override
    public void process() {
        LoginOk loginOk = new LoginOk(device, player);
        loginOk.send();
        OwnHomeData ownHomeData = new OwnHomeData(device, player);
        ownHomeData.send();
    }
}
