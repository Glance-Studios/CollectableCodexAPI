package com.glance.codex.api.collectable;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an object that can be {@code discovered} by a player within the Collections system
 * <p>
 * A {@link Discoverable} is a subtype of {@link Collectable} that has active behavior when unlocked
 * or revisited
 * <p>
 * Implementations should define what happens when a player first discovers this object
 * and what happens when it is replayed (if {@link Collectable#allowReplay()} returns true)
 *
 * @see Collectable
 * @see CollectableRepository
 *
 * @author Cammy
 */
public interface Discoverable {

    /**
     * Called the first time a player unlocks this discoverable
     * <p>
     * Implementations may open GUIs, show written book content, play sounds,
     * or execute commands based on configuration
     *
     * @param player the player who discovered this entry
     */
    void onDiscover(@NotNull Player player);

    /**
     * Called when a player interacts with this discoverable again after already unlocking it,
     * provided {@link Collectable#allowReplay()} returns true
     * <p>
     * Useful for replaying lore, showing book text again, or retriggering commands/animations
     *
     * @param player the player replaying this discoverable
     */
    void onReplay(@NotNull Player player);

}
