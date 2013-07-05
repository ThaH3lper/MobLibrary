package me.ThaH3lper.com.Spawner;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class SpawnerListener implements Listener{
	
	@EventHandler(priority = EventPriority.HIGH)
	public void SignCreate(SignChangeEvent e)
	{
		Sign s = (Sign) e.getBlock();
		if(s.getLine(1).equalsIgnoreCase("monsterspawner"))
		{
			e.getPlayer().sendMessage(ChatColor.GREEN + "Spawner Created");
		}
	}

}
