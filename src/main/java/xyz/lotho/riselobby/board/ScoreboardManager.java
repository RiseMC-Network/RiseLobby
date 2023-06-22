package xyz.lotho.riselobby.board;

import fr.mrmicky.fastboard.FastBoard;
import lombok.Getter;
import org.bukkit.entity.Player;
import xyz.lotho.risecore.network.util.Tasks;
import xyz.lotho.riselobby.RiseLobby;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class ScoreboardManager {

    private final RiseLobby riseLobby;
    private final ScoreboardUpdater scoreboardUpdater;

    private final Map<UUID, FastBoard> boards = new HashMap<>();

    public ScoreboardManager(RiseLobby riseLobby) {
        this.riseLobby = riseLobby;
        this.scoreboardUpdater = new ScoreboardUpdater(getRiseLobby());
    }

    public FastBoard getBoard(Player player) {
        if (!getBoards().containsKey(player.getUniqueId())) {
            getBoards().put(player.getUniqueId(), new FastBoard(player));
        }
        return getBoards().get(player.getUniqueId());
    }

    public void removeBoard(Player player) {
        getBoards().remove(player.getUniqueId());
    }

    public void cancel() {
        getScoreboardUpdater().cancel();
    }

}
