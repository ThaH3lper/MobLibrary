package me.ThaH3lper.com;

import java.util.List;

import me.ThaH3lper.com.Entitys.MobTemplet;
import me.ThaH3lper.com.Entitys.MobsHandler;
import me.ThaH3lper.com.Skills.SkillHandler;
import me.ThaH3lper.com.Spawner.SpawnerPlace;

import org.bukkit.ChatColor;
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
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener 
{			
	@EventHandler(priority = EventPriority.LOWEST)
	public void ModDeath(EntityDeathEvent e)
	{
		LivingEntity l = e.getEntity();
		if(MobsHandler.getSpawnerFromMob(l) != null)
		{
			SpawnerPlace sign = MobsHandler.getSpawnerFromMob(l);
			e.getDrops().clear();
			MobTemplet mt = MobsHandler.getMobTempletFromCmdName(sign.getCmdMob());
			List<ItemStack> items = MobsHandler.getDrops(mt.drops);
			for(ItemStack s : items)
			{
				e.getDrops().add(s);
			}
			if(sign.adds.size() > 0){
				if(MobsHandler.getSpawnerFromMob(l).adds.contains(l) == true){
					MobsHandler.getSpawnerFromMob(l).adds.remove(l);
					e.getDrops().clear();
					MobsHandler.clearFromSkillLists(l);
					return;
				}
			}
		}
		if(MobsHandler.getSkills(l) != null)
		{
			try {
				SkillHandler.executeSkillsOnDeath(MobsHandler.getSkills(l), e);
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (Exception e1) {
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void ModHit(EntityDamageByEntityEvent e)
	{
		if(e.getDamager() instanceof CraftItem){
			return;
		}
		if(e.getDamager() instanceof TNTPrimed){
			return;
		}

		Entity l = e.getEntity();
		if(e.getDamager() instanceof Arrow){
			Arrow arrow = (Arrow)e.getDamager();
			if(l instanceof LivingEntity && l != null){
				if(MobsHandler.getSkills((LivingEntity)l) != null)
				{
					try {
						SkillHandler.executeSkills(MobsHandler.getSkills((LivingEntity)l), (LivingEntity)l);
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
					}
				}
				if(getMobTemplet((LivingEntity)arrow.getShooter()) != null){
					MobTemplet mt = getMobTempletFromSpawner((LivingEntity)arrow.getShooter());
					e.setDamage((double)mt.damage/10);
				}
			}
		}
		else if(e.getDamager() instanceof Snowball){
			Snowball snowball = (Snowball)e.getDamager();
			if(getMobTemplet((LivingEntity)snowball.getShooter()) != null){
				MobTemplet mt = getMobTempletFromSpawner((LivingEntity)snowball.getShooter());
				e.setDamage((double)mt.damage/10);
				if(MobsHandler.getSkills((LivingEntity)l) != null)
				{
					try {
						SkillHandler.executeSkills(MobsHandler.getSkills((LivingEntity)l), (LivingEntity)l);
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
					}
				}
			}
		}
		else if(e.getDamager() instanceof Fireball){
			Fireball fireball = (Fireball)e.getDamager();
			if(getMobTemplet((LivingEntity)fireball.getShooter()) != null){
				MobTemplet mt = getMobTempletFromSpawner((LivingEntity)fireball.getShooter());
				e.setDamage((double)mt.damage/10);
				if(MobsHandler.getSkills((LivingEntity)l) != null)
				{
					try {
						SkillHandler.executeSkills(MobsHandler.getSkills((LivingEntity)l), (LivingEntity)l);
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
					}
				}
			}
		}
		else if(e.getDamager() instanceof LivingEntity)
		{
			LivingEntity damager = (LivingEntity)e.getDamager();
			if(MobsHandler.getSkills((LivingEntity)l) != null)
			{
				try {
					SkillHandler.executeSkills(MobsHandler.getSkills((LivingEntity)l), (LivingEntity)l);
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
				}
			}
			if(getMobTemplet(damager) != null)
			{
				MobTemplet mt = getMobTempletFromSpawner(damager);
				if(mt != null){
					e.setDamage((double)mt.damage/10.0);
				}
			}
		}
	}
	
	public MobTemplet getMobTempletFromSpawner(LivingEntity l){
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

	


