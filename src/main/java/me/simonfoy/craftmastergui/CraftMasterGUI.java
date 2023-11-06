package me.simonfoy.craftmastergui;

import me.simonfoy.craftmastergui.command.ViewRecipeCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class CraftMasterGUI extends JavaPlugin {

    @Override
    public void onEnable() {

        RecipesConfig recipesConfig = new RecipesConfig(this);
        recipesConfig.reloadConfig();

        ViewRecipeCommand viewRecipeCommand = new ViewRecipeCommand(this, recipesConfig);

        this.getCommand("viewrecipe").setExecutor(viewRecipeCommand);
        this.getCommand("viewrecipe").setTabCompleter(viewRecipeCommand);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
