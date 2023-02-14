package discordbot.Commands.Music;
import com.sedmelluq.discord.lavaplayer.player.*;
import com.sedmelluq.discord.lavaplayer.source.*;
import com.sedmelluq.discord.lavaplayer.tools.*;
import com.sedmelluq.discord.lavaplayer.track.*;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import java.net.URI;
import java.util.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PlayerManager {
    private static PlayerManager INSTANCE;
    private final Map<Long, GuidMusicManager> musicManager;
    private final AudioPlayerManager audioPlayerManager;
    public PlayerManager(){
        this.musicManager = new HashMap<>();
        this.audioPlayerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(this.audioPlayerManager);
        AudioSourceManagers.registerLocalSource(this.audioPlayerManager);
    }
    public GuidMusicManager getMusicManager(Guild guild){
        return this.musicManager.computeIfAbsent(guild.getIdLong(), (guildID) ->{
            final GuidMusicManager guidMusicManager = new GuidMusicManager(this.audioPlayerManager);
            guild.getAudioManager().setSendingHandler(guidMusicManager.getSendHandler());
            return guidMusicManager;
        });
    }
    public void loadAndPlay(MessageChannel channel, String trackUrl, Guild guild){
        final GuidMusicManager musicManager = this.getMusicManager(guild);
        this.audioPlayerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                channel.sendMessage("Adding to queue: "+ track.getInfo().title+" by "
                                +track.getInfo().author).queue();
                musicManager.scheduler.queue(track);

            }
            @Override
            public void playlistLoaded(AudioPlaylist audioPlaylist) {


            }

            @Override
            public void noMatches() {
                channel.sendMessage("What the hell, where did you find this?").queue();

            }

            @Override
            public void loadFailed(FriendlyException e) {
                channel.sendMessage(String.format("Ой-ой, не работает")).queue();

            }
        });
    }
    public static PlayerManager getInstance()
    {
        if(INSTANCE ==null){
            INSTANCE = new PlayerManager();
        }
         return INSTANCE;
    }
}
