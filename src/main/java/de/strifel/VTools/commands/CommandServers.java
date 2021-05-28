package de.strifel.VTools.commands;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.text.TextComponent;
import net.kyori.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CommandServers implements Command {
    private final ProxyServer server;

    public CommandServers(ProxyServer server) {
        this.server = server;
    }


    @Override
    public void execute(CommandSource commandSource, @NonNull String[] strings) {
        StringBuilder servers = new StringBuilder();
        for (RegisteredServer server : server.getAllServers()) {
            servers.append(server.getServerInfo().getName());
            servers.append(" ");
        }
        commandSource.sendMessage(TextComponent.of(servers.toString()).color(TextColor.YELLOW));
    }

    @Override
    public List<String> suggest(CommandSource source, @NonNull String[] currentArgs) {
        return new ArrayList<String>();
    }

    @Override
    public boolean hasPermission(CommandSource source, @NonNull String[] args) {
        return source.hasPermission("vtools.find");
    }
}
