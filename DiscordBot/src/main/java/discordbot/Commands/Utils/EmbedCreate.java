package discordbot.Commands.Utils;
import discordbot.Commands.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import java.awt.*;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
public abstract class EmbedCreate {
    public MessageChannel textChannel;

    private static EmbedBuilder AccessEmbed() {
        return new EmbedBuilder();
    }

    public static void createEmbed(String SetTitle, MessageChannel textChannel) {
        EmbedBuilder em = AccessEmbed();
        em.setTitle(SetTitle);
        em.setColor(Color.orange);
        em.setFooter("GayBot", Main.getIcon());
        em.setTimestamp(Instant.now());
        textChannel.sendMessageEmbeds(em.build()).queue(message -> message.delete().queueAfter(20, TimeUnit.SECONDS));
    }

    public static void createEmbedClear(String SetTitle, MessageChannel textChannel) {
        EmbedBuilder em = AccessEmbed();
        em.setTitle(SetTitle);
        em.setColor(Color.orange);
        em.setFooter("GayBot", Main.getIcon());
        em.setTimestamp(Instant.now());
        textChannel.sendMessageEmbeds(em.build()).queue(message -> message.delete().queueAfter(15, TimeUnit.SECONDS));
    }

    public static void createEmbedTrackScheduler(String setTitle, String Description, String Footer, String Icon,
                                                 MessageChannel textChannel, String Image, Color color, Long time) {
        EmbedBuilder embedBuilder = AccessEmbed();
        embedBuilder.setTitle(setTitle);
        embedBuilder.setDescription(Description);
        embedBuilder.setFooter(Footer, Icon);
        embedBuilder.setImage(Image);
        embedBuilder.setColor(color);
        embedBuilder.setTimestamp(Instant.now());
        textChannel.sendMessageEmbeds(embedBuilder.build()).queue(message -> message.delete().queueAfter(time, TimeUnit.MILLISECONDS));
    }

    public static void createEmbedTrackLoaded(String setTitle, String Thunmbail, String addField, String addField1, MessageChannel textChannel) {
        EmbedBuilder embedBuilder = AccessEmbed();
        embedBuilder.setTitle(setTitle);
        embedBuilder.setThumbnail(Thunmbail);
        embedBuilder.addField("Добавил: ", addField, true);
        embedBuilder.addField("Длительность:", addField1, true);
        embedBuilder.setFooter("GayBot", Main.getIcon());
        embedBuilder.setColor(Color.orange);
        embedBuilder.setTimestamp(Instant.now());
        textChannel.sendMessageEmbeds(embedBuilder.build()).queue(message -> message.delete().queueAfter(20, TimeUnit.SECONDS));
    }
    public static void createEmbedException(String title, MessageChannel textChannel) {
        EmbedBuilder embedBuilder = AccessEmbed();
        embedBuilder.setTitle(title);
        embedBuilder.setFooter("GayBot", Main.getIcon());
        embedBuilder.setColor(Color.orange);
        embedBuilder.setTimestamp(Instant.now());
        textChannel.sendMessageEmbeds(embedBuilder.build()).queue(message -> message.delete().queueAfter(15, TimeUnit.SECONDS));
    }
}
