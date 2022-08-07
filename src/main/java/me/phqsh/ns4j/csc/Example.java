package me.phqsh.ns4j.csc;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import lombok.Getter;
import me.phqsh.ns4j.NationStatesAPI;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;

public class Example {
    @Getter
    private static JDA jda;
    private static NationStatesAPI api;

    public static void main(String[] args) throws LoginException, InterruptedException {
        api = new NationStatesAPI();

        CommandClientBuilder cmdbuilder = new CommandClientBuilder();
        CommandClient e = cmdbuilder
                .addSlashCommands(new GolfScoreCommand(api))
                .setActivity(Activity.watching("confederation sports"))
                .setOwnerId("670630054417399808")
                .forceGuildOnly("893547584562544680")
                .build();

        jda = JDABuilder
                .createDefault("OTY1NzA3MzU3OTI3NjYxNjQ4.GP6slV.qnYmbu_vNaiuQyAiSb1lGwJb32FGbngJzmJ0I8")
                .addEventListeners(e)
                .build();

        jda.awaitReady();
        System.out.println("bot online");

    }
}
