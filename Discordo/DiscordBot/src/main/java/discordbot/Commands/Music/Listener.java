package discordbot.Commands.Music;


import discordbot.Commands.Events;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Listener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
    private final Events manager = new Events();

    @Override
    public void onReady( ReadyEvent event) {
        LOGGER.info("{} is ready", event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onGuildMemberJoin( GuildMemberJoinEvent event) {
        final List<TextChannel> dontDoThis = event.getGuild().getTextChannelsByName("bot-spam", true);

        if (dontDoThis.isEmpty()) {
            return;
        }

        final TextChannel pleaseDontDoThisAtAll = dontDoThis.get(0);

        final String useGuildSpecificSettingsInstead = String.format("Welcome %s to %s",
                event.getMember().getUser().getAsTag(), event.getGuild().getName());

        pleaseDontDoThisAtAll.sendMessage(useGuildSpecificSettingsInstead).queue();
    }
}
