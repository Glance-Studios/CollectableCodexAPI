package com.glance.codex.api.collectable.config.model.command;

import java.util.List;

/**
 * Base configuration contract for entries that define a list of commands
 * <p>
 * Often used for "commands on discover/replay" sections in collectable configs
 *
 * @param <T> the command info type
 *
 * @author Cammy
 */
public interface CommandConfig<T extends CommandInfo> {

    /**
     * @return true if this command set is enabled
     */
    default boolean enabled() {
        return true;
    }

    /**
     * @return the list of commands to run
     */
    default List<T> commands() {
        return List.of();
    }

    /**
     * @return true if no commands are defined or the list is empty
     */
    default boolean isEmpty() {
        return (commands() == null || commands().isEmpty());
    }

}
