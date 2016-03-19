package me.RockinChaos.itemjoin.utils;

import java.io.File;

import me.RockinChaos.itemjoin.ItemJoin;
import me.RockinChaos.itemjoin.Listeners.CancelInteract;
import me.RockinChaos.itemjoin.Listeners.Drops;
import me.RockinChaos.itemjoin.Listeners.InteractCmds;
import me.RockinChaos.itemjoin.Listeners.InventoryClick;
import me.RockinChaos.itemjoin.Listeners.ItemsDamaged;
import me.RockinChaos.itemjoin.Listeners.Mainhand;
import me.RockinChaos.itemjoin.Listeners.Offhand;
import me.RockinChaos.itemjoin.Listeners.Pickups;
import me.RockinChaos.itemjoin.Listeners.Placement;
import me.RockinChaos.itemjoin.Listeners.JoinItem.ChangedWorld;
import me.RockinChaos.itemjoin.Listeners.JoinItem.FirstJoin;
import me.RockinChaos.itemjoin.Listeners.JoinItem.JoinItem;
import me.RockinChaos.itemjoin.Listeners.JoinItem.Respawn;

import org.bukkit.ChatColor;

public class Registers {
	
	public static void checkHooks() {
		  if (ItemJoin.pl.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null && ItemJoin.pl.getConfig().getBoolean("PlaceholderAPI") == true) {
			  ItemJoin.pl.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "ItemJoin" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Hooked into PlaceholderAPI!");
	    	  ItemJoin.hasPlaceholderAPI = true;
			 } else if (ItemJoin.pl.getConfig().getBoolean("PlaceholderAPI") == true) {
		     ItemJoin.pl.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "ItemJoin" + ChatColor.GRAY + "] " + ChatColor.RED + "Could not find PlaceholderAPI.");
			 ItemJoin.hasPlaceholderAPI = false;
			 }
		  if (ItemJoin.pl.getServer().getPluginManager().getPlugin("Multiverse-Core") != null && ItemJoin.pl.getConfig().getBoolean("Multiverse-Core") == true) {
			  ItemJoin.pl.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "ItemJoin" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Hooked into Multiverse-Core!");
	    	  ItemJoin.hasMultiverse = true;
	      } else if (ItemJoin.pl.getConfig().getBoolean("Multiverse-Core") == true) {
	    	  ItemJoin.pl.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "ItemJoin" + ChatColor.GRAY + "] " + ChatColor.RED + "Could not find Multiverse-Core.");
	    	  ItemJoin.hasMultiverse = false;
	      }
		  if (ItemJoin.pl.getServer().getPluginManager().getPlugin("Multiverse-Inventories") != null && ItemJoin.pl.getConfig().getBoolean("Multiverse-Inventories") == true) {
			  ItemJoin.pl.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "ItemJoin" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Hooked into Multiverse-Inventories!");
	    	  ItemJoin.hasInventories = true;
	      } else if (ItemJoin.pl.getConfig().getBoolean("Multiverse-Inventories") == true) {
	    	  ItemJoin.hasInventories = false;
	    	  ItemJoin.pl.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "ItemJoin" + ChatColor.GRAY + "] " + ChatColor.RED + "Could not find Multiverse-Inventories.");
	      }
		}

	   public static void configFile() {
		      File cFile = new File(ItemJoin.pl.getDataFolder(), "config.yml");
		      if (cFile.exists() && ItemJoin.getSpecialConfig("config.yml").getInt("config-Version") != 2) {
		      if (ItemJoin.pl.getResource("config.yml") != null) {
		        String newGen = "config" + ItemJoin.getRandom(1500000,10000000) + ".yml";
		        File newFile = new File(ItemJoin.pl.getDataFolder(), newGen);
		           if (!newFile.exists()) {
		        	   cFile.renameTo(newFile);
		              File configFile = new File(ItemJoin.pl.getDataFolder(), "config.yml");
		              configFile.delete();
		             ItemJoin.pl.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "ItemJoin" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Your config.yml is out dated!");
		       	     ItemJoin.pl.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "ItemJoin" + ChatColor.GRAY + "] " + ChatColor.GREEN + "New options may be avaliable, Generating a new one!");
		           }
		        }
		      }
		  	  ItemJoin.pl.saveDefaultConfig();
			  ItemJoin.pl.getConfig().options().copyDefaults(false);
		}

		   public static void firstJoinFile() {
				  if (ItemJoin.getSpecialConfig("config.yml").getBoolean("Global-Settings" + ".First-Join." + "FirstJoin-Mode-Enabled") == true) {
					  ItemJoin.pl.getServer().getPluginManager().registerEvents(new FirstJoin(),ItemJoin.pl);
					  ItemJoin.loadSpecialConfig("FirstJoin.yml");
					  ItemJoin.getSpecialConfig("FirstJoin.yml").options().copyDefaults(false);
					  }
			}
		   
		   public static void itemsFile() {
			      File itemsFile = new File(ItemJoin.pl.getDataFolder(), "items.yml");
			      if (itemsFile.exists() && ItemJoin.getSpecialConfig("items.yml").getInt("items-Version") != 2) {
			      if (ItemJoin.pl.getResource("items.yml") != null) {
			        String newGen = "items" + ItemJoin.getRandom(1500000,10000000) + ".yml";
			        File newFile = new File(ItemJoin.pl.getDataFolder(), newGen);
			           if (!newFile.exists()) {
			        	  itemsFile.renameTo(newFile);
			              File configFile = new File(ItemJoin.pl.getDataFolder(), "items.yml");
			              configFile.delete();
			             ItemJoin.pl.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "ItemJoin" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Your items.yml is out dated!");
			       	     ItemJoin.pl.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "ItemJoin" + ChatColor.GRAY + "] " + ChatColor.GREEN + "New options may be avaliable, Generating a new one!");
			           }
			        }
			      }
				  ItemJoin.loadSpecialConfig("items.yml");
				  ItemJoin.getSpecialConfig("items.yml").options().copyDefaults(false);
				  ItemJoin.pl.worlds = ItemJoin.getSpecialConfig("items.yml").getStringList("world-list");
			}
		   
	    @SuppressWarnings("deprecation")
		public static void enLangFile() {
			      File enLang = new File(ItemJoin.pl.getDataFolder(), "en-lang.yml");
			      if (enLang.exists() && ItemJoin.pl.getConfig().getString("Language").equalsIgnoreCase("English") && ItemJoin.getSpecialConfig("en-lang.yml").getInt("en-Version") != 2) {
			      if (ItemJoin.pl.getResource("en-lang.yml") != null) {
			        String newGen = "en-lang" + ItemJoin.getRandom(1500000,10000000) + ".yml";
			        File newFile = new File(ItemJoin.pl.getDataFolder(), newGen);
			           if (!newFile.exists()) {
			    	      enLang.renameTo(newFile);
			              File configFile = new File(ItemJoin.pl.getDataFolder(), "en-lang.yml");
			              configFile.delete();
			              ItemJoin.pl.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "ItemJoin" + ChatColor.GRAY + "] " + ChatColor.GREEN + "You are using an outdated or bad en-lang!");
			       	   ItemJoin.pl.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + "ItemJoin" + ChatColor.GRAY + "] " + ChatColor.GREEN + "New options may be avaliable, Generating a new one!");
			           }
			        }
			      }
				  if (ItemJoin.pl.getConfig().getString("Language").equalsIgnoreCase("English")) {
					  ItemJoin.loadSpecialConfig("en-lang.yml");
					  ItemJoin.getSpecialConfig("en-lang.yml").options().copyDefaults(false);
					  Commands.RegisterEnLang(ItemJoin.pl.getServer().getPlayer("ItemJoin"), "ItemJoin");
				  }
		   }
		   public static boolean SecretMsg() {
			   boolean isSecret = false;
				  if (ItemJoin.getSpecialConfig("config.yml").getBoolean("Global-Settings" + ".Get-Items." + "Delay") == true) {
					  ItemJoin.secretMsg = "ItemJoin";
					  isSecret = true;
				  } else {
					  ItemJoin.secretMsg = "";
					  isSecret = false;
				  }
				return isSecret;
		   }
		   
		   public static void registerEvents() {
			      ItemJoin.pl.getCommand("itemjoin").setExecutor(new Commands());
				  ItemJoin.pl.getCommand("ij").setExecutor(new Commands());
				  ItemJoin.pl.getServer().getPluginManager().registerEvents(new JoinItem(),ItemJoin.pl);
				  ItemJoin.pl.getServer().getPluginManager().registerEvents(new InventoryClick(),ItemJoin.pl);
				  ItemJoin.pl.getServer().getPluginManager().registerEvents(new Drops(),ItemJoin.pl);
				  ItemJoin.pl.getServer().getPluginManager().registerEvents(new Pickups(),ItemJoin.pl);
				  ItemJoin.pl.getServer().getPluginManager().registerEvents(new Placement(),ItemJoin.pl);
				  ItemJoin.pl.getServer().getPluginManager().registerEvents(new Respawn(),ItemJoin.pl);
				  ItemJoin.pl.getServer().getPluginManager().registerEvents(new ChangedWorld(),ItemJoin.pl);
				  ItemJoin.pl.getServer().getPluginManager().registerEvents(new ItemsDamaged(),ItemJoin.pl);
				  ItemJoin.pl.getServer().getPluginManager().registerEvents(new InteractCmds(),ItemJoin.pl);
				  ItemJoin.pl.getServer().getPluginManager().registerEvents(new CancelInteract(),ItemJoin.pl);
			    String version = ItemJoin.pl.getServer().getVersion();
			    if (version.contains("1.9")) {
					  ItemJoin.pl.getServer().getPluginManager().registerEvents(new Mainhand(),ItemJoin.pl);
					  ItemJoin.pl.getServer().getPluginManager().registerEvents(new Offhand(),ItemJoin.pl);
				}
	    }
}