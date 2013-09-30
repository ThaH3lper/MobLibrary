package me.ThaH3lper.com.Items;

import java.util.ArrayList;
import java.util.List;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.SaveLoad.SaveLoad;
import me.frodenkvist.utils.ItemUtils;
import me.frodenkvist.utils.MaterialFromInt;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItemHandler
{
	private static List<ItemObject> items = new ArrayList<ItemObject>();
	
	public static void Load()
	{
		SaveLoad items = MobLibrary.plugin.getItemConfig();
		if(!items.getCustomConfig().contains("Items"))
			return;
		for(String s : items.getCustomConfig().getConfigurationSection("Items").getKeys(false))
		{
			//Get basic
			int ID = items.getCustomConfig().getInt("Items." + s + ".Id");
			Short data = (short)items.getCustomConfig().getInt("Items." + s + ".Data");
			int Amount = items.getCustomConfig().getInt("Items." + s + ".Amount");
				
			ItemStack is = new ItemStack(MaterialFromInt.getMaterialFromInt(ID), Amount, data);
			
			//Set DisplayName
			if(items.getCustomConfig().contains("Items." + s + ".Name"))
			{
				String name = items.getCustomConfig().getString("Items." + s + ".Name");
				name = name.replace("_", " ");
				ItemUtils.setName(is, name);
			}
				
			//Set Lores
			if(items.getCustomConfig().contains("Items." + s + ".Lore"))
			{
				List<String> lores = items.getCustomConfig().getStringList("Items." + s + ".Lore");
				List<String> newLore = new ArrayList<String>();
				for(String lore : lores)
				{
					lore = lore.replace("_", " ");
					newLore.add(lore);
				}
				ItemUtils.setLore(is, newLore);
			}
				
			//Set Enchantments
			if(items.getCustomConfig().contains("Items." + s + ".Enchantments"))
			{
				List<String> Enchants = items.getCustomConfig().getStringList("Items." + s + ".Enchantments");
				for(String enchant : Enchants)
				{
					String[] split = enchant.split(",");
					int lvl = Integer.parseInt(split[1]);
					is.addUnsafeEnchantment(Enchantment.getByName(split[0]), lvl);
				}
			}
			ItemHandler.items.add(new ItemObject(s, is));
		}
	}
	
	public static ItemStack getItem(String name)
	{
		for(ItemObject io : items)
		{
			if(io.getName().equals(name))
			{
				return io.getItemStack().clone();
			}
		}
		return null;
	}
	
	public static String getList()
	{
		String s = " ";
		for(ItemObject io : items)
		{
			s += ChatColor.LIGHT_PURPLE + io.getName() + ChatColor.DARK_GREEN + ", ";
		}
		return s;
	}
}
