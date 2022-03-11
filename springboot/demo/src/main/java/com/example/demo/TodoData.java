package com.example.demo;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "todo_data")
public class TodoData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column(length = 200)
    @NotEmpty
    private String todo;

    @Column(nullable = true)
    private Date kijitsu;

    @Column(length = 1)
    private Integer sts;

    @Column(name = "delete_flg", length = 1)
    private Integer deleteFlg;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Transient // データベースに登録されないため
    private boolean isCompleted = true;

    @Transient // データベースに登録されないため
    private boolean isDeleted = true;

    public int boolToInt(boolean a) {
        if (a)
            return 1;
        return 0;
    }

    // 追加メソッド
    public void add(String todo, Date kijitsu) {
        setTodo(todo);
        setKijitsu(kijitsu);
        setSts(boolToInt(!isCompleted));
        setDeleteFlg(boolToInt(!isDeleted));
        setCreatedDate(Date.valueOf(LocalDate.now()));
        setUpdatedDate(Date.valueOf(LocalDate.now()));
    }

    // 編集メソッド
    public void edit(String todo, Date kijitsu) {
        setTodo(todo);
        setKijitsu(kijitsu);
        setUpdatedDate(Date.valueOf(LocalDate.now()));
    }

    // 削除メソッド
    public void delete() {
        setDeleteFlg(boolToInt(isDeleted));
        setUpdatedDate(Date.valueOf(LocalDate.now()));
    }

    // 完了メソッド
    public void complete() {
        setSts(boolToInt(isCompleted));
        setUpdatedDate(Date.valueOf(LocalDate.now()));
    }

    // 以下アクセサ
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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
        return this.deleteFlg;
    }

    public void setDeleteFlg(Integer deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

}
