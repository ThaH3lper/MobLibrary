package me.ThaH3lper.com;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.Entitys.MobsHandler;
import me.ThaH3lper.com.Entitys.MobTemplet;
import me.ThaH3lper.com.Items.ItemHandler;
import me.ThaH3lper.com.SaveLoad.SaveLoad;
import me.ThaH3lper.com.Spawner.SpawnerHandler;
import me.ThaH3lper.com.Spawner.SpawnerPlace;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor
{
	final String VERSION = ChatColor.RED + "Version 0.8 ALPHA";
	
	public final String HEAD = ChatColor.GREEN + "vVv----------[" + ChatColor.GOLD + ChatColor.BOLD + " LIBRARY " + ChatColor.RESET + ChatColor.GREEN + "]----------vVv";
	public final String ITEM_HEAD = ChatColor.GREEN + "vVv----------[" + ChatColor.GOLD + ChatColor.BOLD + " ITEM LIBRARY " + ChatColor.RESET + ChatColor.GREEN + "]----------vVv";
	public final String MOB_HEAD = ChatColor.GREEN + "vVv----------[" + ChatColor.GOLD + ChatColor.BOLD + " MOB LIBRARY " + ChatColor.RESET + ChatColor.GREEN + "]----------vVv";
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args)
	{
		if(sender instanceof Player)
		{			
			Player p = (Player) sender;
			if(!p.hasPermission("Moblib.Admin"))
			{
				return true;
			}
			if(args.length == 0)
			{
				p.sendMessage(HEAD);
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib mob" + ChatColor.DARK_GREEN + " to go to Mob Libaray");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib item" + ChatColor.DARK_GREEN + " to go to Item Libaray");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib locations" + ChatColor.DARK_GREEN + " Mob spawner locations");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib load" + ChatColor.DARK_GREEN + " to read sign locations from memory");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib save" + ChatColor.DARK_GREEN + " to write sign locations to memory");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib backup" + ChatColor.DARK_GREEN + " to save signs to backup file");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib restore" + ChatColor.DARK_GREEN + " to restore signs from backup file");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib ver" + ChatColor.DARK_GREEN + " Version Info");

			}
			if(args.length == 1)
			{
				if(args[0].equalsIgnoreCase("item"))
				{
					p.sendMessage(ITEM_HEAD);
					p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib item [name] [amount]" + ChatColor.DARK_GREEN + " to get item");
					p.sendMessage(ChatColor.DARK_GREEN + "Loaded Items:" + ItemHandler.getList());
				}
				else if(args[0].equalsIgnoreCase("mob"))
				{
					p.sendMessage(MOB_HEAD);
					p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib mob [mobname] [boost]" + ChatColor.DARK_GREEN + " to spawn mob! (boost ex: 1.3)");
					p.sendMessage(ChatColor.DARK_GREEN + "Loaded Mobs:");
					for(String s : getMobs())
						p.sendMessage(s);
				}
				else if(args[0].equalsIgnoreCase("load"))
				{					
					p.sendMessage(ChatColor.GREEN + "===READING DATA FROM DATA FILE===");
					//me.ThaH3lper.com.SaveLoad.SaveLoad.readStoredData("StoredLocations.txt");
					SpawnerHandler.load();
				}
				else if(args[0].equalsIgnoreCase("save"))
				{					
					p.sendMessage(ChatColor.GREEN + "===WRITING SIGN DATA TO FILE===");
					//me.ThaH3lper.com.SaveLoad.SaveLoad.storeData("StoredLocations.txt");
					SaveLoad.saveSpawners();

				}
				else if(args[0].equalsIgnoreCase("ver"))
				{					
					p.sendMessage(VERSION);

				}
				else if(args[0].equalsIgnoreCase("locations"))
				{					
					p.sendMessage(ChatColor.GREEN + "Listing Mob Locations:");
					for(String s : getSignLocations())
						p.sendMessage(s);

				}
				else if(args[0].equalsIgnoreCase("backup"))
				{					
					p.sendMessage(ChatColor.YELLOW + "===WRITING SIGN DATA TO BACKUP FILE===");
					me.ThaH3lper.com.SaveLoad.SaveLoad.storeBackupData();
				}
				else if(args[0].equalsIgnoreCase("restore"))
				{					
					p.sendMessage(ChatColor.YELLOW + "===LOADING SIGN DATA FROM BACKUP===");
					me.ThaH3lper.com.SaveLoad.SaveLoad.restoreBackupData();

				}
				else if(args[0].equalsIgnoreCase("mobtimers"))
				{					
					p.sendMessage(ChatColor.YELLOW + "===LISTING MOBTIMERS===");
					for(String s : getMobTimers())
						p.sendMessage(s);

				}
			}
			if(args.length == 3)
			{
				if(args[0].equalsIgnoreCase("item"))
				{
					if(ItemHandler.getItem(args[1]) != null)
					{
						for(int i = 0; i < Integer.parseInt(args[2]); i++)
							p.getInventory().addItem(ItemHandler.getItem(args[1]));
						p.sendMessage(ChatColor.DARK_GREEN + "ItemStack Added!");
					}
					else
						p.sendMessage(ChatColor.RED + "There is no item like that!");
				}
				if(args[0].equalsIgnoreCase("mob"))
				{
					LivingEntity spawned = MobsHandler.SpawnAPI(args[1], p.getLocation(), Float.parseFloat(args[2]));
					if(spawned == null)
					{
						p.sendMessage(ChatColor.RED + "There is no mob with that name!");
					}
				}
			}


		}
		else
		{
			sender.sendMessage("Only Ingame! sorry");
		}
		return false;	
	}
	public List<String> getMobTimers()
	{
		String s = " ";
		int count = 0;
		List<String> mobTimers = new ArrayList<String>();
		for(SpawnerPlace sign : SpawnerHandler.getSpawners())
		{
			count++;
			s += ChatColor.LIGHT_PURPLE + "Loc: " + "X:" + ChatColor.RED + sign.getLocation().getBlockX() + ChatColor.LIGHT_PURPLE + "Y:" + ChatColor.RED + sign.getLocation().getBlockY() + ChatColor.LIGHT_PURPLE + "Z:" + ChatColor.RED + sign.getLocation().getZ() + ChatColor.LIGHT_PURPLE + "TICK:" + ChatColor.RED + sign.getTick() + " OF " + sign.getInterval() + ChatColor.LIGHT_PURPLE + " Type: " + ChatColor.RED + sign.getCmdMob() + ChatColor.LIGHT_PURPLE + " # spawns: " + ChatColor.RED + sign.getNumberOfSpawns() + ChatColor.AQUA + " || ";
			if(count == 1)
			{
				count = 0;
				mobTimers.add(s);
			}
		}
		return mobTimers;
	}
	
	public List<String> getMobs()
	{
		String s = " ";
		int count = 0;
		List<String> mobs = new ArrayList<String>();
		for(MobTemplet mt : MobsHandler.getMobTemplets())
		{
			count++;
			s += ChatColor.LIGHT_PURPLE + mt.cmdName + ChatColor.DARK_GREEN + ", ";
			if(count == 5)
			{
				count = 0;
				mobs.add(s);
			}
		}
		return mobs;
	}
	
	public List<String> getSignLocations()
	{
		String s = " ";
		int count = 0;
		List<String> locations = new ArrayList<String>();
		for(SpawnerPlace sign : SpawnerHandler.getSpawners())
		{
			count++;
			s += ChatColor.LIGHT_PURPLE + "Loc: " + "X:" + ChatColor.RED + sign.getLocation().getBlockX() + ChatColor.LIGHT_PURPLE + "Y:" + ChatColor.RED + sign.getLocation().getBlockY() + ChatColor.LIGHT_PURPLE + "Z:" + ChatColor.RED + sign.getLocation().getZ() + ChatColor.LIGHT_PURPLE + "W:" + ChatColor.RED + sign.getLocation().getWorld().getName() + ChatColor.LIGHT_PURPLE + " Type: " + ChatColor.RED + sign.getCmdMob() + ChatColor.LIGHT_PURPLE + " Time: " + ChatColor.RED + sign.getInterval() + ChatColor.AQUA + " || ";
			if(count == 1)
			{
				count = 0;
				locations.add(s);
			}
		}
		return locations;
	}

}
