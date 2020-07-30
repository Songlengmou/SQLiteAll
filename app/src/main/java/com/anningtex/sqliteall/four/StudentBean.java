package com.anningtex.sqliteall.four;

/**
 * @author Administrator
 */
public class StudentBean {
    private int id;
    private String name;
    private int age;
    private long phone;
    private String address;
    private String classes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public int getId() {
        return id;
    }

    public StudentBean(int id, String name, int age, long phone, String address, String classes) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.address = address;
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", age=" + age + ", phone=" + phone + ", address=" + address
                + ", classes=" + classes + "]";
    }
}
