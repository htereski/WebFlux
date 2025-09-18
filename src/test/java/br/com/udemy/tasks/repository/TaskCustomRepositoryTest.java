package br.com.udemy.tasks.repository;

import br.com.udemy.tasks.model.Task;
import br.com.udemy.tasks.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskCustomRepositoryTest {

    @InjectMocks
    private TaskCustomRepository customRepository;

    @Mock
    private ReactiveMongoOperations mongoOperations;

    @Test
    void customRepository_mustReturnPageWithOneElement_whenSendTask() {
        Task task = TestUtils.buildValidTask();

        when(mongoOperations.find(any(), any())).thenReturn(Flux.just(task));
        when(mongoOperations.count(any(Query.class), eq(Task.class))).thenReturn(Mono.just(1L));

        Mono<Page<Task>> result = customRepository.findPaginated(task, 0, 10);

        assertNotNull(result);
        assertEquals(1, Objects.requireNonNull(result.block()).getNumberOfElements());
    }
}
