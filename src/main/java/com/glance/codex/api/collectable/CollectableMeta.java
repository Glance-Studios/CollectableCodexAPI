package com.glance.codex.api.collectable;

/**
 * Metadata associated with a {@link Collectable}
 * <p>
 * This record provides quick access to contextual information about
 * where the collectable belongs and how it is referenced
 *
 * @param namespace the namespace of the repository
 * @param entryId the ID of the collectable within the repository
 * @param repository the repository instance this collectable belongs to
 */
public record CollectableMeta(
    String namespace,
    String entryId,
    CollectableRepository repository
) {
}
