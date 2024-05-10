package be.faros.flags.service;

import be.faros.flags.FeatureFlags;
import be.faros.flags.web.dto.FlagDTO;
import org.unleash.features.annotation.Toggle;

import java.util.List;
import java.util.UUID;

public interface FlagService {
    @Toggle(name = FeatureFlags.SAVE_FLAG, alterBean = "newFlagService")
    FlagDTO saveFlag(FlagDTO flagDTO);

    @Toggle(name = FeatureFlags.SAVE_FLAG, alterBean = "newFlagService")
    List<FlagDTO> getFlags(String user);

    @Toggle(name = FeatureFlags.SAVE_FLAG, alterBean = "newFlagService")
    FlagDTO getFlag(UUID id);

    @Toggle(name = FeatureFlags.UPDATE_FLAG, alterBean = "newFlagService")
    FlagDTO updateFlag(UUID id, FlagDTO flagDTO);

    @Toggle(name = FeatureFlags.UPDATE_FLAG, alterBean = "newFlagService")
    void deleteFlag(UUID id);

    @Toggle(name = FeatureFlags.LIKE_FLAG, alterBean = "newFlagService")
    void likeFlag(UUID id);
}
