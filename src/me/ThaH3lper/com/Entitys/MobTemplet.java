package me.ThaH3lper.com.Entitys;

import java.util.Iterator;
import java.util.List;

import me.ThaH3lper.com.Items.ItemHandler;
import me.ThaH3lper.com.utils.ItemUtils;
import me.frodenkvist.armoreditor.Store;

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
import org.bukkit.inventory.ItemStack;


public class MobTemplet
{
	private String cmdName;
	private String mob;
	private String display;
	//public double speed;
	private int health;
	private int damage;
	//public float aggro;
	private boolean despawn;
	private List<String> equip;
	private List<String> drops;
	private List<String> skills;
	private boolean epicImmune;
	private boolean arrowImmune;
	private boolean deathBroadcast;
	
	public MobTemplet(String cmdName, String mob, String display, int health, int damage, boolean despawn, List<String> equip, List<String> drops, List<String> skills, boolean epicImmune, boolean arrowImmune,
			boolean deathBroadcast)
	{
		this.cmdName = cmdName;
		this.mob = mob;
		this.display = ItemUtils.addChatColor(display);
		//this.speed = speed;
		this.health = health;
		this.damage = damage;
		//this.aggro = aggro;
		this.despawn = despawn;
		this.equip = equip;
		this.drops = drops;
		this.skills = skills;
		this.epicImmune = epicImmune;
		this.arrowImmune = arrowImmune;
		this.deathBroadcast = deathBroadcast;
	}
	
	public Mob spawn(Location loc)
	{
		LivingEntity le = spawnEntity(loc, mob);
		le.setCustomName(display);
		le.setCustomNameVisible(true);
		le.setMaxHealth(health);
		le.setHealth(health);
		le.setRemoveWhenFarAway(despawn);
		setEquipment(le, equip);
		return new Mob(le, damage, display, drops, skills, epicImmune, arrowImmune, deathBroadcast);
	}
	
