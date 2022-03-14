package com.example.demo;

import java.sql.Date;

public class TodoDataObj {
    private Integer id;
    private String todo;
    private Date kijitsu;
    // private Integer sts;
    // private Integer deleteFlg;
    // private Date createdDate;
    // private Date updatedDate;
    private boolean isCompleted;

    public TodoDataObj(TodoData data) {
        setId(data.getId());
        setTodo(data.getTodo());
        setKijitsu(data.getKijitsu());
        setIsCompleted(intToBool(data.getSts()));
    }

    public boolean intToBool(int a) {
        return a==1;
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

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
    /*
     * public Integer getSts() {
     * return this.sts;
     * }
     * 
     * public void setSts(Integer sts) {
     * this.sts = sts;
     * }
     * 
     * public Integer getDeleteFlg() {
     * return this.deleteFlg;
     * }
     * 
     * public void setDeleteFlg(Integer deleteFlg) {
     * this.deleteFlg = deleteFlg;
     * }
     * 
     * public Date getCreatedDate() {
     * return createdDate;
     * }
     * 
     * public void setCreatedDate(Date createdDate) {
     * this.createdDate = createdDate;
     * }
     * 
     * public Date getUpdatedDate() {
     * return updatedDate;
     * }
     * 
     * public void setUpdatedDate(Date updatedDate) {
     * this.updatedDate = updatedDate;
     * }
     */
}
