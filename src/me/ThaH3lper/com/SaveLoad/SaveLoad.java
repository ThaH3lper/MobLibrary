package me.ThaH3lper.com.SaveLoad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.Entitys.MobsHandler;
import me.ThaH3lper.com.Spawner.SpawnerHandler;
import me.ThaH3lper.com.Spawner.SpawnerPlace;

public class SaveLoad
{
	private FileConfiguration DataConfig = null;
	private File data = null;
	
	private MobLibrary mb;
	private String file;
	private File thefile;
	
	public SaveLoad(MobLibrary mb, String newfile)
	{
		this.mb = mb;
		file = newfile;
		thefile = new File(mb.getDataFolder(), newfile);
		if(thefile.exists())
		{
			data = thefile;
		}
		reloadCustomConfig();
		saveCustomConfig();
	}
	
	public void reloadCustomConfig()
	{
	    if (data == null) 
	    {
	    	data = new File(mb.getDataFolder(), file);
	    	DataConfig = YamlConfiguration.loadConfiguration(data);
	    	InputStream defConfigStream = mb.getResource(file);
	    	if (defConfigStream != null) 
	    	{
	    		YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	    		DataConfig.setDefaults(defConfig);
	    	}
	    	getCustomConfig().options().copyDefaults(true);
	    	mb.logger.info(file + " did not exist! Generated a new one!");
	    }
	    else
	    {
	    	DataConfig = YamlConfiguration.loadConfiguration(data);
	    }
	}

	public FileConfiguration getCustomConfig()
	{
	    if (DataConfig == null)
	    {
	        reloadCustomConfig();
	    }
	    return DataConfig;
	}

	public void saveCustomConfig()
	{
	    if (DataConfig == null || data == null)
	    {
	    	return;
	    }
	    try
	    {
	        getCustomConfig().save(data);
	    }
	    catch(IOException ex)
	    {
	        mb.getLogger().log(Level.SEVERE, "Could not save config to " + data, ex);
	    }
	    
	}

	public static void readStoredData(String file)
	{
		int bossesLoaded = 0;
		String fileName = file;
		File dir = new File(MobLibrary.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " ").replaceAll( ".jar", ""));
		File actualFile = new File (dir, fileName);
		// This will reference one line at a time
		String line = null;
		if(!SpawnerHandler.getSpawners().isEmpty())
		{
			MobsHandler.clearMobs();
		}
		try
		{
			FileReader fileReader = new FileReader(actualFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null)
			{
				bossesLoaded++;
				boolean alreadyexist = false;
				StringTokenizer st= new StringTokenizer(line, "/");
				double x = Integer.parseInt(st.nextToken()),
					y = Integer.parseInt(st.nextToken()),
				    z = Integer.parseInt(st.nextToken());
				World world = Bukkit.getServer().getWorld(st.nextToken());
				Location loc = new Location(world, x, y, z);
				String cmdName = st.nextToken();
				int amount = Integer.parseInt(st.nextToken()),
					interval = Integer.parseInt(st.nextToken()),
					radius = Integer.parseInt(st.nextToken());
				if(SpawnerHandler.getSpawners().isEmpty())
				{
					SpawnerHandler.addSpawner(new SpawnerPlace(loc, cmdName, amount, interval, radius, MobLibrary.plugin));
					Chunk chunk = loc.getChunk();
					chunk.load(true);
					if(loc.getBlock().getType() != Material.SIGN)
					{
						Block block = loc.getBlock();
						block.setType(Material.SIGN_POST);
					}
					Sign sign = (Sign)loc.getBlock().getState();
					sign.setLine(0, ChatColor.GREEN + "[MobSpawner]");
					sign.setLine(1, "" + radius);
					sign.setLine(2, cmdName);
					sign.setLine(3, amount + "i,"+ interval + "s");
					sign.update();
				}
				for(SpawnerPlace sp : SpawnerHandler.getSpawners())
				{
					Location existing = sp.getLocation();
					if(existing.getBlockX() == loc.getBlockX() && existing.getBlockY() == loc.getBlockY() && existing.getBlockZ() == loc.getBlockZ())
					{
						alreadyexist = true;
					}
				}
				if(alreadyexist == false)
				{
					SpawnerHandler.addSpawner(new SpawnerPlace(loc, cmdName, amount, interval, radius, MobLibrary.plugin));
					Chunk chunk = loc.getChunk();
					chunk.load(true);
					if(loc.getBlock().getType() != Material.SIGN)
					{
						Block block = loc.getBlock();
						block.setType(Material.SIGN_POST);
					}
					Sign sign = (Sign)loc.getBlock().getState();
					sign.setLine(0, ChatColor.GREEN + "[MobSpawner]");
					sign.setLine(1, "" + radius);
					sign.setLine(2, cmdName);
					sign.setLine(3, amount + "i,"+ interval + "s");
					sign.update();
				}
				if(alreadyexist == true)
				{
					Bukkit.getLogger().info("TRIED TO LOAD SPAWNER THAT ALREADY EXISTED IGNORED");
				}
				alreadyexist = false;
			}
			bufferedReader.close();			
		}
		catch(FileNotFoundException ex)
		{
			Bukkit.getLogger().info("[SEVERE]:Unable to open file '" + actualFile + "'");				
		}
		catch(IOException ex)
		{
			Bukkit.getLogger().info("[SEVERE]: Error reading file " + actualFile);
		}
		Bukkit.getLogger().info("LOADED :" + bossesLoaded + " Bosses!");
	}
	
	public static void storeData(String file)
	{
		// The name of the file to open change this later to allow filename to be file name.
		//String filename = file;
		String fileName = file;
		File dir = new File(MobLibrary.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " ").replaceAll( ".jar", ""));
		File actualFile = new File (dir, fileName);
		try
		{
			FileWriter fileWriter = new FileWriter(actualFile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for(SpawnerPlace sign: SpawnerHandler.getSpawners())
			{
				fileWriter.write(sign.getLocation().getBlockX() + "/" + sign.getLocation().getBlockY() + "/" + sign.getLocation().getBlockZ() + "/" + sign.getLocation().getWorld().getName() +
						"/" + sign.getCmdMob() + "/" + sign.getAmount() + "/" + sign.getInterval() + "/" + sign.getRadius() + "\n" );
			}
			bufferedWriter.close();			
		}
		catch(FileNotFoundException ex)
		{
			Bukkit.getLogger().info("[SEVERE]: Unable to write file '" + actualFile + "'");				
		}
		catch(IOException ex)
		{
			Bukkit.getLogger().info("[SEVERE]: Error writing file " + actualFile);
		}
	}
	
	public static void storeBackupData()
	{
		storeData("StoredLocationsBackup.txt");
	}
	
	public static void restoreBackupData()
	{
		SpawnerHandler.clear();
		readStoredData("StoredLocationsBackup.txt");
	}
	
	public static void saveSpawners()
	{
		List<String> saves = new ArrayList<String>();
		for(SpawnerPlace sign: SpawnerHandler.getSpawners())
		{
			saves.add(sign.getLocation().getBlockX() + "," + sign.getLocation().getBlockY() + "," + sign.getLocation().getBlockZ() + "," + sign.getLocation().getWorld().getName() +
					"," + sign.getCmdMob() + "," + sign.getAmount() + "," + sign.getInterval() + "," + sign.getRadius());
		}
		MobLibrary.plugin.getSavesConfig().getCustomConfig().set("Spawners", saves);
		MobLibrary.plugin.getSavesConfig().saveCustomConfig();
	}
}
