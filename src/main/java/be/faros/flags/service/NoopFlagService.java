package be.faros.flags.service;

import be.faros.flags.exceptions.NotImplementedException;
import be.faros.flags.web.dto.FlagDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("flagService")
public class NoopFlagService implements FlagService {
    @Override
    public FlagDTO saveFlag(FlagDTO flag) {
        throw new NotImplementedException();
    }

    @Override
    public List<FlagDTO> getFlags(String user) {
        throw new NotImplementedException();
    }

    @Override
    public FlagDTO getFlag(UUID id) {
        throw new NotImplementedException();
    }

    @Override
    public FlagDTO updateFlag(UUID id, FlagDTO flagDTO) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteFlag(UUID id) {
        throw new NotImplementedException();
    }

    @Override
    public void likeFlag(UUID id) {
        throw new NotImplementedException();
    }
}
