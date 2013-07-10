/*package me.ThaH3lper.com.MobHandler;

import org.bukkit.Bukkit;

import net.minecraft.server.v1_5_R3.EntityLiving;
import net.minecraft.server.v1_5_R3.MathHelper;
import net.minecraft.server.v1_5_R3.Navigation;
import net.minecraft.server.v1_5_R3.PathEntity;
import net.minecraft.server.v1_5_R3.World;

public class EntityNavigaton extends Navigation
{
  double speedMod = 1.0D;

  public EntityNavigaton(EntityLiving entity, World world, float f)
  {
    super(entity, world, f);
  }

  public EntityNavigaton(EntityLiving entity, World world, float f, double speedMod)
  {
    super(entity, world, f);
    this.speedMod = speedMod;
  }

  public boolean a(double d0, double d1, double d2, float f)
  {
    PathEntity pathentity = a(MathHelper.floor(d0), (int)d1, MathHelper.floor(d2));

    return a(pathentity, f);
  }

  public boolean a(EntityLiving entityliving, float f)
  {
    PathEntity pathentity = a(entityliving);
    return pathentity != null ? a(pathentity, f) : false;
  }

  public boolean a(PathEntity path, float speed)
  {
    speed = (float)(speedMod);
    return super.a(path, speed);
  }
}
*/