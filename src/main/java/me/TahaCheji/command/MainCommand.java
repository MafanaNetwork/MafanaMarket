package me.TahaCheji.command;

import me.TahaCheji.data.list.ListedItemsGui;
import me.TahaCheji.data.list.Listing;
import me.TahaCheji.data.list.ListingData;
import me.TahaCheji.data.market.ItemType;
import me.TahaCheji.data.market.MarketShopGui;
import me.TahaCheji.data.menu.MarketShopMenu;
import me.TahaCheji.data.shop.ShopData;
import me.TahaCheji.data.shop.Shops;
import me.TahaCheji.data.shop.ShopsGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class MainCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("MafanaMarket")) {
            Player player = (Player) sender;
            if(args.length == 0) {
                return true;
            }
            if(args[0].equalsIgnoreCase("shop")){
                if(args.length == 1) {
                    return true;
                }
                if(args[1].equalsIgnoreCase("menu")) {
                    player.openInventory(new MarketShopMenu(player).getInventory());
                }
                if(args[1].equalsIgnoreCase("all")) {
                    if(args.length == 2) {
                        new MarketShopGui().getMarketShopGui().open(player);
                        return true;
                    }
                    if(args[2].equalsIgnoreCase("sword")) {
                        new MarketShopGui().getMarketShopGui(ItemType.SWORD).open(player);
                    }
                    if(args[2].equalsIgnoreCase("material")) {
                        new MarketShopGui().getMarketShopGui(ItemType.MATERIAL).open(player);
                    }
                    if(args[2].equalsIgnoreCase("tool")) {
                        new MarketShopGui().getMarketShopGui(ItemType.TOOL).open(player);
                    }
                    if(args[2].equalsIgnoreCase("bow")) {
                        new MarketShopGui().getMarketShopGui(ItemType.BOW).open(player);
                    }
                    if(args[2].equalsIgnoreCase("item")) {
                        new MarketShopGui().getMarketShopGui(ItemType.ITEM).open(player);
                    }
                    if(args[2].equalsIgnoreCase("armor")) {
                        new MarketShopGui().getMarketShopGui(ItemType.ARMOR).open(player);
                    }
                    if(args[2].equalsIgnoreCase("boots")) {
                        new MarketShopGui().getMarketShopGui(ItemType.BOOTS).open(player);
                    }
                    if(args[2].equalsIgnoreCase("leggings")) {
                        new MarketShopGui().getMarketShopGui(ItemType.LEGGGINGS).open(player);
                    }
                    if(args[2].equalsIgnoreCase("chestplate")) {
                        new MarketShopGui().getMarketShopGui(ItemType.CHESTPLATE).open(player);
                    }
                    if(args[2].equalsIgnoreCase("spell")) {
                        new MarketShopGui().getMarketShopGui(ItemType.SPELL).open(player);
                    }
                    if(args[2].equalsIgnoreCase("helmet")) {
                        new MarketShopGui().getMarketShopGui(ItemType.HELMET).open(player);
                    }
                }
                Player newPlayer = Bukkit.getPlayer(args[1]);
                if(newPlayer == null) {
                    return true;
                }
                if(ShopData.hasShop(newPlayer)) {
                    player.openInventory(new ShopsGui(ShopData.getPlayerShop(newPlayer), player).getInventory());
                } else {
                    player.sendMessage(ChatColor.RED + "That player does not have a shop");
                }
            }
            if(args[0].equalsIgnoreCase("create")) {
                if(args.length == 1) {
                    return true;
                }
                if(args[1].equalsIgnoreCase("shop")) {
                    Shops shops = new Shops(player);
                    if(ShopData.hasShop(player)) {
                        player.sendMessage("You already have a shop");
                        return true;
                    }
                    try {
                        shops.saveShop();
                        player.sendMessage(ChatColor.GOLD + "You have created a shop");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                if(args[1].equalsIgnoreCase("listing")) {
                    if(args.length == 2) {
                        return true;
                    }
                    if(!ShopData.hasShop(player)) {
                        player.sendMessage(ChatColor.RED + "You do not have a shop to add a listing too");
                        return true;
                    }
                    if(ShopData.getPlayerShop(player).getItems().size() == 28) {
                        player.sendMessage(ChatColor.RED + "You hit the max amount of listings");
                        return true;
                    }
                    int coins = Integer.parseInt(args[2]);
                    ItemStack item = player.getItemInHand();
                    Listing listing = new Listing(player, item, coins);
                    player.setItemInHand(new ItemStack(Material.AIR));
                    try {
                        Shops shop = ShopData.getPlayerShop(player);
                        ListingData.saveListing(listing, shop);
                        player.sendMessage(ChatColor.GOLD + "You have created a listing");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
}
