package net.repakmc.jogar.repaklogger.listener;

import lombok.val;
import net.repakmc.jogar.repaklogger.RepakDiscordLogger;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final RepakDiscordLogger plugin;

    public PlayerQuitListener(RepakDiscordLogger plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        val msg = "O jogador `" + event.getPlayer().getName() + "` saiu do servidor.";
        val total = "Online: " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers();

        plugin.getDiscordAPI().getGuildById(plugin.getGuildId()).getTextChannelById(plugin.getEntryChannelId()).sendMessage(msg).queue();
        plugin.getDiscordAPI().getGuildById(plugin.getGuildId()).getTextChannelById(plugin.getEntryChannelId()).getManager().setTopic(total).queue();
    }


}
