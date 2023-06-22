package xyz.lotho.riselobby.manager.events.listener;

import lombok.Getter;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import xyz.lotho.risecore.network.util.CC;
import xyz.lotho.riselobby.RiseLobby;

@Getter
public class PlayerListener implements Listener {

    private final RiseLobby riseLobby;

    public PlayerListener(RiseLobby riseLobby) {
        this.riseLobby = riseLobby;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.getInventory().clear();
        player.setGameMode(GameMode.ADVENTURE);
        player.teleport(this.riseLobby.getLobbyWorldManager().getSpawn());
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 1.0F);

        StringBuilder lines = new StringBuilder();

        for (String line : getRiseLobby().getConfigurationFile().get().getStringList("messages.join")) {
            if (line.isEmpty()) lines.append("\n ");
            else lines.append(CC.translate(line).replace("%server%", getRiseLobby().getRiseCore().getServerId())).append("\n");
        }

        player.sendMessage(lines.toString());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        getRiseLobby().getScoreboardManager().removeBoard(player);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerEditBook(PlayerEditBookEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerExpChange(PlayerExpChangeEvent event) {
        event.setAmount(0);
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
            if (event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                event.getEntity().teleport(this.getRiseLobby().getLobbyWorldManager().getSpawn());
                event.getEntity().sendMessage(CC.RED + "You can't do that!");
            }
        }
    }

}
