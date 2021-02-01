package dev.dankom.survival.command;

import dev.dankom.survival.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("survival")) {
            if (args[0].equalsIgnoreCase("start")) {
                Main.start(Integer.getInteger(args[1]));
            }
        }
        return false;
    }
}
