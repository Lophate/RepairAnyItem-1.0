package loscut.the.maker.plugin;

import loscut.the.maker.commands.commandRepair;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class RepairAnyItem extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("repair").setExecutor(new commandRepair());
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[RAI]: Enabled");
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[RAI]: Disabled");
    }
}
