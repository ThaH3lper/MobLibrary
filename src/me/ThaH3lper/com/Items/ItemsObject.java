package me.ThaH3lper.com.Items;

import org.bukkit.inventory.ItemStack;

public class ItemsObject
{	
	private ItemStack itemStack;
	private String name;
	
	public ItemsObject(String name, ItemStack itemStack)
	{
		this.name = name;
		this.itemStack = itemStack;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ItemStack getItem()
	{
		return itemStack.clone();
	}
}
