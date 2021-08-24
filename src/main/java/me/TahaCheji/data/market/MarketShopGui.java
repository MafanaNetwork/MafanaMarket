package me.TahaCheji.data.market;

import de.tr7zw.changeme.nbtapi.NBTItem;
import dev.triumphteam.gui.builder.gui.PaginatedBuilder;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import me.TahaCheji.Main;
import me.TahaCheji.data.list.Listing;
import me.TahaCheji.data.list.ListingData;
import me.TahaCheji.util.NBTUtils;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MarketShopGui {


    public ItemStack getCloseShop() {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        meta.setDisplayName(ChatColor.RED + "Close");
        lore.add("--------------------------");
        lore.add(ChatColor.GOLD + "Click to go back to the main menu");
        lore.add("--------------------------");
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public PaginatedGui getMarketShopGui(ItemType itemType) {
        PaginatedGui gui = Gui.paginated()
                .title(Component.text(ChatColor.GOLD + "MafanaMarket " + ChatColor.DARK_GREEN + itemType.getLore() + ChatColor.GOLD + " Listings"))
                .rows(6)
                .pageSize(54)
                .disableAllInteractions()
                .create();
        List<String> lore = new ArrayList<>();
        ItemStack greystainedglass = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        ItemMeta newmeta = greystainedglass.getItemMeta();
        newmeta.setDisplayName(ChatColor.GRAY + " ");
        newmeta.setLore(lore);
        greystainedglass.setItemMeta(newmeta);

        ItemStack closeShop = new ItemStack(Material.BARRIER);
        ItemMeta closeShopeta = closeShop.getItemMeta();
        closeShopeta.setDisplayName(ChatColor.GRAY + "Close Shop");
        closeShopeta.setLore(lore);
        closeShop.setItemMeta(closeShopeta);

        gui.setItem(0, new GuiItem(greystainedglass));
        gui.setItem(1,new GuiItem(greystainedglass));
        gui.setItem(2,new GuiItem(greystainedglass));
        gui.setItem(3,new GuiItem(greystainedglass));
        gui.setItem(4,new GuiItem(greystainedglass));
        gui.setItem(5,new GuiItem(greystainedglass));
        gui.setItem(6,new GuiItem(greystainedglass));
        gui.setItem(7,new GuiItem(greystainedglass));
        gui.setItem(8,new GuiItem(greystainedglass));
        gui.setItem(17,new GuiItem(greystainedglass));
        gui.setItem(26,new GuiItem(greystainedglass));
        gui.setItem(35,new GuiItem(greystainedglass));
        gui.setItem(45,new GuiItem(greystainedglass));
        gui.setItem(53,new GuiItem(greystainedglass));
        gui.setItem(52,new GuiItem(greystainedglass));
        gui.setItem(51,new GuiItem(greystainedglass));
        gui.setItem(50,new GuiItem(greystainedglass));
        gui.setItem(48,new GuiItem(greystainedglass));
        gui.setItem(47,new GuiItem(greystainedglass));
        gui.setItem(46,new GuiItem(greystainedglass));
        gui.setItem(44,new GuiItem(greystainedglass));
        gui.setItem(36,new GuiItem(greystainedglass));
        gui.setItem(27,new GuiItem(greystainedglass));
        gui.setItem(18,new GuiItem(greystainedglass));
        gui.setItem(9,new GuiItem(greystainedglass));
        gui.setItem(49, new GuiItem(getCloseShop()));
        gui.setItem(6, 3, ItemBuilder.from(Material.PAPER).setName(ChatColor.DARK_GRAY + "Previous").asGuiItem(event -> gui.previous()));
        gui.setItem(6, 7, ItemBuilder.from(Material.PAPER).setName(ChatColor.DARK_GRAY + "Next").asGuiItem(event -> gui.next()));
        for(Listing listing : ListingData.getAllSavedListings()) {
            if(!new NBTItem(listing.getItem()).getString("ItemType").contains(itemType.getLore())) {
                continue;
            }
            ItemStack item = listing.getItem();
            ItemMeta itemMeta = item.getItemMeta();
            List<String> itemLore = new ArrayList<>();
            for(String string : itemMeta.getLore()){
                itemLore.add(string);
            }
            itemLore.add(ChatColor.DARK_GRAY + "");
            itemLore.add("------------------------");
            itemLore.add(ChatColor.DARK_GRAY + "Price: $" + listing.getPrice());
            if(listing.getPlayer().isOnline()) {
                itemLore.add(ChatColor.DARK_GRAY + "Seller: " + listing.getPlayer().getDisplayName() + " " + ChatColor.GREEN + "[ONLINE]");
            } else {
                itemLore.add(ChatColor.DARK_GRAY + "Seller: " + listing.getPlayer().getDisplayName() + " " + ChatColor.RED + "[OFFLINE]");
            }
            itemLore.add(ChatColor.DARK_GRAY + "Listing UUID: " + listing.getUuid().toString());
            itemLore.add(ChatColor.DARK_GRAY + "");
            itemLore.add(ChatColor.DARK_GRAY + "Click to buy!");
            itemLore.add("------------------------");
            itemMeta.setLore(itemLore);
            item.setItemMeta(itemMeta);
            item = NBTUtils.setString(item, "ListUUID", listing.getUuid().toString());
            gui.addItem(new GuiItem(item));
        }

        return gui;
    }

    public PaginatedGui getMarketShopGui() {
        PaginatedGui gui = Gui.paginated()
                .title(Component.text(ChatColor.GOLD + "MafanaMarket All Listings"))
                .rows(6)
                .pageSize(54)
                .disableAllInteractions()
                .create();
        List<String> lore = new ArrayList<>();
        ItemStack greystainedglass = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        ItemMeta newmeta = greystainedglass.getItemMeta();
        newmeta.setDisplayName(ChatColor.GRAY + " ");
        newmeta.setLore(lore);
        greystainedglass.setItemMeta(newmeta);

        ItemStack closeShop = new ItemStack(Material.BARRIER);
        ItemMeta closeShopeta = closeShop.getItemMeta();
        closeShopeta.setDisplayName(ChatColor.GRAY + "Close Shop");
        closeShopeta.setLore(lore);
        closeShop.setItemMeta(closeShopeta);

        gui.setItem(0, new GuiItem(greystainedglass));
        gui.setItem(1,new GuiItem(greystainedglass));
        gui.setItem(2,new GuiItem(greystainedglass));
        gui.setItem(3,new GuiItem(greystainedglass));
        gui.setItem(4,new GuiItem(greystainedglass));
        gui.setItem(5,new GuiItem(greystainedglass));
        gui.setItem(6,new GuiItem(greystainedglass));
        gui.setItem(7,new GuiItem(greystainedglass));
        gui.setItem(8,new GuiItem(greystainedglass));
        gui.setItem(17,new GuiItem(greystainedglass));
        gui.setItem(26,new GuiItem(greystainedglass));
        gui.setItem(35,new GuiItem(greystainedglass));
        gui.setItem(45,new GuiItem(greystainedglass));
        gui.setItem(53,new GuiItem(greystainedglass));
        gui.setItem(52,new GuiItem(greystainedglass));
        gui.setItem(51,new GuiItem(greystainedglass));
        gui.setItem(50,new GuiItem(greystainedglass));
        gui.setItem(48,new GuiItem(greystainedglass));
        gui.setItem(47,new GuiItem(greystainedglass));
        gui.setItem(46,new GuiItem(greystainedglass));
        gui.setItem(44,new GuiItem(greystainedglass));
        gui.setItem(36,new GuiItem(greystainedglass));
        gui.setItem(27,new GuiItem(greystainedglass));
        gui.setItem(18,new GuiItem(greystainedglass));
        gui.setItem(9,new GuiItem(greystainedglass));
        gui.setItem(49, new GuiItem(closeShop));
        gui.setItem(6, 3, ItemBuilder.from(Material.PAPER).setName(ChatColor.DARK_GRAY + "Previous").asGuiItem(event -> gui.previous()));
        gui.setItem(6, 7, ItemBuilder.from(Material.PAPER).setName(ChatColor.DARK_GRAY + "Next").asGuiItem(event -> gui.next()));
        for(Listing listing : ListingData.getAllSavedListings()) {
            ItemStack item = listing.getItem();
            ItemMeta itemMeta = item.getItemMeta();
            List<String> itemLore = new ArrayList<>();
            for(String string : itemMeta.getLore()){
                itemLore.add(string);
            }
            itemLore.add(ChatColor.DARK_GRAY + "");
            itemLore.add("------------------------");
            itemLore.add(ChatColor.DARK_GRAY + "Price: $" + listing.getPrice());
            if(listing.getPlayer().isOnline()) {
                itemLore.add(ChatColor.DARK_GRAY + "Seller: " + listing.getPlayer().getDisplayName() + " " + ChatColor.GREEN + "[ONLINE]");
            } else {
                itemLore.add(ChatColor.DARK_GRAY + "Seller: " + listing.getPlayer().getDisplayName() + " " + ChatColor.RED + "[OFFLINE]");
            }
            itemLore.add(ChatColor.DARK_GRAY + "Listing UUID: " + listing.getUuid().toString());
            itemLore.add(ChatColor.DARK_GRAY + "");
            itemLore.add(ChatColor.DARK_GRAY + "Click to buy!");
            itemLore.add("------------------------");
            itemMeta.setLore(itemLore);
            item.setItemMeta(itemMeta);
            item = NBTUtils.setString(item, "ListUUID", listing.getUuid().toString());
            gui.addItem(new GuiItem(item));
        }

        return gui;
    }
}
