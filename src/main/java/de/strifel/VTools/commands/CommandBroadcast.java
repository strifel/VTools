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

public class CommandBroadcast implements Command {
    private final ProxyServer server;

    public CommandBroadcast(ProxyServer server) {
        this.server = server;
    }

    @Override
    public void execute(CommandSource commandSource, @NonNull String[] strings) {
        if (strings.length > 0) {
            String message = String.join(" ", strings).replace("&", "§");
            for (Player player : server.getAllPlayers()) {
                player.sendMessage(TextComponent.of(message));
            }
        } else {
            commandSource.sendMessage(TextComponent.of("Usage: /broadcast <message>").color(TextColor.RED));
        }
    }

    @Override
    public List<String> suggest(CommandSource source, @NonNull String[] currentArgs) {
        return new ArrayList<String>();
    }

    @Override
    public boolean hasPermission(CommandSource source, @NonNull String[] args) {
        return source.hasPermission("vtools.broadcast");
    }
}
