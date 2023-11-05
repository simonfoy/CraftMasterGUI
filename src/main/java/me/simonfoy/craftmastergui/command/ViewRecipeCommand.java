package me.simonfoy.craftmastergui.command;

import me.simonfoy.craftmastergui.CraftMasterGUI;
import me.simonfoy.craftmastergui.RecipesConfig;
import me.simonfoy.craftmastergui.menu.RecipeGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ViewRecipeCommand implements CommandExecutor {
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
}
