package discordbot.Commands;
import discordbot.Commands.Music.AudioManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main{
    public static JDA jda;
    public static void main(String[] args) throws LoginException {

        JDABuilder builder = JDABuilder.createDefault("MTA3MjM5NTExMjI0NTY0NTM2Mg.GDt1Q-.K-1V-GcWESHkMWn0F1ex5klXGpJzyZbIqwIu2A");
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES,GatewayIntent.GUILD_PRESENCES);
        Events events = new Events();
        builder.addEventListeners(events);
        builder.build();
    }
    public static JDA getJda(){
        if (jda != null) {
            return jda;
        }
        return null;
    }
}









