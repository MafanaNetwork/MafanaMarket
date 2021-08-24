package me.TahaCheji.util;

import me.TahaCheji.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Files {
	
	
	static File configFile = new File("plugins/MafanaMarket/config.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(configFile);
	
	public static void initFiles() throws FileNotFoundException, IOException, InvalidConfigurationException {
		
		if(!new File("plugins/MafanaMarket").exists()) {
			new File("plugins/MafanaMarket").mkdir();
		}
		
		if(!configFile.exists()) {
			Main.getInstance().saveDefaultConfig();
		}
		
		File playerData = new File("plugins/MafanaMarket/playerData");
		File st = new File("plugins/MafanaMarket/shops");
		File listingData = new File("plugins/MafanaMarket/listings");
		if(!playerData.exists()) {
			playerData.mkdir();
		}
		if(!st.exists()) {
			st.mkdir();
		}
		if(!listingData.exists()) {
			listingData.mkdir();
		}
		
		loadFiles();
		
	}

	private static void loadFiles() throws FileNotFoundException, IOException, InvalidConfigurationException {
		cfg.load(configFile);
	}


	private static void loadFile(InputStream paramInputStream, File paramFile) throws IOException, InvalidConfigurationException {

		if(!paramFile.exists()) {
			FileUtils.copyInputStreamToFile(paramInputStream, paramFile);
		}
		((FileConfiguration)YamlConfiguration.loadConfiguration(paramFile)).load(paramFile);
	}
	
}
