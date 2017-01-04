package me.jdog.configurationlib;

import me.jdog.configurationlib.lib.Config;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Muricans on 1/3/17.
 */
public class ConfigLib extends JavaPlugin {
    @Override
    public void onEnable() {
        loadExampleConfig();
    }

    private void loadExampleConfig() {
        Config config = new Config(this, "example_config.yml");
        config.create();
        config.options().copyDefaults(true);
        config.loadFromJar();
        config.save();
    }

}
