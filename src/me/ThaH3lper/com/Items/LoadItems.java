package me.ThaH3lper.com.Items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.ThaH3lper.com.MobLibrary;

public class LoadItems {
	
	public MobLibrary ml;
	
	public LoadItems(MobLibrary ml)
	{
		this.ml = ml;
		Load();
	}
	
	public void Load()
	{
		if(ml.items.getCustomConfig().contains("Items"))
		{
			for(String s : ml.items.getCustomConfig().getConfigurationSection("Items").getKeys(false))
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
		}
	
	}
	
	public ItemStack getItem(String name)
	{
		for(ItemsObject oi : ml.itemList)
		{
			if(oi.name.equals(name))
			{
				ItemStack stack = oi.itemStack.clone();
				return stack;
			}
		}
		return null;
	}
	
	/*
	 * Save and Load Spawners
	 * Going to add here
	 */

	/*public void saveSpawner(SpawnerPlace sp)
	{
		sp.getLocation();
	}*/

}
