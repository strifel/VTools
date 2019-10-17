package de.strifel.VTools.commands;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.text.TextComponent;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CommandRestart implements Command {
    private final ProxyServer server;

    public CommandRestart(ProxyServer server) {
        this.server = server;
    }


    @Override
    public void execute(CommandSource commandSource, @NonNull String[] strings) {
        if (strings.length > 0) {
            String message = String.join(" ", strings).replace("&", "§");
            for (Player player : server.getAllPlayers()) {
                player.disconnect(TextComponent.of(message));
            }
        }
        server.getCommandManager().execute(server.getConsoleCommandSource(), "shutdown");
    }

    @Override
    public List<String> suggest(CommandSource source, @NonNull String[] currentArgs) {
        return new ArrayList<String>();
    }

    @Override
    public boolean hasPermission(CommandSource source, @NonNull String[] args) {
        return source.hasPermission("vtools.shutdown");
    }
}
