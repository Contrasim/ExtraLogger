package dev.Contrasim.messagelogger;

import dev.Contrasim.messagelogger.listeners.MessageListener;
import dev.Contrasim.messagelogger.commands.CommandManager;
import dev.Contrasim.messagelogger.configuration.ConfigManager;
import dev.Contrasim.messagelogger.utils.LoggingManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MessageLoggerPlugin extends JavaPlugin {

    private MessageListener messageListener;
    private LoggingManager logger;
    private CommandManager commandManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        this.messageListener = new MessageListener(this);
        this.commandManager = new CommandManager(this);
        this.configManager = new ConfigManager(this);
        this.logger = new LoggingManager(this);

        commandManager.registerCommands();
        messageListener.initializeMessageListener();

        saveDefaultConfig();

        logger.logMessage("MessageLogger has been successfully enabled!");
    }

    @Override
    public void onDisable() {
        logger.logMessage("MessageLogger has been successfully disabled!");
    }

    public LoggingManager getLoggingManager() {
        return logger;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

}
