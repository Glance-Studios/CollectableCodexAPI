package com.glance.codex.api.collectable;

import com.glance.codex.api.collectable.type.CollectableType;
import com.glance.codex.utils.data.TypeCodec;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * Entry point for interacting with the Collections API from external plugins
 * <p>
 * Provides access to the {@link CollectableManager} and extension points
 * for registering custom {@link CollectableType types}
 * <p>
 * Implementations of this interface are typically exposed via a static
 * getter or service manager lookup
 *
 * @author Cammy
 */
public interface CollectableAPI {

    /**
     * @return the central {@link CollectableManager} for unlock/progress operations
     */
    @NotNull
    CollectableManager collectables();

    /**
     * Registers a new collectable type.
     * <p>
     * Custom types allow plugins to extend the system with new subclasses
     * of {@link Collectable}, complete with a {@link TypeCodec} for
     * serialization and configuration
     *
     * @param type the type to register
     */
    void registerCollectableType(@NotNull CollectableType type);

    /**
     * Retrieves a registered collectable type
     *
     * @param typeId the type ID (case-insensitive)
     * @return the collectable type, if present
     */
    Optional<CollectableType> getCollectableType(@NotNull String typeId);

    /**
     * @return the API version string
     */
    String apiVersion();

}
