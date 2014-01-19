package me.ThaH3lper.com.Skills;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import me.ThaH3lper.com.Effects.FireWorkEffect;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Shuffle extends Skill
{
	private int radius;
	private FireWorkEffect fplayer = new FireWorkEffect();
	
	public Shuffle(double chance, int radius)
	{
		super(chance);
		this.radius = radius;
	}
	
	public void playSkill(LivingEntity caster)
	{
		List<Player> list = SkillHandler.getPlayers(radius, caster);
		if(list.isEmpty())
			return;
		
		List<Location> locs = new ArrayList<Location>(list.size());
		for(Player p : list)
		{
			locs.add(p.getLocation());
		}
		FireworkEffect fe = FireworkEffect.builder().withColor(Color.PURPLE).withFade(Color.BLUE).with(Type.BURST).build();
		Random rand = new Random();
		Iterator<Player> itr = list.iterator();
		while(itr.hasNext())
		{
			Player temp = itr.next();
			try
			{
				fplayer.playFirework(temp.getWorld(), temp.getLocation(), fe);
			}
			catch(Exception e)
			{
			}
			int r = rand.nextInt(locs.size());
			temp.teleport(locs.get(r));
			temp.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,10*20,1));
			locs.remove(r);
		}
	}
}