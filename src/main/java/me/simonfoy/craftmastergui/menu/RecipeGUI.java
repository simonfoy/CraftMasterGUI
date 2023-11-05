package me.simonfoy.craftmastergui.menu;

import me.simonfoy.craftmastergui.RecipesConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class RecipeGUI {

    public static Inventory getRecipeGUI(RecipesConfig recipesConfig, String itemName) {
        List<String> recipeLines = recipesConfig.getConfig().getStringList("recipes." + itemName);
        if (recipeLines.isEmpty()) {
            Bukkit.getLogger().info("No recipe found for " + itemName);
            return null;
        }

        Inventory inv = Bukkit.createInventory(null, 54, itemName + " Recipe");

        List<Integer> craftingSlots = Arrays.asList(10, 11, 12, 19, 20, 21, 28, 29, 30);
        int i = 0;
        for (String line : recipeLines) {
            String[] items = line.split(",");
            for (String itemStr : items) {
                Material material = Material.matchMaterial(itemStr);
                if (material != null && material != Material.AIR) {
                    ItemStack item = new ItemStack(material, 1);
                    inv.setItem(craftingSlots.get(i), item);
                }
                i++;
            }
        }

        ItemStack craftTableItem = new ItemStack(Material.CRAFTING_TABLE, 1);
        ItemMeta craftTableMeta = craftTableItem.getItemMeta();
        craftTableMeta.setDisplayName(ChatColor.GREEN + "Crafting Table");
        craftTableItem.setItemMeta(craftTableMeta);
        inv.setItem(23, craftTableItem);

        ItemStack resultItem = new ItemStack(Material.matchMaterial(itemName.toUpperCase()), 1);
        inv.setItem(25, resultItem);

        ItemStack closeButton = new ItemStack(Material.BARRIER, 1);
        ItemMeta closeButtonMeta = closeButton.getItemMeta();
        closeButtonMeta.setDisplayName(ChatColor.RED + "Close");
        closeButton.setItemMeta(closeButtonMeta);
        inv.setItem(49, closeButton);

        return inv;
    }
}
