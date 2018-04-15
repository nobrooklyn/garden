package local.garden.user.domain.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private long height;
    private long weight;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public long getHeight() {
        return height;
    }

    public long getWeight() {
        return weight;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public long age() {
        return ChronoUnit.YEARS.between(birthday, LocalDate.now());
    }
}