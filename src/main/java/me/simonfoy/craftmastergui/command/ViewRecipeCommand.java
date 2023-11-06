package me.simonfoy.craftmastergui.command;

import me.simonfoy.craftmastergui.CraftMasterGUI;
import me.simonfoy.craftmastergui.RecipesConfig;
import me.simonfoy.craftmastergui.menu.RecipeGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewRecipeCommand implements CommandExecutor, TabCompleter {
    private final CraftMasterGUI craftMasterGUI;
    private final RecipesConfig recipesConfig;

    public ViewRecipeCommand(CraftMasterGUI craftMasterGUI, RecipesConfig recipesConfig) {
        this.craftMasterGUI = craftMasterGUI;
        this.recipesConfig = recipesConfig;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                String itemName = args[0].toLowerCase();
                Inventory gui = RecipeGUI.getRecipeGUI(recipesConfig, itemName);
                if (gui != null) {
                    player.openInventory(gui);
                } else {
                    player.sendMessage(ChatColor.RED + "Recipe for item not found.");
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(recipesConfig.getRecipeKeys()).stream()
                    .filter(name -> name.startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
