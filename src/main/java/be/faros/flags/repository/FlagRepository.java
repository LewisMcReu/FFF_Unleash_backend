package be.faros.flags.repository;

import be.faros.flags.domain.Flag;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FlagRepository {
    private final Map<UUID, Flag> flags = new HashMap<>();

    public Flag save(Flag flag) {
        flags.put(flag.getId(), flag);
        return flag;
    }

    public Optional<Flag> findById(UUID id) {
        return Optional.ofNullable(flags.get(id));
    }

    public List<Flag> findAll(String user) {
        if (user == null) {
            return flags.values().stream().toList();
        } else {
            return flags.values().stream().filter(f -> f.getUser().equals(user)).toList();
        }
    }

    public void delete(UUID id) {
        flags.remove(id);
    }
}
