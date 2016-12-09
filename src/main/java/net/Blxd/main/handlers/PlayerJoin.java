package net.Blxd.main.handlers;

import net.Blxd.main.Main;
import net.Blxd.main.dbmanager.DBManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoin implements Listener{
	
	@SuppressWarnings("unused")
	private static Main plugin;
	public PlayerJoin(Main listener) {
		PlayerJoin.plugin = listener;	
	}
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
		DBManager.insert("Stats", "Players", e.getPlayer().getName(), e.getPlayer().getUniqueId(), e.getPlayer().getGameMode().toString(), e.getPlayer().getHealth());
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e){
		DBManager.update("Stats", "Players", e.getPlayer().getName(), e.getPlayer().getUniqueId(), e.getPlayer().getGameMode().toString(), e.getPlayer().getHealth());
	}

}
