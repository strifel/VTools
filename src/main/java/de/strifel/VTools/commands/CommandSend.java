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

public class CommandSend implements Command {
    private final ProxyServer server;

    public CommandSend(ProxyServer server) {
        this.server = server;
    }

    public void execute(CommandSource commandSource, @NonNull String[] strings) {
        if (strings.length == 2) {
            Optional<Player> oPlayer = server.getPlayer(strings[0]);
            Optional<RegisteredServer> oServer = server.getServer(strings[1]);
            if (oPlayer.isPresent() && oServer.isPresent()) {
                Player player = oPlayer.get();
                RegisteredServer server = oServer.get();
                player.createConnectionRequest(server).connect();
                commandSource.sendMessage(TextComponent.of("You send " + player.getUsername() + " to " + server.getServerInfo().getName()).color(TextColor.YELLOW));
                commandSource.sendMessage(TextComponent.of("You got send to " + server.getServerInfo().getName()).color(TextColor.YELLOW));
            } else {
                commandSource.sendMessage(TextComponent.of("The server or user does not exists!").color(TextColor.RED));
            }
        } else {
            commandSource.sendMessage(TextComponent.of("Usage: /send <username> <server>").color(TextColor.RED));
        }
    }

    public List<String> suggest(CommandSource source, @NonNull String[] currentArgs) {
        List<String> arg = new ArrayList<String>();
        if (currentArgs.length == 1) {
            for (Player player : server.getAllPlayers()) {
                arg.add(player.getUsername());
            }
            return arg;
        } else if (currentArgs.length == 2) {
            for (RegisteredServer server : server.getAllServers()) {
                arg.add(server.getServerInfo().getName());
            }
        }
        return arg;
    }

    public boolean hasPermission(CommandSource source, @NonNull String[] args) {
        return source.hasPermission("vtools.send");
    }
}
