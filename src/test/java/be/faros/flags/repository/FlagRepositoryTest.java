package be.faros.flags.repository;

import be.faros.flags.domain.Flag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class FlagRepositoryTest {

    @Autowired
    private FlagRepository flagRepository;

    @BeforeEach
    void setUp() {
        flagRepository.getFlags().clear();
    }

    @Test
    void saveSomething() {
        var id = UUID.randomUUID();
        var flag = new Flag(id, "unitTester");

        flagRepository.save(flag);

        assertThat(flagRepository.getFlags())
                .isNotEmpty()
                .hasSize(1)
                .containsEntry(id, flag);
    }

    @Test
    @WithMockUser(username = "unitTester")
    void deleteFlag() {
        var id = UUID.randomUUID();
        var flag = new Flag(id, "unitTester");
        flagRepository.getFlags().put(id, flag);

        flagRepository.delete(id);
    }


}