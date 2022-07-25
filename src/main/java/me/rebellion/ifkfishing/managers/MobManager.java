package me.rebellion.ifkfishing.managers;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Bee;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MobManager {
    enum MobType {
        FISHERHUNTER, TRUSTYBEE
    }

    public static void spawn(final Player target, final MobType type) {
        if (type.equals(MobType.FISHERHUNTER)) {
            final ItemStack bow = new ItemStack(Material.BOW);
            final ItemStack helmet = new ItemStack(Material.DRAGON_HEAD);
            final ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
            bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 9);
            boots.addEnchantment(Enchantment.DEPTH_STRIDER, 3);

            final PotionEffect potion2 = new PotionEffect(PotionEffectType.WATER_BREATHING, 100000, 0);
            final PotionEffect potion3 = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100000, 0);
            final Skeleton skeleton = (Skeleton) target.getWorld().spawnEntity(target.getLocation(),
                    EntityType.SKELETON);

            skeleton.addPotionEffect(potion2);
            skeleton.addPotionEffect(potion3);
            skeleton.getEquipment().setItemInMainHand(bow);
            skeleton.getEquipment().setHelmet(helmet);
            skeleton.getEquipment().setBoots(boots);
            skeleton.getEquipment().setItemInMainHandDropChance(0.0F);
            skeleton.getEquipment().setHelmetDropChance(0.0F);
            skeleton.getEquipment().setBootsDropChance(0.0F);
            skeleton.setCustomName("Pengganggu Mancing");
            skeleton.setCustomNameVisible(true);
            skeleton.attack(target);
        } else if (type.equals(MobType.TRUSTYBEE)) {
            final PotionEffect potion = new PotionEffect(PotionEffectType.WITHER, 100000, 0);
            final Bee bee = (Bee) target.getWorld().spawnEntity(target.getLocation(), EntityType.BEE);
            bee.addPotionEffect(potion);
            bee.addPassenger(target);
            bee.setCustomName("BLBLBLBLBLBL");
            bee.setCustomNameVisible(true);
            bee.setGlowing(true);
            bee.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            bee.getAttribute(Attribute.GENERIC_FLYING_SPEED).setBaseValue(3);
            bee.setHealth(20);
        }
    }
}