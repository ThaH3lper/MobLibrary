package me.ThaH3lper.com.Skills;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class FireBall {
	
	public static void playEffect(LivingEntity entity)
	{
		spawnFire(entity, new Vector(10, 0, 0));
		spawnFire(entity, new Vector(0.5, 0, 0.5));
		spawnFire(entity, new Vector(1, 0, 0));
		spawnFire(entity, new Vector(0.5, 0, -0.5));
		spawnFire(entity, new Vector(0, 0, -1));
		spawnFire(entity, new Vector(-0.5, 0, -0.5));
		spawnFire(entity, new Vector(-1, 0, 0));
		spawnFire(entity, new Vector(-0.5, 0, 0.5));
	}
	public static void spawnFire(LivingEntity entity, Vector vector)
	{
		Location l = entity.getLocation();
		l.setX(l.getX() + (vector.getX()*10));
		l.setZ(l.getZ() + (vector.getZ()*10));
		Fireball ball = (Fireball) entity.getWorld().spawnEntity(l, EntityType.FIREBALL);
		ball.setBounce(false);
		ball.setIsIncendiary(false);
		ball.setYield(0);
		ball.setDirection(vector);
		ball.setVelocity(new Vector(0, 0, 0));
	}

}
