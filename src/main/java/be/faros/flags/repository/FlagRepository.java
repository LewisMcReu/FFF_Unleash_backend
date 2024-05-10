package be.faros.flags.repository;

import be.faros.flags.domain.Flag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FlagRepository {

    final Map<UUID, Flag> flags = new ConcurrentHashMap<>();

    Map<UUID, Flag> getFlags() {
        return flags;
    }

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
