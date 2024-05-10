package be.faros.flags.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class Flag {
    private final UUID id;
    private final String user;
    private String name;
    private List<Layer> layers = new ArrayList<>();
    private final AtomicLong likeCount = new AtomicLong();

    public Flag(UUID id, String user) {
        this.id = id;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public void setLayers(List<Layer> layers) {
        this.layers = layers;
    }

    public UUID getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void like() {
        this.likeCount.incrementAndGet();
    }

    public long getLikeCount() {
        return this.likeCount.get();
    }
}
