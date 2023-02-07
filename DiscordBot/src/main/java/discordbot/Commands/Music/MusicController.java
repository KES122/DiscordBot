package discordbot.Commands.Music;
import discordbot.Commands.Main;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import net.dv8tion.jda.api.entities.Guild;
import java.nio.ByteBuffer;

public class MusicController {
    private final Guild guild;
    private final AudioPlayer audioPlayer;
    private ByteBuffer buffer;
    private MutableAudioFrame frame;

    public MusicController(Guild guildById) {

        this.guild = guildById;
        this.audioPlayer = Main.getAudioPlayerManager().createPlayer();
        this.guild.getAudioManager().setSendingHandler(new AudioPlayerSendHandler(audioPlayer, buffer, frame));


    }
    public Guild getGuild() {
        if (this.guild != null) {
            return this.guild;
        }
        return null;
    }

    public AudioPlayer getAudioPlayer() {
        if (this.audioPlayer != null) {
            return this.audioPlayer;
        }
        return null;
    }

}
