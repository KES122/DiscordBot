package discordbot.Commands.Music;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;


public class TrackScheduler extends AudioEventAdapter
{
    public final AudioPlayer audioPlayer;
    public MessageChannel channel;
    public final BlockingQueue<AudioTrack> queue;
    public boolean repeating = false;
    public TrackScheduler(AudioPlayer audioPlayer)
    {

        this.audioPlayer = audioPlayer;
        this.queue = new LinkedBlockingDeque<>();
    }


    public void queue(AudioTrack track)
    {
        if(!this.audioPlayer.startTrack(track, true))
        {
            this.queue.offer(track);
        }
    }

    public void nextTrack()
    {
        this.audioPlayer.startTrack(this.queue.poll(), false);
        AudioTrack track = audioPlayer.getPlayingTrack();
        String trackUrl = track.getInfo().uri;

    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason)
    {
            if (endReason.mayStartNext)
            {
                nextTrack();
            }

    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {


    }
}