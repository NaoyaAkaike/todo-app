package com.example.demo;

import java.sql.Date;

import com.example.demo.repositories.TodoDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin(origins = "*") // よくわからない
public class MainController {

    @Autowired
    TodoDataRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public Iterable<TodoData> show() {
        Iterable<TodoData> list = repository.findAll();
        return list;// 格納した配列
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @Transactional(readOnly = false)
    public ModelAndView add(ModelAndView mav) {
        
        //デモデータ
        TodoData d1 = new TodoData();
        d1.setTodo("running");
        d1.setKijitsu(Date.valueOf("2020-07-20"));
        d1.setSts(1);
        d1.setDeleteFlg(0);
        d1.setCreatedDate(Date.valueOf("2020-07-10"));
        d1.setUpdatedDate(Date.valueOf("2020-07-15"));

        TodoData d2 = new TodoData();
        d2.setTodo("read some books");
        d2.setKijitsu(Date.valueOf("2020-07-31"));
        d2.setSts(1);
        d2.setDeleteFlg(0);
        d2.setCreatedDate(Date.valueOf("2020-07-20"));
        d2.setUpdatedDate(Date.valueOf("2020-07-20"));


        repository.saveAndFlush(d1);
        repository.saveAndFlush(d2);
        return new ModelAndView("redirect:/");// 格納した配列
    }

    @RequestMapping("/delete")
    @Transactional
    public void delete() {
    }

    @RequestMapping("/edit")
    @Transactional
    public void edit() {
    }
}