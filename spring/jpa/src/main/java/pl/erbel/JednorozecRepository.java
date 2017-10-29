package pl.erbel;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JednorozecRepository extends CrudRepository<Jednorozec, Integer> {

    public List<Jednorozec> findByImie(String imie);

    @Cacheable("jednorozce")
    public List<Jednorozec> findByNazwisko(String nazwisko);

    //    @Query("select j from Jednorozec j where j.nazwisko= ?1")
    @Query(value = "select * from Jednorozec where Jednorozec.nazwisko= ?1", nativeQuery = true)
    public List<Jednorozec> doTheMagic(String nazwisko);

    @Cacheable("magic")
    @Query(value = "select * from Jednorozec where Jednorozec.nazwisko= ?1", nativeQuery = true)
    public List<Jednorozec> doTheCachedMagic(String nazwisko);

}
