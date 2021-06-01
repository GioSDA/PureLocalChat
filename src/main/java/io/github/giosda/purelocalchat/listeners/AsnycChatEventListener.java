package io.github.giosda.purelocalchat.listeners;

import io.github.giosda.purelocalchat.PureLocalChat;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AsnycChatEventListener implements Listener {

    PureLocalChat simpleLocalChat = PureLocalChat.getPlugin();

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncChatEvent e) {
        Boolean local = simpleLocalChat.getLocalChat().get(e.getPlayer().getUniqueId());

        //Make sure player has local set
        if (local == null) {
            simpleLocalChat.getLocalChat().put(e.getPlayer().getUniqueId(), false);
            return;
        }

        //Make sure player has local as true
        if (!local) return;

        e.setCancelled(true);

        if (simpleLocalChat.getLocalChat().get(e.getPlayer().getUniqueId()).equals(true)) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getWorld().equals(e.getPlayer().getWorld())) { // checks if players are in same world
                    if (p.getLocation().distance(e.getPlayer().getLocation()) < simpleLocalChat.getLocalChatDistance()) {
                        p.sendMessage(e.message());
                    }
                }
            }
        }
    }

}
