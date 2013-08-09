package me.ThaH3lper.com;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.ThaH3lper.com.Entitys.MobsHandler;

import me.ThaH3lper.com.Items.ItemsObject;
import me.ThaH3lper.com.Items.LoadItems;
import me.ThaH3lper.com.SaveLoad.SaveLoad;
import me.ThaH3lper.com.Spawner.SpawnerHandler;
import me.ThaH3lper.com.Spawner.SpawnerListener;
import me.ThaH3lper.com.Spawner.Ticker;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MobLibrary extends JavaPlugin{
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static MobLibrary plugin;
	
	public SaveLoad items, mobs;
	public LoadItems loadItems;
	
	//public List<SpawnerPlace> spawnerList = new ArrayList<SpawnerPlace>();
	public List<ItemsObject> itemList = new ArrayList<ItemsObject>();
	
	@Override
	public void onDisable()
	{
		SaveLoad.storeData("StoredLocations.txt");
		SpawnerHandler.clear();
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
		
		//Setup();
		
		items = new SaveLoad(this, "Items.yml");
		mobs = new SaveLoad(this, "Mobs.yml");
		loadItems = new LoadItems(this);
		MobsHandler.load(this);
				
		getCommand("Library").setExecutor(new CommandHandler(this));
		
		PluginManager manager = this.getServer().getPluginManager();
		manager.registerEvents(new EventListener(), this);
		manager.registerEvents(new SpawnerListener(this), this);
		
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Ticker(this), 10l, 20l);
		SaveLoad.readStoredData("StoredLocations.txt");
	}
	
	/*public void Setup()
	{
	    try
	    {
	      Class[] args = new Class[3];
	      args[0] = Class.class;
	      args[1] = String.class;
	      args[2] = Integer.TYPE;

	      Method a = EntityTypes.class.getDeclaredMethod("a", args);
	      a.setAccessible(true);

	      a.invoke(a, new Object[] { ModZombie.class, "Zombie", Integer.valueOf(54) });
	      a.invoke(a, new Object[] { ModSkeleton.class, "Skeleton", Integer.valueOf(51) });
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      setEnabled(false);
	    }
	}*/
}
