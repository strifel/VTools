package de.strifel.VTools.commands;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.text.TextComponent;
import net.kyori.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandTp implements Command {

    private final ProxyServer server;

    public CommandTp(ProxyServer server) {
        this.server = server;
    }

    @Override
    public void execute(CommandSource commandSource, @NonNull String[] strings) {
        if (commandSource instanceof Player) {
            if (strings.length == 1) {
                Optional<Player> player = server.getPlayer(strings[0]);
                if (player.isPresent()) {
                    player.get().getCurrentServer().ifPresent(serverConnection -> ((Player) commandSource).createConnectionRequest(serverConnection.getServer()).fireAndForget());
                    commandSource.sendMessage(TextComponent.of("Connecting to the server of " + strings[0]).color(TextColor.YELLOW));
                } else {
                    commandSource.sendMessage(TextComponent.of("Player does not exists.").color(TextColor.RED));
                }
            } else {
                commandSource.sendMessage(TextComponent.of("Usage: /tps <username>").color(TextColor.RED));
            }
        } else {
            commandSource.sendMessage(TextComponent.of("Command is only for players.").color(TextColor.RED));
        }
    }

    @Override
    public List<String> suggest(CommandSource source, @NonNull String[] currentArgs) {
        List<String> arg = new ArrayList<>();
        if (currentArgs.length == 1) {
            for (Player player : server.getAllPlayers()) {
                arg.add(player.getUsername());
            }
        }
        return arg;
    }

    @Override
    public boolean hasPermission(CommandSource source, @NonNull String[] args) {
        return source.hasPermission("VTools.tps");
    }
}
