package me.simonfoy.craftmastergui;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class RecipesConfig {
    private FileConfiguration recipesConfig = null;
    private final File recipesFile;

    public RecipesConfig(CraftMasterGUI craftMasterGUI) {
        recipesFile = new File(craftMasterGUI.getDataFolder(), "recipes.yml");
        if (!recipesFile.exists()) {
            craftMasterGUI.saveResource("recipes.yml", false);
        }
        reloadConfig();
    }

    public void reloadConfig() {
        recipesConfig = YamlConfiguration.loadConfiguration(recipesFile);
    }

    public FileConfiguration getConfig() {
        if (recipesConfig == null) {
            reloadConfig();
        }
        return recipesConfig;
    }
}
