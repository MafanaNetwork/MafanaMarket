package me.TahaCheji.events;

import me.TahaCheji.data.shop.ShopData;
import me.TahaCheji.data.shop.Shops;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(!ShopData.hasShop(e.getPlayer())) {
            Player player = e.getPlayer();
            player.sendMessage(ChatColor.RED + "You do not have a shop how about you create a shop using the /MafanaMarket create shop");
        }
    }

}
