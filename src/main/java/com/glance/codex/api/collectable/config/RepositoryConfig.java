package com.glance.codex.api.collectable.config;

import com.glance.codex.api.collectable.Collectable;
import com.glance.codex.api.collectable.CollectableRepository;
import com.glance.codex.api.collectable.config.model.ItemConfig;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents configuration for a {@link CollectableRepository}
 * <p>
 * Typically loaded from a YAML file, this provides display information,
 * icons, and raw entry nodes for parsing into {@link Collectable} objects
 *
 * @author Cammy
 */
public interface RepositoryConfig {

    /**
     * @return true if this repository is enabled and should be loaded
     */
    boolean enabled();

    /**
     * @return the namespace ID for this repository (e.g. "notes", "fish")
     */
    @NotNull
    String namespace();

    /**
     * @return the formatted display name of this repository
     */
    @NotNull String displayName();

    /**
     * @return the plain-text display name without formatting codes,
     * or null if not explicitly provided
     */
    @Nullable String plainDisplayName();

    /**
     * @return the configured icon for menus
     */
    @NotNull
    ItemConfig icon();

    /**
     * @return the configured icon used when this repository is selected
     */
    @NotNull
    ItemConfig selectedIcon();

    /**
     * Raw node per entry
     * <p>
     * Keys are entry IDs; values are arbitrary maps
     */
    @Nullable
    ConfigurationSection rawEntries();

}
