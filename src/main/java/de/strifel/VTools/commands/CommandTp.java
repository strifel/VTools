package de.strifel.VTools.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.strifel.VTools.VTools.COLOR_RED;
import static de.strifel.VTools.VTools.COLOR_YELLOW;

public class CommandTp implements SimpleCommand {

    private final ProxyServer server;

    public CommandTp(ProxyServer server) {
        this.server = server;
    }

    @Override
    public void execute(Invocation commandInvocation) {
        CommandSource commandSource = commandInvocation.source();
        String[] strings = commandInvocation.arguments();

        if (commandSource instanceof Player) {
            if (strings.length == 1) {
                Optional<Player> player = server.getPlayer(strings[0]);
                if (player.isPresent()) {
                    player.get().getCurrentServer().ifPresent(serverConnection -> ((Player) commandSource).createConnectionRequest(serverConnection.getServer()).fireAndForget());
                    commandSource.sendMessage(Component.text("Connecting to the server of " + strings[0]).color(COLOR_YELLOW));
                } else {
                    commandSource.sendMessage(Component.text("Player does not exists.").color(COLOR_RED));
                }
            } else {
                commandSource.sendMessage(Component.text("Usage: /tps <username>").color(COLOR_RED));
            }
        } else {
            commandSource.sendMessage(Component.text("Command is only for players.").color(COLOR_RED));
        }
    }

    @Override
    public List<String> suggest(Invocation commandInvocation) {
        List<String> arg = new ArrayList<>();
        if (commandInvocation.arguments().length == 1) {
            for (Player player : server.getAllPlayers()) {
                arg.add(player.getUsername());
            }
        }
        return arg;
    }

    @Override
    public boolean hasPermission(Invocation commandInvocation) {
        return commandInvocation.source().hasPermission("VTools.tps");
    }
}
