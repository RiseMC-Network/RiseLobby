package xyz.lotho.riselobby.manager.events.listener;

import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.*;
import xyz.lotho.riselobby.RiseLobby;

@Getter
public class BuildListener implements Listener {

    private final RiseLobby riseLobby;

    public BuildListener(RiseLobby riseLobby) {
        this.riseLobby = riseLobby;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!getRiseLobby().getBypassManager().hasBypass(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!getRiseLobby().getBypassManager().hasBypass(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        if (!getRiseLobby().getBypassManager().hasBypass(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if (!getRiseLobby().getBypassManager().hasBypass(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onEnchantItem(EnchantItemEvent event) {
        if (!getRiseLobby().getBypassManager().hasBypass(event.getEnchanter().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onPrepareItemEnchant(PrepareItemEnchantEvent event) {
        if (!getRiseLobby().getBypassManager().hasBypass(event.getEnchanter().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
        if (!getRiseLobby().getBypassManager().hasBypass(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
        if (!getRiseLobby().getBypassManager().hasBypass(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        if (!getRiseLobby().getBypassManager().hasBypass(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (!getRiseLobby().getBypassManager().hasBypass(event.getPlayer().getUniqueId())) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerEggThrow(PlayerEggThrowEvent event) {
        if (!getRiseLobby().getBypassManager().hasBypass(event.getPlayer().getUniqueId())) event.setHatching(false);
    }

    @EventHandler
    public void onPlayerPickupItem(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!getRiseLobby().getBypassManager().hasBypass(player.getUniqueId())) event.setCancelled(true);
    }

}
