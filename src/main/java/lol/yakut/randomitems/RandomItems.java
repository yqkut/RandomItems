package lol.yakut.randomitems;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class RandomItems extends JavaPlugin implements Listener {

    private final Material[] valuableMaterials = {
            Material.DIAMOND_SWORD,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_BLOCK,
            Material.ENCHANTED_GOLDEN_APPLE,
            Material.NETHER_STAR,
            Material.ELYTRA
    };

    @Override
    public void onEnable() {
        getLogger().info("-------------------------");
        getLogger().info("RandomItems is active!");
        getLogger().info("Made by yakut!");
        getLogger().info("-------------------------");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("-------------------------");
        getLogger().info("RandomItems is not active!");
        getLogger().info("Made by yakut!");
        getLogger().info("-------------------------");
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        ItemStack result = getRandomItem();
        event.setCurrentItem(result);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        event.setDropItems(false);
        ItemStack result = getRandomItem();
        event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), result);
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            return;
        }

        if (event.getState() == PlayerFishEvent.State.FISHING) {
            ItemStack result = getValuableItem();
            event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), result);
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER ||
                event.getEntity().getType() == EntityType.HORSE ||
                event.getEntity().getType() == EntityType.COW) {
            ItemStack result = getRandomItem();
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), result);
        }
    }

    private ItemStack getRandomItem() {
        Random random = new Random();
        Material randomMaterial;

        if (random.nextInt(7) == 0) {
            randomMaterial = valuableMaterials[random.nextInt(valuableMaterials.length)];
        } else {
            Material[] materials = Material.values();
            randomMaterial = materials[random.nextInt(materials.length)];
        }

        return new ItemStack(randomMaterial);
    }

    private ItemStack getValuableItem() {
        Random random = new Random();
        int itemType = random.nextInt(50);

        switch (itemType) {
            case 0:
                ItemStack diamondSword = new ItemStack(Material.DIAMOND_SWORD);
                diamondSword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                diamondSword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                diamondSword.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                return diamondSword;
            case 1:
                ItemStack enchantedArmor = new ItemStack(Material.DIAMOND_HELMET);
                enchantedArmor.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                enchantedArmor.addEnchantment(Enchantment.THORNS, 3);
                return enchantedArmor;
            case 2:
                ItemStack legendaryBow = new ItemStack(Material.BOW);
                legendaryBow.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
                legendaryBow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
                legendaryBow.addEnchantment(Enchantment.ARROW_FIRE, 1);
                return legendaryBow;
            case 3:
                return new ItemStack(Material.POTION, 1);
            case 4:
                return new ItemStack(Material.ELYTRA, 1);
            case 5:
                ItemStack spiderEye = new ItemStack(Material.SPIDER_EYE);
                spiderEye.addUnsafeEnchantment(Enchantment.LUCK, 10);
                spiderEye.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                return spiderEye;
            case 6:
                ItemStack enchantedGoldenApple = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE);
                return enchantedGoldenApple;
            case 7:
                return new ItemStack(Material.ENDER_EYE, 1);
            case 8:
                return new ItemStack(Material.DIAMOND_BLOCK, 1);
            case 9:
                return new ItemStack(Material.NETHER_STAR, 1);
            case 10:
                ItemStack sharpnessSword = new ItemStack(Material.DIAMOND_SWORD);
                sharpnessSword.addEnchantment(Enchantment.DAMAGE_ALL, 10);
                sharpnessSword.addEnchantment(Enchantment.DURABILITY, 3);
                sharpnessSword.addEnchantment(Enchantment.SWEEPING_EDGE, 3);
                return sharpnessSword;
            case 11:
                ItemStack luckPotion = new ItemStack(Material.POTION);
                luckPotion.addUnsafeEnchantment(Enchantment.LUCK, 10);
                return luckPotion;
            case 12:
                ItemStack strengthPotion = new ItemStack(Material.POTION);
                strengthPotion.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
                return strengthPotion;
            case 13:
                ItemStack speedPotion = new ItemStack(Material.POTION);
                PotionMeta meta = (PotionMeta) speedPotion.getItemMeta();
                meta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 600, 4), true);
                speedPotion.setItemMeta(meta);
                return speedPotion;
            case 14:
                return new ItemStack(Material.GOLDEN_APPLE, 1);
            case 15:
                return new ItemStack(Material.EMERALD, 1);
            case 16:
                return new ItemStack(Material.DRAGON_EGG, 1);
            case 17:
                ItemStack ghastTear = new ItemStack(Material.GHAST_TEAR, 1);
                ghastTear.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
                return ghastTear;
            case 18:
                return new ItemStack(Material.MAGMA_CREAM, 1);
            case 19:
                return new ItemStack(Material.REDSTONE, 1);
            case 20:
                ItemStack ladybug = new ItemStack(Material.AIR);
                ladybug.addUnsafeEnchantment(Enchantment.LUCK, 10);
                ladybug.addUnsafeEnchantment(Enchantment.MENDING, 1);
                return ladybug;
            case 21:
                return new ItemStack(Material.NETHER_WART, 1);
            case 22:
                return new ItemStack(Material.DRAGON_BREATH, 1);
            case 23:
                return new ItemStack(Material.DARK_OAK_LOG, 1);
            case 24:
                return new ItemStack(Material.ELYTRA, 1);
            case 25:
                return new ItemStack(Material.CHARCOAL, 1);
            case 26:
                return new ItemStack(Material.GLOWSTONE_DUST, 1);
            case 27:
                ItemStack enchantedIronSword = new ItemStack(Material.IRON_SWORD);
                enchantedIronSword.addEnchantment(Enchantment.DAMAGE_ALL, 4);
                enchantedIronSword.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                return enchantedIronSword;
            case 28:
                ItemStack crown = new ItemStack(Material.GOLDEN_HELMET);
                crown.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
                crown.addUnsafeEnchantment(Enchantment.THORNS, 3);
                crown.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                return crown;
            case 29:
                return new ItemStack(Material.BONE, 1);
            case 30:
                ItemStack enchantedArrow = new ItemStack(Material.ARROW);
                enchantedArrow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 10);
                enchantedArrow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 5);
                return enchantedArrow;
            case 31:
                ItemStack lightningRod = new ItemStack(Material.STICK);
                lightningRod.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
                lightningRod.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
                return lightningRod;
            case 32:
                return new ItemStack(Material.GOLDEN_APPLE, 1);
            case 33:
                ItemStack luckyArmor = new ItemStack(Material.DIAMOND_CHESTPLATE);
                luckyArmor.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
                luckyArmor.addUnsafeEnchantment(Enchantment.THORNS, 10);
                luckyArmor.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                return luckyArmor;
            case 34:
                ItemStack shovel = new ItemStack(Material.DIAMOND_SHOVEL);
                shovel.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
                shovel.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                return shovel;
            case 35:
                ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
                axe.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
                axe.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                axe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
                return axe;
            case 36:
                ItemStack runningShoes = new ItemStack(Material.DIAMOND_BOOTS);
                runningShoes.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 10);
                runningShoes.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 10);
                runningShoes.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 10);
                runningShoes.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 10);
                runningShoes.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 10);
                runningShoes.addUnsafeEnchantment(Enchantment.SOUL_SPEED, 10);
                runningShoes.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                return runningShoes;
            case 37:
                return new ItemStack(Material.NETHERITE_HOE, 1);
            case 38:
                ItemStack armorBreaker = new ItemStack(Material.DIAMOND_PICKAXE);
                armorBreaker.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
                armorBreaker.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                armorBreaker.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
                return armorBreaker;
            case 39:
                return new ItemStack(Material.DRAGON_HEAD, 1);
            case 40:
                ItemStack enchantedPlate = new ItemStack(Material.CAULDRON);
                enchantedPlate.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 10);
                enchantedPlate.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 5);
                return enchantedPlate;
            case 41:
                ItemStack legendaryAxe = new ItemStack(Material.DIAMOND_AXE);
                legendaryAxe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
                legendaryAxe.addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
                legendaryAxe.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                return legendaryAxe;
            case 42:
                ItemStack enchantedHelmet = new ItemStack(Material.DIAMOND_HELMET);
                enchantedHelmet.addUnsafeEnchantment(Enchantment.OXYGEN, 10);
                enchantedHelmet.addUnsafeEnchantment(Enchantment.WATER_WORKER, 10);
                enchantedHelmet.addUnsafeEnchantment(Enchantment.THORNS, 10);
                enchantedHelmet.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                return enchantedHelmet;
            case 43:
                ItemStack enemySlayer = new ItemStack(Material.DIAMOND_SWORD);
                enemySlayer.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 10);
                enemySlayer.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 10);
                enemySlayer.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 10);
                enemySlayer.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                return enemySlayer;
            case 44:
                ItemStack apocalypseRing = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
                apocalypseRing.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
                apocalypseRing.addUnsafeEnchantment(Enchantment.THORNS, 10);
                apocalypseRing.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                return apocalypseRing;
            case 45:
                ItemStack enchantedFruit = new ItemStack(Material.APPLE);
                enchantedFruit.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 10);
                enchantedFruit.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
                enchantedFruit.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                return enchantedFruit;
            case 46:
                ItemStack armorCarrier = new ItemStack(Material.ENDER_CHEST);
                armorCarrier.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
                armorCarrier.addUnsafeEnchantment(Enchantment.THORNS, 10);
                armorCarrier.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                return armorCarrier;
            case 47:
                ItemStack blackShield = new ItemStack(Material.SHIELD);
                blackShield.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
                blackShield.addUnsafeEnchantment(Enchantment.THORNS, 10);
                blackShield.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                return blackShield;
            case 48:
                ItemStack legendaryArmor = new ItemStack(Material.DIAMOND_CHESTPLATE);
                legendaryArmor.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
                legendaryArmor.addUnsafeEnchantment(Enchantment.THORNS, 10);
                legendaryArmor.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                return legendaryArmor;
            case 49:
                ItemStack dragonEye = new ItemStack(Material.ENDER_PEARL);
                dragonEye.addUnsafeEnchantment(Enchantment.THORNS, 10);
                dragonEye.addUnsafeEnchantment(Enchantment.KNOCKBACK, 10);
                dragonEye.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                return dragonEye;
            default:
                return new ItemStack(Material.AIR);
        }
    }
}
