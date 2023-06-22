package xyz.lotho.riselobby.util;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;
import xyz.lotho.risecore.network.RiseCore;
import xyz.lotho.riselobby.RiseLobby;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;

@Getter
public class Configuration {

    private final RiseLobby riseLobby;

    private YamlConfiguration configurationFile;
    private File configuration;

    private final String configurationName;

    public Configuration(RiseLobby riseLobby, String configurationName) {
        this.riseLobby = riseLobby;
        this.configurationName = configurationName;

        load();
    }

    public boolean load() {
        if (!getRiseLobby().getDataFolder().exists()) {
            getRiseLobby().getDataFolder().mkdirs();
        }

        configuration = new File(getRiseLobby().getDataFolder(), getConfigurationName() + ".yml");

        if (!getConfiguration().exists()) {
            try (InputStream stream = getRiseLobby().getResource(getConfigurationName() + ".yml")) {
                if (stream != null) {
                    Files.copy(stream, getConfiguration().toPath());
                }
            } catch (IOException e) {
                getRiseLobby().getLogger().log(Level.WARNING, "Unable to copy or create " + getConfigurationName() + ".yml");
                e.printStackTrace();
                return false;
            }
        }

        configurationFile = YamlConfiguration.loadConfiguration(getConfiguration());
        return true;
    }

    public void save() {
        try {
            getConfigurationFile().save(getConfiguration());
        } catch (IOException e) {
            getRiseLobby().getLogger().log(Level.WARNING, "Unable to save " + getConfigurationName() + ".yml");
        }
    }

    public YamlConfiguration get() {
        return getConfigurationFile();
    }

}
