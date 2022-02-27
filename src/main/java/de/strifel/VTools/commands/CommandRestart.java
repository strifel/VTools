package de.strifel.VTools.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

public class CommandRestart implements SimpleCommand {
    private final ProxyServer server;

    public CommandRestart(ProxyServer server) {
        this.server = server;
    }


    @Override
    public void execute(SimpleCommand.Invocation invocation) {
        CommandSource commandSource = invocation.source();
        String[] strings = invocation.arguments();

        if (strings.length > 0) {
            String message = String.join(" ", strings).replace("&", "§");
            for (Player player : server.getAllPlayers()) {
                player.disconnect(Component.text(message));
            }
        }
        server.getCommandManager().executeAsync(server.getConsoleCommandSource(), "shutdown");
    }

    @Override
    public List<String> suggest(SimpleCommand.Invocation invocation) {
        return new ArrayList<String>();
    }

    @Override
    public boolean hasPermission(SimpleCommand.Invocation invocation) {
        return invocation.source().hasPermission("vtools.shutdown");
    }
}
