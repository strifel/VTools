package de.strifel.VTools.commands;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.text.TextComponent;
import net.kyori.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandSendall implements Command {
    private final ProxyServer server;

    public CommandSendall(ProxyServer server) {
        this.server = server;
    }

    @Override
    public void execute(CommandSource commandSource, @NonNull String[] strings) {
        if (strings.length == 1) {
            Optional<RegisteredServer> oServer = server.getServer(strings[0]);
            if (oServer.isPresent()) {
                for (Player player : server.getAllPlayers()) {
                    player.createConnectionRequest(oServer.get()).connect();
                    player.sendMessage(TextComponent.of("You are being sent to " + strings[0]).color(TextColor.YELLOW));
                }
            } else {
                commandSource.sendMessage(TextComponent.of("The server does not exists!").color(TextColor.RED));
            }
        } else {
            commandSource.sendMessage(TextComponent.of("Usage: /sendall <server>").color(TextColor.RED));
        }
    }

    @Override
    public List<String> suggest(CommandSource source, @NonNull String[] currentArgs) {
        List<String> arg = new ArrayList<String>();
        if (currentArgs.length == 1) {
            for (RegisteredServer server : server.getAllServers()) {
                arg.add(server.getServerInfo().getName());
            }
        }
        return arg;
    }

    @Override
    public boolean hasPermission(CommandSource source, @NonNull String[] args) {
        return source.hasPermission("vtools.sendall");
    }
}
