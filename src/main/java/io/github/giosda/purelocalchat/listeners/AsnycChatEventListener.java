package io.github.giosda.purelocalchat.listeners;

import io.github.giosda.purelocalchat.PureLocalChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsnycChatEventListener implements Listener {

    PureLocalChat simpleLocalChat = PureLocalChat.getPlugin();

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
        Boolean local = simpleLocalChat.getLocalChat().get(e.getPlayer().getUniqueId());

        //Make sure player has local set
        if (local == null) {
            simpleLocalChat.getLocalChat().put(e.getPlayer().getUniqueId(), false);
            return;
        }

        //Make sure player has local as true
        if (local == false) return;

        for (Player p : e.getRecipients()) {
            if (p.getWorld().equals(e.getPlayer().getWorld())) { // checks if players are in same world
                if (p.getLocation().distanceSquared(e.getPlayer().getLocation()) < simpleLocalChat.getLocalChatDistance() * simpleLocalChat.getLocalChatDistance()) {
                    e.setFormat(ChatColor.GREEN + "[LOCAL] " + ChatColor.WHITE + "<" + e.getPlayer().getDisplayName() + "> " + e.getMessage()); //Prepends [LOCAL] to message
                    continue;
                }
            }

            e.getRecipients().remove(p);
        }


    }

}
