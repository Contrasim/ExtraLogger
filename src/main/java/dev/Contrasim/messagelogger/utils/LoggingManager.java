package dev.Contrasim.messagelogger.utils;

import dev.Contrasim.messagelogger.MessageLoggerPlugin;

import java.util.logging.Level;

public class LoggingManager {

    private final MessageLoggerPlugin plugin;
    private boolean isLogging;

    public LoggingManager(MessageLoggerPlugin plugin) {
        this.plugin = plugin;
        this.isLogging = plugin.getConfigManager().isLogging();
    }

    public void setLogging(boolean state) {
        this.isLogging = state;
        plugin.getConfigManager().setLogging(state);
    }

    public boolean isLogging() {
        return isLogging;
    }

    public void logMessage(String message) {
        plugin.getLogger().log(Level.INFO, message);
    }

}
