package de.strifel.VTools.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.strifel.VTools.VTools.COLOR_RED;
import static de.strifel.VTools.VTools.COLOR_YELLOW;

public class CommandSendall implements SimpleCommand {
    private final ProxyServer server;

    public CommandSendall(ProxyServer server) {
        this.server = server;
    }

    @Override
    public void execute(SimpleCommand.Invocation invocation) {
        CommandSource commandSource = invocation.source();
        String[] strings = invocation.arguments();

        if (strings.length == 1) {
            Optional<RegisteredServer> oServer = server.getServer(strings[0]);
            if (oServer.isPresent()) {
                for (Player player : server.getAllPlayers()) {
                    player.createConnectionRequest(oServer.get()).connect();
                    player.sendMessage(Component.text("You are being sent to " + strings[0]).color(COLOR_YELLOW));
                }
            } else {
                commandSource.sendMessage(Component.text("The server does not exists!").color(COLOR_RED));
            }
        } else {
            commandSource.sendMessage(Component.text("Usage: /sendall <server>").color(COLOR_RED));
        }
    }

    @Override
    public List<String> suggest(SimpleCommand.Invocation invocation) {
        String[] currentArgs = invocation.arguments();

        List<String> arg = new ArrayList<String>();
        if (currentArgs.length == 1) {
            for (RegisteredServer server : server.getAllServers()) {
                arg.add(server.getServerInfo().getName());
            }
        }
        return arg;
    }

    @Override
    public boolean hasPermission(SimpleCommand.Invocation invocation) {
        return invocation.source().hasPermission("vtools.sendall");
    }
}
