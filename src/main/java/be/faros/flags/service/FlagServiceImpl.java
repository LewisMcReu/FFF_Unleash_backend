package be.faros.flags.service;

import be.faros.flags.SecurityUtilities;
import be.faros.flags.domain.BasicLayer;
import be.faros.flags.domain.ChargeLayer;
import be.faros.flags.domain.Flag;
import be.faros.flags.domain.TribandLayer;
import be.faros.flags.exceptions.EntityNotFoundException;
import be.faros.flags.repository.FlagRepository;
import be.faros.flags.web.dto.BasicLayerDTO;
import be.faros.flags.web.dto.ChargeLayerDTO;
import be.faros.flags.web.dto.FlagDTO;
import be.faros.flags.web.dto.TribandLayerDTO;
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
        Flag flag = new Flag(UUID.randomUUID(), SecurityUtilities.getUser());
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
                BasicLayer basicLayer = new BasicLayer();
                basicLayer.setColour(bl.getColour());
                basicLayer.setType(bl.getType());
                return basicLayer;
            } else if (dto instanceof ChargeLayerDTO cl) {
                ChargeLayer chargeLayer = new ChargeLayer();
                chargeLayer.setColour(cl.getColour());
                chargeLayer.setEmblem(cl.getEmblem());
                chargeLayer.setHorizontalOffsetPercentage(cl.getHorizontalOffsetPercentage());
                chargeLayer.setVerticalOffsetPercentage(cl.getVerticalOffsetPercentage());
                chargeLayer.setScaling(cl.getScaling());
                return chargeLayer;
            } else if (dto instanceof TribandLayerDTO tl) {
                TribandLayer tribandLayer = new TribandLayer();
                tribandLayer.setFlyColour(tl.getFlyColour());
                tribandLayer.setOrientation(tl.getOrientation());
                tribandLayer.setHoistColour(tl.getHoistColour());
                tribandLayer.setPaleColour(tl.getPaleColour());
                return tribandLayer;
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
                lDTO.setHorizontalOffsetPercentage(cl.getHorizontalOffsetPercentage());
                lDTO.setVerticalOffsetPercentage(cl.getVerticalOffsetPercentage());
                lDTO.setScaling(cl.getScaling());
                return lDTO;
            } else if (layer instanceof TribandLayer tl) {
                TribandLayerDTO lDTO = new TribandLayerDTO();
                lDTO.setFlyColour(tl.getFlyColour());
                lDTO.setOrientation(tl.getOrientation());
                lDTO.setHoistColour(tl.getHoistColour());
                lDTO.setPaleColour(tl.getPaleColour());
                return lDTO;
            }
            throw new IllegalStateException("Unknown layer type");
        }).toList());
        return dto;
    }

}
