package io.github.giosda.purelocalchat;

import io.github.giosda.purelocalchat.commands.SetLocalCommand;
import io.github.giosda.purelocalchat.listeners.AsnycChatEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class PureLocalChat extends JavaPlugin {

    private HashMap<UUID, Boolean> localChat = new HashMap<>();
    private double localChatDistance;

    private static PureLocalChat plugin;

    @Override
    public void onEnable() {
        plugin = this;

        //Setup config
        this.getConfig().options().copyDefaults();
        this.saveDefaultConfig();

        localChatDistance = getConfig().getDouble("local-chat-distance");

        //Register events
        Bukkit.getPluginManager().registerEvents(new AsnycChatEventListener(), this);
        this.getCommand("local").setExecutor(new SetLocalCommand());
    }

    @Override
    public void onDisable() {

    }

    public HashMap<UUID, Boolean> getLocalChat() {
        return localChat;
    }

    public double getLocalChatDistance() {
        return localChatDistance;
    }

    public static PureLocalChat getPlugin() {
        return plugin;
    }
}
