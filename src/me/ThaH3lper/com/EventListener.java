package me.ThaH3lper.com;

import me.ThaH3lper.com.Entitys.Mob;
import me.ThaH3lper.com.Entitys.MobTemplet;
import me.ThaH3lper.com.Entitys.MobsHandler;
import me.ThaH3lper.com.Spawner.SpawnerHandler;

import org.bukkit.craftbukkit.v1_6_R2.entity.CraftItem;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;

public class EventListener implements Listener 
{			
	@EventHandler(priority = EventPriority.LOWEST)
	public void ModDeath(EntityDeathEvent e)
	{
		LivingEntity l = e.getEntity();
		Mob mob = MobsHandler.getMob(l);
		if(mob != null)
		{
			e.getDrops().clear();
			mob.dropLoot();
			/*MobTemplet mt = SpawnerHandler.getMobTemplet(l);
			List<ItemStack> items = MobsHandler.getDrops(mt.drops);
			for(ItemStack s : items)
			{
				e.getDrops().add(s);
			}*/
		}
		/*if(MobsHandler.getSkills(l) != null)
		{
			try {
				SkillHandler.executeSkillsOnDeath(MobsHandler.getSkills(l), e);
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
			}
		}*/
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void ModHit(EntityDamageByEntityEvent e)
	{
		if(e.getDamager() instanceof CraftItem)
		{
			return;
		}
		if(e.getDamager() instanceof TNTPrimed)
		{
			return;
		}

		Entity l = e.getEntity();
		if(e.getDamager() instanceof Arrow)
		{
			Arrow arrow = (Arrow)e.getDamager();
			if(l instanceof LivingEntity && l != null)
			{
				Mob mob = MobsHandler.getMob((LivingEntity)l);
				if(mob != null)
					mob.executeSkills();
				
				if(SpawnerHandler.getMobTemplet((LivingEntity)arrow.getShooter()) != null)
				{
					MobTemplet mt = SpawnerHandler.getMobTempletFromSpawner((LivingEntity)arrow.getShooter());
					e.setDamage((double)mt.damage/10);
				}
			}
		}
		else if(e.getDamager() instanceof Snowball)
		{
			Snowball snowball = (Snowball)e.getDamager();
			if(SpawnerHandler.getMobTemplet((LivingEntity)snowball.getShooter()) != null)
			{
				MobTemplet mt = SpawnerHandler.getMobTempletFromSpawner((LivingEntity)snowball.getShooter());
				e.setDamage((double)mt.damage/10);
				
				Mob mob = MobsHandler.getMob((LivingEntity)l);
				if(mob != null)
					mob.executeSkills();
			}
		}
		else if(e.getDamager() instanceof Fireball)
		{
			Fireball fireball = (Fireball)e.getDamager();
			if(SpawnerHandler.getMobTemplet((LivingEntity)fireball.getShooter()) != null)
			{
				MobTemplet mt = SpawnerHandler.getMobTempletFromSpawner((LivingEntity)fireball.getShooter());
				e.setDamage((double)mt.damage/10);
				
				Mob mob = MobsHandler.getMob((LivingEntity)l);
				if(mob != null)
					mob.executeSkills();
			}
		}
		else if(e.getDamager() instanceof LivingEntity)
		{
			LivingEntity damager = (LivingEntity)e.getDamager();
			
			Mob mob = MobsHandler.getMob((LivingEntity)l);
			if(mob != null)
				mob.executeSkills();
			
			if(SpawnerHandler.getMobTemplet(damager) != null)
			{
				MobTemplet mt = SpawnerHandler.getMobTempletFromSpawner(damager);
				if(mt != null)
				{
					e.setDamage((double)mt.damage/10.0);
				}
			}
		}
	}
	
	/*public MobTemplet getMobTempletFromSpawner(LivingEntity l){
		for(SpawnerPlace sign: MobLibrary.plugin.spawnerList){
			for(LivingEntity mob:sign.getMobsList()){
				if(mob == l){
					return getMobTempletFromCmdName(sign.getCmdMob());
				}
			}
		}
		return null;
	}

	public static SpawnerPlace getSpawner(LivingEntity l){
		for(SpawnerPlace sign: MobLibrary.plugin.spawnerList){
			for(LivingEntity mob:sign.getMobsList()){
				if(mob == l){
					return sign;
				}
			}
		}
		return null;
	}

	
	public static MobTemplet getMobTemplet(LivingEntity l)
	{
		for(MobTemplet mt : MobsHandler.getMobTemplets())
		{
			String name = mt.display;
			name = ChatColor.translateAlternateColorCodes('&', name);
			name = name.replace(" ", "_");

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
	
	public static MobTemplet getMobTempletFromCmdName(String cmdName)
	{
		for(MobTemplet mt : MobsHandler.getMobTemplets())
		{
			String name = mt.cmdName;
			if(cmdName != null)
			{
				if(cmdName.equals(name))
				{
					return mt;
				}
			}
		}
		return null;
	}*/
	
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

	


