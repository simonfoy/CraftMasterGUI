package me.simonfoy.craftmastergui;

import me.simonfoy.craftmastergui.command.ViewRecipeCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class CraftMasterGUI extends JavaPlugin {

    @Override
    public void onEnable() {

        RecipesConfig recipesConfig = new RecipesConfig(this);
        recipesConfig.reloadConfig();


        this.getCommand("viewrecipe").setExecutor(new ViewRecipeCommand(this, recipesConfig));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
