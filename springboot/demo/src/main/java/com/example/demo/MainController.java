package com.example.demo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.repositories.TodoDataRepository;

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

    // 全リスト
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @Transactional
    public Iterable<TodoDataObj> show() {
        List<TodoData> dataList = repository.findByDeleteFlg(0);

        // TodoData型からTodoDataObj型に変換
        List<TodoDataObj> list = new ArrayList<>();
        for (TodoData data : dataList) {
            TodoDataObj obj = new TodoDataObj(data);
            list.add(obj);
        }
        return list;
    }

    // 追加メソッド
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public void add(@RequestBody @Validated Param param, BindingResult result) {

        errorCheck(param, result); // エラーがなかったら下に行く

        // 受け取ったオブジェクト
        String todo = param.getTodo();
        Date kijitsu = Date.valueOf(param.getKijitsu());

        // データベースに追加
        TodoData data = new TodoData(todo, kijitsu);
        repository.saveAndFlush(data);
    }

    // 編集メソッド
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @Transactional(readOnly = false)
    public void edit(@RequestBody @Validated Param param, BindingResult result) {

        errorCheck(param, result);

        int id = param.getId();
        String todo = param.getTodo();
        Date kijitsu = Date.valueOf(param.getKijitsu());

        // データベースに追加
        Optional<TodoData> data = repository.findById(id);
        data.get().edit(todo, kijitsu);
        repository.saveAndFlush(data.get());
    }

    // 削除メソッド
    @RequestMapping("/delete")
    @Transactional(readOnly = false)
    public void delete(@RequestBody Param param) {
        int id = param.getId();

        Optional<TodoData> data = repository.findById(id);
        data.get().delete();
        repository.saveAndFlush(data.get());
    }

    // 完了メソッド
    @RequestMapping("/done")
    @Transactional(readOnly = false)
    public void done(@RequestBody Param param) {
        int id = param.getId();

        Optional<TodoData> data = repository.findById(id);
        data.get().complete();
        repository.saveAndFlush(data.get());
    }

    // errorCheck
    public void errorCheck(Param param, BindingResult result) {
        boolean isDateFormatError = false;

        // kijitsuのチェック
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dtf.format(LocalDate.parse(param.getKijitsu(), dtf));
        } catch (DateTimeParseException dtp) {
            isDateFormatError = true;
        }

        if (isDateFormatError && result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "03"); // 両方エラー
        }
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "01"); // todoがエラー
        }
        if (isDateFormatError) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "02"); // kijitsuがエラー
        }
    }
}