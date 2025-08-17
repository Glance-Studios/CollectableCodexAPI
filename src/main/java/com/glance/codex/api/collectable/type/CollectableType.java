package com.glance.codex.api.collectable.type;

import com.glance.codex.api.collectable.Collectable;
import com.glance.codex.api.collectable.CollectableAPI;
import com.glance.codex.utils.data.TypeCodec;
import org.jetbrains.annotations.NotNull;

/**
 * Defines a registered type of {@link Collectable}
 * <p>
 * A collectable type is identified by a unique {@link #id()} and
 * associates a concrete class with a {@link TypeCodec} for
 * configuration serialization and deserialization
 * <p>
 * Example: a {@code "note"} type backed by {@code NoteCollectable.class}
 * with a codec that parses book/lore content from YAML
 *
 * @see Collectable
 * @see CollectableAPI#registerCollectableType(CollectableType)
 *
 * @author Cammy
 */
public interface CollectableType {

    /**
     * @return the unique ID of this collectable type (e.g. "note", "fish")
     */
    @NotNull String id();

    /**
     * @return the concrete class implementing this collectable type
     */
    @NotNull Class<? extends Collectable> type();

    /**
     * @return the codec used to serialize/deserialize this collectable type
     */
    @NotNull
    TypeCodec<? extends Collectable> codec();
}
