package net.Blxd.main;

import net.Blxd.main.dbmanager.DBManager;
import net.Blxd.main.handlers.PlayerJoin;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	private static Main instance;
	
	public void onEnable(){
		registerListeners();
		instance = this;
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage("&aThe database test plugin has been enabled!");
		DBManager.connect("127.0.0.1", 27017, "Stats", "Players");
	}
	
	public void onDisable(){
		DBManager.closeConnection();
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage("&cThe database test plugin has been disabled!");
	}
	
	public static Main getInstance(){
	     return instance;
	}
	
	public void registerListeners(){
		PluginManager manager = Bukkit.getServer().getPluginManager();
		manager.registerEvents(new PlayerJoin(this), this);
		manager.registerEvents(new DBManager(this), this);
	}

}
