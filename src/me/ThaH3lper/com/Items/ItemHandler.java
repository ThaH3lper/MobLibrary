package me.ThaH3lper.com.Items;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.ThaH3lper.com.utils.ItemUtils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItemHandler
{
	private static List<ItemsObject> items = new ArrayList<ItemsObject>();
	
	public static void load()
	{
		File folder = new File("plugins/MobLibrary");
		if(!folder.exists())
			folder.mkdirs();
		
		File tempFile = new File("plugins/MobLibrary/Items.yml");
		if(!tempFile.exists())
		{
			try
			{
				tempFile.createNewFile();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return;
			}
		}
		
		FileConfiguration config = new YamlConfiguration();
		try
		{
			config.load(tempFile);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
		
		if(!config.contains("Items"))
			return;
		
		Iterator<String> itr = config.getConfigurationSection("Items").getKeys(false).iterator();
		while(itr.hasNext())
		{
			String s = itr.next();
			
			int id = config.getInt("Items." + s + ".Id");
			short data = (short)config.getInt("Items." + s + ".Data");
			int amount = config.getInt("Items." + s + ".Amount");
			
			ItemStack is = new ItemStack(id, amount, data);
			
			if(config.contains("Items." + s + ".Name"))
			{
				ItemUtils.setName(is, config.getString("Items." + s + ".Name"));
			}
			
			if(config.contains("Items." + s + ".Lore"))
			{
				ItemUtils.setLore(is, config.getStringList("Items." + s + ".Lore"));
			}
			
			if(config.contains("Items." + s + ".Enchantments"))
			{
				Iterator<String> eItr = config.getStringList("Items." + s + ".Enchantments").iterator();
				while(eItr.hasNext())
				{
					String[] split = eItr.next().split(",");
					int lvl = Integer.valueOf(split[1]);
					is.addUnsafeEnchantment(Enchantment.getByName(split[0]), lvl);
				}
			}
			
			items.add(new ItemsObject(s, is));
		}
		
		/*if(MobLibrary.plugin.items.getCustomConfig().contains("Items"))
		{
			for(String s : MobLibrary.plugin.items.getCustomConfig().getConfigurationSection("Items").getKeys(false))
			{
				//Get basic
				int ID = ml.items.getCustomConfig().getInt("Items." + s + ".Id");
				Short data = (short)ml.items.getCustomConfig().getInt("Items." + s + ".Data");
				int Amount = ml.items.getCustomConfig().getInt("Items." + s + ".Amount");
				
				ItemStack stack = new ItemStack(Material.getMaterial(ID), Amount, data);
				ItemMeta IM = stack.getItemMeta();
				
				//Set DisplayName
				if(ml.items.getCustomConfig().contains("Items." + s + ".Name"))
				{
					String name = ml.items.getCustomConfig().getString("Items." + s + ".Name");
					name = name.replace("_", " ");
					name = ChatColor.translateAlternateColorCodes('&', name);
					IM.setDisplayName(name);
				}
				
				//Set Lores
				if(ml.items.getCustomConfig().contains("Items." + s + ".Lore"))
				{
					List<String> lores = ml.items.getCustomConfig().getStringList("Items." + s + ".Lore");
					List<String> newLores = new ArrayList<String>();
					for(String lore : lores)
					{
						lore = lore.replace("_", " ");
						lore = ChatColor.translateAlternateColorCodes('&', lore);
						newLores.add(lore);
					}
					IM.setLore(newLores);
				}
				
				stack.setItemMeta(IM);
				
				//Set Enchantments
				if(ml.items.getCustomConfig().contains("Items." + s + ".Enchantments"))
				{
					List<String> enchants = ml.items.getCustomConfig().getStringList("Items." + s + ".Enchantments");
					for(String enchant : enchants)
					{
						String[] split = enchant.split(",");
						int lvl = Integer.parseInt(split[1]);
						stack.addUnsafeEnchantment(Enchantment.getByName(split[0]), lvl);
					}
				}
				
				ml.itemList.add(new ItemsObject(s, stack));	
			}
		}*/
	}
	
	public static ItemsObject getItemObj(String name)
	{
		Iterator<ItemsObject> itr = items.iterator();
		while(itr.hasNext())
		{
			ItemsObject temp = itr.next();
			if(temp.getName().equalsIgnoreCase(name))
				return temp;
		}
		return null;
	}
	
	public static ItemStack getItem(String name)
	{
		Iterator<ItemsObject> itr = items.iterator();
		while(itr.hasNext())
		{
			ItemsObject temp = itr.next();
			if(temp.getName().equalsIgnoreCase(name))
				return temp.getItem();
		}
		return null;
	}
	
	public static String getList()
	{
		String s = " ";
		Iterator<ItemsObject> itr = items.iterator();
		while(itr.hasNext())
		{
			s += ChatColor.LIGHT_PURPLE +  itr.next().getName() + ChatColor.DARK_GREEN + ", ";
		}
		return s;
	}
}
