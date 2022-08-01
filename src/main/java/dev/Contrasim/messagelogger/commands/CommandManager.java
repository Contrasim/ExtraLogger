package dev.Contrasim.messagelogger.commands;

import dev.Contrasim.messagelogger.commands.defined.SetLoggingMessagesCommand;
import dev.Contrasim.messagelogger.commands.defined.ToggleLoggingCommand;
import dev.Contrasim.messagelogger.MessageLoggerPlugin;
import dev.Contrasim.messagelogger.utils.Prefix;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

public class CommandManager {

    private final MessageLoggerPlugin plugin;

    public CommandManager(MessageLoggerPlugin plugin) {
        this.plugin = plugin;
    }

    public void addCommand(String command, Command commandClass) {
        if (!(plugin.getServer().getPluginManager() instanceof SimplePluginManager)) {
            plugin.getLoggingManager().logMessage(Prefix.NONE + "Your server is running an unrecognized PluginManager.");
            return;
        }

        CommandMap commandMap = null;

        try {
            Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            commandMap = (CommandMap) commandMapField.get(plugin.getServer().getPluginManager());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (commandMap == null) {
            plugin.getLoggingManager().logMessage(Prefix.NONE + "Your server is running an unrecognized CommandMap.");
            return;
        }

        commandMap.register(command, commandClass);

    }

    public void registerCommands() {
        new SetLoggingMessagesCommand(plugin).registerCommand();
        new ToggleLoggingCommand(plugin).registerCommand();
    }

}
