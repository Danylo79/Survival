package dev.dankom.survival;

import dev.dankom.survival.command.Commands;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginCommand("survival").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {

    }

    public static void start(int length) {
        new Survival(length);
    }

    public static Main getInstance() {
        return getPlugin(Main.class);
    }
}
