package springkafkastudy.kafkastudy.model;

import javax.validation.constraints.Email;

public class Member {
    @Email
    private String email;
    private int age;

    public Member() {
    }

    public Member(String email, int age) {
        this.email = email;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Member{" +
                "email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
