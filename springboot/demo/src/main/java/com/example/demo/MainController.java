package com.example.demo;

import java.sql.Date;
import java.time.LocalDate;

import com.example.demo.repositories.TodoDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class MainController {

    @Autowired
    TodoDataRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public Iterable<TodoData> show() {
        Iterable<TodoData> list = repository.findAll();
        return list;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public void add(@RequestBody Param param) {
        //受け取ったオブジェクト
        String todo = param.getTodo();
        String kijitsu = param.getKijitsu();

        //データベースに追加
        TodoData data = new TodoData();
        data.setTodo(todo);
        data.setKijitsu(Date.valueOf(kijitsu));
        data.setSts(0);
        data.setDeleteFlg(0);
        data.setCreatedDate(Date.valueOf(LocalDate.now()));
        data.setUpdatedDate(Date.valueOf(LocalDate.now()));
        repository.saveAndFlush(data);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public void edit(@RequestBody Param param) {
        //受け取ったオブジェクト
        String todo = param.getTodo();
        Date kijitsu = Date.valueOf(param.getKijitsu());
        String preTodo = param.getPreTodo();
        Date preKijitsu = Date.valueOf(param.getPreKijitsu());
        
        //データベースに追加
        TodoData data = repository.findByTodoAndKijitsu(preTodo,preKijitsu);
        data.setTodo(todo);
        data.setKijitsu(kijitsu);
        data.setUpdatedDate(Date.valueOf(LocalDate.now()));
        repository.saveAndFlush(data);     
    }

    @RequestMapping("/delete")
    @Transactional(readOnly = false)
    public void delete(@RequestBody Param param) {
        String preTodo = param.getPreTodo();
        Date preKijitsu = Date.valueOf(param.getPreKijitsu());

        TodoData data = repository.findByTodoAndKijitsu(preTodo, preKijitsu);
        data.setDeleteFlg(1);
        repository.saveAndFlush(data);
    }

    
    static class Param {
        private String todo;
        private String kijitsu;
        private String preTodo;
        private String preKijitsu;

        public String getTodo() {
            return this.todo;
        }
        public String getKijitsu() {
            return this.kijitsu;
        }
        public String getPreTodo() {
            return this.preTodo;
        }
        public String getPreKijitsu() {
            return this.preKijitsu;
        }
    }
}