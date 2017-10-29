package pl.erbel;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Unit test for simple App.
 */

public class AppTest {

    @org.junit.Test
    public void test() {
        String data = "2, \"Judon\", \"Romanini\", \"jromanini1@sun.com\", \"Male\";\n" +
                "3, \"Aurilia\", \"Kersting\", \"akersting2@google.fr\", \"Female\";\n" +
                "4, \"Ulla\", \"Lamden\", \"ulamden3@fotki.com\", \"Female\";\n" +
                "5, \"Jania\", \"Littell\", \"jlittell4@homestead.com\", \"Female\";\n" +
                "6, \"Beitris\", \"Halligan\", \"bhalligan5@reverbnation.com\", \"Female\";\n" +
                "7, \"Jaquenette\", \"Humphris\", \"jhumphris6@amazon.de\", \"Female\";\n" +
                "8, \"Susette\", \"Wescott\", \"swescott7@biblegateway.com\", \"Female\";\n" +
                "9, \"Cecil\", \"Esome\", \"cesome8@ezinearticles.com\", \"Male\";\n" +
                "10, \"Merwyn\", \"Marrion\", \"mmarrion9@omniture.com\", \"Male\";";
        List<String[]> collect = Arrays.stream(data.split(";")).map(s -> s.split(",")).collect(toList());
        System.out.println(collect);
    }
}
