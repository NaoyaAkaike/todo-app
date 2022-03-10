package com.example.demo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.example.demo.repositories.TodoDataRepository;

import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin("*")
public class MainController {

    

    @Autowired
    TodoDataRepository repository;

    //全リスト
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public Iterable<TodoData> show() {
        Iterable<TodoData> list = repository.findAll();
        return list;
    }

    //追加メソッド
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public void add(@RequestBody @Validated Param param, BindingResult result) {

        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String str = dtf.format(LocalDate.parse(param.getKijitsu(), dtf));

            if (!result.hasErrors()){
                //受け取ったオブジェクト
            String todo = param.getTodo();
            Date kijitsu = Date.valueOf(str);
    
            //データベースに追加
            TodoData data = new TodoData();
            data.add(todo, kijitsu);
            repository.saveAndFlush(data);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "02");        //todoがエラー
            }
        } catch (DateTimeParseException dtp){
            if (!result.hasErrors()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "03");        //kijitsuがエラー
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "01");        //両方エラー
            }
        }         
    }

    //編集メソッド
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public void edit(@RequestBody @Validated Param param, BindingResult result) {
        if (!result.hasErrors()) {
            //受け取ったオブジェクト
        String todo = param.getTodo();
        Date kijitsu = Date.valueOf(param.getKijitsu());
        String preTodo = param.getPreTodo();
        Date preKijitsu = Date.valueOf(param.getPreKijitsu());
        
        //データベースに追加
        TodoData data = repository.findByTodoAndKijitsu(preTodo,preKijitsu);
        data.edit(todo, kijitsu);
        repository.saveAndFlush(data);
        } else {
            //エラー時の処理
        }
        
    }

    //削除メソッド
    @RequestMapping("/delete")
    @Transactional(readOnly = false)
    public void delete(@RequestBody Param param) {
        String preTodo = param.getPreTodo();
        Date preKijitsu = Date.valueOf(param.getPreKijitsu());

        TodoData data = repository.findByTodoAndKijitsu(preTodo, preKijitsu);
        data.delete();
        repository.saveAndFlush(data);
    }

    //完了メソッド
    @RequestMapping("/done")
    @Transactional(readOnly = false)
    public void done(@RequestBody Param param) {
        String preTodo = param.getPreTodo();
        Date preKijitsu = Date.valueOf(param.getPreKijitsu());

        TodoData data = repository.findByTodoAndKijitsu(preTodo, preKijitsu);
        data.complete();
        repository.saveAndFlush(data);
    }    
}