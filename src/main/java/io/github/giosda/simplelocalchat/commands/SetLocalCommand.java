package io.github.giosda.simplelocalchat.commands;

import io.github.giosda.simplelocalchat.SimpleLocalChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetLocalCommand implements CommandExecutor {

    SimpleLocalChat simpleLocalChat = SimpleLocalChat.getPlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            boolean local = !simpleLocalChat.getLocalChat().get(p.getUniqueId());
            simpleLocalChat.getLocalChat().put(p.getUniqueId(), local);

            if (local) p.sendMessage("You have been moved to local chat.");
            if (!local) p.sendMessage("You have been moved to global chat.");
        }

        sender.sendMessage("You must be a player to use this command!");
        return false;
    }
}
