package me.ThaH3lper.com;

import java.util.List;

import me.ThaH3lper.com.Entitys.MobTemplet;
import me.ThaH3lper.com.Skills.SkillHandler;

import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {
			
	MobLibrary ml;
	public EventListener(MobLibrary ml)
	{
		this.ml = ml;
	}
			
	@EventHandler(priority = EventPriority.HIGH)
	public void ModDeath(EntityDeathEvent e)
	{
		LivingEntity l = e.getEntity();
		if(getMobTemplet(l) != null)
		{
			e.getDrops().clear();
			MobTemplet mt = getMobTemplet(l);
			List<ItemStack> items = ml.mobHandler.getDrops(l, mt.drops);
			for(ItemStack s : items)
			{
				e.getDrops().add(s);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void ModHit(EntityDamageByEntityEvent e)
	{
		if(e.getEntity() instanceof LivingEntity)
		{
			LivingEntity l = (LivingEntity) e.getEntity();
			if(ml.mobHandler.getSkills(l) != null)
			{
				try {
					SkillHandler.executeSkills(ml.mobHandler.getSkills(l), l);
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
				}
			}
		}
	}
	
	public MobTemplet getMobTemplet(LivingEntity l)
	{
		for(MobTemplet mt : ml.mobTempletList)
		{
			String name = mt.display;
			name = name.replace("_", " ");
			name = ChatColor.translateAlternateColorCodes('&', name);
			if(l.getCustomName() != null)
			{
				if(l.getCustomName().equals(name))
				{
					return mt;
				}
			}
		}
		return null;
	}
	@EventHandler
	public void entityTnTDamage(EntityDamageEvent event){
		if(event.getEntity() instanceof Player){
			return;
		}
		else{
			if(event.getCause() == DamageCause.ENTITY_EXPLOSION || event.getCause() == DamageCause.BLOCK_EXPLOSION){
				event.setCancelled(true);
			}
		}
		
	}
}

	


