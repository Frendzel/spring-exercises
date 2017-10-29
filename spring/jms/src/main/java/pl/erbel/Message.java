package pl.erbel;

import java.io.Serializable;

public class Message implements Serializable {

    private int id;

    private String topic;

    private String content;

    public Message() {
    }

    public Message(int id, String topic, String content) {
        this.id = id;
        this.topic = topic;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
