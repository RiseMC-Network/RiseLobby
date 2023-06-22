package xyz.lotho.riselobby.manager.events.listener;

import lombok.Getter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import xyz.lotho.riselobby.RiseLobby;

import java.util.Arrays;
import java.util.List;

@Getter
public class EnvironmentListener implements Listener {

    private final RiseLobby riseLobby;

    private final List<CreatureSpawnEvent.SpawnReason> disallowedSpawnReasons;

    public EnvironmentListener(RiseLobby riseLobby) {
        this.riseLobby = riseLobby;
        this.disallowedSpawnReasons = Arrays.asList(
                CreatureSpawnEvent.SpawnReason.NATURAL,
                CreatureSpawnEvent.SpawnReason.JOCKEY,
                CreatureSpawnEvent.SpawnReason.LIGHTNING,
                CreatureSpawnEvent.SpawnReason.VILLAGE_DEFENSE,
                CreatureSpawnEvent.SpawnReason.VILLAGE_INVASION,
                CreatureSpawnEvent.SpawnReason.REINFORCEMENTS,
                CreatureSpawnEvent.SpawnReason.NETHER_PORTAL,
                CreatureSpawnEvent.SpawnReason.OCELOT_BABY,
                CreatureSpawnEvent.SpawnReason.SILVERFISH_BLOCK,
                CreatureSpawnEvent.SpawnReason.MOUNT,
                CreatureSpawnEvent.SpawnReason.DEFAULT
        );
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (this.disallowedSpawnReasons.contains(event.getSpawnReason())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBurn(BlockBurnEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockExp(BlockExpEvent event) {
        event.setExpToDrop(0);
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockForm(BlockFormEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockGrow(BlockGrowEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        if (event.getPlayer() == null) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockSpread(BlockSpreadEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityBlockForm(EntityBlockFormEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onEntityPortal(EntityPortalEvent event) {
        event.setCancelled(true);
    }
}
