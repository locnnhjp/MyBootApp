package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mydata")
public class MyData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 200, nullable = true)
    private String mail;

    @Column(nullable = true)
    private int age;

    @Column(nullable = true)
    private String memo;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 名前を設定するクラス
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 名前を設定するクラス
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p>
     * メールを取得する。
     * </p>
     * @return mail
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * メールを設定する。
     * @param mail
     * @return null
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
