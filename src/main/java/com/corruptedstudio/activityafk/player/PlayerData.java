package com.corruptedstudio.activityafk.player;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Persistent player state for ActivityAFK.
 */
public final class PlayerData {

    private final UUID uuid;
    private String username;
    private Instant registeredAt;
    private Instant lastSeenAt;
    private PlayerStatus status = PlayerStatus.ACTIVE;
    private final PlayerStatistics statistics = new PlayerStatistics();

    public PlayerData(UUID uuid, String username, Instant registeredAt) {
        this.uuid = Objects.requireNonNull(uuid, "uuid");
        this.username = Objects.requireNonNull(username, "username");
        this.registeredAt = Objects.requireNonNull(registeredAt, "registeredAt");
        this.lastSeenAt = registeredAt;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = Objects.requireNonNull(username, "username");
    }

    public Instant getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(Instant registeredAt) {
        this.registeredAt = Objects.requireNonNull(registeredAt, "registeredAt");
    }

    public Instant getLastSeenAt() {
        return lastSeenAt;
    }

    public void setLastSeenAt(Instant lastSeenAt) {
        this.lastSeenAt = Objects.requireNonNull(lastSeenAt, "lastSeenAt");
    }

    public PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(PlayerStatus status) {
        this.status = Objects.requireNonNull(status, "status");
    }

    public PlayerStatistics getStatistics() {
        return statistics;
    }
}
