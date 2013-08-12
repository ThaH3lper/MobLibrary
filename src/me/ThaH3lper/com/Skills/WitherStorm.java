package me.ThaH3lper.com.Skills;

import java.util.List;
import java.util.Random;

import me.frodenkvist.armoreditor.ArmorEditor;
import me.frodenkvist.armoreditor.FireworkEffectPlayer;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import PvpBalance.PvpHandler;

public class WitherStorm extends Skill
{
	private int radius;
	
	public WitherStorm(double chance, int radius)
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
		final Location center = target.getLocation();
		final FireworkEffectPlayer fplayer = new FireworkEffectPlayer();
		final Location start1 = new Location(center.getWorld(),center.getX()+7,center.getY()+1,center.getZ());
		final Location start2 = start1.clone();
		try
		{
			final FireworkEffect fe = FireworkEffect.builder().flicker(true).with(Type.BURST).withColor(Color.GRAY).withFade(Color.WHITE).trail(false).build();
			fplayer.playFirework(start1.getWorld(), start1, fe);
			Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						fplayer.playFirework(start1.getWorld(), start1.add(0, 0, 1), fe);
						fplayer.playFirework(start2.getWorld(), start2.add(0, 0, -1), fe);
						Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
						{
							@Override
							public void run()
							{
								try
								{
									fplayer.playFirework(start1.getWorld(), start1.add(0, 0, 1), fe);
									fplayer.playFirework(start2.getWorld(), start2.add(0, 0, -1), fe);
									Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
									{
										@Override
										public void run()
										{
											try
											{
												fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, 1), fe);
												fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, -1), fe);
												Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
												{
													@Override
													public void run()
													{
														try
														{
															fplayer.playFirework(start1.getWorld(), start1.add(0, 0, 1), fe);
															fplayer.playFirework(start2.getWorld(), start2.add(0, 0, -1), fe);
															Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
															{
																@Override
																public void run()
																{
																	try
																	{
																		fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, 1), fe);
																		fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, -1), fe);
																		Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																		{
																			@Override
																			public void run()
																			{
																				try
																				{
																					fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, 1), fe);
																					fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, -1), fe);
																					Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																					{
																						@Override
																						public void run()
																						{
																							try
																							{
																								fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, 0), fe);
																								fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, 0), fe);
																								Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																								{
																									@Override
																									public void run()
																									{
																										try
																										{
																											fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, 1), fe);
																											fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, -1), fe);
																											Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																											{
																												@Override
																												public void run()
																												{
																													try
																													{
																														fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, 0), fe);
																														fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, 0), fe);
																														Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																														{
																															@Override
																															public void run()
																															{
																																try
																																{
																																	fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, 0), fe);
																																	fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, 0), fe);
																																	Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																																	{
																																		@Override
																																		public void run()
																																		{
																																			try
																																			{
																																				fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, 0), fe);
																																				fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, 0), fe);
																																				Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																																				{
																																					@Override
																																					public void run()
																																					{
																																						try
																																						{
																																							fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, 0), fe);
																																							fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, 0), fe);
																																							Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																																							{
																																								@Override
																																								public void run()
																																								{
																																									try
																																									{
																																										fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, -1), fe);
																																										fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, 1), fe);
																																										Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																																										{
																																											@Override
																																											public void run()
																																											{
																																												try
																																												{
																																													fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, 0), fe);
																																													fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, 0), fe);
																																													Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																																													{
																																														@Override
																																														public void run()
																																														{
																																															try
																																															{
																																																fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, -1), fe);
																																																fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, 1), fe);
																																																Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																																																{
																																																	@Override
																																																	public void run()
																																																	{
																																																		try
																																																		{
																																																			fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, -1), fe);
																																																			fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, 1), fe);
																																																			Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																																																			{
																																																				@Override
																																																				public void run()
																																																				{
																																																					try
																																																					{
																																																						fplayer.playFirework(start1.getWorld(), start1.add(0, 0, -1), fe);
																																																						fplayer.playFirework(start2.getWorld(), start2.add(0, 0, 1), fe);
																																																						Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																																																						{
																																																							@Override
																																																							public void run()
																																																							{
																																																								try
																																																								{
																																																									fplayer.playFirework(start1.getWorld(), start1.add(-1, 0, -1), fe);
																																																									fplayer.playFirework(start2.getWorld(), start2.add(-1, 0, 1), fe);
																																																									Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																																																									{
																																																										@Override
																																																										public void run()
																																																										{
																																																											try
																																																											{
																																																												fplayer.playFirework(start1.getWorld(), start1.add(0, 0, -1), fe);
																																																												fplayer.playFirework(start2.getWorld(), start2.add(0, 0, 1), fe);
																																																												Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																																																												{
																																																													@Override
																																																													public void run()
																																																													{
																																																														try
																																																														{
																																																															fplayer.playFirework(start1.getWorld(), start1.add(0, 0, -1), fe);
																																																															fplayer.playFirework(start2.getWorld(), start2.add(0, 0, 1), fe);
																																																															Bukkit.getScheduler().scheduleSyncDelayedTask(ArmorEditor.plugin, new Runnable()
																																																															{
																																																																@Override
																																																																public void run()
																																																																{
																																																																	damagePlayers(center,15,7,caster);
																																																																}
																																																															},(long)1.65);
																																																														}
																																																														catch (Exception e)
																																																														{
																																																															e.printStackTrace();
																																																														}
																																																													}
																																																												},(long)1.65);
																																																											}
																																																											catch (Exception e)
																																																											{
																																																												e.printStackTrace();
																																																											}
																																																										}
																																																									},(long)1.65);
																																																								}
																																																								catch (Exception e)
																																																								{
																																																									e.printStackTrace();
																																																								}
																																																							}
																																																						},(long)1.65);
																																																					}
																																																					catch (Exception e)
																																																					{
																																																						e.printStackTrace();
																																																					}
																																																				}
																																																			},(long)1.65);
																																																		}
																																																		catch (Exception e)
																																																		{
																																																			e.printStackTrace();
																																																		}
																																																	}
																																																},(long)1.65);
																																															}
																																															catch (Exception e)
																																															{
																																																e.printStackTrace();
																																															}
																																														}
																																													},(long)1.65);
																																												}
																																												catch (Exception e)
																																												{
																																													e.printStackTrace();
																																												}
																																											}
																																										},(long)1.65);
																																									}
																																									catch (Exception e)
																																									{
																																										e.printStackTrace();
																																									}
																																								}
																																							},(long)1.65);
																																						}
																																						catch (Exception e)
																																						{
																																							e.printStackTrace();
																																						}
																																					}
																																				},(long)1.65);
																																			}
																																			catch (Exception e)
																																			{
																																				e.printStackTrace();
																																			}
																																		}
																																	},(long)1.65);
																																}
																																catch (Exception e)
																																{
																																	e.printStackTrace();
																																}
																															}
																														},(long)1.65);
																													}
																													catch (Exception e)
																													{
																														e.printStackTrace();
																													}
																												}
																											},(long)1.65);
																										}
																										catch (Exception e)
																										{
																											e.printStackTrace();
																										}
																									}
																								},(long)1.65);
																							}
																							catch (Exception e)
																							{
																								e.printStackTrace();
																							}
																						}
																					},(long)1.65);
																				}
																				catch (Exception e)
																				{
																					e.printStackTrace();
																				}
																			}
																		},(long)1.65);
																	}
																	catch (Exception e)
																	{
																		e.printStackTrace();
																	}
																}
															},(long)1.65);
														}
														catch (Exception e)
														{
															e.printStackTrace();
														}
													}
												},(long)1.65);
											}
											catch (Exception e)
											{
												e.printStackTrace();
											}
										}
									},(long)1.65);
								}
								catch (Exception e)
								{
									e.printStackTrace();
								}
							}
						},(long)1.65);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			},(long)1.65);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void damagePlayers(Location center, int height, int radius, LivingEntity caster)
	{
		int radiusX = radius;
		int radiusZ = radius;
		
		radiusX += 0.5;
        radiusZ += 0.5;
        
        double invRadiusX = 1 / radiusX;
        double invRadiusZ = 1 / radiusZ;

        //int ceilRadiusX = (int) Math.ceil(radiusX);
        //int ceilRadiusZ = (int) Math.ceil(radiusZ);
        
        for(Entity en : center.getWorld().getEntities())
        {
        	if(en == null)
        		continue;
        	if(!(en instanceof Player))
        		continue;
        	Player target = (Player)en;
        	Location loc = target.getLocation();
        	//Bukkit.broadcastMessage(center.getBlockX() + "");
        	if(center.getBlockX()+radius-loc.getBlockX() >= 0 && center.getBlockX()+radius-loc.getBlockX() < radius*2)
        	{
	        		//Bukkit.broadcastMessage("2");
        		if(center.getBlockZ()+radius-loc.getBlockZ() >= 0 && center.getBlockZ()+radius-loc.getBlockZ() < radius*2)
        		{
        			//Bukkit.broadcastMessage("3");
        			if(center.getBlockY()+height-loc.getBlockY() < center.getBlockY()+height)
        			{
        				//Bukkit.broadcastMessage("4");
        				if(lengthSq((center.getBlockX()+radius-loc.getBlockX() + 1)*invRadiusX, (center.getBlockZ()+radius-loc.getBlockZ() + 1)*invRadiusZ) <= 1)
        				{
        					//Bukkit.broadcastMessage("5");
        					//center.getWorld().strikeLightningEffect(le.getLocation());
        					PvpHandler.getPvpPlayer(target).uncheckedDamage(300);
        					target.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 6*20, 1));
        					target.damage(0D);
        				}
        			}
        		}
	        }
        }
	}
	
	private final double lengthSq(double x, double z)
    {
        return (x * x) + (z * z);
    }
}
