package com.glance.codex.api.collectable;

import com.glance.codex.api.collectable.config.RepositoryConfig;
import com.glance.codex.utils.lifecycle.Manager;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Central service for managing {@link CollectableRepository repositories}
 * and per-player unlock state
 * <p>
 * The {@code CollectableManager} is responsible for:
 * <ul>
 *   <li>Loading repositories from configuration ({@link #loadFromConfig(RepositoryConfig)})</li>
 *   <li>Registering new repositories programmatically</li>
 *   <li>Handling player unlock/relock/clear operations asynchronously</li>
 *   <li>Querying unlocked progress for players</li>
 * </ul>
 * <p>
 * All persistence and player-specific operations are asynchronous and return
 * {@link CompletableFuture} results to avoid blocking the server thread
 *
 * @see CollectableRepository
 * @see Collectable
 *
 * @author Cammy
 */
public interface CollectableManager extends Manager {

    /**
     * Loads a repository definition from configuration and registers it
     *
     * @param config the configuration describing the repository
     */
    void loadFromConfig(RepositoryConfig config);

    /**
     * Registers a repository instance
     *
     * @param repo the repository to register
     */
    void registerRepository(CollectableRepository repo);

    /**
     * @return all currently registered repositories
     */
    Collection<CollectableRepository> getRepositories();

    /**
     * Looks up a repository by its namespace
     *
     * @param namespace the namespace identifier (e.g. "notes")
     * @return the repository, or null if not registered
     */
    @Nullable CollectableRepository getRepo(@NotNull String namespace);

    /**
     * Looks up a collectable by its full namespaced key
     *
     * @param key the namespaced key of the collectable
     * @return the collectable, or null if not found
     */
    @Nullable Collectable get(@NotNull NamespacedKey key);

    /**
     * Unlocks a collectable for a player
     *
     * @param player the player to unlock for
     * @param key the collectable ID
     * @return a future completed with {@code true} if the 'unlock' succeeded,
     * {@code false} if the collectable was already unlocked (and replay not allowed)
     */
    CompletableFuture<Boolean> unlock(@NotNull Player player, NamespacedKey key);

    /**
     * Checks if a collectable is unlocked for a player
     *
     * @param player the player to check
     * @param key the collectable ID
     * @return a future completed with {@code true} if unlocked, else false
     */
    CompletableFuture<Boolean> isUnlocked(@NotNull Player player, NamespacedKey key);

    /**
     * Retrieves the set of unlocked IDs within a given repository for a player
     *
     * @param player the player
     * @param namespace the repository namespace
     * @return a future completed with the set of unlocked entry IDs
     */
    CompletableFuture<Set<String>> unlockedIds(@NotNull Player player, @NotNull String namespace);

    /**
     * Relocks a collectable for a player, removing it from their unlocked state
     *
     * @param player the player
     * @param key the collectable ID
     * @return a future completed with {@code true} if the relock succeeded, else false
     */
    CompletableFuture<Boolean> relock(@NotNull Player player, @NotNull NamespacedKey key);

    /**
     * Clears all unlocked progress in a repository for a player
     *
     * @param player the player
     * @param namespace the repository namespace
     * @return a future completed with the number of entries cleared
     */
    CompletableFuture<Integer> clearRepo(@NotNull Player player, @NotNull String namespace);

    /**
     * Clears all unlocked progress across all repositories for a player
     *
     * @param player the player
     * @return a future completed when all progress is cleared
     */
    CompletableFuture<Void> clearAll(@NotNull Player player);

}
