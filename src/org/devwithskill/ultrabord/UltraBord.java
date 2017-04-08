package org.devwithskill.ultrabord;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

public class UltraBord extends JavaPlugin implements Listener {
	
	
Scoreboard sb;
	
	@Override
	public void onEnable() {
		
		this.getServer().getPluginManager().registerEvents(this, this);
		
		sb = Bukkit.getScoreboardManager().getNewScoreboard();
	
		sb.registerNewTeam("01admin");
		sb.registerNewTeam("02spieler");
		sb.registerNewTeam("03mod");
		sb.registerNewTeam("04owner");
		sb.registerNewTeam("05baumeister");
		sb.registerNewTeam("06supporter");
		sb.registerNewTeam("07premi");
		sb.registerNewTeam("08premi+");
		
		sb.getTeam("01admin").setPrefix("§aAdmin §7- ");
		sb.getTeam("03spieler").setPrefix("§fSpieler §7- ");
		sb.getTeam("04owner").setPrefix("§bOwner §7- ");
		sb.getTeam("05baumeister").setPrefix("§cBuilder §7- ");
		sb.getTeam("06supporter").setPrefix("§0Supporter §7- ");
		sb.getTeam("07premi").setPrefix("§ePremium §7- ");
		sb.getTeam("08premi+").setPrefix("§6Premium+ §7- ");
		
	}
	
	@EventHandler
	public void onJoin(final PlayerJoinEvent event) {
		
		new BukkitRunnable() {
			
			public void run() {
				setPrefix(event.getPlayer());
			}
		}.runTaskLaterAsynchronously(this, 1);
		
	}
	
	public void setPrefix(Player p) {
		
		String team = "";
		
		if (p.hasPermission("Prefix.admin")) {
			team = "01admin";
		} else if (p.hasPermission("Prefix.mod")) {
			team = "03mod";
		} else if (p.hasPermission("Prefix.owner")) {
			team = "04owner";
		} else if (p.hasPermission("Prefix.builder")) {
			team = "05baumeister";
		} else if (p.hasPermission("Prefix.supporter")) {
			team = "06supporter";
		} else if (p.hasPermission("Prefix.premium")) {
			team = "07premi";
		} else if (p.hasPermission("Prefix.premium+")) {
			team = "08premi+";
		} else {
			team = "02spieler";
			}
		
		sb.getTeam(team).addPlayer(p);
		p.setDisplayName(sb.getTeam(team).getPrefix() + p.getName());
		
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.setScoreboard(sb);
		}
		
		
	}

}
