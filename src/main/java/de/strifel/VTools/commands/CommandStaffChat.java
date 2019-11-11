package de.strifel.VTools.commands;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.text.TextComponent;
import net.kyori.text.format.TextColor;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandStaffChat implements Command {
    private final ProxyServer server;

    public CommandStaffChat(ProxyServer server) {
        this.server = server;
    }


    @Override
    public void execute(CommandSource commandSource, @NonNull String[] strings) {
        if (strings.length > 0) {
            String channel = strings[0].startsWith("c:") && !strings[0].equals("c:") ? strings[0].split(":")[1] : null;
            String message = "§4[Staff]§r " + (commandSource instanceof Player ? ((Player) commandSource).getUsername() : "Console") + (channel != null ? " (" + channel + ")" : "")+ " > " + String.join(" ", Arrays.copyOfRange(strings, channel == null ? 0 : 1, strings.length)).replace("&", "§");
            for (Player player : server.getAllPlayers()) {
                if (player.hasPermission("vtools.staffchat" + (channel != null ? "." + channel : ""))) {
                    player.sendMessage(TextComponent.of(message));
                }
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
        return source.hasPermission("vtools.staffchat");
    }
}
