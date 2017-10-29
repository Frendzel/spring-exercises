package pl.erbel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Manager {

    @NotNull
    @Size(min = 20, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 20, max = 30)
    private String lastName;

    @Min(0)
    @Max(150)
    private int age;

    @NotNull
    @Size(min = 30, max = 50)
    private String company;

    public Manager(String firstName, String lastName, int age, String company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.company = company;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", company='" + company + '\'' +
                '}';
    }
}
