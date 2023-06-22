package xyz.lotho.riselobby.manager.bypass;

import lombok.Getter;
import xyz.lotho.riselobby.RiseLobby;

import java.util.*;

@Getter
public class BypassManager {

    private final RiseLobby riseLobby;

    private final Set<UUID> players = new HashSet<>();

    public BypassManager(RiseLobby riseLobby) {
        this.riseLobby = riseLobby;
    }

    public boolean hasBypass(UUID uuid) {
        return this.getPlayers().contains(uuid);
    }

    public void addBypass(UUID uuid) {
        getPlayers().add(uuid);
    }

    public void removeBypass(UUID uuid) {
        getPlayers().remove(uuid);
    }

}
