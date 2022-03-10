package com.example.demo;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

public class Param {

    @NotEmpty
    private String todo;

    @Column(length = 50, nullable = true)
    @DateTimeFormat(pattern =  "yyyy/MM/dd")
    private String kijitsu;

    @Column(nullable = true)
    private String preTodo;

    @Column(nullable = true)
    private String preKijitsu;

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

    public String getPreTodo() {
        return this.preTodo;
    }

    public void setPreTodo(String preTodo) {
        this.preTodo = preTodo;
    }

    public String getPreKijitsu() {
        return this.preKijitsu;
    }

    public void setPreKijitsu(String preKijitsu) {
        this.preKijitsu = preKijitsu;
    }
}
