package be.faros.flags.service;

import be.faros.flags.domain.*;
import be.faros.flags.exceptions.EntityNotFoundException;
import be.faros.flags.repository.FlagRepository;
import be.faros.flags.web.dto.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("newFlagService")
public class FlagServiceImpl extends NoopFlagService {
    private final FlagRepository repository;

    public FlagServiceImpl(FlagRepository repository) {
        this.repository = repository;
    }

    @Override
    public FlagDTO saveFlag(FlagDTO flagDTO) {
        Flag flag = new Flag(UUID.randomUUID(), getUser());
        mapDTOToFlag(flagDTO, flag);
        flag = repository.save(flag);
        return mapFlagToDTO(flag);
    }

    @Override
    public List<FlagDTO> getFlags(String user) {
        return repository.findAll(user).stream().map(this::mapFlagToDTO).toList();
    }

    @Override
    public FlagDTO getFlag(UUID id) {
        return repository.findById(id).map(this::mapFlagToDTO).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public FlagDTO updateFlag(UUID id, FlagDTO flagDTO) {
        Optional<Flag> byId = repository.findById(id);
        Flag flag = byId.orElseThrow(EntityNotFoundException::new);
        mapDTOToFlag(flagDTO, flag);
        return mapFlagToDTO(repository.save(flag));
    }

    @Override
    public void deleteFlag(UUID id) {
        repository.delete(id);
    }

    @Override
    public void likeFlag(UUID id) {
        Flag flag = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        flag.like();
        repository.save(flag);
    }

    private void mapDTOToFlag(FlagDTO flagDTO, Flag flag) {
        flag.setName(flagDTO.getName());
        flag.setLayers(flagDTO.getLayers().stream().map(dto -> {
            if (dto instanceof BasicLayerDTO bl) {
                BasicLayer l = new BasicLayer();
                l.setColour(bl.getColour());
                l.setType(bl.getType());
                return l;
            } else if (dto instanceof ChargeLayerDTO cl) {
                ChargeLayer l = new ChargeLayer();
                l.setColour(cl.getColour());
                l.setEmblem(cl.getEmblem());
                return l;
            } else if (dto instanceof TribandLayerDTO tl) {
                TribandLayer l = new TribandLayer();
                l.setOrientation(tl.getOrientation());
                l.setFlyColour(tl.getFlyColour());
                l.setHoistColour(tl.getHoistColour());
                l.setPaleColour(tl.getPaleColour());
                return l;
            } else if (dto instanceof BasicScalableLayerDTO sl) {
                BasicScalableLayer l = new BasicScalableLayer();
                l.setType(sl.getType());
                l.setColour(sl.getColour());
                l.setScale(sl.getScale());
                return l;
            }
            throw new IllegalStateException("Unknown layer type");
        }).toList());
    }

    private FlagDTO mapFlagToDTO(Flag flag) {
        FlagDTO dto = new FlagDTO();
        dto.setId(flag.getId());
        dto.setName(flag.getName());
        dto.setLikeCount(flag.getLikeCount());
        dto.setLayers(flag.getLayers().stream().map(layer -> {
            if (layer instanceof BasicLayer bl) {
                BasicLayerDTO lDTO = new BasicLayerDTO();
                lDTO.setColour(bl.getColour());
                lDTO.setType(bl.getType());
                return lDTO;
            } else if (layer instanceof ChargeLayer cl) {
                ChargeLayerDTO lDTO = new ChargeLayerDTO();
                lDTO.setColour(cl.getColour());
                lDTO.setEmblem(cl.getEmblem());
                return lDTO;
            } else if (layer instanceof TribandLayer tl) {
                TribandLayerDTO lDTO = new TribandLayerDTO();
                lDTO.setFlyColour(tl.getFlyColour());
                lDTO.setOrientation(tl.getOrientation());
                lDTO.setHoistColour(tl.getHoistColour());
                lDTO.setPaleColour(tl.getPaleColour());
                return lDTO;
            } else if (layer instanceof BasicScalableLayer sl) {
                BasicScalableLayerDTO lDTO = new BasicScalableLayerDTO();
                lDTO.setType(sl.getType());
                lDTO.setColour(sl.getColour());
                lDTO.setScale(sl.getScale());
                return lDTO;
            }
            throw new IllegalStateException("Unknown layer type");
        }).toList());
        return dto;
    }

    private String getUser() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
