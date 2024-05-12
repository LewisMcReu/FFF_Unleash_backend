package be.faros.flags.service;

import be.faros.flags.SecurityUtilities;
import be.faros.flags.exceptions.EntityNotFoundException;
import be.faros.flags.repository.FlagRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Aspect
@Configuration
public class FlagSecurityAdvice {

    @Autowired
    private FlagRepository flagRepository;

    @Before("execution(* delete(..)) && args(id,..)")
    public void logBeforeAllMethods(UUID id) {
        var user = SecurityUtilities.getUser();
        flagRepository.findById(id).filter(flag -> flag.getUser().equals(user))
                .orElseThrow(EntityNotFoundException::new);
    }


}
