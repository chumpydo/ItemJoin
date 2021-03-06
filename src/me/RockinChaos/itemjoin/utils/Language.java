package me.RockinChaos.itemjoin.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.RockinChaos.itemjoin.ItemJoin;
import me.RockinChaos.itemjoin.handlers.ConfigHandler;
import me.RockinChaos.itemjoin.handlers.ServerHandler;

public class Language {
	public static CommandSender argsplayer;

	public static void getSendMessage(CommandSender sender, String MessageType, String ReplacementText) {
		if (ItemJoin.pl.getConfig().getString("Language") != null 
				&& ItemJoin.pl.getConfig().getString("Language").equalsIgnoreCase("English")) {
			sendEnglishMessage(sender, MessageType, ReplacementText);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void sendEnglishMessage(CommandSender sender, String MessageType, String ReplacementText) {
		Player player;
		if(sender.toString().contains("ConsoleSender")) {
			player = ItemJoin.pl.getServer().getPlayer("Console");
		} else {
			player = (Player) sender;
		}
		if (ConfigHandler.getConfig("en-lang.yml").getString(MessageType) != null 
				&& !ConfigHandler.getConfig("en-lang.yml").getString(MessageType).isEmpty()) {
			String Prefix = "";
			if (!MessageType.equalsIgnoreCase("inWorldListHeader") && !MessageType.equalsIgnoreCase("inWorldListed") 
					&& !MessageType.equalsIgnoreCase("listWorldsHeader") && !MessageType.equalsIgnoreCase("listItems")) {
			Prefix = Utils.format(ConfigHandler.getConfig("en-lang.yml").getString("Prefix"), player);
			}
			String sendMessage = Utils.format(ConfigHandler.getConfig("en-lang.yml").getString(MessageType), player);
				String ReplacementTextList = ReplacementText.replace(" ", "");
				String[] TextSplits = ReplacementTextList.split(",");
				for (String ReplaceText: TextSplits) {
			sendMessage = sendMessage.replace("%items%", ReplaceText);
			sendMessage = sendMessage.replace("%item%", ReplaceText);
			if (MessageType.equalsIgnoreCase("givenToPlayer") || MessageType.equalsIgnoreCase("removedFromPlayer") 
					|| MessageType.equalsIgnoreCase("playerTriedGive") || MessageType.equalsIgnoreCase("itemExistsInOthersInventory") || MessageType.equalsIgnoreCase("itemDoesntExistInOthersInventory")) {
				if (argsplayer != null) {
				sendMessage = sendMessage.replace("%argsplayer%", argsplayer.getName());
				}
			}
			sendMessage = sendMessage.replace("%argsplayer%", ReplaceText);
			sendMessage = sendMessage.replace("%failcount%", ReplaceText);
			sendMessage = sendMessage.replace("%failedcount%", ReplaceText);
			sendMessage = sendMessage.replace("%cost%", ReplaceText);
			sendMessage = sendMessage.replace("%amount%", ReplaceText);
			sendMessage = sendMessage.replace("%world%", ReplaceText);
				}
		if (MessageType.equalsIgnoreCase("updateChecking")) {
			ServerHandler.sendConsoleMessage(sendMessage);
		} else {
			ServerHandler.sendCommandsMessage(sender, Prefix + sendMessage);
		}
		}
	}
}
