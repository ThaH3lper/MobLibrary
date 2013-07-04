package me.ThaH3lper.com.SaveLoad;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.ThaH3lper.com.MobLibrary;


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
}
