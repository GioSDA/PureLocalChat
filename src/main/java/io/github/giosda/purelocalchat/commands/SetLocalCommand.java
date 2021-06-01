package io.github.giosda.purelocalchat.commands;

import io.github.giosda.purelocalchat.PureLocalChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLocalCommand implements CommandExecutor {

    PureLocalChat simpleLocalChat = PureLocalChat.getPlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            Boolean local = simpleLocalChat.getLocalChat().get(p.getUniqueId());

            //Make sure player has local set
            if (local == null) {
                simpleLocalChat.getLocalChat().put(p.getUniqueId(), true);
                p.sendMessage("§2You have been moved to local chat.");
                return true;
            }

            simpleLocalChat.getLocalChat().put(p.getUniqueId(), !local);

            local = !local;

            if (local) p.sendMessage("§2You have been moved to local chat.");
            if (!local) p.sendMessage("§2You have been moved to global chat.");
            return true;
        }

        sender.sendMessage("You must be a player to use this command!");
        return false;
    }
}
