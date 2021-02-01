package dev.dankom.survival;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Survival {

    private long time;

    public Survival(int length) {
        this.time = TimeUnit.MINUTES.toSeconds(length);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            private long time = 900;

            @Override
            public void run() {
                if (time <= 0) {
                    cancel();
                }
                time -= 1;
                updateScoreboard();
            }
        }, 0, 1000);
        done();
    }

    public void done() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cTimes up!"));
        }
    }

    public void updateScoreboard() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.setScoreboard(getScoreboard());
        }
    }

    public Scoreboard getScoreboard() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Objective objective = scoreboard.getObjective("scoreboard");
        if (objective != null) {
            objective.unregister();
            objective = scoreboard.registerNewObjective("scoreboard", "dummy");
        }

        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&e----> &aSurvival &e<----"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        List<String> objectives = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date(TimeUnit.SECONDS.toMillis(time));

        //Items
        objectives.add("&aTime: &e" + dateFormat.format(date));
        objectives.add("&a------------------");
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            objectives.add("&a" + p.getName() + " - &c" + p.getHealth() + "♥");
        }
        objectives.add("&a------------------");
        //

        Collections.reverse(objectives);
        for (int i = 0; i < objectives.size(); i++) {
            Score var1 = objective.getScore(ChatColor.translateAlternateColorCodes('&', objectives.get(i)));
            var1.setScore(i);
        }
        return scoreboard;
    }
}
