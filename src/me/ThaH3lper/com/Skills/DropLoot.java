package me.ThaH3lper.com.Skills;

import java.util.List;

import me.ThaH3lper.com.Entitys.MobTemplet;
import me.ThaH3lper.com.Entitys.MobsHandler;
import me.ThaH3lper.com.Spawner.SpawnerHandler;

import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class DropLoot extends DeathSkill
{
	private String cmdName;
	
	public DropLoot(String cmdName)
	{
		this.cmdName = cmdName;
	}
	
	@Override
	public void playSkill(LivingEntity le) throws IllegalArgumentException, Exception
	{
		if(SpawnerHandler.getMobTempletFromCmdName(cmdName) != null)
		{
			MobTemplet mt = SpawnerHandler.getMobTempletFromCmdName(cmdName);
			List<ItemStack> items = MobsHandler.getDrops(mt.drops);
			for(ItemStack is : items)
			{
				le.getWorld().dropItemNaturally(le.getLocation(), is);
			}
		}
	}
}
