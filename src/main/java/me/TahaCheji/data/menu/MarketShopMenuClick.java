package me.TahaCheji.data.menu;

import me.TahaCheji.data.market.ItemType;
import me.TahaCheji.data.market.MarketShopGui;
import me.TahaCheji.data.shop.ShopData;
import me.TahaCheji.data.shop.ShopsGui;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MarketShopMenuClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!e.getView().getTitle().contains("MarketMenu")) {
            return;
        }
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            return;
        }
        Player player = (Player) e.getWhoClicked();
        e.setCancelled(true);
        if(e.getSlot() == 10) {
            new MarketShopGui().getMarketShopGui(ItemType.ITEM).open(player);
        }
        if(e.getSlot() == 11) {
            new MarketShopGui().getMarketShopGui(ItemType.TOOL).open(player);
        }
        if(e.getSlot() == 12) {
            new MarketShopGui().getMarketShopGui(ItemType.SWORD).open(player);
        }
        if(e.getSlot() == 19) {
            new MarketShopGui().getMarketShopGui(ItemType.SPELL).open(player);
        }
        if(e.getSlot() == 20) {
            new MarketShopGui().getMarketShopGui(ItemType.MATERIAL).open(player);
        }
        if(e.getSlot() == 21) {
            new MarketShopGui().getMarketShopGui(ItemType.BOW).open(player);
        }
        if(e.getSlot() == 28) {
            new MarketShopGui().getMarketShopGui(ItemType.BOOTS).open(player);
        }
        if(e.getSlot() == 29) {
            new MarketShopGui().getMarketShopGui(ItemType.LEGGGINGS).open(player);
        }
        if(e.getSlot() == 30) {
            new MarketShopGui().getMarketShopGui(ItemType.CHESTPLATE).open(player);
        }
        if(e.getSlot() == 37) {
            new MarketShopGui().getMarketShopGui(ItemType.HELMET).open(player);
        }
        if(e.getSlot() == 38) {
            new MarketShopGui().getMarketShopGui().open(player);
        }
        if(e.getSlot() == 39) {
            new MarketShopGui().getMarketShopGui(ItemType.ARMOR).open(player);
        }
        if(e.getSlot() == 49) {
            player.closeInventory();
        }
        if(e.getSlot() == 25) {
            if(ShopData.hasShop(player)) {
                player.openInventory(new ShopsGui(ShopData.getPlayerShop(player), player).getInventory());
            } else {
                player.sendMessage(ChatColor.RED + "Error contact a admin if this is not correct");
            }
        }

    }

}
