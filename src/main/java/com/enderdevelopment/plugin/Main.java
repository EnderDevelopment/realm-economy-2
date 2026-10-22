package com.enderdevelopment.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Objective;

public class Main extends JavaPlugin {
    private static Main instance;
    private Scoreboard scoreboard;
    private Objective economyObjective;

    @Override
    public void onEnable() {
        instance = this;
        setupScoreboard();
        getCommand("balance").setExecutor(new BalanceCommand());
        getCommand("pay").setExecutor(new PayCommand());
        getCommand("shop").setExecutor(new ShopCommand());
        getCommand("jobs").setExecutor(new JobsCommand());
        getCommand("quests").setExecutor(new QuestsCommand());
        getCommand("leaderboard").setExecutor(new LeaderboardCommand());
        getCommand("transactions").setExecutor(new TransactionsCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setupScoreboard() {
        ScoreboardManager manager = getServer().getScoreboardManager();
        scoreboard = manager.getMainScoreboard();
        economyObjective = scoreboard.getObjective("economy");
        if (economyObjective == null) {
            economyObjective = scoreboard.registerNewObjective("economy", "dummy", "Economy");
        }
    }

    public static Main getInstance() {
        return instance;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public Objective getEconomyObjective() {
        return economyObjective;
    }
}