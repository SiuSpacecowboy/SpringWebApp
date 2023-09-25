package springTest.DataModels;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Player {

    private int id;

    private String classType;

    @NotEmpty(message = "Field must be not empty")
    @Size(min = 1, max = 30, message = "Name should be between 1 and 30")
    private String nickname;

    @Min(value = 14, message = "Is too young for this game")
    @Max(value = 75, message = "Is too old for this game")
    private int age;


    @NotEmpty(message = "Field must be not empty")
    @Email(message = "enter right email")
    private String email;

    public Player() {}

    public Player(int id, String classType, String nickname, int age, String email) {
        this.id = id;
        this.classType = classType;
        this.nickname = nickname;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String name) {
        this.classType = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
