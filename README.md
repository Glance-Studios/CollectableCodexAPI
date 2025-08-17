# Collectable Codex API

The **Collectable Codex API** is a lightweight public library for integrating with the **Collections / Discoverables system**.  
It provides a generic, extensible framework for managing *collectables* in Minecraft servers, such as lore notes, recipes, rare fish, music, or hidden easter eggs

This API is designed for **plugin developers** who want to register new collectable types, repositories, or custom unlock triggers

---

## Features

- Generic and modular system - supports any type of collectable
- Configuration-driven repositories and entries
- Simple commands and API hooks for triggering discoveries
- Per-player unlock tracking with replay support
- Extendable with custom `CollectableType` implementations

---

## 📦 Getting Started

### Gradle (Kotlin DSL)

```kotlin
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/Glance-Studios/CollectableCodexAPI")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GPR_USER")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GPR_TOKEN")
        }
    }
}

dependencies {
    compileOnly("com.glance.codex:codex-api:1.0.0")
}
```

---

## Core Concepts

### Collectable
Represents a single unlockable entry (e.g. a note, recipe, or artifact)
- Has display name, icons (locked/unlocked), and metadata
- May implement `Discoverable` for custom unlock/replay logic

### CollectableRepository
Groups related collectables under a namespace (e.g. `notes`, `fish`)
- Provides display name and icons for menus
- Manages all entries within its namespace

### CollectableManager
Central service for:
- Loading repositories from config
- Registering repositories programmatically
- Unlocking/relocking/clearing progress
- Querying unlocked collectables per player

All operations return `CompletableFuture` to ensure async safety

### CollectableType
Defines a type of collectable, binding:
- A unique type ID
- A `Collectable` implementation class
- A `TypeCodec` for config (de)serialization

Plugins can register custom types through the API

### CollectableAPI
Entry point to the system:
- Access the global `CollectableManager`
- Register or query `CollectableType`s
- Get the API version string

---

## 🔌 Accessing the API

There are two common ways to access the API from your plugin:

### 1. Via Bukkit Services

```java
CollectableAPI api = Bukkit.getServicesManager().load(CollectableAPI.class);
if (api != null) {
    api.collectables().unlock(player, new NamespacedKey("notes", "forgotten_path"));
}
```

### 2. Using the Codex Utility

The API also provides a static helper for convenience:

```java
Codex.api();
```

---

## 🔌 Example Usage

### Registering a Custom Collectable Type
```java
public final class FishType implements CollectableType {
@Override
public @NotNull String id() {
return "fish";
}

    @Override
    public @NotNull Class<? extends Collectable> type() {
        return FishCollectable.class;
    }

    @Override
    public @NotNull TypeCodec<? extends Collectable> codec() {
        return new FishCollectableCodec();
    }
}

// Registering via API
CollectableAPI api = ...;
api.registerCollectableType(new FishType());
```

### Unlocking a Collectable
```java
NamespacedKey key = new NamespacedKey("notes", "forgotten_path");
api.collectables().unlock(player, key).thenAccept(success -> {
    if (success) {
        player.sendMessage("You discovered a new note!");
    }
});
```

---

## 🔧 Extending PlayerCollectable

For most custom implementations, you’ll want to extend the abstract base class `PlayerCollectable`.  
It already implements `Collectable` and `Discoverable`, and provides convenience hooks for:

- Global broadcast messages on discover/replay
- Per-player messages on discover/replay
- Config-driven command execution
- Default no-op `onDiscover(Player)` and `onReplay(Player)` methods you can override

This makes it simple to add custom behavior without boilerplate.

```java
public class NoteCollectable extends PlayerCollectable {
    @Override
    public String playerMessageOnDiscover() {
        return "<gold>You discovered a mysterious note!</gold>";
    }

    @Override
    public void onDiscover(Player player) {
        // Example: open a written book or trigger sound effects
        player.sendMessage("Opening your mysterious note...");
    }

    @Override
    public void onReplay(Player player) {
        // Example: allow the player to re-read content later
        player.sendMessage("Re-reading the mysterious note...");
    }
}
```

👉 You can override any combination of message, command, or event methods to tailor how your collectable behaves when unlocked or replayed

---
