package xyz.lotho.riselobby.manager.events;

import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import xyz.lotho.riselobby.RiseLobby;
import xyz.lotho.riselobby.manager.events.listener.BuildListener;
import xyz.lotho.riselobby.manager.events.listener.EnvironmentListener;
import xyz.lotho.riselobby.manager.events.listener.PlayerListener;

import java.util.ArrayList;
import java.util.List;

@Getter
public class LobbyServerEventsManager {

    private final RiseLobby riseLobby;

    public final List<Listener> listeners = new ArrayList<>();

    public LobbyServerEventsManager(RiseLobby riseLobby) {
        this.riseLobby = riseLobby;

        addListener(new PlayerListener(riseLobby));
        addListener(new EnvironmentListener(riseLobby));
        addListener(new BuildListener(riseLobby));

        loadListeners();
    }

    public void addListener(Listener listener) {
        getListeners().add(listener);
    }

    public void removeListener(Listener listener) {
        getListeners().remove(listener);
    }

    public void reloadListeners() {
        getListeners().forEach(HandlerList::unregisterAll);
        getListeners().forEach(listener -> getRiseLobby().getServer().getPluginManager().registerEvents(listener, getRiseLobby()));
    }

    public void loadListeners() {
        getListeners().forEach(listener -> getRiseLobby().getServer().getPluginManager().registerEvents(listener, getRiseLobby()));
    }

    public void unloadListeners() {
        getListeners().forEach(HandlerList::unregisterAll);
    }

}
