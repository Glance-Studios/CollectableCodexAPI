package com.glance.codex.api.collectable.config.model;

import com.glance.codex.utils.item.LoreMergeMode;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

/**
 * Configuration for building an {@link ItemStack} icon
 * <p>
 * Provides material, display name, lore, flags, and optional
 * custom model data or item component definitions
 *
 * @author Cammy
 */
public interface ItemConfig {

    /**
     * @return the item material type
     */
    Material material();

    /**
     * @return the raw, unformatted display name string
     */
    default String rawDisplayName() {
        return "";
    }

    /**
     * @return the configured item lore lines
     */
    default List<String> lore() {
        return List.of();
    }

    /**
     * @return the lore merge strategy to apply when combining
     * with existing item metadata
     */
    default LoreMergeMode loreMergeMode() {
        return LoreMergeMode.REPLACE;
    }

    /**
     * @return true if the item should appear with an enchantment glint
     */
    default boolean glint() {
        return false;
    }

    /**
     * @return the custom model data value, or 0 if not set
     */
    default Integer customModelData() {
        return 0;
    }

    /**
     * @return item flags to apply (e.g. hide attributes, hide enchantments)
     */
    default List<ItemFlag> flags() {
        return List.of();
    }

    /**
     * @return raw item component values (1.20.5+), keyed by component ID
     */
    default Map<String, Object> itemComponents() {
        return Map.of();
    }

    /**
     * @return configuration for line wrapping of display/lore text
     */
    default LineWrapConfig lineWrap() {
        return new LineWrapConfig(){};
    }

    /**
     * @return true if either custom model data or item components are present
     */
    default boolean hasModelData() {
        return customModelData() != null || (itemComponents() != null && !itemComponents().isEmpty());
    }

}
