package be.faros.flags;

import io.getunleash.UnleashContext;
import io.getunleash.UnleashContextProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.unleash.features.autoconfigure.UnleashProperties;

@Configuration
public class UnleashConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public UnleashContextProvider unleashContextProvider(final UnleashProperties unleashProperties) {
        return () -> {
            UnleashContext.Builder builder = UnleashContext.builder();
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof User user) {
                builder.userId(user.getUsername());
                builder.addProperty("role", "beta");
            }

            return builder
                    .appName(unleashProperties.getAppName())
                    .environment(unleashProperties.getEnvironment())
                    .build();
        };
    }
}
