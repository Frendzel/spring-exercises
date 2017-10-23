package pl.erbel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JednorozecService {

    @Autowired
    JednorozecRepository jednorozecRepository;

    public JednorozecService() {
    }

    @Transactional
    public void transactionalSave(Jednorozec jednorozec)
            throws RuntimeException {
        jednorozecRepository.save(jednorozec);
        System.out.println(jednorozecRepository.findByImie(jednorozec.getImie()));
        throw new RuntimeException();
    }

    public void save(Jednorozec jednorozec) {
        jednorozecRepository.save(jednorozec);
        System.out.println(jednorozecRepository.findByImie(jednorozec.getImie()));
        throw new RuntimeException();
    }
}
