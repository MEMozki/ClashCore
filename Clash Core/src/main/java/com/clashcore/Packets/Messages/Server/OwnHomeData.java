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
        writeString("{\"buildings\":[{\"data\":1000001,\"lvl\":0,\"x\":21,\"y\":20},{\"data\":1000004,\"lvl\":0,\"x\":20,\"y\":16,\"res_time\":8992},{\"data\":1000000,\"lvl\":0,\"x\":26,\"y\":19,\"units\":[],\"storage_type\":0},{\"data\":1000015,\"lvl\":0,\"x\":18,\"y\":20},{\"data\":1000014,\"lvl\":0,\"locked\":true,\"x\":25,\"y\":32}],\"obstacles\":[{\"data\":8000007,\"x\":5,\"y\":13},{\"data\":8000007,\"x\":15,\"y\":29},{\"data\":8000008,\"x\":7,\"y\":7},{\"data\":8000005,\"x\":29,\"y\":4},{\"data\":8000006,\"x\":15,\"y\":37},{\"data\":8000000,\"x\":20,\"y\":4},{\"data\":8000008,\"x\":15,\"y\":22},{\"data\":8000005,\"x\":37,\"y\":18},{\"data\":8000007,\"x\":6,\"y\":4},{\"data\":8000003,\"x\":26,\"y\":10},{\"data\":8000004,\"x\":21,\"y\":9},{\"data\":8000008,\"x\":32,\"y\":21},{\"data\":8000005,\"x\":20,\"y\":36},{\"data\":8000003,\"x\":29,\"y\":34},{\"data\":8000005,\"x\":5,\"y\":29},{\"data\":8000005,\"x\":8,\"y\":10},{\"data\":8000005,\"x\":5,\"y\":17},{\"data\":8000002,\"x\":4,\"y\":33},{\"data\":8000002,\"x\":5,\"y\":21},{\"data\":8000002,\"x\":10,\"y\":32},{\"data\":8000008,\"x\":5,\"y\":37},{\"data\":8000001,\"x\":9,\"y\":4},{\"data\":8000001,\"x\":13,\"y\":31},{\"data\":8000001,\"x\":7,\"y\":35},{\"data\":8000007,\"x\":4,\"y\":9},{\"data\":8000004,\"x\":9,\"y\":23},{\"data\":8000004,\"x\":6,\"y\":26},{\"data\":8000003,\"x\":35,\"y\":21},{\"data\":8000005,\"x\":32,\"y\":28},{\"data\":8000005,\"x\":34,\"y\":13},{\"data\":8000001,\"x\":14,\"y\":18},{\"data\":8000001,\"x\":35,\"y\":5},{\"data\":8000012,\"x\":24,\"y\":30},{\"data\":8000012,\"x\":31,\"y\":10},{\"data\":8000010,\"x\":26,\"y\":38},{\"data\":8000010,\"x\":14,\"y\":5},{\"data\":8000013,\"x\":34,\"y\":33},{\"data\":8000013,\"x\":13,\"y\":9},{\"data\":8000014,\"x\":10,\"y\":17},{\"data\":8000014,\"x\":24,\"y\":7},{\"data\":8000006,\"x\":36,\"y\":26},{\"data\":8000011,\"x\":23,\"y\":34},{\"data\":8000011,\"x\":24,\"y\":37},{\"data\":8000000,\"x\":27,\"y\":35},{\"data\":8000000,\"x\":25,\"y\":35},{\"data\":8000000,\"x\":26,\"y\":30},{\"data\":8000007,\"x\":23,\"y\":32},{\"data\":8000001,\"x\":28,\"y\":31},{\"data\":8000014,\"x\":28,\"y\":29}],\"traps\":[],\"decos\":[],\"respawnVars\":{\"secondsFromLastRespawn\":7200,\"respawnSeed\":561881117,\"obstacleClearCounter\":16,\"time_to_gembox_drop\":5,\"time_in_gembox_period\":1},\"cooldowns\":[],\"newShopBuildings\":[3,0,6,2,6,2,3,1,3,3,125,2,1,2,1,5,0,0,0,0,1,0,0,0,0,0,0,0],\"newShopTraps\":[4,4,1,0,0,2,0,0],\"newShopDecos\":[1,4,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"last_league_rank\":0,\"last_league_shuffle\":0,\"last_news_seen\":58,\"edit_mode_shown\":false,\"war_tutorials_seen\":0,\"war_base\":false}"); // Home
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
