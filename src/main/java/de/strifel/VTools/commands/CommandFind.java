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

public class CommandFind implements Command {
    private final ProxyServer server;

    public CommandFind(ProxyServer server) {
        this.server = server;
    }


    @Override
    public void execute(CommandSource commandSource, @NonNull String[] strings) {
        if (strings.length == 1) {
            Optional<Player> player = server.getPlayer(strings[0]);
            if (player.isPresent() && player.get().getCurrentServer().isPresent()) {
                commandSource.sendMessage(TextComponent.of("Player " + strings[0] + " is on " + player.get().getCurrentServer().get().getServerInfo().getName() + "!").color(TextColor.YELLOW));
            } else {
                commandSource.sendMessage(TextComponent.of("The player is not online!").color(TextColor.YELLOW));
            }
        } else {
            commandSource.sendMessage(TextComponent.of("Usage: /find <username>").color(TextColor.RED));
        }
    }

    @Override
    public List<String> suggest(CommandSource source, @NonNull String[] currentArgs) {
        List<String> arg = new ArrayList<>();
        if (currentArgs.length == 1 && source.hasPermission("vtools.find.autocomplete")) {
            for (Player player : server.getAllPlayers()) {
                arg.add(player.getUsername());
            }
        }
        return arg;
    }

    @Override
    public boolean hasPermission(CommandSource source, @NonNull String[] args) {
        return source.hasPermission("vtools.find");
    }
}
