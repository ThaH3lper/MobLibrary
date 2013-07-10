package me.ThaH3lper.com.Spawner;

import me.ThaH3lper.com.MobLibrary;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class SpawnerListener implements Listener{
	
	MobLibrary ml;
	
	public SpawnerListener(MobLibrary ml)
	{
		this.ml = ml;
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void SignCreate(PlayerInteractEvent e)
	{
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		if(!(e.getClickedBlock().getState() instanceof Sign))
			return;
		
		Sign s = (Sign) e.getClickedBlock().getState();
		if(s.getLine(0).equalsIgnoreCase("[mobs]"))
		{			
			int inteval = 0;
			int amount = 0;
			int radious = 0;
			String cmdname = s.getLine(2);
			
			String str = s.getLine(3);
			str = str.replace("i", "");
			str = str.replace("s", "");
			String[] parts = str.split(",");
			
			amount = Integer.parseInt(parts[0]);
			inteval = Integer.parseInt(parts[1]);
			radious = Integer.parseInt(s.getLine(1));
			
			if(inteval != 0 && amount != 0 && radious != 0)
			{
				e.getPlayer().sendMessage(ChatColor.GREEN + "Spawner Created");
				s.setLine(0, ChatColor.GREEN + "[MobSpawner]");
				s.update();
				
				ml.spawnerList.add(new SpawnerPlace(e.getClickedBlock().getLocation(), cmdname, amount, inteval, radious, ml));
			}
			else
			{
				e.getPlayer().sendMessage(ChatColor.RED + "Spawner Creation Failed!"  + parts[0] + " : " + parts[1] + " : " + inteval + " : " + amount);
			}
			
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void SignCreate(EntityDeathEvent e)
	{
		if(e.getEntity() instanceof LivingEntity)
		{
			LivingEntity l = (LivingEntity) e.getEntity();
			for(SpawnerPlace sp : ml.spawnerList)
			{
				sp.DeathMob(l);
			}
		}
	}

}
