package Packets.Messages.Client;

import Utils.Reader;
import Packets.Factory.Packet;
import Packets.Messages.Server.KeepAliveOk;
import Logic.Device;

public class KeepAlive extends Reader implements Packet {
    private Device device;

    public KeepAlive(byte[] data, Device device) {
        super(data);
        this.device = device;
    }

    @Override
    public void decode() {
    	// Logic decode
    }

    @Override
    public void process() {
        KeepAliveOk keepAliveOk = new KeepAliveOk(device);
        keepAliveOk.send();
    }
}
