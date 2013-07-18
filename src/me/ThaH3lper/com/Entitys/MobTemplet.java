package me.ThaH3lper.com.Entitys;

import java.util.List;

public class MobTemplet
{
	public final String cmdName;
	public final String mob;
	public final String display;
	public final double speed;
	public final int health;
	public final int damage;
	public final float aggro;
	public final boolean despawn;
	public final List<String> equip;
	public final List<String> drops;
	public final List<String> skills;
	
	public MobTemplet(String cmdName, String mob, String display, double speed, int health, int damage, float aggro, boolean despawn, List<String> equip, List<String> drops, List<String> skills)
	{
		this.cmdName = cmdName;
		this.mob = mob;
		this.display = display;
		this.speed = speed;
		this.health = health;
		this.damage = damage;
		this.aggro = aggro;
		this.despawn = despawn;
		this.equip = equip;
		this.drops = drops;
		this.skills = skills;
	}
}
