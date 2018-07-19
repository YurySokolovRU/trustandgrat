package ru.sokolov.jz.thegame.entities;

/**
 * Created by sokolov
 * Created on 13.04.2018.
 */
public class User implements Comparable {
    private String login;
    private String password;
    private String contact;
    private int sex;//1-м;2-ж
    private int age;
    private int income;//1:до15;2:16-40;3:41-70;4:71-120;5:от121
    private int count;//кол-во сыгранных игр!!!TODO важно

    public User() {
    }

    public User(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof User) {
            return getLogin().toLowerCase().compareTo(((User) o).getLogin().toLowerCase());
        } else {
            return 0;
        }
    }
}
