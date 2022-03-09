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
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin("*")
public class MainController {

    private boolean isCompleted = true;
    private boolean isDeleted = true;
    public int boolToInt(boolean a) {
        if(a)
            return 1;
            return 0;
    }

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
    public ModelAndView add(@RequestBody Param param) {
        //受け取ったオブジェクト
        String todo = param.getTodo();
        String kijitsu = param.getKijitsu();

        //データベースに追加
        TodoData data = new TodoData();
        data.setTodo(todo);
        data.setKijitsu(Date.valueOf(kijitsu));
        data.setSts(boolToInt(!isCompleted));
        data.setDeleteFlg(boolToInt(!isDeleted));
        data.setCreatedDate(Date.valueOf(LocalDate.now()));
        data.setUpdatedDate(Date.valueOf(LocalDate.now()));
        repository.saveAndFlush(data);
        return new ModelAndView("redirect:/");
    }

    //編集メソッド
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView edit(@RequestBody Param param) {
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
        return new ModelAndView("redirect:/");  
    }

    //削除メソッド
    @RequestMapping("/delete")
    @Transactional(readOnly = false)
    public void delete(@RequestBody Param param) {
        String preTodo = param.getPreTodo();
        Date preKijitsu = Date.valueOf(param.getPreKijitsu());

        TodoData data = repository.findByTodoAndKijitsu(preTodo, preKijitsu);
        data.setDeleteFlg(boolToInt(isDeleted));
        data.setUpdatedDate(Date.valueOf(LocalDate.now()));
        repository.saveAndFlush(data);
    }

    //完了メソッド
    @RequestMapping("/done")
    @Transactional(readOnly = false)
    public void done(@RequestBody Param param) {
        String preTodo = param.getPreTodo();
        Date preKijitsu = Date.valueOf(param.getPreKijitsu());

        TodoData data = repository.findByTodoAndKijitsu(preTodo, preKijitsu);
        data.setSts(boolToInt(isCompleted));
        data.setUpdatedDate(Date.valueOf(LocalDate.now()));
        repository.saveAndFlush(data);
    }    
}