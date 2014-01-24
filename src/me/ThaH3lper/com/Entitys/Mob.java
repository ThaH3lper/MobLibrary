package me.ThaH3lper.com.Entitys;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import me.ThaH3lper.com.Items.ItemHandler;
import me.ThaH3lper.com.Skills.Detonate;
import me.ThaH3lper.com.Skills.DragIn;
import me.ThaH3lper.com.Skills.Enrage;
import me.ThaH3lper.com.Skills.FireStorm;
import me.ThaH3lper.com.Skills.HealthDepend;
import me.ThaH3lper.com.Skills.Ignite;
import me.ThaH3lper.com.Skills.Jockey;
import me.ThaH3lper.com.Skills.LightningStorm;
import me.ThaH3lper.com.Skills.Parry;
import me.ThaH3lper.com.Skills.Potion;
import me.ThaH3lper.com.Skills.Shuffle;
import me.ThaH3lper.com.Skills.Skill;
import me.ThaH3lper.com.Skills.SpawnMobs;
import me.ThaH3lper.com.Skills.Teleport;
import me.ThaH3lper.com.Skills.Tnt;
import me.ThaH3lper.com.Skills.Toss;
import me.ThaH3lper.com.Skills.UsableOnce;
import me.ThaH3lper.com.Skills.WitherStorm;
import me.frodenkvist.armoreditor.Store;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Mob
{
	private int damage;
	private String name;
	private List<String> drops = new ArrayList<String>();
	private LivingEntity entity;
	private List<Skill> skills = new ArrayList<Skill>();
	private List<Mob> adds = new ArrayList<Mob>();
	private List<Player> lootPlayers = new ArrayList<Player>();
	private int lootRecipiant;
	private int lastLootPlayerAdded;
	private Mob spawner;
	private boolean parry;
	private double lastDamageTime;
	private boolean hasBeenDamaged;
	private boolean epicImmune;
	private boolean arrowImmune;
	private boolean deathBroadcast;
	private int numberOfAdds;
	
	public Mob(LivingEntity entity, int damage, String name, List<String> drops, List<String> skills, boolean epicImmune, boolean arrowImmune, boolean deathBroadcast)
	{
		this.entity = entity;
		this.damage = damage;
		this.name = name;
		this.drops = drops;
		this.epicImmune = epicImmune;
		this.arrowImmune = arrowImmune;
		this.deathBroadcast = deathBroadcast;
		this.lootRecipiant = 0;
		this.lastLootPlayerAdded = 0;
		Iterator<String> itr = skills.iterator();
		while(itr.hasNext())
		{
			String s = itr.next();
			String[] split = s.split(" ");
			if(split.length <= 0)
				continue;
			if(split[0].equalsIgnoreCase("dragin"))
			{
				if(split.length != 3)
					continue;
				int radius = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new DragIn(chance, radius));
			}
			else if(split[0].equalsIgnoreCase("tnt"))
			{
				if(split.length != 3)
					continue;
				int radius = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new Tnt(chance, radius));
			}
			else if(split[0].equalsIgnoreCase("teleport"))
			{
				if(split.length != 3)
					continue;
				int radius = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new Teleport(chance, radius));
			}
			else if(split[0].equalsIgnoreCase("ignite"))
			{
				if(split.length != 4)
					continue;
				int radius = Integer.valueOf(split[1]);
				int duration = Integer.valueOf(split[2]);
				double chance = Double.valueOf(split[3]);
				this.skills.add(new Ignite(chance, radius, duration));
			}
			else if(split[0].equalsIgnoreCase("potion"))
			{
				if(split.length != 3)
					continue;
				String[] data = split[1].split(":");
				if(data.length != 4)
					continue;
				int radius = Integer.valueOf(data[3]);
				double chance = Double.valueOf(split[2]);
				PotionEffect potion = new PotionEffect(PotionEffectType.getByName(data[0]), Integer.parseInt(data[1]) * 20, Integer.parseInt(data[2]) - 1);
				this.skills.add(new Potion(chance, radius, potion));
			}
			else if(split[0].equalsIgnoreCase("toss"))
			{
				if(split.length != 4)
					continue;
				int radius = Integer.valueOf(split[1]);
				int power = Integer.valueOf(split[2]);
				double chance = Double.valueOf(split[3]);
				this.skills.add(new Toss(chance, radius, power));
			}
			else if(split[0].equalsIgnoreCase("spawn"))
			{
				if(split.length != 5)
					continue;
				String cmdName = split[1];
				int healthNeedToCast = Integer.valueOf(split[2]);
				int amount = Integer.valueOf(split[3]);
				double chance = Double.valueOf(split[4]);
				this.skills.add(new SpawnMobs(chance, cmdName, amount, healthNeedToCast));
			}
			else if(split[0].equalsIgnoreCase("firestorm"))
			{
				if(split.length != 3)
					continue;
				int radius = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new FireStorm(chance, radius));
			}
			else if(split[0].equalsIgnoreCase("lightningstorm"))
			{
				if(split.length != 3)
					continue;
				int radius = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new LightningStorm(chance, radius));
			}
			else if(split[0].equalsIgnoreCase("shuffle"))
			{
				if(split.length != 3)
					continue;
				int radius = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new Shuffle(chance, radius));
			}
			else if(split[0].equalsIgnoreCase("enrage"))
			{
				if(split.length != 3)
					continue;
				int duration = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new Enrage(chance, duration));
			}
			else if(split[0].equalsIgnoreCase("detonate"))
			{
				if(split.length != 5)
					continue;
				int radius = Integer.valueOf(split[1]);
				int dmg = Integer.valueOf(split[2]);
				int delay = Integer.valueOf(split[3]);
				double chance = Double.valueOf(split[4]);
				this.skills.add(new Detonate(chance, radius, dmg, delay));
			}
			else if(split[0].equalsIgnoreCase("witherstorm"))
			{
				if(split.length != 3)
					continue;
				int radius = Integer.valueOf(split[1]);
				double chance = Double.valueOf(split[2]);
				this.skills.add(new WitherStorm(chance, radius));
			}
			else if(split[0].equalsIgnoreCase("jockey"))
			{
				if(split.length != 4)
					continue;
				int radius = Integer.valueOf(split[1]);
				int mDamage = Integer.valueOf(split[2]);
				double chance = Double.valueOf(split[3]);
				this.skills.add(new Jockey(chance, radius, mDamage));
			}
			else if(split[0].equalsIgnoreCase("parry"))
			{
				if(split.length != 2)
					continue;
				double chance = Double.valueOf(split[1]);
				this.skills.add(new Parry(chance));
			}
		}
	}
	
	public boolean loadChunk()
	{
		Location loc = entity.getLocation();
		if(loc.getChunk().isLoaded())
			return false;
		return loc.getChunk().load();
	}
	
	public void executeSkills()
	{
		Iterator<Skill> itr = skills.iterator();
		while(itr.hasNext())
		{
			Skill temp = itr.next();
			if(temp instanceof UsableOnce)
			{
				if(((UsableOnce) temp).hasUsed())
					continue;
			}
			if(temp instanceof HealthDepend)
			{
				if(((HealthDepend) temp).getHealthNeedToCast() < entity.getHealth())
					continue;
			}
			if(Math.random() > temp.getChance())
				continue;
			try
			{
				temp.playSkill(entity);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void dropLoot()
	{
		for(Player looter: this.lootPlayers){
			if(!(this.entity.getNearbyEntities(35, 35, 35).contains(looter))){
				looter.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "YOU ARE OUT OF RANGE AND CANNOT RECEIVE LOOT!");
				this.lootPlayers.remove(looter);
			}
		}
		Iterator<String> itr = drops.iterator();
		while(itr.hasNext())
		{
			String s = itr.next();
			String[] parts = s.split(" ");
			if(s.contains(":"))
			{
				String[] splits = parts[0].split(":");
				ItemStack stack = new ItemStack(Material.getMaterial(Integer.valueOf(splits[0])), Integer.valueOf(splits[2]), Short.valueOf(splits[1]));
				if(Math.random() > Double.valueOf(parts[1]))
					continue;
				Player looter = this.getLootRecipent();
				if(looter != null){
					int inventory = looter.getInventory().firstEmpty();
					if(inventory != -1){
						looter.getInventory().addItem(stack);
						looter.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "LOOT REWARDED! CHECK YOUR INVENTORY!");
					}
					else{
						entity.getWorld().dropItemNaturally(entity.getLocation(), stack);
						looter.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "OH NO! YOUR INVENTORY WAS FULL LOOT DROPED ON GROUND!" + stack.getType().toString());
					}
				}
				else{
					entity.getWorld().dropItemNaturally(entity.getLocation(), stack);
				}
			}
			else
			{
				ItemStack stack = ItemHandler.getItem(parts[0]);
				if(stack == null)
					stack = Store.getItem(parts[0]);
				if(stack == null)
					continue;
				if(Math.random() > Double.valueOf(parts[1]))
					continue;
				Player looter = this.getLootRecipent();
				if(looter != null){
					int inventory = looter.getInventory().firstEmpty();
					if(inventory != -1){
						looter.getInventory().addItem(stack);
						looter.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "LOOT REWARDED! CHECK YOUR INVENTORY!");
					}
					else{
						entity.getWorld().dropItemNaturally(entity.getLocation(), stack);
						looter.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "OH NO! YOUR INVENTORY WAS FULL LOOT DROPED ON GROUND!" + stack.getType().toString());
					}
				}
				else{
					entity.getWorld().dropItemNaturally(entity.getLocation(), stack);
				}
			}
			this.lootRecipiant++;
		}
	}
	
	public void addAdds(Mob add)
	{
		adds.add(add);
	}
	
	public void clearAdds()
	{
		Iterator<Mob> itr = adds.iterator();
		while(itr.hasNext())
		{
			Mob add = itr.next();
			if(add == null)
			{
				itr.remove();
				continue;
			}
			add.remove();
			itr.remove();
		}
		adds.clear();
	}
	
	public void remove()
	{
		
		clearAdds();
		if(entity != null)
			entity.remove();
	}
	
	public void setCustomName(String customName)
	{
		entity.setCustomName(customName);
	}

	public void setCustomName()
	{
		entity.setCustomName(name);
		Iterator<Mob> itr = adds.iterator();
		while(itr.hasNext())
		{
			itr.next().setCustomName();
		}
	}
	public int getAddCount(){
		int checktotal = 0;
		for(Mob check:this.adds){
			if(check.getHealth() >= 1){
				checktotal++;
			}
		}
		return checktotal;
	}
	public void resetMob()
	{
		this.lootPlayers.clear();
		if((new Date().getTime() - lastDamageTime) < 30000)
			return;
		if(adds.size() > 0 && (new Date().getTime() - lastDamageTime) < 300000){
		    return;
		}
		entity.setHealth(entity.getMaxHealth());
		if(!hasBeenDamaged)
			return;
		Iterator<Skill> sItr = skills.iterator();
		while(sItr.hasNext())
		{
			Skill skill = sItr.next();
			if(skill instanceof UsableOnce)
			{
				((UsableOnce) skill).setUsed(false);
			}
		}
		Iterator<Mob> mItr = adds.iterator();
		while(mItr.hasNext())
		{
			mItr.next().remove();
			mItr.remove();
		}
		hasBeenDamaged = false;
	}
	
	public boolean isDead()
	{
		return entity.isDead();
	}
	
	public double getHealth()
	{
		return entity.getHealth();
	}
	
	public Player getKiller()
	{
		return entity.getKiller();
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public boolean hasSkills()
	{
		return !skills.isEmpty();
	}
	
	public LivingEntity getEntity()
	{
		return entity;
	}
	
	public boolean isValid()
	{
		return entity.isValid();
	}
	
	public String getName()
	{
		return name;
	}
	
	public Location getLocation()
	{
		return entity.getLocation();
	}
	
	public boolean isParrying()
	{
		return parry;
	}
	
	public void setParrying(boolean value)
	{
		parry = value;
	}
	
	public void setSpawner(Mob mob)
	{
		spawner = mob;
	}
	
	public Mob getSpawner()
	{
		return spawner;
	}
	
	public boolean isEpicImmune()
	{
		return epicImmune;
	}
	
	public boolean isArrowImmune()
	{
		return arrowImmune;
	}
	
	public boolean hasDeathBroadcast()
	{
		return deathBroadcast;
	}
	
	public void setLastDamageTime()
	{
		double time = new Date().getTime();
		if(spawner == null)
		{
			lastDamageTime = time;
			hasBeenDamaged = true;
			if(adds.size() <= 0)
				return;
			Iterator<Mob> itr = adds.iterator();
			while(itr.hasNext())
			{
				itr.next().setLastDamageTime(time);
			}
		}
		else
		{
			spawner.setLastDamageTime();
		}
	}
	
	public void setLastDamageTime(double time)
	{
		lastDamageTime = time;
		hasBeenDamaged = true;
		if(adds.size() <= 0)
			return;
		Iterator<Mob> itr = adds.iterator();
		while(itr.hasNext())
		{
			itr.next().setLastDamageTime(time);
		}
	}
	public void addLootPlayer(Player player)
	{
		if(this.lootPlayers.contains(player)){
			return;
		}
		else{
			this.lootPlayers.add(player);
		}
	}
	public Player getLootRecipent(){
		if(this.lootRecipiant > this.lootPlayers.size() - 1){
			this.lootRecipiant = 0;
		}
		return this.lootPlayers.get(lootRecipiant);

	}

}
