package me.TahaCheji.data.list;

import me.TahaCheji.data.shop.Shops;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.UUID;

public class Listing {

    private final Player player;
    private final ItemStack item;
    private final int price;
    private final UUID uuid;

    public Listing(Player player, ItemStack item, int price) {
        this.player = player;
        this.item = item;
        this.price = price;
        UUID uuid = UUID.randomUUID();
        this.uuid = uuid;
    }

    public Listing(Player player, ItemStack item, int price, UUID uuid) {
        this.player = player;
        this.item = item;
        this.price = price;
        this.uuid = uuid;
    }

    public void saveListing(Shops shops) throws IOException {
        ListingData.saveListing(this, shops);
    }


    public void setPlayerListing(Player player) {
        player.sendMessage(ChatColor.GOLD + "You have listed " + item.getItemMeta().getDisplayName() + ChatColor.GOLD  + " for a price of $" + price);
    }

    public void removeListing() {
        ListingData.removeListings(this);
    }


    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

}
