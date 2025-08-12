// import discord.js
import {
    ActionRowBuilder,
    ButtonBuilder,
    ButtonStyle,
    Client, EmbedBuilder,
    Events,
    GatewayIntentBits, MessageFlagsBitField,
} from 'discord.js';
import {createMagicLink} from "./api.ts";

const client = new Client({
    intents: [GatewayIntentBits.Guilds, GatewayIntentBits.GuildMessages]
});

client.once(Events.ClientReady, (bot) => {
    console.log(`Ready! Logged in as ${bot.user.tag}`);
});

client.on(Events.InteractionCreate, async (event) => {
    if (event.isMessageComponent() && event.customId === "login") {
        const username = event.user.displayName || event.user.username;
        const avatar = event.user.displayAvatarURL({extension: "png"});

        const link = await createMagicLink(event.user.id, username, avatar)

        console.log(link);

        const button = new ButtonBuilder()
            .setStyle(ButtonStyle.Link)
            .setLabel("Odkaz pro přihlášení")
            .setURL(link)

        const row = new ActionRowBuilder()
            .addComponents(button)
            .toJSON()

        const embed = new EmbedBuilder()
            .setTitle("Odkaz pro přihlášení")
            .setDescription("Po kliknutí na tlačítko pod zprávou tě aplikace automaticky přihlásí.")
            .setColor("#fcba03")

        await event.reply({
            flags: [MessageFlagsBitField.Flags.Ephemeral],
            embeds: [embed],
            components: [row]
        });
    }
});

await client.login(process.env.DISCORD_TOKEN);