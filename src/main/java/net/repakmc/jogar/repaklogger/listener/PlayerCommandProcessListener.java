package net.repakmc.jogar.repaklogger.listener;

import lombok.val;
import net.repakmc.jogar.repaklogger.RepakDiscordLogger;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandProcessListener implements Listener {

    private final RepakDiscordLogger plugin;

    public PlayerCommandProcessListener(RepakDiscordLogger plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        val msg = "`" + event.getPlayer().getName() + "` executou o comando: " + "`" + event.getMessage() + "`.";

        plugin.getDiscordAPI().getGuildById(plugin.getGuildId()).getTextChannelById(plugin.getChannelId()).sendMessage(msg).queue();
    }

}
