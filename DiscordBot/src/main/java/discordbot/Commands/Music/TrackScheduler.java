package discordbot.Commands.Music;
import discordbot.Commands.Main;
import discordbot.Commands.Utils.Utils;
import discordbot.Commands.Utils.EmbedCreate;
import com.sedmelluq.discord.lavaplayer.filter.equalizer.EqualizerFactory;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;


public class TrackScheduler extends AudioEventAdapter {
    private final AudioPlayer player;
    private final BlockingDeque<AudioTrack> queue;
    private TextChannel textChannel;

    private final EqualizerFactory equalizerFactory;
    public void skip() {
        startNextTrack(false);
    }
    public TrackScheduler(AudioPlayer player, Guild guild) {
        this.player = player;
        this.queue = new LinkedBlockingDeque<>();
        this.textChannel = guild.getDefaultChannel().asTextChannel();
        this.equalizerFactory = new EqualizerFactory();
        this.player.setFilterFactory(equalizerFactory);
        this.player.setFrameBufferDuration(500);
    }

    @Override
    public void onPlayerPause(AudioPlayer player) {
        super.onPlayerPause(player);
        player.isPaused();

    }

    @Override
    public void onPlayerResume(AudioPlayer player) {
        super.onPlayerResume(player);

    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        super.onTrackStart(player, track);
        String time = Utils.formatLongDuration(track.getInfo().length);
        long millisecond = track.getInfo().length;
        EmbedCreate.createEmbedTrackScheduler("На связи: " + track.getInfo().title, time,
                "SusBot", Main.getIcon(), textChannel, Main.getJda(getLink(track)), Color.orange, millisecond);
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        super.onTrackEnd(player, track, endReason);
        if (endReason.mayStartNext) {
            startNextTrack(true);
        }
    }

    @Override
    public void onTrackException(AudioPlayer player, AudioTrack track, FriendlyException exception) {
        EmbedCreate.createEmbedException("Не удалось запустить трек: " + track.getInfo().title, textChannel);
        skip();
    }

    @Override
    public void onTrackStuck(AudioPlayer player, AudioTrack track, long thresholdMs) {
        super.onTrackStuck(player, track, thresholdMs);
        startNextTrack(false);
    }


    public void addToQueue(AudioTrack audioTrack) {
        queue.addLast(audioTrack);
        startNextTrack(true);
    }

    public void startNextTrack(boolean noInterrupt) {
        AudioTrack next = queue.pollFirst();
        if (next != null) {
            if (!player.startTrack(next, noInterrupt)) {
                queue.addFirst(next);
            }
        } else {
            player.stopTrack();
        }
    }
    public static String getLink(AudioTrack track) {
        String link = track.getInfo().uri;
        return link.split("v=")[1];
    }
    public void setTextChannel(TextChannel textChannel){
        this.textChannel = textChannel;
    }

}
