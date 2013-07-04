package me.ThaH3lper.com.Entitys.Custom;

import java.lang.reflect.Field;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_5_R3.CraftServer;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftSkeleton;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftSpider;
import org.bukkit.craftbukkit.v1_5_R3.entity.CraftZombie;
import org.bukkit.plugin.Plugin;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.MobHandler.EntityNavigaton;
import me.ThaH3lper.com.MobHandler.ReflectionUtils;
import net.minecraft.server.v1_5_R3.Entity;
import net.minecraft.server.v1_5_R3.EntityHuman;
import net.minecraft.server.v1_5_R3.EntityLiving;
import net.minecraft.server.v1_5_R3.EntitySkeleton;
import net.minecraft.server.v1_5_R3.EntitySpider;
import net.minecraft.server.v1_5_R3.EntityVillager;
import net.minecraft.server.v1_5_R3.EntityZombie;
import net.minecraft.server.v1_5_R3.ItemStack;
import net.minecraft.server.v1_5_R3.PathfinderGoalBreakDoor;
import net.minecraft.server.v1_5_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_5_R3.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_5_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_5_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_5_R3.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.v1_5_R3.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_5_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_5_R3.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_5_R3.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_5_R3.World;



public class ModSkeleton extends EntitySkeleton
{
  private MobLibrary plugin;
  int damage = 4;
  float aggro = 16;
  private String name;
  private double speed = 20.0D;
  String say = "mob.skeleton.say";
  String hurt = "mob.skeleton.hurt";
  String death = "mob.skeleton.death";
  String step = "mob.skeleton.step";

  public ModSkeleton(World world)
  {
    this(world, 0.25D, 10, 20, 16f, 1f);
  }

  @SuppressWarnings("rawtypes")
public ModSkeleton(World world, double speed, int damage, int health, Float aggro, float multi)
  {
    super(world);
    this.speed = speed;
    this.damage = (int)((damage - 1) * multi);
    this.health = (int)(health * multi);
    this.aggro = aggro;
    Plugin plugin = Bukkit.getPluginManager().getPlugin("MobLibrary");

    if ((plugin == null) || (!(plugin instanceof MobLibrary)))
    {
      this.world.removeEntity(this);
      return;
    }

    this.plugin = ((MobLibrary)plugin);

    this.bukkitEntity = new CraftSkeleton((CraftServer)this.plugin.getServer(), this);
    try
    {
      ReflectionUtils.setFieldValue(EntityLiving.class, "navigation", this, new EntityNavigaton(this, this.world, aggro, speed));

      Field goala = this.goalSelector.getClass().getDeclaredField("a");
      goala.setAccessible(true);
      ((List)goala.get(this.goalSelector)).clear();

      Field targeta = this.targetSelector.getClass().getDeclaredField("a");
      targeta.setAccessible(true);
      ((List)targeta.get(this.targetSelector)).clear();

      this.goalSelector.a(0, new PathfinderGoalFloat(this));
      this.goalSelector.a(1, new PathfinderGoalBreakDoor(this));
      this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, this.bH, false));
      this.goalSelector.a(3, new PathfinderGoalMeleeAttack(this, EntityVillager.class, this.bH, true));
      this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, this.bH));
      this.goalSelector.a(5, new PathfinderGoalMoveThroughVillage(this, this.bH, false));
      this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, this.bH));
      this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
      this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));

      this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
      this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, aggro, 0, true));
      this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, aggro, 0, false));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    this.name = name;
  }

  protected String bb()
  {
    return this.say;
  }

  protected String bc()
  {
    return this.hurt;
  }

  protected String bd()
  {
    return this.death;
  }

  protected void a(int i, int j, int k, int l)
  {
    makeSound(this.step, 0.15F, 1.0F);
  }

  public void setHurt(String hurt)
  {
    this.hurt = hurt;
  }

  public void setSay(String say)
  {
    this.say = say;
  }

  public void setDeath(String death)
  {
    this.death = death;
  }

  public void setStep(String step)
  {
    this.step = step;
  }

  public int c(Entity entity)
  {
    ItemStack itemstack = bG();
    int i = this.damage;

    if (itemstack != null)
    {
      i += itemstack.a((Entity)this);
    }

    makeSound("mob.irongolem.throw", 1.0F, 1.0F);

    return i;
  }

  public void setDamage(int value)
  {
    this.damage = value;
  }

  public int getDamage()
  {
    return this.damage;
  }

  public String getName()
  {
    return this.name;
  }

  public void setAgroRange(float aggro)
  {
    try
    {
      ReflectionUtils.setFieldValue(EntityLiving.class, "navigation", this, new EntityNavigaton(this, this.world, aggro, this.speed));

      Field goala = this.goalSelector.getClass().getDeclaredField("a");
      goala.setAccessible(true);
      ((List)goala.get(this.goalSelector)).clear();

      Field targeta = this.targetSelector.getClass().getDeclaredField("a");
      targeta.setAccessible(true);
      ((List)targeta.get(this.targetSelector)).clear();

      this.goalSelector.a(0, new PathfinderGoalFloat(this));
      this.goalSelector.a(1, new PathfinderGoalBreakDoor(this));
      this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, this.bH, false));
      this.goalSelector.a(3, new PathfinderGoalMeleeAttack(this, EntityVillager.class, this.bH, true));
      this.goalSelector.a(4, new PathfinderGoalMoveTowardsRestriction(this, this.bH));
      this.goalSelector.a(5, new PathfinderGoalMoveThroughVillage(this, this.bH, false));
      this.goalSelector.a(6, new PathfinderGoalRandomStroll(this, this.bH));
      this.goalSelector.a(7, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
      this.goalSelector.a(7, new PathfinderGoalRandomLookaround(this));

      this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, false));
      this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, aggro, 0, true));
      this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, aggro, 0, false));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void setHealth(int i)
  {
    this.health = i;
  }
}