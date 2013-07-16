package me.ThaH3lper.com.Entitys;

//SYNCING!

import org.bukkit.Location;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Horse;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.Skeleton.SkeletonType;

import me.ThaH3lper.com.MobLibrary;

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
			Zombie m = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			return m;
		}
		else if(s.equals("babyzombie"))
		{
			Zombie m = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			m.setBaby(true);
			return m;
		}
		else if(s.equals("villagezombie"))
		{
			Zombie m = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			m.setVillager(true);
			return m;
		}
		else if(s.equals("babyvillagezombie"))
		{
			Zombie m = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			m.setBaby(true);
			m.setVillager(true);
			return m;
		}
		//spawnSkeleton
		else if(s.equals("skeleton"))
		{
			Skeleton m = (Skeleton) loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			return m;
		}
		else if(s.equals("skeletonwither"))
		{
			Skeleton m = (Skeleton) loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			m.setSkeletonType(SkeletonType.WITHER);
			return m;
		}
		//none editables
		else if(s.equals("spider"))
		{
			Spider m = (Spider) loc.getWorld().spawnEntity(loc, EntityType.SPIDER);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			return m;
		}
		else if(s.equals("cavespider"))
		{
			CaveSpider m = (CaveSpider) loc.getWorld().spawnEntity(loc, EntityType.CAVE_SPIDER);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			return m;
		}
		else if(s.equals("slime"))
		{
			Slime m = (Slime) loc.getWorld().spawnEntity(loc, EntityType.SLIME);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			m.setSize(1);
			return m;
		}
		else if(s.equals("magmacube"))
		{
			MagmaCube m = (MagmaCube) loc.getWorld().spawnEntity(loc, EntityType.MAGMA_CUBE);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			m.setSize(1);
			return m;
		}
		else if(s.equals("pigmen"))
		{
			PigZombie m = (PigZombie) loc.getWorld().spawnEntity(loc, EntityType.PIG_ZOMBIE);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			m.setAngry(true);
			return m;
		}
		else if(s.equals("babypigmen"))
		{
			PigZombie m = (PigZombie) loc.getWorld().spawnEntity(loc, EntityType.PIG_ZOMBIE);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			m.setBaby(true);
			m.setAngry(true);
			return m;
		}
		else if(s.equals("blaze"))
		{
			Blaze m = (Blaze) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			return m;
		}
		else if(s.equals("bat"))
		{
			Bat m = (Bat) loc.getWorld().spawnEntity(loc, EntityType.BAT);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			return m;
		}
		else if(s.equals("witch"))
		{
			Witch m = (Witch) loc.getWorld().spawnEntity(loc, EntityType.WITCH);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			return m;
		}
		else if(s.equals("wolf"))
		{
			Wolf m = (Wolf) loc.getWorld().spawnEntity(loc, EntityType.WOLF);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			m.setAngry(true);
			return m;
		}
		else if(s.equals("creeper"))
		{
			Creeper m = (Creeper) loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			return m;
		}
		else if(s.equals("ghast"))
		{
			Ghast m = (Ghast) loc.getWorld().spawnEntity(loc, EntityType.GHAST);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			return m;
		}
		else if(s.equals("zombie"))
		{
			Ghast m = (Ghast) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			return m;
		}
		else if(s.equals("enderman"))
		{
			Enderman m = (Enderman) loc.getWorld().spawnEntity(loc, EntityType.ENDERMAN);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			return m;
		}
		else if(s.equals("enderdragon"))
		{
			EnderDragon m = (EnderDragon) loc.getWorld().spawnEntity(loc, EntityType.ENDER_DRAGON);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			return m;
		}
		else if(s.equals("wither"))
		{
			Wither m = (Wither) loc.getWorld().spawnEntity(loc, EntityType.WITHER);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			return m;
		}
		else if(s.equals("ocelot"))
		{
			Ocelot m = (Ocelot) loc.getWorld().spawnEntity(loc, EntityType.OCELOT);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			m.setAdult();
			return m;
		}
		else if(s.equals("babyocelot"))
		{
			Ocelot m = (Ocelot) loc.getWorld().spawnEntity(loc, EntityType.OCELOT);
			setMobMaxHealth(m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed(m,speed);
			setMobDamage(m ,damage);
			m.setBaby();
			return m;
		}
		else if(s.equals("horse"))
		{
			Horse m = (Horse) loc.getWorld().spawnEntity(loc, EntityType.HORSE);
			setMobMaxHealth( m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			m.setAdult();
			return m;
		}
		else if(s.equals("babyhorse"))
		{
			Horse m = (Horse) loc.getWorld().spawnEntity(loc, EntityType.HORSE);
			setMobMaxHealth( m, (float)health);
			setMobHealth(m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			m.setBaby();
			return m;
		}
		else if(s.equals("chicken"))
		{
			Chicken m = (Chicken) loc.getWorld().spawnEntity(loc, EntityType.CHICKEN);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			m.setAdult();
			return m;
		}
		else if(s.equals("babychicken"))
		{
			Chicken m = (Chicken) loc.getWorld().spawnEntity(loc, EntityType.CHICKEN);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			m.setBaby();
			return m;
		}	
		else if(s.equals("cow"))
		{
			Cow m = (Cow) loc.getWorld().spawnEntity(loc, EntityType.COW);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			m.setAdult();
			return m;
		}		
		else if(s.equals("babycow"))
		{
			Cow m = (Cow) loc.getWorld().spawnEntity(loc, EntityType.COW);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			m.setBaby();
			return m;
		}	
		else if(s.equals("mushroomcow"))
		{
			MushroomCow m = (MushroomCow) loc.getWorld().spawnEntity(loc, EntityType.MUSHROOM_COW);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			m.setAdult();
			return m;
		}		
		else if(s.equals("babymushroomcow"))
		{
			MushroomCow m = (MushroomCow) loc.getWorld().spawnEntity(loc, EntityType.MUSHROOM_COW);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			m.setBaby();
			return m;
		}
		else if(s.equals("sheep"))
		{
			Sheep m = (Sheep) loc.getWorld().spawnEntity(loc, EntityType.SHEEP);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			m.setAdult();
			return m;
		}		
		else if(s.equals("babysheep"))
		{
			Sheep m = (Sheep) loc.getWorld().spawnEntity(loc, EntityType.SHEEP);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			m.setBaby();
			return m;
		}
		else if(s.equals("squid"))
		{
			Squid m = (Squid) loc.getWorld().spawnEntity(loc, EntityType.SQUID);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			m.setRemainingAir(1000);
			return m;
		}
		else if(s.equals("giant"))
		{
			Giant m = (Giant) loc.getWorld().spawnEntity(loc, EntityType.GIANT);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			return m;
		}
		else if(s.equals("irongolem"))
		{
			IronGolem m = (IronGolem) loc.getWorld().spawnEntity(loc, EntityType.IRON_GOLEM);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			return m;
		}
		else if(s.equals("pig"))
		{
			Pig m = (Pig) loc.getWorld().spawnEntity(loc, EntityType.PIG);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			return m;
		}
		else if(s.equals("villager"))
		{
			Villager m = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			m.setAdult();
			return m;
		}
		else if(s.equals("babyvillager"))
		{
			Villager m = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			m.setBaby();
			return m;
		}
		else if(s.equals("snowman"))
		{
			Snowman m = (Snowman) loc.getWorld().spawnEntity(loc, EntityType.SNOWMAN);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			return m;
		}
		else if(s.equals("silverfish"))
		{
			Silverfish m = (Silverfish) loc.getWorld().spawnEntity(loc, EntityType.SILVERFISH);
			setMobMaxHealth( m, (float)health);
			setMobHealth( m , m.getMaxHealth());
			setMobSpeed( m,speed);
			setMobDamage( m ,damage);
			return m;
		}
		return null;
	}
	public static void setMobMaxHealth(LivingEntity mob , double maxHealth){
		mob.setMaxHealth(maxHealth);
	}
	public static void setMobHealth(LivingEntity mob , double health){
		mob.setHealth(health);
	}
	public static void setMobSpeed(LivingEntity mob , double speed){
		//mob.setVelocity(mob.getVelocity().multiply(speed));
	}
	public static void setMobDamage(LivingEntity mob, double damage){
		//mob.setLastDamage(damage);
	}
//	public Zombie spawnZombie(Location loc, double speed, int damage, int health, float aggro, float multi)
//	{
//	    ModZombie med = new ModZombie(((CraftWorld)loc.getWorld()).getHandle(), speed, damage, health, aggro, multi);
//	    World mcWorld = ((CraftWorld)loc.getWorld()).getHandle();
//	    med.setPosition(loc.getX(), loc.getY(), loc.getZ());
//	    mcWorld.addEntity(med, SpawnReason.CUSTOM);
//	    return (Zombie) med.getBukkitEntity();
//	}
	
//	public Skeleton spawnSkeleton(Location loc, double speed, int damage, int health, float aggro, float multi)
//	{
//	    ModSkeleton med = new ModSkeleton(((CraftWorld)loc.getWorld()).getHandle(), speed, damage, health, aggro, multi);
//	    World mcWorld = ((CraftWorld)loc.getWorld()).getHandle();
//	    med.setPosition(loc.getX(), loc.getY(), loc.getZ());
//	    mcWorld.addEntity(med, SpawnReason.CUSTOM);
//	    return (Skeleton) med.getBukkitEntity();
//	}
	
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
