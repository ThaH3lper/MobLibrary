package me.ThaH3lper.com.Entitys;

import java.util.List;

public class MobTemplet {
	public String cmdName;
	public String mob;
	public String display;
	public double speed;
	public int health;
	public int damage;
	public float aggro;
	public boolean despawn;
	public List<String> equip;
	public List<String> drops;
	public List<String> skills;
	
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
