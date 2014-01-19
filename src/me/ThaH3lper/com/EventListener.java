package me.ThaH3lper.com;

import java.util.Random;

import me.ThaH3lper.com.Entitys.Mob;
import me.ThaH3lper.com.Entitys.MobsHandler;
import me.ThaH3lper.com.Skills.SkillHandler;
import me.ThaH3lper.com.Spawner.SpawnerHandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R1.entity.CraftItem;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.util.Vector;

import PvpBalance.PvpHandler;

public class EventListener implements Listener 
{			
	@EventHandler(priority = EventPriority.LOWEST)
	public void ModDeath(EntityDeathEvent e)
	{
		LivingEntity l = e.getEntity();
		Mob mob = MobsHandler.getMob(l);
		if(mob == null)
			return;
		e.getDrops().clear();
		mob.dropLoot();
		mob.clearAdds();
		if(mob.hasDeathBroadcast())
		{
			Player killer = l.getKiller();
			if(killer == null)
				return;
			Bukkit.broadcastMessage(killer.getName() + ChatColor.YELLOW + " Has Slain " + ChatColor.DARK_PURPLE + mob.getName());
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void ModHit(EntityDamageByEntityEvent e)
	{
		//if(e.getDamager() instanceof CraftItem){
		//	return;
		//}
		if(e.getDamager() instanceof TNTPrimed){
			return;
		}

		Entity l = e.getEntity();
		if(e.getDamager() instanceof Arrow)
		{
			if(l == null)
				return;
			if(!(l instanceof LivingEntity))
				return;
			Mob mob = MobsHandler.getMob((LivingEntity)l);
			if(mob == null)
				return;
			if(mob.isArrowImmune())
			{
				e.getDamager().remove();
				e.setCancelled(true);
				return;
			}
			Projectile p = (Arrow)e.getDamager();
			if(p.getShooter() instanceof Player){
				Player shooter = (Player) p.getShooter();
				mob.addLootPlayer(shooter);
				if(mob.getAddCount()> 0){
					shooter.sendMessage(ChatColor.RED + "<<<YOU MUST KILL ALL ADDS BEFORE DOING MORE DAMAGE TO BOSS!>>>");
					e.setCancelled(true);
					return;
				}
				Random rand = new Random();
				if(rand.nextBoolean() == true && rand.nextBoolean()== true){
					mob.getEntity().teleport(shooter.getLocation());
					shooter.damage(mob.getDamage());
					shooter.sendMessage("<"+mob.getName()+ ChatColor.WHITE + "> :"+ ChatColor.RED + " Has retaliated against your ranged attack!");
				}
			}
			mob.setLastDamageTime();
			if(mob.hasSkills())
			{
				if(mob.getEntity().getNoDamageTicks() <= 15)
					mob.executeSkills();
			}
		}
		else if(e.getDamager() instanceof Snowball)
		{
			Snowball snowball = (Snowball)e.getDamager();
			
			LivingEntity shooter = (LivingEntity)snowball.getShooter();
			if(shooter == null)
				return;
			Mob mDamager = MobsHandler.getMob(shooter);
			if(mDamager == null)
				return;
			e.setDamage(mDamager.getDamage());
			if(l == null)
				return;
			if(!(l instanceof LivingEntity))
				return;
			Mob mob = MobsHandler.getMob((LivingEntity)l);
			if(mob == null)
				return;
			mob.setLastDamageTime();
			if(mob.hasSkills())
			{
				if(mob.getEntity().getNoDamageTicks() <= 15)
					mob.executeSkills();
			}
		}
		else if(e.getDamager() instanceof Fireball)
		{
			Fireball fireball = (Fireball)e.getDamager();
			LivingEntity shooter = (LivingEntity)fireball.getShooter();
			if(shooter == null)
				return;
			Mob mDamager = MobsHandler.getMob(shooter);
			if(mDamager == null)
				return;
			e.setDamage(mDamager.getDamage());
			if(l == null)
				return;
			if(!(l instanceof LivingEntity))
				return;
			Mob mob = MobsHandler.getMob((LivingEntity)l);
			if(mob == null)
				return;
			mob.setLastDamageTime();
			if(mob.hasSkills())
			{
				if(mob.getEntity().getNoDamageTicks() <= 15)
					mob.executeSkills();
			}
		}
		else if(e.getDamager() instanceof LivingEntity)
		{
			LivingEntity damager = (LivingEntity)e.getDamager();
			if(l == null)
				return;
			if(!(l instanceof LivingEntity))
				return;
			Mob mob = MobsHandler.getMob((LivingEntity)l);
			if(damager instanceof Player && mob != null){
				Player player = (Player) damager;
				if(mob.getAddCount()> 0){
					player.sendMessage(ChatColor.RED + "<<<YOU MUST KILL ALL ADDS BEFORE DOING MORE DAMAGE TO BOSS!>>>");
					e.setCancelled(true);
					return;
				}
				mob.addLootPlayer(player);
			}
			if(mob != null)
			{
				mob.setLastDamageTime();
				if(mob.isParrying())
				{
					if(e.getDamager() instanceof Player)
					{
						Player target = (Player)e.getDamager();
						PvpHandler.getPvpPlayer(target).damage(mob.getDamage());
						Vector v = SkillHandler.getTargetVector(mob.getEntity().getLocation(), target.getLocation());
						target.setVelocity(v.add(new Vector(0,1,0)));
						target.damage(0D, mob.getEntity());
						SkillHandler.message(30, mob.getEntity(), ChatColor.RED + "Parried " + ChatColor.AQUA + target.getName());
						e.setCancelled(true);
						return;
					}
				}
				if(mob.hasSkills())
				{
					if(mob.getEntity().getNoDamageTicks() <= 15)
						mob.executeSkills();
				}
			}
			Mob mDamager = MobsHandler.getMob(damager);
			if(mDamager == null)
				return;
			//MOB DAMAGE INFO
			e.setDamage(mDamager.getDamage()/15);
		}
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
	public void tameEvent(EntityTameEvent event)
	{
		LivingEntity tameTarget = event.getEntity();
		if(SpawnerHandler.getSpawner(tameTarget) != null)
		{
			event.setCancelled(true);
			if(tameTarget.getType() == EntityType.WOLF)
			{
				Wolf wolf = (Wolf)event.getEntity();
				wolf.setAngry(true);
			}
		}
	}
}

	


