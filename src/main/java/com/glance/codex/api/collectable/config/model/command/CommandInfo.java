package com.glance.codex.api.collectable.config.model.command;

/**
 * Represents a single configured command line
 * <p>
 * Provides the command string and whether it should be executed
 * by console or by the player
 *
 * @author Cammy
 */
public interface CommandInfo {

    /**
     * @return the execution context for this command
     */
    default Target runAs() {
        return Target.CONSOLE;
    }

    /**
     * @return the command string to execute (without leading slash)
     */
    String command();

    /**
     * Execution target for commands
     */
    enum Target {
        CONSOLE,
        PLAYER
    }

}
