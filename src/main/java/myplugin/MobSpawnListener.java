package myplugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;


public class MobSpawnListener implements Listener {

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event){
        LivingEntity entity = event.getEntity();

        if (entity instanceof Player){
            return;
        }

        if (entity instanceof Creature) {
            int level = 5 + (int) (Math.random() * 6);
            applyStats(entity, level);// Random level between 5 and 10
            updateNameTag(entity, level);
        } else if (entity instanceof Monster){
            int level = 15 + (int) (Math.random() * 6);
            applyStats(entity, level);// Random level between 15 and 20
            updateNameTag(entity, level);
        } else {
            int level = 10 + (int) (Math.random() * 6);
            applyStats(entity, level);// Random level between 10 and 15
            updateNameTag(entity, level);
        }



    }

    private void applyStats(LivingEntity entity, int level){
        AttributeInstance maxHealthAttr = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (maxHealthAttr != null) {
            double baseHealth = maxHealthAttr.getBaseValue();
            double newHealth = baseHealth * (1.0 + (0.1 * level));

            maxHealthAttr.setBaseValue(newHealth);
            entity.setHealth(newHealth);
        }

        AttributeInstance attackDamageAttr = entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        if (attackDamageAttr != null){
            double baseDamage = attackDamageAttr.getBaseValue();
            double newDamage = baseDamage * (1.0 + (0.05 * level));
            attackDamageAttr.setBaseValue(newDamage);
        }
    }

    private void updateNameTag(LivingEntity entity, int level){
        String rawType = entity.getType().name().replace("_", " ").toLowerCase();
        String mobType = rawType.substring(0, 1).toUpperCase() + rawType.substring(1);

        Component nameTag = Component.text("[Lv. " + level + "] ", NamedTextColor.GOLD)
                .append(Component.text(mobType, NamedTextColor.RED));

        entity.customName(nameTag);
        entity.setCustomNameVisible(true);
    }
}
