package de.strifel.VTools.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

import static de.strifel.VTools.VTools.COLOR_YELLOW;

public class CommandServers implements SimpleCommand {
    private final ProxyServer server;

    public CommandServers(ProxyServer server) {
        this.server = server;
    }


    @Override
    public void execute(SimpleCommand.Invocation invocation) {
        CommandSource commandSource = invocation.source();
        String[] strings = invocation.arguments();

        StringBuilder servers = new StringBuilder();
        for (RegisteredServer server : server.getAllServers()) {
            servers.append(server.getServerInfo().getName());
            servers.append(" ");
        }
        commandSource.sendMessage(Component.text(servers.toString()).color(COLOR_YELLOW));
    }

    @Override
    public List<String> suggest(SimpleCommand.Invocation invocation) {
        return new ArrayList<String>();
    }

    @Override
    public boolean hasPermission(SimpleCommand.Invocation invocation) {
        return invocation.source().hasPermission("vtools.find");
    }
}
