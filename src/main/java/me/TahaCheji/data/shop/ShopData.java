package me.TahaCheji.data.shop;

import me.TahaCheji.Main;
import me.TahaCheji.data.list.Listing;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopData {

    public static void saveShop(Shops shop) throws IOException {
        File listingData = new File("plugins/MafanaMarket/shops/" + shop.getUuid() + "/data.yml");
        FileConfiguration pD = YamlConfiguration.loadConfiguration(listingData);
        if (!new File("plugins/MafanaMarket/shops/" + shop.getUuid()).exists()) {
            new File("plugins/MafanaMarket/shops/" + shop.getUuid()).mkdir();
        }
        if (!listingData.exists()) {
            listingData.createNewFile();
            pD.set("data.owner", shop.getOwner().getUniqueId().toString());
            pD.set("data.uuid", shop.getUuid().toString());
            pD.save(listingData);
        }
    }

    public static List<Shops> getAllSavedShops() {
        List<Shops> arrayList = new ArrayList<>();
        //loop all the yml in the listings folder
        File dataFolder = new File("plugins/MafanaMarket/shops");
        File[] files = dataFolder.listFiles();
        for (File newFile : files) {
            File newDataFolder = new File("plugins/MafanaMarket/shops/" + newFile.getName());
            File[] fil = newDataFolder.listFiles();
            for (File file : fil) {
                FileConfiguration pD = YamlConfiguration.loadConfiguration(file);
                if (file.getName().contains("data")) {
                    UUID uuid = UUID.fromString(pD.getString("data.owner"));
                    UUID newUuid = UUID.fromString(pD.getString("data.uuid"));
                    Shops shops = new Shops(Bukkit.getPlayer(uuid), newUuid);
                    arrayList.add(shops);
                }
            }
        }
        return arrayList;
    }

    public static Shops getPlayerShop(Player player) {
        Shops shop = null;
        for(Shops shops : getAllSavedShops()) {
            if(shops.getOwner().getUniqueId().toString().contains(player.getUniqueId().toString())) {
                shop = shops;
            }
        }
        return shop;
    }

    public static Boolean hasShop(Player player) {
        boolean check = false;
        for (Shops shops : getAllSavedShops()) {
            if (shops.getOwner().getUniqueId().toString().contains(player.getUniqueId().toString())) {
                check = true;
            }
        }
        return check;
    }

    public static void removeAllShops() {
        File dataFolder = new File("plugins/MafanaMarket/shops");
        File[] files = dataFolder.listFiles();
        for (File file : files) {
            file.delete();
        }
    }

    public static void removeShop(Shops shops) {
        File dataFolder = new File("plugins/MafanaMarket/shops");
        File[] files = dataFolder.listFiles();
        for (File file : files) {
            if (file.getName().contains(shops.getUuid().toString())) {
                file.delete();
            }
        }
    }


}
