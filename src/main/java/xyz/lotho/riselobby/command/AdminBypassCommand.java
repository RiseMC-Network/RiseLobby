package xyz.lotho.riselobby.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import lombok.Getter;
import org.bukkit.entity.Player;
import xyz.lotho.risecore.network.util.CC;
import xyz.lotho.riselobby.RiseLobby;

@Getter
@CommandAlias("adminbypass|ab")
@CommandPermission("risecore.admin")
public class AdminBypassCommand extends BaseCommand {

    private final RiseLobby riseLobby;

    public AdminBypassCommand(RiseLobby riseLobby) {
        this.riseLobby = riseLobby;
    }

    @Default
    @Description("Toggle admin bypass mode.")
    public void toggleBypass(Player player) {
        if (getRiseLobby().getBypassManager().hasBypass(player.getUniqueId())) {
            getRiseLobby().getBypassManager().removeBypass(player.getUniqueId());
        } else {
            getRiseLobby().getBypassManager().addBypass(player.getUniqueId());
        }

        boolean bypass = getRiseLobby().getBypassManager().hasBypass(player.getUniqueId());
        player.sendMessage(CC.translate("&7You have " + (bypass ? "&aenabled" : "&cdisabled") + " &7admin bypass mode."));
    }

}
