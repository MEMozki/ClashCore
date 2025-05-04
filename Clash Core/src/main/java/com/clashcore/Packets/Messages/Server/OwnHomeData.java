package Packets.Messages.Server;

import Utils.Writer;
import Logic.Player;
import Logic.Device;

public class OwnHomeData extends Writer {

    private Player player;

    public OwnHomeData(Device client, Player player) {
        super(client);
        this.player = player;
        this.device = client;
        this.id = 24101;
    }

    @Override
    public void encode() {
        writeInt(0);
        writeInt(0);
        writeInt(0); // HighID
        writeInt(1); // LowID
         writeString("{\"buildings\":[{\"data\":1000001,\"x\":21,\"y\":21}]}"); // Home
        writeInt(0);
        writeInt(0);
        writeInt(0);

        // LogicClientAvatar
        writeInt(0); // unk
        writeInt(0); // HighID
        writeInt(1); // LowID
        writeInt(0);
        writeInt(1);

        writeByte(1); // Player is in clan

        writeInt(0);
        writeInt(1);

        writeString("GITHUB & TELEGRAM:\n@MEMozki");
        writeInt(13000001);

        writeInt(0);
        writeByte(1);
        writeInt(0);
        writeInt(1);
        writeByte(1);
        writeInt(0);
        writeInt(1);

        writeInt(0); // League

        writeInt(0);
        writeInt(10);
        writeInt(0);
        writeInt(0);

        writeString("Clash Core project"); // Name
        writeString(null);

        writeInt(0); // level
        writeInt(0); // exp
        writeInt(0); // gems
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0); // Trophies
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);
        writeInt(0);

        writeByte(0); // unk boolean
        writeByte(0); // unk boolean

        writeInt(0);

        writeInt(0); // array
        writeInt(0); // array 2, resource data slot
        writeInt(0); // array 3, unit slot data
        writeInt(0); // array 4, spell slot data
        writeInt(0); // array 5, unit upgrade slot
        writeInt(0); // array 6, spell upgrade slot
        writeInt(0); // array 7, hero upgrade slot
        writeInt(0); // array 8, hero health slot
        writeInt(0); // array 9, hero state slot
        writeInt(0); // array 10, alliance unit data
        writeInt(0); // array 11
        writeInt(0); // array 12
        writeInt(0); // array 13, achievement progress data
        writeInt(0); // array 14, npc map progress data
        writeInt(0); // array 15, npc looted gold data
        writeInt(0); // array 16, npc looted elixir data
    }
}
