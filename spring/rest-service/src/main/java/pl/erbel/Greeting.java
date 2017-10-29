package pl.erbel;

public class Greeting {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Greeting() {
    }

    public Greeting(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "name='" + name + '\'' +
                '}';
    }
}
