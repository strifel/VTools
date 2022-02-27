package de.strifel.VTools.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.strifel.VTools.VTools.COLOR_RED;

public class CommandStaffChat implements SimpleCommand {
    private final ProxyServer server;

    public CommandStaffChat(ProxyServer server) {
        this.server = server;
    }


    @Override
    public void execute(SimpleCommand.Invocation invocation) {
        CommandSource commandSource = invocation.source();
        String[] strings = invocation.arguments();

        if (strings.length > 0) {
            String channel = strings[0].startsWith("c:") && !strings[0].equals("c:") ? strings[0].split(":")[1] : null;
            String message = "§4[Staff]§r " + (commandSource instanceof Player ? ((Player) commandSource).getUsername() : "Console") + (channel != null ? " (" + channel + ")" : "")+ " > " + String.join(" ", Arrays.copyOfRange(strings, channel == null ? 0 : 1, strings.length)).replace("&", "§");
            for (Player player : server.getAllPlayers()) {
                if (player.hasPermission("vtools.staffchat" + (channel != null ? "." + channel : ""))) {
                    player.sendMessage(Component.text(message));
                }
            }
        } else {
            commandSource.sendMessage(Component.text("Usage: /broadcast <message>").color(COLOR_RED));
        }
    }

    @Override
    public List<String> suggest(SimpleCommand.Invocation invocation) {
        return new ArrayList<String>();
    }

    @Override
    public boolean hasPermission(SimpleCommand.Invocation invocation) {
        return invocation.source().hasPermission("vtools.staffchat");
    }
}
