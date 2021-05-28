package de.strifel.VTools;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import de.strifel.VTools.commands.*;
import org.slf4j.Logger;

import javax.inject.Inject;

@Plugin(id = "vtools", name="VTools", version="1.0-SNAPSHOT", description="Some commands!")
public class VTools {
    private final ProxyServer server;

    @Inject
    public VTools(ProxyServer server, Logger logger) {
        this.server = server;
    }


    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getCommandManager().register(new CommandSend(server), "send");
        server.getCommandManager().register(new CommandSendall(server), "sendall");
        server.getCommandManager().register(new CommandBroadcast(server), "broadcast", "bc", "alert");
        server.getCommandManager().register(new CommandFind(server), "find", "search");
        server.getCommandManager().register(new CommandStaffChat(server), "staffchat", "sc");
        server.getCommandManager().register(new CommandRestart(server), "restart");
        server.getCommandManager().register(new CommandTp(server), "tps", "jump");
        server.getCommandManager().register(new CommandServers(server), "servers", "allservers");
    }

}
