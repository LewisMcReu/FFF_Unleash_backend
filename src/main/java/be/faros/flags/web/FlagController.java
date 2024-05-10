package be.faros.flags.web;

import be.faros.flags.service.FlagService;
import be.faros.flags.web.dto.FlagDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/flags")
public class FlagController {
    private final FlagService flagService;
    private final FlagValidator validator;

    public FlagController(FlagService flagService, FlagValidator validator) {
        this.flagService = flagService;
        this.validator = validator;
    }

    @GetMapping
    public List<FlagDTO> getFlags(@RequestParam(required = false) String user) {
        return flagService.getFlags(user);
    }

    @GetMapping("/{id}")
    public FlagDTO getFlag(@PathVariable UUID id) {
        return flagService.getFlag(id);
    }

    @PostMapping
    public FlagDTO saveFlag(@RequestBody FlagDTO flag) {
        validator.validate(flag);
        return flagService.saveFlag(flag);
    }

    @PutMapping("/{id}")
    public FlagDTO updateFlag(@PathVariable UUID id, @RequestBody FlagDTO flag) {
        validator.validate(flag);
        return flagService.updateFlag(id, flag);
    }

    @DeleteMapping("/{id}")
    public void deleteFlag(@PathVariable UUID id) {
        flagService.deleteFlag(id);
    }
}
