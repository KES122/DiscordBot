package discordbot.Commands;
import discordbot.Commands.Main;
import discordbot.Commands.Utils.EmbedCreate;
import discordbot.Commands.Utils.Utils;

import discordbot.Commands.Music.MusicController;
import discordbot.Commands.Music.TrackScheduler;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import static discordbot.Commands.Main.audioPlayerManager;

public class Events extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getAuthor().isBot()) {
            String message = event.getMessage().getContentRaw().toLowerCase();
            MessageChannel textchannel = event.getChannel();
            Member member = event.getMember();

            if (message.equals("/j")) {
                AudioChannel channel = member.getVoiceState().getChannel();
                if (channel == null) {
                    // Don't forget to .queue()!
                    textchannel.sendMessage("You are not connected to a voice channel!").queue();
                    return;
                }
                AudioManager audioManager = event.getGuild().getAudioManager();
                audioManager.openAudioConnection(channel);
                audioManager.setSelfDeafened(true);
                textchannel.sendMessage("I have connected to " + channel.getName()).queue();
            } else if (message.equals("/leave")) {
                AudioChannel channel = event.getGuild().getSelfMember().getVoiceState().getChannel();
                if (channel == null) {
                    // Get slightly fed up at the user.
                    textchannel.sendMessage("I am not connected to a voice channel!").queue();
                    return;
                }
                event.getGuild().getAudioManager().closeAudioConnection();
                textchannel.sendMessage("I have disconnected from " + channel.getName()).queue();
            }
            if (message.equals("/+-25")) {
                AudioChannel channel = member.getVoiceState().getChannel();
                if (channel == null) {
                    textchannel.sendMessage("You dumbass, i am not connected to a voice channel!").queue();
                    return;
                }
                AudioManager audioManager = event.getGuild().getAudioManager();
                audioManager.openAudioConnection(channel);
                audioManager.setSelfDeafened(true);
                textchannel.sendMessage("PENETRATION CONFIMED").queue();
            }
        }
    }
}








