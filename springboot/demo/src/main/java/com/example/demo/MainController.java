package com.example.demo;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*") // よくわからない
public class MainController {

    @RequestMapping("/")
    public ArrayList<DataObject> index() {
        String[] todo = { "ランニングをする", "買い物に行く", "本を読む" };//test-data
        String[] kijitsu = { "2020/07/20", "", "2020/07/23" };//test-data

        ArrayList<DataObject> objs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            objs.add(new DataObject(i, todo[i], kijitsu[i]));
            // DataObjectの配列に格納
        }
        return objs;// 格納した配列
    }
}

class DataObject {
    private int id;
    private String todo;
    private String kijitsu;
    // private int sts;
    // private int delete_flug;
    // private String created_date;
    // private String updated_date;

    public DataObject(int id, String todo, String kijitsu) {
        this.id = id;
        this.todo = todo;
        this.kijitsu = kijitsu;
    }

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
