package com.example.demo;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

public class Param {

    private int id;

    @NotEmpty
    private String todo;

    @Column(length = 50, nullable = true)
    private String kijitsu;

    //以下アクセサ
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTodo() {
        return this.todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getKijitsu() {
        return this.kijitsu;
    }

    public void setKijitsu(String kijitsu) {
        this.kijitsu = kijitsu;
    }
}
