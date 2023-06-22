package xyz.lotho.riselobby.board;

import fr.mrmicky.fastboard.FastBoard;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.lotho.risecore.network.RiseCore;
import xyz.lotho.risecore.network.manager.profile.Profile;
import xyz.lotho.risecore.network.util.CC;
import xyz.lotho.riselobby.RiseLobby;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Objects;

@Getter
public class ScoreboardUpdater extends BukkitRunnable {

    private final RiseLobby riseLobby;

    public ScoreboardUpdater(RiseLobby riseLobby) {
        this.riseLobby = riseLobby;

        runTaskTimerAsynchronously(getRiseLobby(), 0, 40L);
    }

    public void run() {
        for (Player player : getRiseLobby().getServer().getOnlinePlayers()) {
            FastBoard board = getRiseLobby().getScoreboardManager().getBoard(player);
            Profile profile = RiseCore.getInstance().getProfileManager().getProfile(player.getUniqueId());

            board.updateTitle(CC.translate("&6&lRise&e&lMC"));

            board.updateLines(
                    CC.translate("&7&m----------------"),
                    CC.translate("&7" + getDate()),
                    "",
                    CC.translate("&6&lYou"),
                    CC.translate(" &eRank: " + (Objects.equals(profile.getRank().getId(), "DEFAULT") ? "&7Default" : profile.getRank().getPrefix())),
                    CC.translate(" &eGold: &f" + profile.getGold()),
                    "",
                    CC.translate("&6&lServer"),
                    CC.translate(" &eOnline: &f" + RiseCore.getInstance().getServerManager().getNetworkPlayerCount()),
                    CC.translate(" &ePlaying: &f" + RiseCore.getInstance().getServerManager().getPlayingCount()),
                    "",
                    CC.translate("&7&m----------------"),
                    CC.translate("&eplay.risemc.xyz")
            );
        }
    }

    public String getDate() {
        LocalDate currentDate = LocalDate.now();

        String formattedDate = currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        formattedDate += " " + currentDate.getDayOfMonth();

        if (currentDate.getDayOfMonth() % 10 == 1 && currentDate.getDayOfMonth() != 11) formattedDate += "st";
        else if (currentDate.getDayOfMonth() % 10 == 2 && currentDate.getDayOfMonth() != 12) formattedDate += "nd";
        else if (currentDate.getDayOfMonth() % 10 == 3 && currentDate.getDayOfMonth() != 13) formattedDate += "rd";
        else formattedDate += "th";

        formattedDate += " " + currentDate.getYear();

        return formattedDate;
    }

}
