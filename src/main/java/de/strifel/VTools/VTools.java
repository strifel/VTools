package de.strifel.VTools;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import de.strifel.VTools.commands.*;
import net.kyori.adventure.text.format.TextColor;
import org.slf4j.Logger;

import javax.inject.Inject;

@Plugin(id = "vtools", name="VTools", version="1.0-SNAPSHOT", description="Some commands!")
public class VTools {
    private final ProxyServer server;

    public static final TextColor COLOR_RED = TextColor.fromCSSHexString("FF5555");
    public static final TextColor COLOR_YELLOW = TextColor.fromCSSHexString("FFFF55");

    @Inject
    public VTools(ProxyServer server, Logger logger) {
        this.server = server;
    }


    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getCommandManager().register("send", new CommandSend(server));
        server.getCommandManager().register("sendall", new CommandSendall(server));
        server.getCommandManager().register("broadcast", new CommandBroadcast(server), "bc", "alert");
        server.getCommandManager().register("find", new CommandFind(server), "search");
        server.getCommandManager().register("staffchat", new CommandStaffChat(server), "sc");
        server.getCommandManager().register("restart", new CommandRestart(server));
        server.getCommandManager().register("tps", new CommandTp(server), "jump");
        server.getCommandManager().register("servers", new CommandServers(server), "allservers");
    }

}
