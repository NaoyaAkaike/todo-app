package com.example.demo;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name = "todo_data")
public class TodoData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column(length = 200)
    @NotEmpty
    private String todo;

    @Column(nullable = true)
    private Date kijitsu;

    @Column(length = 1)
    private Integer sts;

    @Column(length = 1)
    private Integer delete_flg;

    @Column
    private Date created_date;

    @Column
    private Date updated_date;

    private boolean isCompleted = true;     //データベースに登録されちゃってる（修正必要）
    private boolean isDeleted = true;       //データベースに登録されちゃってる（修正必要）
    public int boolToInt(boolean a) {
        if(a)
            return 1;
            return 0;
    }
    //追加メソッド
    public void add(String todo, Date kijitsu) {
        setTodo(todo);
        setKijitsu(kijitsu);
        setSts(boolToInt(!isCompleted));
        setDeleteFlg(boolToInt(!isDeleted));
        setCreatedDate(Date.valueOf(LocalDate.now()));
        setUpdatedDate(Date.valueOf(LocalDate.now()));
    }
    //編集メソッド
    public void edit(String todo, Date kijitsu) {
        setTodo(todo);
        setKijitsu(kijitsu);
        setUpdatedDate(Date.valueOf(LocalDate.now()));
    }
    //削除メソッド
    public void delete() {
        setDeleteFlg(boolToInt(isDeleted));
        setUpdatedDate(Date.valueOf(LocalDate.now()));
    }
    //完了メソッド
    public void complete() {
        setSts(boolToInt(isCompleted));
        setUpdatedDate(Date.valueOf(LocalDate.now()));
    }

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
