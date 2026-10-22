package com.enderdevelopment.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public class PayCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage("Usage: /pay <player> <amount>");
            return true;
        }

        Player player = (Player) sender;
        Player target = sender.getServer().getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage("Player not found.");
            return true;
        }

        int amount;
        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("Invalid amount.");
            return true;
        }

        if (amount <= 0) {
            sender.sendMessage("Amount must be positive.");
            return true;
        }

        Objective objective = Main.getInstance().getEconomyObjective();
        Score senderScore = objective.getScore(player.getName());
        Score targetScore = objective.getScore(target.getName());

        if (senderScore.getScore() < amount) {
            sender.sendMessage("You don't have enough money.");
            return true;
        }

        senderScore.setScore(senderScore.getScore() - amount);
        targetScore.setScore(targetScore.getScore() + amount);

        player.sendMessage("You paid " + amount + " coins to " + target.getName() + ".");
        target.sendMessage("You received " + amount + " coins from " + player.getName() + ".");

        return true;
    }
}