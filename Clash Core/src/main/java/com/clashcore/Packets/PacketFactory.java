package Packets.Factory;

import Logic.Device;

public interface PacketFactory {
    Packet createInstance(byte[] data, Device device);
}
