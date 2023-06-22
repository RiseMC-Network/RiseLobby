package xyz.lotho.riselobby;

import co.aikar.commands.BukkitCommandManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.lotho.risecore.network.RiseCore;
import xyz.lotho.riselobby.board.ScoreboardManager;
import xyz.lotho.riselobby.command.AdminBypassCommand;
import xyz.lotho.riselobby.manager.bypass.BypassManager;
import xyz.lotho.riselobby.manager.world.LobbyWorldManager;
import xyz.lotho.riselobby.manager.events.LobbyServerEventsManager;
import xyz.lotho.riselobby.util.Configuration;

@Getter
public final class RiseLobby extends JavaPlugin {

    private RiseCore riseCore;

    private BukkitCommandManager commandManager;
    private LobbyServerEventsManager lobbyServerEventsManager;

    private Configuration configurationFile;

    private ScoreboardManager scoreboardManager;
    private LobbyWorldManager lobbyWorldManager;
    private BypassManager bypassManager;

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("RiseCore") == null) {
            getLogger().severe("RiseCore not found! Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        riseCore = RiseCore.getInstance();

        commandManager = new BukkitCommandManager(this);
        commandManager.enableUnstableAPI("help");
        loadCommands();

        lobbyServerEventsManager = new LobbyServerEventsManager(this);

        configurationFile = new Configuration(this, "config");

        scoreboardManager = new ScoreboardManager(this);
        lobbyWorldManager = new LobbyWorldManager(this);
        bypassManager = new BypassManager(this);
    }

    @Override
    public void onDisable() {
        getLobbyServerEventsManager().unloadListeners();
        getScoreboardManager().cancel();
    }

    public void loadCommands() {
        getCommandManager().registerCommand(new AdminBypassCommand(this));
    }
}
