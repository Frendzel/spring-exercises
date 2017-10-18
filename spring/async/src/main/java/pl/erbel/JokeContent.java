package pl.erbel;

import java.util.List;

public class JokeContent {

    int id;

    String joke;

    List<String> categories;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    //TODO SPRAWDZIC
    public JokeContent() {
    }

    public JokeContent(int id, String joke, List<String> categories) {
        this.id = id;
        this.joke = joke;
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "JokeContent{" +
                "id=" + id +
                ", joke='" + joke + '\'' +
                ", categories=" + categories +
                '}';
    }
}
