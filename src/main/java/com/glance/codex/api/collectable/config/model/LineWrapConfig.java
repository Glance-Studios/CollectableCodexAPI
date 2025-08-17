package com.glance.codex.api.collectable.config.model;

/**
 * Configuration options for wrapping lore or text lines
 * <p>
 * Provides maximum line length and whether word-breaking is permitted
 *
 * @author Cammy
 */
public interface LineWrapConfig {

    /**
     * @return maximum characters per line, or -1 for unlimited
     */
    default Integer maxLineLength() {
        return -1;
    }

    /**
     * @return true if words may be broken to fit within line length,
     * false to only break on whitespace
     */
    default boolean breakWords() {
        return false;
    }

}
