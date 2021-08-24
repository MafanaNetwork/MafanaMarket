package me.TahaCheji.data.list;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.TahaCheji.data.buyingListedItems.BuyListedItemGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListedItemsClick implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().contains("All Listings")) {
            return;
        }
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (!new NBTItem(e.getCurrentItem()).hasKey("ListUUID")) {
            e.setCancelled(true);
            return;
        }
        for (Listing listing : ListingData.getAllSavedListings()) {
            if (new NBTItem(e.getCurrentItem()).getString("ListUUID").contains(listing.getUuid().toString())) {
                Player player = (Player) e.getWhoClicked();
                player.openInventory(new BuyListedItemGui(listing, player).getInventory());
            }
        }
    }


}
