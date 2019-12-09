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
public class MainControllerImpl implements MainController {

    @Autowired
    private PhoneCallService phoneCallService;

    @Override
    public String showMain(Model model) {
        model.addAttribute("calls", phoneCallService.findAll());
        return "index";
    }

    @Override
    public String showAddForm(Model model){
        PhoneCall call = new PhoneCall();
        model.addAttribute("phoneCall", call);
        return "add-call";
    }

    @Override
    public String addCall(@Valid PhoneCall call, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("phoneCall", call);
            return "add-call";
        }
        phoneCallService.save(call);
        model.addAttribute("calls", phoneCallService.findAll());
        return "index";
    }

    @Override
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        PhoneCall call = phoneCallService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid call Id:" + id));

        model.addAttribute("phoneCall", call);
        return "update-call";
    }

    @Override
    public String updateCall(@PathVariable("id") long id, @Valid PhoneCall call,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("phoneCall", call);
            return "update-call";
        }

        phoneCallService.save(call);
        model.addAttribute("calls", phoneCallService.findAll());
        return "index";
    }

    @Override
    public String deleteCall(@PathVariable("id") long id, Model model) {
        PhoneCall call = phoneCallService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid call Id:" + id));
        phoneCallService.delete(call);
        model.addAttribute("calls", phoneCallService.findAll());
        return "index";
    }

    @Override
    public String showStatForm(Model model){
        Iterable<PhoneCallStat> calls = phoneCallService.getStat();
        model.addAttribute("calls", calls);
        return "stat-call";
    }

    @Override
    public String showStatFormByDate(){
        return "stat-call-by-date";
    }

    @Override
    public String showStatFormByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date start,
                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date end,
                                     Model model){
        Iterable<PhoneCallStat> calls = phoneCallService.getStatByDate(start, end);
        model.addAttribute("calls", calls);
        return "stat-call-by-date";
    }
}
