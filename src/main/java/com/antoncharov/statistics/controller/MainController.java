package com.antoncharov.statistics.controller;

import com.antoncharov.statistics.model.PhoneCall;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

public interface MainController {

    @GetMapping
    String showMain(Model model);

    @GetMapping(value = "/add")
    String showAddForm(Model model);

    @PostMapping(value = "/add")
    String addCall(@Valid PhoneCall call, BindingResult result, Model model);

    @GetMapping("/update/{id}")
    String showUpdateForm(@PathVariable("id") long id, Model model);

    @PostMapping("/update/{id}")
    String updateCall(@PathVariable("id") long id, @Valid PhoneCall call,
                             BindingResult result, Model model);

    @GetMapping("/delete/{id}")
    String deleteCall(@PathVariable("id") long id, Model model);

    @GetMapping("/stat")
    String showStatForm(Model model);

    @GetMapping("/statbydate")
    String showStatFormByDate();

    @PostMapping("/statbydate")
    String showStatFormByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date start,
                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date end,
                                     Model model);
}
