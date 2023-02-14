package discordbot.Commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import discordbot.Commands.Music.PlayerManager;
import discordbot.Commands.Music.TrackScheduler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import java.net.URI;
import java.net.URISyntaxException;

public class Events extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            if (!event.getAuthor().isBot()) {

                String[] arg = event.getMessage().getContentRaw().split(" ");
                StringBuilder stringBuilder = new StringBuilder();
                MessageChannel textChannel = event.getChannel();
                Member member = event.getMember();
                AudioChannel audioChannel = member.getVoiceState().getChannel().asVoiceChannel();
                switch (arg[0]) {
                    case "/play":
                        String link = arg[1];
                        System.out.println(link);
                        final AudioManager audioManager = event.getGuild().getAudioManager();
                        audioManager.openAudioConnection(audioChannel);
                        if (!link.startsWith("https")) {
                            link = " ";
                            for (int i = 0; i < arg.length; i++) {
                                link += arg[i];
                            }
                            link = "ytsearch:" + link;
                            System.out.println(link);

                        }
                        textChannel.sendMessage("Your music was played now").queue();
                        PlayerManager.getInstance().loadAndPlay(textChannel, link, event.getGuild());
                        break;
                }
                if (arg[0].equals("/j")) {
                    AudioChannel channel = member.getVoiceState().getChannel().asVoiceChannel();
                    if (channel == null) {
                        // Don't forget to .queue()!
                        textChannel.sendMessage("You are not connected to a voice channel!").queue();
                        return;
                    }
                    AudioManager audioManager = event.getGuild().getAudioManager();
                    audioManager.openAudioConnection(channel);
                    textChannel.sendMessage("I have connected to " + channel.getName()).queue();
                } else if (arg[0].equals("/leave")) {
                    AudioChannel channel = event.getGuild().getSelfMember().getVoiceState().getChannel();
                    if (channel == null) {
                        // Get slightly fed up at the user.
                        textChannel.sendMessage("I am not connected to a voice channel!").queue();
                        return;
                    }
                    event.getGuild().getAudioManager().closeAudioConnection();
                    textChannel.sendMessage("I have disconnected from " + channel.getName()).queue();
                }
                if (arg[0].equals("/+-25")) {
                    AudioChannel channel = member.getVoiceState().getChannel();
                    if (channel == null) {
                        textChannel.sendMessage("You dumbass, i am not connected to a voice channel!").queue();
                        return;
                    }
                    AudioManager audioManager = event.getGuild().getAudioManager();
                    audioManager.openAudioConnection(channel);
                    textChannel.sendMessage("PENETRATION CONFIMED").queue();
                }
            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
    private boolean isUrl(String url)
    {
        try {
            new URI(url);
            return true;
        }
        catch (URISyntaxException e)
        {
            return false;
        }
    }
}
