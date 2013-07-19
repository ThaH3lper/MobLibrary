package me.ThaH3lper.com.Items;

import org.bukkit.inventory.ItemStack;

public class ItemObject
{
	private ItemStack itemStack;
	private String name;
	
	public ItemObject(String name, ItemStack itemStack)
	{
		this.name = name;
		this.itemStack = itemStack;
	}
	
	public ItemStack getItemStack()
	{
		return itemStack;
	}
	
	public String getName()
	{
		return name;
	}
}
