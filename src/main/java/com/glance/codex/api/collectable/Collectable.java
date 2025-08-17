package com.glance.codex.api.collectable;

import net.kyori.adventure.text.Component;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a single entry in a {@link CollectableRepository}
 * <p>
 * Collectables are the core units of the Collections system. Each has
 * a display name, icon (locked/unlocked), and metadata describing how it behaves
 * <p>
 * Some collectables may also implement {@link Discoverable} to provide
 * active behavior on unlock or replay
 *
 * @author Cammy
 */
public interface Collectable {

    /**
     * @return the formatted display name of this collectable for UI use
     */
    @NotNull
    Component displayName();

    /**
     * @return the raw, unparsed display name string (MiniMessage or legacy)
     */
    @NotNull String rawDisplayName();

    /**
     * @return the plain-text display name without formatting codes
     */
    @NotNull
    String plainDisplayName();

    /**
     * Returns the item icon shown when the collectable is unlocked
     *
     * @param player the player viewing the menu (maybe null)
     * @return the unlocked icon
     */
    @NotNull
    ItemStack iconUnlocked(@Nullable OfflinePlayer player);

    /**
     * Returns the item icon shown when the collectable is locked
     * <p>
     * Defaults to the unlocked icon, but repositories may override
     * to show a question mark or different placeholder
     *
     * @param player the player viewing the menu (maybe null)
     * @return the locked icon
     */
    @NotNull
    default ItemStack iconLocked(@Nullable OfflinePlayer player) {
        return iconUnlocked(player);
    }

    /**
     * @return true if this collectable should be visible in menus when locked,
     * false if hidden entirely until unlocked
     */
    boolean showWhenLocked();

    /**
     * @return true if players may replay this collectable after unlocking
     */
    boolean allowReplay();

    /**
     * Attaches metadata to this collectable, typically loaded from config
     *
     * @param meta the collectable metadata
     */
    void setMeta(@NotNull CollectableMeta meta);

    /**
     * @return the metadata object for this collectable, or null if none set
     */
    @Nullable
    CollectableMeta getMeta();

}
