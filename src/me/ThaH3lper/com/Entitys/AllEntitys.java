package me.ThaH3lper.com.Entitys;

import net.minecraft.server.v1_5_R3.World;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_5_R3.CraftWorld;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.Entitys.Custom.ModSkeleton;
import me.ThaH3lper.com.Entitys.Custom.ModZombie;

public class AllEntitys {

	MobLibrary ml;
	
	public AllEntitys(MobLibrary ml)
	{
		this.ml = ml;
	}
	
	public LivingEntity SpawnMob(String s, Location loc, double speed, int damage, int health, float aggro, float multi)
	{
		//spawnZombie
		if(s.equals("zombie"))
		{
			Zombie z = spawnZombie(loc, speed, damage, health, aggro, multi);
			return z;
		}
		else if(s.equals("babyzombie"))
		{
			Zombie z = spawnZombie(loc, speed, damage, health, aggro, multi);
			z.setBaby(true);
			return z;
		}
		else if(s.equals("villagezombie"))
		{
			Zombie z = spawnZombie(loc, speed, damage, health, aggro, multi);
			z.setVillager(true);
			return z;
		}
		else if(s.equals("babyvillagezombie"))
		{
			Zombie z = spawnZombie(loc, speed, damage, health, aggro, multi);
			z.setBaby(true);
			z.setVillager(true);
			return z;
		}
		//spawnSkeleton
		else if(s.equals("skeleton"))
		{
			Skeleton m = spawnSkeleton(loc, speed, damage, health, aggro, multi);
			return m;
		}
		else if(s.equals("skeletonwither"))
		{
			Skeleton m = spawnSkeleton(loc, speed, damage, health, aggro, multi);
			m.setSkeletonType(SkeletonType.WITHER);
			return m;
		}
		//none editables
		else if(s.equals("spider"))
		{
			Spider m = (Spider) loc.getWorld().spawnEntity(loc, EntityType.SPIDER);
			m.setMaxHealth(health);
			m.setHealth(m.getMaxHealth());
			return m;
		}
		else if(s.equals("cavespider"))
		{
			CaveSpider m = (CaveSpider) loc.getWorld().spawnEntity(loc, EntityType.CAVE_SPIDER);
			m.setMaxHealth(health);
			m.setHealth(m.getMaxHealth());
			return m;
		}
		else if(s.equals("slime"))
		{
			Slime m = (Slime) loc.getWorld().spawnEntity(loc, EntityType.SLIME);
			m.setMaxHealth(health);
			m.setHealth(m.getMaxHealth());
			m.setSize(1);
			return m;
		}
		else if(s.equals("magmacube"))
		{
			MagmaCube m = (MagmaCube) loc.getWorld().spawnEntity(loc, EntityType.MAGMA_CUBE);
			m.setMaxHealth(health);
			m.setHealth(m.getMaxHealth());
			m.setSize(1);
			return m;
		}
		else if(s.equals("pigmen"))
		{
			PigZombie m = (PigZombie) loc.getWorld().spawnEntity(loc, EntityType.PIG_ZOMBIE);
			m.setMaxHealth(health);
			m.setHealth(m.getMaxHealth());
			return m;
		}
		else if(s.equals("babypigmen"))
		{
			PigZombie m = (PigZombie) loc.getWorld().spawnEntity(loc, EntityType.PIG_ZOMBIE);
			m.setMaxHealth(health);
			m.setHealth(m.getMaxHealth());
			m.setBaby(true);
			return m;
		}
		else if(s.equals("blaze"))
		{
			Blaze m = (Blaze) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
			m.setMaxHealth(health);
			m.setHealth(m.getMaxHealth());
			return m;
		}
		else if(s.equals("bat"))
		{
			Bat m = (Bat) loc.getWorld().spawnEntity(loc, EntityType.BAT);
			m.setMaxHealth(health);
			m.setHealth(m.getMaxHealth());
			return m;
		}
		else if(s.equals("witch"))
		{
			Witch m = (Witch) loc.getWorld().spawnEntity(loc, EntityType.WITCH);
			m.setMaxHealth(health);
			m.setHealth(m.getMaxHealth());
			return m;
		}
		else if(s.equals("wolf"))
		{
			Wolf m = (Wolf) loc.getWorld().spawnEntity(loc, EntityType.WOLF);
			m.setMaxHealth(health);
			m.setHealth(m.getMaxHealth());
			m.setAngry(true);
			return m;
		}
		else if(s.equals("creeper"))
		{
			Creeper m = (Creeper) loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
			m.setMaxHealth(health);
			m.setHealth(m.getMaxHealth());
			return m;
		}
		else if(s.equals("ghast"))
		{
			Ghast m = (Ghast) loc.getWorld().spawnEntity(loc, EntityType.GHAST);
			m.setMaxHealth(health);
			m.setHealth(m.getMaxHealth());
			return m;
		}
		return null;
	}
	
	public Zombie spawnZombie(Location loc, double speed, int damage, int health, float aggro, float multi)
	{
	    ModZombie med = new ModZombie(((CraftWorld)loc.getWorld()).getHandle(), speed, damage, health, aggro, multi);
	    World mcWorld = ((CraftWorld)loc.getWorld()).getHandle();
	    med.setPosition(loc.getX(), loc.getY(), loc.getZ());
	    mcWorld.addEntity(med, SpawnReason.CUSTOM);
	    return (Zombie) med.getBukkitEntity();
	}
	
	public Skeleton spawnSkeleton(Location loc, double speed, int damage, int health, float aggro, float multi)
	{
	    ModSkeleton med = new ModSkeleton(((CraftWorld)loc.getWorld()).getHandle(), speed, damage, health, aggro, multi);
	    World mcWorld = ((CraftWorld)loc.getWorld()).getHandle();
	    med.setPosition(loc.getX(), loc.getY(), loc.getZ());
	    mcWorld.addEntity(med, SpawnReason.CUSTOM);
	    return (Skeleton) med.getBukkitEntity();
	}
	
	/*public Spider spawnSpider(Location loc, int damage, int health, float multi)
	{
	    //ModSpider med = new ModSpider(((CraftWorld)loc.getWorld()).getHandle(), "null", damage, health, multi);
	    ModSpider med = new ModSpider(((CraftWorld)loc.getWorld()).getHandle());
	    World mcWorld = ((CraftWorld)loc.getWorld()).getHandle();
	    med.setPosition(loc.getX(), loc.getY(), loc.getZ());
	    mcWorld.addEntity(med, SpawnReason.CUSTOM);
	    return (Spider) med.getBukkitEntity();
	}*/
}
