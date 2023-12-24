package loscut.the.maker.commands;

import loscut.the.maker.plugin.RepairAnyItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

import java.util.List;

public class commandRepair implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String label, String[] strings) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can do this.");
            return false;
        }

        List<?> badItems = RepairAnyItem.getPlugin(RepairAnyItem.class).getConfig().getList("non-fixable");

        Player player = (((Player) commandSender).getPlayer());
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        final Material itemID = itemInHand.getType();
        short maxDurability = itemID.getMaxDurability();
        short itemDurability = itemInHand.getDurability();

        if (badItems.contains(String.valueOf(itemID))) {
            commandSender.sendMessage(ChatColor.RED + "This plugin won't fix that item.");
            return false;
        } else if (itemInHand.getDurability() == -1 || itemID.isBlock()) {
            commandSender.sendMessage(ChatColor.RED + "This can't be fixed.");
            return false;
        }
        // /repair
        if(cmd.getName().equalsIgnoreCase("repair")) {

            if (player.getInventory().getItemInMainHand() != null && itemID.isItem()) {
                if (itemDurability != maxDurability && itemDurability != 0 || itemDurability > maxDurability)  {
                    player.getInventory().getItemInMainHand().setDurability((short) 0);
                    commandSender.sendMessage(ChatColor.YELLOW + "Your item has been repaired.");
                } else if (itemDurability == 0) {
                    commandSender.sendMessage(ChatColor.RED + "This can't be fixed.");
                }
            }
        }
        return true;
    }
}
