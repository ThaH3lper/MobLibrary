package me.ThaH3lper.com;

import java.util.logging.Logger;

import me.ThaH3lper.com.Entitys.MobsHandler;
import me.ThaH3lper.com.Items.ItemHandler;
import me.ThaH3lper.com.SaveLoad.SaveLoad;
import me.ThaH3lper.com.Spawner.SpawnerHandler;
import me.ThaH3lper.com.Spawner.SpawnerListener;
import me.ThaH3lper.com.Spawner.Ticker;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MobLibrary extends JavaPlugin
{
	public final Logger logger = Logger.getLogger("Minecraft");
	public static MobLibrary plugin;
	private SaveLoad items;
	private SaveLoad mobs;
	private SaveLoad saves;
	
	@Override
	public void onDisable()
	{
		SaveLoad.saveSpawners();
		MobsHandler.clearMobs();
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() +  " Has Been Disabled!");
	}
	
	@Override
	public void onEnable()
	{
		plugin = this;
		//Enable
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName()+ " " + pdfFile.getVersion() +  " Has Been Enabled!");
		
		items = new SaveLoad(this, "Items.yml");
		mobs = new SaveLoad(this, "Mobs.yml");
		saves = new SaveLoad(this, "Save.yml");
		ItemHandler.Load();
		MobsHandler.load(this);

		getCommand("Library").setExecutor(new CommandHandler());
		
		PluginManager manager = this.getServer().getPluginManager();
		manager.registerEvents(new EventListener(), this);
		manager.registerEvents(new SpawnerListener(this), this);
		
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Ticker(this), 10L, 20L);
		SpawnerHandler.load();
		SaveLoad.readStoredData("StoredLocations.txt");
	}
	
	public SaveLoad getItemConfig()
	{
		return items;
	}
	
	public SaveLoad getMobConfig()
	{
		return mobs;
	}
	
	public SaveLoad getSavesConfig()
	{
		return saves;
	}
}
