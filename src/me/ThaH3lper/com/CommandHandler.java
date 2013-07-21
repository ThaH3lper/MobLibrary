package me.ThaH3lper.com;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.Entitys.MobsHandler;
import me.ThaH3lper.com.Entitys.MobTemplet;
import me.ThaH3lper.com.Items.ItemsObject;
import me.ThaH3lper.com.Spawner.SpawnerPlace;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor
{
	final String VERSION = ChatColor.RED + "Version 0.7 ALPHA";
	
	private MobLibrary ml;
	
	private String head = ChatColor.GREEN + "vVv----------[" + ChatColor.GOLD + ChatColor.BOLD + " LIBRARY " + ChatColor.RESET + ChatColor.GREEN + "]----------vVv";
	private String ihead = ChatColor.GREEN + "vVv----------[" + ChatColor.GOLD + ChatColor.BOLD + " ITEM LIBRARY " + ChatColor.RESET + ChatColor.GREEN + "]----------vVv";
	private String mhead = ChatColor.GREEN + "vVv----------[" + ChatColor.GOLD + ChatColor.BOLD + " MOB LIBRARY " + ChatColor.RESET + ChatColor.GREEN + "]----------vVv";
	
	public CommandHandler(MobLibrary ml)
	{
		this.ml = ml;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args){
		if(sender instanceof Player)
		{			
			Player p = (Player) sender;
			if(!p.hasPermission("Moblib.Admin"))
			{
				return true;
			}
			if(args.length == 0)
			{
				p.sendMessage(head);
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib ver" + ChatColor.DARK_GREEN + " Version Info");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib mobs" + ChatColor.DARK_GREEN + " to go to Mob Libaray");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib items" + ChatColor.DARK_GREEN + " to go to Item Libaray");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib locations" + ChatColor.DARK_GREEN + " Mob spawner locations");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib mobtimers" + ChatColor.DARK_GREEN + " get boss spawn timer info");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib load" + ChatColor.DARK_GREEN + " to read sign locations from memory");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib save" + ChatColor.DARK_GREEN + " to write sign locations to memory");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib backup" + ChatColor.DARK_GREEN + " to save signs to backup file");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib restore" + ChatColor.DARK_GREEN + " to restore signs from backup file");

			}
			if(args.length == 1)
			{
				if(args[0].equalsIgnoreCase("items"))
				{
					p.sendMessage(ihead);
					p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib item [name] [amount]" + ChatColor.DARK_GREEN + " to get item");
					p.sendMessage(ChatColor.DARK_GREEN + "Loaded Items:" + getList());
				}
				else if(args[0].equalsIgnoreCase("mobs"))
				{
					p.sendMessage(mhead);
					p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib mob [mobname] [boost]" + ChatColor.DARK_GREEN + " to spawn mob! (boost ex: 1.3)");
					p.sendMessage(ChatColor.DARK_GREEN + "Loaded Mobs:");
					for(String s : getMobs())
						p.sendMessage(s);
				}
				else if(args[0].equalsIgnoreCase("load"))
				{					
					p.sendMessage(ChatColor.GREEN + "===READING DATA FROM DATA FILE===");
					me.ThaH3lper.com.SaveLoad.SaveLoad.readStoredData("StoredLocations.txt");

				}
				else if(args[0].equalsIgnoreCase("save"))
				{					
					p.sendMessage(ChatColor.GREEN + "===WRITING SIGN DATA TO FILE===");
					me.ThaH3lper.com.SaveLoad.SaveLoad.storeData("StoredLocations.txt");

				}
				else if(args[0].equalsIgnoreCase("ver"))
				{					
					p.sendMessage(VERSION);

				}
				else if(args[0].equalsIgnoreCase("locations"))
				{					
					p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD +  "Listing Mob Locations:");
					for(String s : getSignLocations())
						p.sendMessage(s);

				}
				else if(args[0].equalsIgnoreCase("mobtimers"))
				{					
					p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Listing Mob Timers:");
					for(String s : getMobTimers())
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
			}
			if(args.length == 3)
			{
				if(args[0].equalsIgnoreCase("items"))
				{
					if(ml.loadItems.getItem(args[1]) != null)
					{
						for(int i = 0; i < Integer.parseInt(args[2]); i++)
							p.getInventory().addItem(ml.loadItems.getItem(args[1]));
						p.sendMessage(ChatColor.DARK_GREEN + "ItemStack Added!");
					}
					else
						p.sendMessage(ChatColor.RED + "There is no item like that!");
				}
				if(args[0].equalsIgnoreCase("mobs"))
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
	
	public String getList()
	{
		String s = " ";
		for(ItemsObject io : ml.itemList)
		{
			s += ChatColor.LIGHT_PURPLE +io.name + ChatColor.DARK_GREEN + ", ";
		}
		return s;
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
		List<String> locations = new ArrayList<String>();
		for(SpawnerPlace sign : ml.spawnerList)
		{
			s = ChatColor.LIGHT_PURPLE + "Loc: " + "X:" + ChatColor.RED + sign.getLocation().getBlockX() + ChatColor.LIGHT_PURPLE + "Y:" + ChatColor.RED + sign.getLocation().getBlockY() + ChatColor.LIGHT_PURPLE + "Z:" + ChatColor.RED + sign.getLocation().getZ() + ChatColor.LIGHT_PURPLE + "W:" + ChatColor.RED + sign.getLocation().getWorld().getName() + ChatColor.LIGHT_PURPLE + " Type: " + ChatColor.RED + sign.getCmdMob() + ChatColor.LIGHT_PURPLE + " Time: " + ChatColor.RED + sign.getInterval() + ChatColor.AQUA + " || ";
			locations.add(s);
		}
		return locations;
	}
	public List<String> getMobTimers()
	{
		String s = " ";
		List<String> mobTimers = new ArrayList<String>();
		for(SpawnerPlace sign : ml.spawnerList){
			s = ChatColor.LIGHT_PURPLE + "MobName: " + ChatColor.YELLOW + "" + sign.getCmdMob() + " " + ChatColor.LIGHT_PURPLE + "TICKS :" + ChatColor.RED + sign.getTick() + ChatColor.LIGHT_PURPLE + " of " + ChatColor.GREEN + sign.getInterval() + "" + ChatColor.LIGHT_PURPLE + "Amt: " + ChatColor.RED + (sign.mobs.size()-sign.adds.size()) + " of " + sign.getAmount() + "" + ChatColor.LIGHT_PURPLE + " Adds: " + ChatColor.RED + "" + sign.adds.size();
			mobTimers.add(s);
		}
		return mobTimers;
	}

}
