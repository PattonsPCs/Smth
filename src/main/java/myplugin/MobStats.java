package myplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class MobStats extends JavaPlugin{

    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(new MobSpawnListener(), this);
        getLogger().info("MobStats has successfully loaded!");
    }

    @Override
    public void onDisable(){
        getLogger().info("MobStats has been disabled.");
    }


}
