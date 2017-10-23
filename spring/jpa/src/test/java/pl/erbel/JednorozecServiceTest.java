package pl.erbel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JednorozecServiceTest {

    @Autowired
    JednorozecService jednorozecService;

    @Autowired
    JednorozecRepository jednorozecRepository;

    @Test
    public void transactionalTest() throws Exception {
        //given
        Jednorozec jednorozec =
                new Jednorozec(1, "a", "b", "c", "d");
        //when
        try {
            jednorozecService.transactionalSave(jednorozec);
            fail();
        } catch (RuntimeException e) {
            //then
            assertTrue(jednorozecRepository.findByImie("a").isEmpty());
        }
        try {
            jednorozecService.save(jednorozec);
            fail();
        } catch (RuntimeException e) {
            //then
            assertFalse(jednorozecRepository.findByImie("a").isEmpty());
        }

    }

}