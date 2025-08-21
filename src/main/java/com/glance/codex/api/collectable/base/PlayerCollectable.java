package com.glance.codex.api.collectable.base;

import com.glance.codex.api.collectable.Collectable;
import com.glance.codex.api.collectable.Discoverable;
import com.glance.codex.api.collectable.config.model.command.CommandConfig;
import com.glance.codex.api.collectable.config.model.command.CommandInfo;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Abstract base class for a {@link Collectable} that is also {@link Discoverable}
 * <p>
 * Provides convenience hooks for:
 * <ul>
 *   <li>Global broadcast messages when discovered or replayed</li>
 *   <li>Per-player messages when discovered or replayed</li>
 *   <li>Configured command execution on discover/replay</li>
 *   <li>Default no-op {@link #onDiscover(Player)} and {@link #onReplay(Player)} implementations</li>
 * </ul>
 * <p>
 * Subclasses can override any combination of these methods to implement
 * custom unlock behavior, such as playing sounds, executing commands,
 * or opening menus/books for the player
 *
 * <p><b>Example usage:</b></p>
 * <pre>{@code
 * public class NoteCollectable extends PlayerCollectable {
 *     @Override
 *     public String playerMessageOnDiscover() {
 *         return "<gold>You discovered a mysterious note!</gold>";
 *     }
 *
 *     @Override
 *     public void onDiscover(Player player) {
 *         // Open a written book or trigger other effects
 *     }
 * }
 * }</pre>
 *
 * True best usage is as a Config Handler class
 *
 * @author Cammy
 */
public abstract class PlayerCollectable implements Collectable, Discoverable {

    /* Discover Messages Commands */

    /**
     * @return a global broadcast message shown to all players when this collectable is discovered,
     * or an empty string if none
     */
    public String globalMessageOnDiscover() {
        return "";
    }

    /**
     * @return a global broadcast message shown to all players when this collectable is replayed,
     * or the discover message by default
     */
    public String globalMessageOnReplay() {
        return globalMessageOnDiscover();
    }

    /**
     * @return a private message shown to the discovering player,
     * or an empty string if none
     */
    public String playerMessageOnDiscover() {
        return "";
    }

    /**
     * @return a private message shown to the replaying player,
     * or the discover message by default
     */
    public String playerMessageOnReplay() {
        return playerMessageOnDiscover();
    }

    /* Discover Commands */

    /**
     * @return the command set to execute when discovered,
     * or an empty command config if none
     */
    public CommandConfig<? extends CommandInfo> commandsOnDiscover() {
        return new CommandConfig<>(){};
    }

    /**
     * @return the command set to execute when replayed,
     * or the discover command config by default
     */
    public CommandConfig<? extends CommandInfo> commandsOnReplay() {
        return commandsOnDiscover();
    }

    /* Menu Commands */

    /**
     * @return the command set to execute when the unlocked menu item
     * is left-clicked
     * <p>
     * defaults to null
     */
    public @Nullable CommandConfig<? extends CommandInfo> commandsOnMenuLeftClick() { return null; }

    /**
     * @return the command set to execute when the unlocked menu item
     * is right-clicked
     * <p>
     * defaults to left-clicks setting
     */
    public @Nullable CommandConfig<? extends CommandInfo> commandsOnMenuRightClick() {
        return commandsOnMenuLeftClick();
    }

    /**
     * @return the command set to execute when the unlocked menu item
     * is shift-clicked
     * <p>
     * defaults to left-clicks setting
     */
    public @Nullable CommandConfig<? extends CommandInfo> commandsOnShiftClick() {
        return commandsOnMenuLeftClick();
    }

    @Override
    public void onDiscover(@NotNull Player player) {
        // default no-op
    }

    @Override
    public void onReplay(@NotNull Player player) {
        // default no-op
    }

}
