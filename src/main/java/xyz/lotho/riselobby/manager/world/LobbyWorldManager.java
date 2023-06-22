package xyz.lotho.riselobby.manager.world;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.World;
import xyz.lotho.riselobby.RiseLobby;

@Getter
@Setter
public class LobbyWorldManager {

    private final RiseLobby riseLobby;

    private final String serverId;
    private final String worldName;
    private final World lobbyWorld;

    private Location spawn;

    public LobbyWorldManager(RiseLobby riseLobby) {
        this.riseLobby = riseLobby;

        this.serverId = getRiseLobby().getRiseCore().getServerId();
        this.worldName = getRiseLobby().getConfigurationFile().get().getString("server.worldName");
        this.lobbyWorld = getRiseLobby().getServer().getWorld(worldName);

        setSpawnLocation();
    }

    public void setSpawnLocation() {
        String[] coordinates = getRiseLobby().getConfigurationFile().get().getString("server.worldSpawn").split(", ");
        Location location = new Location(getLobbyWorld(), Float.parseFloat(coordinates[0]), Float.parseFloat(coordinates[1]), Float.parseFloat(coordinates[2]), Float.parseFloat(coordinates[3]), Float.parseFloat(coordinates[4]));

        setSpawn(location);
    }

}
