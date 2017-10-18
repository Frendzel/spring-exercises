package pl.erbel;


/**
 * {
 * "type": "success",
 * "value": {
 * "id": 101,
 * "joke": "Archeologists unearthed an old english dictionary dating back to the year 1236. It defined &quot;victim&quot; as &quot;one who has encountered Chuck Norris&quot;",
 * "categories": []
 * }
 * }
 */
public class Joke {

    String type;

    JokeContent value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JokeContent getValue() {
        return value;
    }

    public void setValue(JokeContent value) {
        this.value = value;
    }

    public Joke() {
    }

    public Joke(String type, JokeContent value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
