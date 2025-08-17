package com.glance.codex.api.collectable;

import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Represents a namespaced repository of {@link Collectable} entries
 * <p>
 * A repository groups a set of related collectables under a common namespace
 * (e.g. {@code notes}, {@code fish}, {@code music}). Each entry has a unique
 * ID within this repository
 * <p>
 * Repositories are typically defined via configuration, but may also be
 * registered programmatically by other plugins
 *
 * @see Collectable
 * @see Discoverable
 *
 * @author Cammy
 */
public interface CollectableRepository {

    /**
     * @return the namespace identifier for this repository (e.g. {@code "notes"})
     */
    @NotNull String namespace();

    /**
     * @return the formatted display name of this repository for UI use
     */
    @NotNull
    Component displayName();

    /**
     * @return the raw, unformatted display name (MiniMessage or legacy formatting)
     */
    @NotNull String displayNameRaw();

    /**
     * @return the plain-text display name without formatting codes
     */
    @NotNull String plainDisplayName();

    /**
     * @return an immutable map of all collectables in this repository, keyed by their ID
     */
    @NotNull
    Map<String, Collectable> entries();

    /**
     * Returns the icon representing this repository in a menu
     * <p>
     * This may vary depending on player progress (e.g. glowing if all entries are complete)
     *
     * @param player the player viewing the menu (maybe null for a generic icon)
     * @return the repository's icon item
     */
    @NotNull
    ItemStack getRepoIcon(@Nullable OfflinePlayer player);

    /**
     * Returns the icon to show when this repository is currently selected in a menu
     * <p>
     * By default, this is the same as {@link #getRepoIcon(OfflinePlayer)}
     *
     * @param player the player viewing the menu (maybe null)
     * @return the selected-state icon item
     */
    @NotNull
    default ItemStack getSelectedIcon(@Nullable OfflinePlayer player) {
        return getRepoIcon(player);
    }

    /**
     * Retrieves a collectable by its namespaced key
     *
     * @param key the namespaced key (namespace must match this repository)
     * @return the collectable entry, or null if not found
     */
    @Nullable Collectable get(@NotNull NamespacedKey key);

    /**
     * Retrieves a collectable by its simple ID (without namespace)
     *
     * @param id the collectable ID within this repository
     * @return the collectable entry, or null if not found
     */
    default @Nullable Collectable get(@NotNull String id) {
        return get(new NamespacedKey(namespace(), id));
    }

}
