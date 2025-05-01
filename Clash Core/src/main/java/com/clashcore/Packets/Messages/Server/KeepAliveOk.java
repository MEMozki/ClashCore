package Packets.Messages.Server;

import Utils.Writer;
import Logic.Device;

public class KeepAliveOk extends Writer {

    public KeepAliveOk(Device device) {
        super(device);
        this.id = 20108;
        this.device = device;
    }

    @Override
    public void encode() {
        // Logic Decode
    }
}