	private LivingEntity spawnEntity(Location loc, String s)
	{
		if(s.equals("zombie"))
		{
			Zombie m = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			return m;
		}
		else if(s.equals("babyzombie"))
		{
			Zombie m = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			m.setBaby(true);
			return m;
		}
		else if(s.equals("villagezombie"))
		{
			Zombie m = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			m.setVillager(true);
			return m;
		}
		else if(s.equals("babyvillagezombie"))
		{
			Zombie m = (Zombie) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			m.setBaby(true);
			m.setVillager(true);
			return m;
		}
		//spawnSkeleton
		else if(s.equals("skeleton"))
		{
			Skeleton m = (Skeleton) loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
			return m;
		}
		else if(s.equals("skeletonwither"))
		{
			Skeleton m = (Skeleton) loc.getWorld().spawnEntity(loc, EntityType.SKELETON);
			m.setSkeletonType(SkeletonType.WITHER);
			return m;
		}
		//none editables
		else if(s.equals("spider"))
		{
			Spider m = (Spider) loc.getWorld().spawnEntity(loc, EntityType.SPIDER);
			return m;
		}
		else if(s.equals("cavespider"))
		{
			CaveSpider m = (CaveSpider) loc.getWorld().spawnEntity(loc, EntityType.CAVE_SPIDER);
			return m;
		}
		else if(s.equals("slime"))
		{
			Slime m = (Slime) loc.getWorld().spawnEntity(loc, EntityType.SLIME);
			m.setSize(1);
			return m;
		}
		else if(s.equals("magmacube"))
		{
			MagmaCube m = (MagmaCube) loc.getWorld().spawnEntity(loc, EntityType.MAGMA_CUBE);
			m.setSize(1);
			return m;
		}
		else if(s.equals("pigmen"))
		{
			PigZombie m = (PigZombie) loc.getWorld().spawnEntity(loc, EntityType.PIG_ZOMBIE);
			m.setAngry(true);
			return m;
		}
		else if(s.equals("babypigmen"))
		{
			PigZombie m = (PigZombie) loc.getWorld().spawnEntity(loc, EntityType.PIG_ZOMBIE);
			m.setBaby(true);
			m.setAngry(true);
			return m;
		}
		else if(s.equals("blaze"))
		{
			Blaze m = (Blaze) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
			return m;
		}
		else if(s.equals("bat"))
		{
			Bat m = (Bat) loc.getWorld().spawnEntity(loc, EntityType.BAT);
			return m;
		}
		else if(s.equals("witch"))
		{
			Witch m = (Witch) loc.getWorld().spawnEntity(loc, EntityType.WITCH);
			return m;
		}
		else if(s.equals("wolf"))
		{
			Wolf m = (Wolf) loc.getWorld().spawnEntity(loc, EntityType.WOLF);
			m.setAngry(true);
			return m;
		}
		else if(s.equals("creeper"))
		{
			Creeper m = (Creeper) loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
			return m;
		}
		else if(s.equals("ghast"))
		{
			Ghast m = (Ghast) loc.getWorld().spawnEntity(loc, EntityType.GHAST);
			return m;
		}
		else if(s.equals("zombie"))
		{
			Ghast m = (Ghast) loc.getWorld().spawnEntity(loc, EntityType.ZOMBIE);
			return m;
		}
		else if(s.equals("enderman"))
		{
			Enderman m = (Enderman) loc.getWorld().spawnEntity(loc, EntityType.ENDERMAN);
			return m;
		}
		else if(s.equals("enderdragon"))
		{
			EnderDragon m = (EnderDragon) loc.getWorld().spawnEntity(loc, EntityType.ENDER_DRAGON);
			return m;
		}
		else if(s.equals("wither"))
		{
			Wither m = (Wither) loc.getWorld().spawnEntity(loc, EntityType.WITHER);
			return m;
		}
		else if(s.equals("ocelot"))
		{
			Ocelot m = (Ocelot) loc.getWorld().spawnEntity(loc, EntityType.OCELOT);
			m.setAdult();
			return m;
		}
		else if(s.equals("babyocelot"))
		{
			Ocelot m = (Ocelot) loc.getWorld().spawnEntity(loc, EntityType.OCELOT);
			m.setBaby();
			return m;
		}
		else if(s.equals("horse"))
		{
			Horse m = (Horse) loc.getWorld().spawnEntity(loc, EntityType.HORSE);
			m.setAdult();
			return m;
		}
		else if(s.equals("babyhorse"))
		{
			Horse m = (Horse) loc.getWorld().spawnEntity(loc, EntityType.HORSE);
			m.setBaby();
			return m;
		}
		else if(s.equals("chicken"))
		{
			Chicken m = (Chicken) loc.getWorld().spawnEntity(loc, EntityType.CHICKEN);
			m.setAdult();
			return m;
		}
		else if(s.equals("babychicken"))
		{
			Chicken m = (Chicken) loc.getWorld().spawnEntity(loc, EntityType.CHICKEN);
			m.setBaby();
			return m;
		}	
		else if(s.equals("cow"))
		{
			Cow m = (Cow) loc.getWorld().spawnEntity(loc, EntityType.COW);
			m.setAdult();
			return m;
		}		
		else if(s.equals("babycow"))
		{
			Cow m = (Cow) loc.getWorld().spawnEntity(loc, EntityType.COW);
			m.setBaby();
			return m;
		}	
		else if(s.equals("mushroomcow"))
		{
			MushroomCow m = (MushroomCow) loc.getWorld().spawnEntity(loc, EntityType.MUSHROOM_COW);
			m.setAdult();
			return m;
		}		
		else if(s.equals("babymushroomcow"))
		{
			MushroomCow m = (MushroomCow) loc.getWorld().spawnEntity(loc, EntityType.MUSHROOM_COW);
			m.setBaby();
			return m;
		}
		else if(s.equals("sheep"))
		{
			Sheep m = (Sheep) loc.getWorld().spawnEntity(loc, EntityType.SHEEP);
			m.setAdult();
			return m;
		}		
		else if(s.equals("babysheep"))
		{
			Sheep m = (Sheep) loc.getWorld().spawnEntity(loc, EntityType.SHEEP);
			m.setBaby();
			return m;
		}
		else if(s.equals("squid"))
		{
			Squid m = (Squid) loc.getWorld().spawnEntity(loc, EntityType.SQUID);
			m.setRemainingAir(1000);
			return m;
		}
		else if(s.equals("giant"))
		{
			Giant m = (Giant) loc.getWorld().spawnEntity(loc, EntityType.GIANT);
			return m;
		}
		else if(s.equals("irongolem"))
		{
			IronGolem m = (IronGolem) loc.getWorld().spawnEntity(loc, EntityType.IRON_GOLEM);
			return m;
		}
		else if(s.equals("pig"))
		{
			Pig m = (Pig) loc.getWorld().spawnEntity(loc, EntityType.PIG);
			return m;
		}
		else if(s.equals("villager"))
		{
			Villager m = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
			m.setAdult();
			return m;
		}
		else if(s.equals("babyvillager"))
		{
			Villager m = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
			m.setBaby();
			return m;
		}
		else if(s.equals("snowman"))
		{
			Snowman m = (Snowman) loc.getWorld().spawnEntity(loc, EntityType.SNOWMAN);
			return m;
		}
		else if(s.equals("silverfish"))
		{
			Silverfish m = (Silverfish) loc.getWorld().spawnEntity(loc, EntityType.SILVERFISH);
			return m;
		}
		return null;
	}
	
	private void setEquipment(LivingEntity l, List<String> items)
	{
		Iterator<String> itr = items.iterator();
		while(itr.hasNext())
		{
			ItemStack is = null;
			String s = itr.next();
			String[] split = s.split(" ");
			if(s.contains(":"))
			{
				String[] parts = split[0].split(":");
				is = new ItemStack(Integer.valueOf(parts[0]),0,Short.valueOf(parts[1]));
			}
			else
			{
				if(isInteger(split[0]))
				{
					is = new ItemStack(Integer.valueOf(split[0]));
				}
				else
				{
					is = ItemHandler.getItem(split[0]);
					if(is == null)
						is = Store.getItem(split[0]);
				}
			}
			if(split[1].equals("0"))
			{
				l.getEquipment().setItemInHand(is);
				l.getEquipment().setItemInHandDropChance(0f);
			}
			else if(split[1].equals("4"))
			{
				l.getEquipment().setHelmet(is);
				l.getEquipment().setHelmetDropChance(0f);
			}
			else if(split[1].equals("3"))
			{
				l.getEquipment().setChestplate(is);
				l.getEquipment().setChestplateDropChance(0f);
			}
			else if(split[1].equals("2"))
			{
				l.getEquipment().setLeggings(is);
				l.getEquipment().setLeggingsDropChance(0f);
			}
			else if(split[1].equals("1"))
			{
				l.getEquipment().setBoots(is);
				l.getEquipment().setBootsDropChance(0f);
			}
		}
	}
	
	private boolean isInteger(String s)
	{
		try
		{
			Integer.valueOf(s);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public String getCmdName()
	{
		return cmdName;
	}
}
