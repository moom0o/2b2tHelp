package me.moomoo.help;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    FileConfiguration config = getConfig();

    public void onEnable() {
        saveDefaultConfig();
        System.out.println("[ENABLED] AnarchyExploitFixes - Made by moomoo");
        Bukkit.getServer().getPluginManager().registerEvents((Listener) this, this);
        int pluginId = 8767; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCommandPreprocess(PlayerCommandPreprocessEvent evt) {
        if (evt.getMessage().toLowerCase().startsWith("/help")) {
            getConfig().getList("help").forEach(b -> evt.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', (String) b)));
            evt.setCancelled(true);
        }
        if (evt.getMessage().toLowerCase().startsWith("/kill ") || evt.getMessage().equalsIgnoreCase("/kill")) {
            if (getConfig().getBoolean("killcommand")) {
                evt.getPlayer().setHealth(0.0D);
                evt.setCancelled(true);
            }
        }
    }
}
