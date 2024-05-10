package be.faros.flags.web;

import be.faros.flags.service.FlagService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/flags/like")
public class Countroller {
    private final FlagService flagService;

    public Countroller(FlagService flagService) {
        this.flagService = flagService;
    }

    @PostMapping("/{id}")
    public void getCounts(@PathVariable UUID id) {
        flagService.likeFlag(id);
    }
}
