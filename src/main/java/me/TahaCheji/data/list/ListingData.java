package me.TahaCheji.data.list;

import me.TahaCheji.Main;
import me.TahaCheji.data.shop.Shops;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListingData {

    public static void saveListing(Listing listing, Shops shops) throws IOException {
        File listingData = new File(Main.getInstance().getDataFolder(), "shops/" + shops.getUuid() + "/" + listing.getUuid() + ".yml");
        FileConfiguration pD = YamlConfiguration.loadConfiguration(listingData);
        if (!listingData.exists()) {
            listingData.createNewFile();
            pD.set("data.seller", listing.getPlayer().getUniqueId().toString());
            pD.set("data.item", listing.getItem());
            pD.set("data.price", listing.getPrice());
            pD.set("data.uuid", listing.getUuid().toString());
            pD.save(listingData);
        }
    }

    public static List<Listing> getAllSavedListings() {
        List<Listing> arrayList = new ArrayList<>();
        //loop all the yml in the listings folder
        File dataFolder = new File("plugins/MafanaMarket/shops");
        File[] files = dataFolder.listFiles();
        for (File newFile : files) {
            File newDataFolder = new File("plugins/MafanaMarket/shops/" + newFile.getName());
            File[] fil = newDataFolder.listFiles();
            for (File file : fil) {
                FileConfiguration pD = YamlConfiguration.loadConfiguration(file);
                if(file.getName().contains("data")) {
                    continue;
                } else {
                    UUID player = UUID.fromString(pD.getString("data.seller"));
                    UUID uuid = UUID.fromString(pD.getString("data.uuid"));
                    Listing listing = new Listing(Bukkit.getPlayer(player), pD.getItemStack("data.item"), pD.getInt("data.price"), uuid);
                    arrayList.add(listing);
                }
            }

        }
        return arrayList;
    }

    public static List<Listing> getAllSavedListings(Shops shops) {
        List<Listing> arrayList = new ArrayList<>();
        //loop all the yml in the listings folder
        File dataFolder = new File("plugins/MafanaMarket/shops");
        File[] files = dataFolder.listFiles();
        for (File newFile : files) {
            File newDataFolder = new File("plugins/MafanaMarket/shops/" + shops.getUuid().toString());
            File[] fil = newDataFolder.listFiles();
            for (File file : fil) {
            FileConfiguration pD = YamlConfiguration.loadConfiguration(file);
           if(file.getName().contains("data")) {
               continue;
           }
                UUID player = UUID.fromString(pD.getString("data.seller"));
                UUID uuid = UUID.fromString(pD.getString("data.uuid"));
                Listing listing = new Listing(Bukkit.getPlayer(player), pD.getItemStack("data.item"), pD.getInt("data.price"), uuid);
                arrayList.add(listing);

            }
        }
        return arrayList;
    }

    public static void removeListings(Listing listing) {
        File dataFolder = new File("plugins/MafanaMarket/shops");
        File[] files = dataFolder.listFiles();
        for (File newFile : files) {
            File newDataFolder = new File("plugins/MafanaMarket/shops/" + newFile.getName());
            File[] fil = newDataFolder.listFiles();
            for (File file : fil) {
                if (file.getName().contains(listing.getUuid().toString())) {
                    file.delete();
                }
            }
        }
    }


}
