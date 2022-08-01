package dev.Contrasim.messagelogger.configuration;

import dev.Contrasim.messagelogger.MessageLoggerPlugin;

public class ConfigManager {

    private final MessageLoggerPlugin plugin;

    public ConfigManager(MessageLoggerPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean isLogging() {
        return plugin.getConfig().getBoolean("isLoggingMessages");
    }

    public void setLogging(boolean value) {
        plugin.getConfig().set("isLoggingMessages", value);
        plugin.saveConfig();
    }

    public String logComponent() {
        return plugin.getConfig().getString("logComponent");
    }

}
