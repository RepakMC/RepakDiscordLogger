package net.repakmc.jogar.repaklogger;

import lombok.Getter;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.repakmc.jogar.repaklogger.listener.PlayerCommandProcessListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

/*
* Created by mattnicee7
* github.com/mattnicee7
* for RepakMC.
* */
public final class RepakDiscordLogger extends JavaPlugin {

    @Getter private JDA discordAPI;

    @Getter private String channelId;
    @Getter private String guildId;

    @Override
    public void onEnable() {
        loadModules();
        initJDA(getConfig().getString("token"));
        registerEvents();
    }

    @Override
    public void onDisable() {

    }

    private void loadModules() {
        saveDefaultConfig();
        channelId = getConfig().getString("channelId");
        guildId = getConfig().getString("guildId");
    }

    private void registerEvents() {
        new PlayerCommandProcessListener(this);
    }

    @SneakyThrows
    private void initJDA(String token) {
        discordAPI = JDABuilder.createDefault(token)
                .disableCache(Arrays.asList(CacheFlag.values()))
                .setChunkingFilter(ChunkingFilter.ALL)
                .disableIntents(Arrays.asList(GatewayIntent.values()))
                .enableIntents(GatewayIntent.GUILD_MESSAGES)
                .build();
    }


}
