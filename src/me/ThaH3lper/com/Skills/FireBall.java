package me.ThaH3lper.com.Skills;

import java.util.List;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class FireBall extends Skill
{
	private int radius;
	
	public FireBall(double chance, int radius)
	{
		super(chance);
		this.radius = radius;
	}
	
	public void playSkill(final LivingEntity caster)
	{
		List<Player> list = SkillHandler.getPlayers(radius, caster);
		if(list.isEmpty())
			return;
		Random rand = new Random();
		Player target = list.get(rand.nextInt(list.size()));
		if(target == null)
			return;
		Location l = caster.getLocation();
		Fireball ball = (Fireball) caster.getWorld().spawnEntity(l, EntityType.FIREBALL);
		ball.setBounce(false);
		ball.setIsIncendiary(false);
		ball.setYield(1);
		ball.setDirection(target.getLocation().toVector().normalize());
		ball.setVelocity(target.getLocation().toVector().normalize());
	}
}
