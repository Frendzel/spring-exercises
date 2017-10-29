package pl.erbel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Api {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/jednorozec")
    public List<Jednorozec> zajrzyjDoStajni(
            @RequestParam(value = "id", required = false, defaultValue = "10") String id) {
        return jdbcTemplate.query("select * from jednorozec where id= ?", new Object[]{id}, (rs, number) ->
                new Jednorozec(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
    }


}
