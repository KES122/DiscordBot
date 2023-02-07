package discordbot.Commands;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import discordbot.Commands.Music.AudioManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {
    public static AudioPlayerManager audioPlayerManager;
    private static AudioManager audioManager;
    public static JDA jda;

    public static void main(String[] args) throws LoginException {

        JDABuilder builder = JDABuilder.createDefault(Token.token);
        builder.addEventListeners(new Events());

        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.build();
    }

    public static JDA getJda() {
        if (jda != null) {
            return jda;
        }
        return null;
    }

    public static AudioPlayerManager getAudioPlayerManager() {
        if (audioPlayerManager != null) {
            return audioPlayerManager;
        }
        return null;
    }

    public static AudioManager getAudioManager() {
        if (audioManager != null) {
            return audioManager;
        }
        return null;
    }

    public static String getIcon() {
        return "https://goo.su/vZAAi";
    }




    public static String getJda(String link) {
        return getJda().toString();
    }
}













