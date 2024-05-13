package be.faros.flags.repository;

import be.faros.flags.domain.Flag;
import be.faros.flags.exceptions.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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

    @Test
    @WithMockUser(username = "Sheldon")
    void deleteFlagFailsWithWrongUser() {
        var id = UUID.randomUUID();
        var flag = new Flag(id, "unitTester");
        flagRepository.getFlags().put(id, flag);

        assertThatThrownBy(() -> flagRepository.delete(id))
                .isInstanceOf(EntityNotFoundException.class);
    }


}