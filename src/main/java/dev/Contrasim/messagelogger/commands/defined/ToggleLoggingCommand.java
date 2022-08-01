package dev.Contrasim.messagelogger.commands.defined;

import dev.Contrasim.messagelogger.utils.Registrar;
import dev.Contrasim.messagelogger.MessageLoggerPlugin;
import dev.Contrasim.messagelogger.utils.Prefix;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

public class ToggleLoggingCommand extends BukkitCommand implements Registrar {

    private final MessageLoggerPlugin plugin;

    public ToggleLoggingCommand(MessageLoggerPlugin plugin) {
        super(plugin.getName());
        setName("toggleloggingmessages");
        setDescription("Toggles logging messages on/off");
        setPermission("messagelogger.togglelogging");
        setPermissionMessage(Prefix.ERROR + "You do not have permission to execute this command.");

        this.plugin = plugin;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            plugin.getLoggingManager().logMessage(Prefix.NONE + "Console may not use player commands.");
            return true;
        }

        Player player = (Player) sender;
        boolean isLogging = plugin.getLoggingManager().isLogging();

        plugin.getLoggingManager().setLogging(!isLogging);
        player.sendMessage(Prefix.SUCCESS + "Message logging has been set to " + ChatColor.LIGHT_PURPLE + !isLogging + ChatColor.GRAY + "!");

        return true;
    }

    @Override
    public void registerCommand() {
        plugin.getCommandManager().addCommand(plugin.getName(), new ToggleLoggingCommand(plugin));
    }
}
