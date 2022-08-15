package com.example.sessioncookie.controller;

import com.example.sessioncookie.dto.TodoItem;
import com.example.sessioncookie.dto.TodoList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

@Controller
@SessionAttributes("todos")
public class TodoController {
    @GetMapping("/form")
    public String showForm(Model model, @ModelAttribute("todos") TodoList todos) {
        if (!todos.isEmpty()) {
            model.addAttribute("todo", todos.peekLast());
        } else {
            model.addAttribute("todo", new TodoItem());
        }
        return "sessionattributesform";
    }

    @PostMapping("/form")
    public RedirectView create(@ModelAttribute TodoItem todo, @ModelAttribute("todos") TodoList todos, RedirectAttributes attributes) {
        todo.setCreateDate(LocalDateTime.now());
        todos.add(todo);
        attributes.addFlashAttribute("todos", todos);
        return new RedirectView("todos.html");
    }

    @GetMapping("/todos.html")
    public String list(Model model, @ModelAttribute("todos") TodoList todos) {
        model.addAttribute("todos", todos);
        return "sessionattributestodos";
    }

    @GetMapping("/info")
    public String userInfo(@SessionAttribute("todos") TodoList todos) {
        System.out.println("Length " + todos.size());
        return "sessionattributestodos";
    }

    @ModelAttribute("todos")
    public TodoList todos() {
        return new TodoList();
    }
}
