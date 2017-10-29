package pl.erbel;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;

//{
//        "_id": {
//        "$oid": "50906d7fa3c412bb040eb579"
//        },
//        "student_id": 0,
//        "type": "homework",
//        "score": 14.8504576811645
//        }
public class Grades {

    //@Transient
    private ObjectId _id;

    private int student_id;

    private String type;

    private double score;

    public Grades() {
    }

    public Grades(ObjectId _id, int student_id, String type, double score) {
        this._id = _id;
        this.student_id = student_id;
        this.type = type;
        this.score = score;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Grades{" +
                "_id=" + _id +
                ", student_id=" + student_id +
                ", type='" + type + '\'' +
                ", score=" + score +
                '}';
    }
}
