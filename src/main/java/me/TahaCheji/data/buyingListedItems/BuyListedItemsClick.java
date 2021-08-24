package me.TahaCheji.data.buyingListedItems;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.TahaCheji.Main;
import me.TahaCheji.data.list.ListedItemsGui;
import me.TahaCheji.data.list.Listing;
import me.TahaCheji.data.list.ListingData;

import me.TahaCheji.data.market.MarketShopGui;
import me.TahaCheji.data.menu.MarketShopMenu;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class BuyListedItemsClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) throws IOException {
        if (!e.getView().getTitle().contains("Listing")) {
            return;
        }
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        e.setCancelled(true);
        Economy economy = Main.getEcon();
        Player player = (Player) e.getWhoClicked();
        if (e.getSlot() == 42) {
            player.openInventory(new MarketShopMenu(player).getInventory());
            return;
        }
        if (e.getSlot() == 38) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Accept")) {
                for (Listing listing : ListingData.getAllSavedListings()) {
                    if (new NBTItem(e.getCurrentItem()).getString("ListUUID").contains(listing.getUuid().toString())) {
                        economy.withdrawPlayer(player, listing.getPrice());
                        economy.depositPlayer(listing.getPlayer(), listing.getPrice());
                        ItemStack itemStack = listing.getItem();
                        player.getInventory().addItem(itemStack);
                        listing.removeListing();
                        player.sendMessage(ChatColor.GOLD + "You have bought " + listing.getItem().getItemMeta().getDisplayName() + ChatColor.GOLD + " from " + listing.getPlayer().getDisplayName());
                        player.closeInventory();
                    }
                }
            }

        }

    }
}
