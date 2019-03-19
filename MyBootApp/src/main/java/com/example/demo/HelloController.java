package com.example.demo;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.repositories.MyDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    MyDataRepository repository;
    /**
     * HELLO_WORLD
     */
    private static final String HELLO_WORLD = "Hello world!!!";

    @RequestMapping("/")
    public Object index() {
        Iterable<MyData> list = repository.findAll();
        return list;
    }

    @RequestMapping("/create")
    public String create(
        @RequestParam(name="name", required = true) String name,
        @RequestParam(name="age", required = false) String age,
        @RequestParam(name="mail", required = false) String mail,
        @RequestParam(name="memo", required = false) String memo,
        HttpServletResponse response
        ) throws IOException {
        MyData myData = new MyData();
        
        myData.setName(name);
        if (age != null) {
            myData.setAge(Integer.parseInt(age));
        }
        if (mail != null) {
            myData.setMail(mail);
        }
        if (memo != null) {
            myData.setMemo(memo);
        }
        repository.saveAndFlush(myData);
        response.sendRedirect("/");
        return "CREATED!";
    }

    @RequestMapping("/edit/{id}")
    public String edit(
        @PathVariable Long id,
        @RequestParam(name="name", required = false) String name,
        @RequestParam(name="age", required = false) String age,
        @RequestParam(name="mail", required = false) String mail,
        @RequestParam(name="memo", required = false) String memo,
        HttpServletResponse response
    ) throws IOException {
        Optional<MyData> list = repository.findById(id);
        MyData myData = list.get();
        if (name != null) {
            myData.setName(name);
        }
        if (age != null) {
            myData.setAge(Integer.parseInt(age));
        }
        if (mail != null) {
            myData.setMail(mail);
        }
        if (memo != null) {
            myData.setMemo(memo);
        }
        repository.saveAndFlush(myData);
        response.sendRedirect("/");
        return "EDITTED!";
    }

    @RequestMapping("/delete/{id}")
    public String delete(
        @PathVariable Long id,
        HttpServletResponse response
    ) throws IOException {
        if (!repository.findById(id).isPresent()) {
            return "このIDに対応するデータが存在しません!";
        }
        repository.deleteById(id);
        response.sendRedirect("/");
        return "EDITTED!";
    }

    @PostConstruct
    public void init() {
        MyData d1 = new MyData();
        d1.setName("Google");
        d1.setAge(30);
        d1.setMail("google@mail.com");
        d1.setMemo("This is google memo!!!");
        repository.saveAndFlush(d1);

        MyData d2 = new MyData();
        d2.setName("Facebook");
        d2.setAge(40);
        d2.setMail("facebook@mail.com");
        d2.setMemo("This is facebook memo!!!");
        repository.saveAndFlush(d2);

        MyData d3 = new MyData();
        d3.setName("Amazon");
        d3.setAge(50);
        d3.setMail("amazon@mail.com");
        d3.setMemo("This is amazon memo!!!");
        repository.saveAndFlush(d3);

        MyData d4 = new MyData();
        d4.setName("Apple");
        d4.setAge(50);
        d4.setMail("apple@mail.com");
        d4.setMemo("This is apple memo!!!");
        repository.saveAndFlush(d4);
    }
}