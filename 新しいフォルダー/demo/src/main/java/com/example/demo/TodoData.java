package com.example.demo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tododata")
public class TodoData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column(length = 20, nullable = false)
    private String todo;

    @Column(nullable = true)
    private Date kijitsu;

    @Column(length = 1, nullable = false)
    private Integer sts;

    @Column(length = 1, nullable = false)
    private Integer delete_flg;

    @Column(nullable = false)
    private Date created_date;

    @Column(nullable = false)
    private Date updated_date;

    // 以下アクセサ
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTodo() {
        return this.todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public Date getKijitsu() {
        return this.kijitsu;
    }

    public void setKijitsu(Date kijitsu) {
        this.kijitsu = kijitsu;
    }

    public Integer getSts() {
        return this.sts;
    }

    public void setSts(Integer sts) {
        this.sts = sts;
    }

    public Integer getDeleteFlg() {
        return this.delete_flg;
    }

    public void setDeleteFlg(Integer delete_flg) {
        this.delete_flg = delete_flg;
    }

    public Date getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(Date created_date) {
        this.created_date = created_date;
    }

    public Date getUpdatedDate() {
        return updated_date;
    }

    public void setUpdatedDate(Date updated_date) {
        this.updated_date = updated_date;
    }

}
