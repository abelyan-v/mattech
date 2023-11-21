package com.mattech.snab.mattech;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class controllers {

    MySQL mysql = new MySQL();

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/materials")
    public String materials(Model model) {
        ArrayList<String> output_list = mysql.SelectQuery("select * from materials");
        model.addAttribute("name", output_list);
        return "materials";
    }

    @GetMapping("/providers")
    public String providers(Model model) {
        ArrayList<String> output_list = mysql.SelectQuery("select * from providers");
        model.addAttribute("name", output_list);
        return "providers";
    }

    @GetMapping("/recipients")
    public String recipients(Model model) {
        ArrayList<String> output_list = mysql.SelectQuery("select * from recipients");
        model.addAttribute("name", output_list);
        return "recipients";
    }

    @GetMapping("/applications")
    public String applications(Model model) {
        ArrayList<ArrayList<String>> output_list = mysql.ApplicationSelectQuery("select * from applications");
        model.addAttribute("name", output_list);
        return "applications";
    }

    @GetMapping("/material/add")
    public String add_material(Model model) {
        return "add/material";
    }

    @GetMapping("/provider/add")
    public String add_provider(Model model) {
        return "add/provider";
    }

    @GetMapping("/recipient/add")
    public String add_recipient(Model model) {
        return "add/recipient";
    }

    @GetMapping("/application/add")
    public String add_application(Model model) {
        ArrayList<String> materials_list = mysql.SelectQuery("select * from materials");
        ArrayList<String> providers_list = mysql.SelectQuery("select * from providers");
        ArrayList<String> recipients_list = mysql.SelectQuery("select * from recipients");
        model.addAttribute("materials", materials_list);
        model.addAttribute("providers", providers_list);
        model.addAttribute("recipients", recipients_list);
        return "add/application";
    }

    @PostMapping("/material/add")
    public String add_material(@RequestParam String name, Model model) {
        mysql.InsertQuery("INSERT INTO `materials`(`name`) VALUES ('" + name + "')");
        return "redirect:/material/add";
    }

    @PostMapping("/provider/add")
    public String add_provider(@RequestParam String name, Model model) {
        mysql.InsertQuery("INSERT INTO `providers`(`name`) VALUES ('" + name + "')");
        return "redirect:/provider/add";
    }

    @PostMapping("/recipient/add")
    public String add_recipient(@RequestParam String name, Model model) {
        mysql.InsertQuery("INSERT INTO `recipients`(`name`) VALUES ('" + name + "')");
        return "redirect:/recipient/add";
    }

    @PostMapping("/application/add")
    public String add_recipient(@RequestParam String material, @RequestParam String provider, @RequestParam String recipient, @RequestParam String fund, Model model) {
        System.out.println("debug");
        mysql.ApplicationInsertQuery("INSERT INTO `applications`(`material`, `provider`, `recipient`, `fund`) VALUES ('" + material + "','" + provider + "','" + recipient + "'," + fund + ")");
        return "redirect:/application/add";
    }

    @GetMapping("/test")
    public String test(Model model) {
        ArrayList<String> one = new ArrayList<String>();
        one.add("one");
        one.add("two");
        one.add("three");
        model.addAttribute("name", one);
        return "test";
    }
}
