package me.ThaH3lper.com;

import me.ThaH3lper.com.Entitys.MobTemplet;
import me.ThaH3lper.com.Items.ItemsObject;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor{
	
	MobLibrary ml;
	
	public String head = ChatColor.GREEN + "vVv----------[" + ChatColor.GOLD + ChatColor.BOLD + " LIBRARY " + ChatColor.RESET + ChatColor.GREEN + "]----------vVv";
	public String ihead = ChatColor.GREEN + "vVv----------[" + ChatColor.GOLD + ChatColor.BOLD + " ITEM LIBRARY " + ChatColor.RESET + ChatColor.GREEN + "]----------vVv";
	public String mhead = ChatColor.GREEN + "vVv----------[" + ChatColor.GOLD + ChatColor.BOLD + " MOB LIBRARY " + ChatColor.RESET + ChatColor.GREEN + "]----------vVv";
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
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib mob" + ChatColor.DARK_GREEN + " to go to Mob Libaray");
				p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib item" + ChatColor.DARK_GREEN + " to go to Item Libaray");
			}
			if(args.length == 1)
			{
				if(args[0].equalsIgnoreCase("item"))
				{
					p.sendMessage(ihead);
					p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib item [name] [amount]" + ChatColor.DARK_GREEN + " to get item");
					p.sendMessage(ChatColor.DARK_GREEN + "Loaded Items:" + getList());
				}
				else if(args[0].equalsIgnoreCase("mob"))
				{
					p.sendMessage(mhead);
					p.sendMessage(ChatColor.DARK_GREEN + "Type " + ChatColor.WHITE + "/lib mob [mobname] [boost]" + ChatColor.DARK_GREEN + " to spawn mob! (boost ex: 1.3)");
					p.sendMessage(ChatColor.DARK_GREEN + "Loaded Mobs:" + getMobs());
				}
				else if(args[0].equalsIgnoreCase("testread"))
				{					
					Bukkit.broadcastMessage("===TESTING READDATA DATA WILL PRINT BELOW===");
					me.ThaH3lper.com.SaveLoad.SaveLoad.readStoredData("test");

				}
				else if(args[0].equalsIgnoreCase("testwrite"))
				{					
					Bukkit.broadcastMessage("===TESTING READDATA DATA WILL PRINT BELOW===");
					me.ThaH3lper.com.SaveLoad.SaveLoad.storeData("test");

				}
			}
			if(args.length == 3)
			{
				if(args[0].equalsIgnoreCase("item"))
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
				if(args[0].equalsIgnoreCase("mob"))
				{
					LivingEntity spawned = ml.mobHandler.SpawnAPI(args[1], p.getLocation(), Float.parseFloat(args[2]));
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
	
	public String getMobs()
	{
		String s = " ";
		for(MobTemplet mt : ml.mobTempletList)
		{
			s += ChatColor.LIGHT_PURPLE + mt.cmdName + ChatColor.DARK_GREEN + ", ";
		}
		return s;
	}

}
