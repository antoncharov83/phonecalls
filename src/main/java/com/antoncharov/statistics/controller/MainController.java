package com.antoncharov.statistics.controller;

import com.antoncharov.statistics.model.PhoneCall;
import com.antoncharov.statistics.model.PhoneCallStat;
import com.antoncharov.statistics.service.PhoneCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping(value = "/call")
public class MainController {

    @Autowired
    private PhoneCallService phoneCallService;

    @GetMapping(value = "/add")
    public String showAddForm(Model model){
        PhoneCall call = new PhoneCall();
        model.addAttribute("call", call);
        return "add-call";
    }

    @PostMapping(value = "/add")
    public String addCall(@Valid PhoneCall call, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("call", call);
            return "add-call";
        }
        phoneCallService.save(call);
        model.addAttribute("calls", phoneCallService.findAll());
        return "index";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        PhoneCall call = phoneCallService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid call Id:" + id));

        model.addAttribute("call", call);
        return "update-call";
    }

    @PostMapping("/update/{id}")
    public String updateCall(@PathVariable("id") long id, @Valid PhoneCall call,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("call", call);
            return "update-call";
        }

        phoneCallService.save(call);
        model.addAttribute("calls", phoneCallService.findAll());
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteCall(@PathVariable("id") long id, Model model) {
        PhoneCall call = phoneCallService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid call Id:" + id));
        phoneCallService.delete(call);
        model.addAttribute("calls", phoneCallService.findAll());
        return "index";
    }

    @GetMapping("/stat")
    public String showStatForm(Model model){
        Iterable<PhoneCallStat> calls = phoneCallService.getStat();
        model.addAttribute("calls", calls);
        return "stat-call";
    }

    @GetMapping("/statbydate")
    public String showStatFormByDate(){
        return "stat-call-by-date";
    }

    @PostMapping("/statbydate")
    public String showStatFormByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date start,
                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date end,
                                     Model model){
        Iterable<PhoneCallStat> calls = phoneCallService.getStatByDate(start, end);
        model.addAttribute("calls", calls);
        return "stat-call-by-date";
    }
}
