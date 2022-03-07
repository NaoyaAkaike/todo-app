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

    @Autowired
    TodoDataRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public Iterable<TodoData> show() {
        Iterable<TodoData> list = repository.findAll();
        return list;// 格納した配列
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public ModelAndView add(@RequestBody Parameter param,ModelAndView mav) {
        
        String todo = param.getTodo();
        String kijitsu = param.getKijitsu();
        
        TodoData data = new TodoData();
        data.setTodo(todo);
        data.setKijitsu(Date.valueOf(kijitsu));
        data.setSts(0);
        data.setDeleteFlg(0);
        data.setCreatedDate(Date.valueOf(LocalDate.now()));
        data.setUpdatedDate(Date.valueOf(LocalDate.now()));
        repository.saveAndFlush(data);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/delete")
    @Transactional
    public void delete() {
    }

    @RequestMapping("/edit")
    @Transactional
    public void edit() {
    }


    static class Parameter {
        private String todo;
        private String kijitsu;

        public String getTodo() {
            return this.todo;
        }
        public String getKijitsu() {
            return this.kijitsu;
        }
    }
}