package dev.Contrasim.messagelogger.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.Contrasim.messagelogger.MessageLoggerPlugin;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MessageListener {

    private MessageLoggerPlugin plugin;

    public MessageListener(MessageLoggerPlugin plugin) {
        this.plugin = plugin;
    }

    public void initializeMessageListener() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(plugin, PacketType.Play.Server.CHAT) {
            private final JsonParser parser = new JsonParser();

            @Override
            public void onPacketSending(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                Player player = event.getPlayer();

                StructureModifier<WrappedChatComponent> components = packet.getChatComponents();

                try {
                    JsonObject json = (JsonObject) parser.parse(components.read(0).getJson());
                    MessageLoggerPlugin plugin = JavaPlugin.getPlugin(MessageLoggerPlugin.class);

                    if (plugin.getLoggingManager().isLogging()) {
                        switch (plugin.getConfigManager().logComponent().toLowerCase()) {
                            case "json":
                                plugin.getLoggingManager().logMessage("Player " + player.getName() + " received message: " + json);
                                return;
                            case "text":
                                StringBuilder textBuilder = new StringBuilder();
                                for (int i = 0; i < 256; i++) {
                                    try {
                                        String text = new JsonParser().parse(components.read(0).getJson())
                                                .getAsJsonObject()
                                                .get("extra")
                                                .getAsJsonArray()
                                                .get(i)
                                                .getAsJsonObject()
                                                .get("text")
                                                .getAsString();
                                        textBuilder.append(text);
                                    } catch (IndexOutOfBoundsException e) {
                                        break;
                                    }
                                }
                                textBuilder.append(new JsonParser().parse(components.read(0).getJson())
                                        .getAsJsonObject()
                                        .get("text")
                                        .getAsString());
                                plugin.getLoggingManager().logMessage("Player " + player.getName() + " received message: " + textBuilder);
                                return;
                            default:
                                plugin.getLoggingManager().logMessage("Player " + player.getName() + " received message: " + json);
                        }
                    }

                } catch (NullPointerException e) {
                    // null chat message
                }
            }
        });
    }

}
