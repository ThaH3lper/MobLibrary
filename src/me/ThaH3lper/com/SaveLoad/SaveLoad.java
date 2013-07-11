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
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.ThaH3lper.com.MobLibrary;
import me.ThaH3lper.com.Spawner.SpawnerPlace;


public class SaveLoad {
		
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
	public void reloadCustomConfig() {
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

	public FileConfiguration getCustomConfig() {
	    if (DataConfig == null) {
	        reloadCustomConfig();
	    }
	    return DataConfig;
	}

	public void saveCustomConfig() {
	    if (DataConfig == null || data == null) {
	    return;
	    }
	    try {
	        getCustomConfig().save(data);
	    } catch (IOException ex) {
	        mb.getLogger().log(Level.SEVERE, "Could not save config to " + data, ex);
	    }
	    
	}
	public static void readStoredData(String file){
		// The name of the file to open change this later to allow filename to be file name.
		//String filename = file;
		String fileName = "StoredLocations.txt";
		File dir = new File(MobLibrary.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " ").replaceAll( ".jar", ""));
		File plugins = new File(dir.getParentFile().getPath());
		File actualFile = new File (dir, fileName);
		// This will reference one line at a time
		String line = null;
		try {
			List<Location> fromSave = new ArrayList<Location>();
			FileReader fileReader = new FileReader(actualFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				Bukkit.broadcastMessage(line) ;
			}
			bufferedReader.close();			
		}
		catch(FileNotFoundException ex) {
			Bukkit.getLogger().info("[SEVERE]:Unable to open file '" + actualFile + "'");				
		}
		catch(IOException ex) {
			Bukkit.getLogger().info("[SEVERE]: Error reading file " + actualFile);
		}
	}
	public static void storeData(String file){
		// The name of the file to open change this later to allow filename to be file name.
		//String filename = file;
		String fileName = "StoredLocations.txt";
		File dir = new File(MobLibrary.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " ").replaceAll( ".jar", ""));
		File plugins = new File(dir.getParentFile().getPath());
		File actualFile = new File (dir, fileName);
		try {
			FileWriter fileWriter = new FileWriter(actualFile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for(SpawnerPlace sign: me.ThaH3lper.com.MobLibrary.spawnerList){
				fileWriter.write(sign.getLocation().toString() + "\n" );
			}
			bufferedWriter.close();			
		}
		catch(FileNotFoundException ex) {
			Bukkit.getLogger().info("[SEVERE]:Unable to write file '" + actualFile + "'");				
		}
		catch(IOException ex) {
			Bukkit.getLogger().info("[SEVERE]: Error writing file " + actualFile);
		}
	}
}
