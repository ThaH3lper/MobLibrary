package me.ThaH3lper.com.Spawner;

import java.util.List;

import me.ThaH3lper.com.MobLibrary;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class SpawnerListener implements Listener{
	
	private MobLibrary ml;
	
	public SpawnerListener(MobLibrary ml)
	{
		this.ml = ml;
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void chunkUnload(ChunkUnloadEvent event){
		for(SpawnerPlace sign : ml.spawnerList){
			if(sign.getLocation().getChunk().equals(event.getChunk())){
				event.setCancelled(true);
			}
			if(sign.getLocation().getChunk() == event.getChunk()){
				event.setCancelled(true);
			}
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void SignCreate(PlayerInteractEvent e)
	{
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		if(!(e.getClickedBlock().getState() instanceof Sign))
			return;
		
		Sign s = (Sign) e.getClickedBlock().getState();
		if(s.getLine(0).equalsIgnoreCase("[mobs]") && e.getPlayer().hasPermission("Mobs.Create"))
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
			
			if(inteval != 0 && amount != 0 && radious != 0 && ml.mobs.getCustomConfig().contains("Mobs." + cmdname))
			{
				e.getPlayer().sendMessage(ChatColor.GREEN + "Spawner Created");
				s.setLine(0, ChatColor.GREEN + "[MobSpawner]");
				s.update();
				
				ml.spawnerList.add(new SpawnerPlace(e.getClickedBlock().getLocation(), cmdname, amount, inteval, radious, ml));
				me.ThaH3lper.com.SaveLoad.SaveLoad.storeData("StoredLocations.txt");
			}
			else
			{
				e.getPlayer().sendMessage(ChatColor.RED + "Spawner Creation Failed!"  + parts[0] + " : " + parts[1] + " : " + inteval + " : " + amount);
				if(!ml.mobs.getCustomConfig().contains("Mobs." + cmdname)){
					e.getPlayer().sendMessage(ChatColor.RED + "Spawner Creation Failed: "+ cmdname + " Doesn't Exist in config canceling sign");
				}
				Block block = s.getBlock();
				block.setType(Material.AIR);
			}
			
		}
		else if(s.getLine(0).equalsIgnoreCase("[mobs]")){
			Block block = s.getBlock();
			block.setType(Material.AIR);
		}
	}
	
	@EventHandler
	public void signDestroy(BlockBreakEvent e){
		if(!(e.getBlock().getState() instanceof Sign)){
			return;
		}
		Sign s = (Sign) e.getBlock().getState();
		if(s.getLine(0).equalsIgnoreCase(ChatColor.GREEN + "[MobSpawner]")){
			for(int i = 0; i<= ml.spawnerList.size() - 1; i++){
				Location existing = ml.spawnerList.get(i).getLocation();
				Location loc = s.getLocation();
				if(existing.getBlockX() == loc.getBlockX() && existing.getBlockY() == loc.getBlockY() && existing.getBlockZ() == loc.getBlockZ()){
					List<LivingEntity> mobs = ml.spawnerList.get(i).getMobsList();
					for(LivingEntity mob:mobs){
						mob.remove();
					}
					e.getPlayer().sendMessage(ChatColor.GREEN + "[MOBS]: " + ChatColor.RED + "Spawner for " + ChatColor.LIGHT_PURPLE + ml.spawnerList.get(i).getCmdMob() + ChatColor.RED + " Removed!");
					ml.spawnerList.remove(i);
					me.ThaH3lper.com.SaveLoad.SaveLoad.storeData("StoredLocations.txt");
				}
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
