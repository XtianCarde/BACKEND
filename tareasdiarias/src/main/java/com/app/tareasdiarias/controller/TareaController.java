package com.app.tareasdiarias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.app.tareasdiarias.entity.Tarea;
import com.app.tareasdiarias.service.TareaService;

@Controller
@RequestMapping("/")
public class TareaController {
    
    @Autowired
    private TareaService objTareaService;

    @GetMapping
    public String showViewTareas(Model objModel){
        List<Tarea> listTareas = this.objTareaService.findAll();
        objModel.addAttribute("listTareas", listTareas);
        return "viewTareas";
    }

    @GetMapping("form")
    public String showViewForm(Model objModel){
        objModel.addAttribute("tarea", new Tarea());
        objModel.addAttribute("action", "/create-task");
        return "viewForm";
    }

    @PostMapping("create-task")
    public String createTarea(@ModelAttribute Tarea objTarea){
        this.objTareaService.create(objTarea);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteTarea(@PathVariable Long id){
        this.objTareaService.delete(id);
        return "redirect:/";
    }

    @GetMapping("update/{id}")
    public String updateTarea(@PathVariable Long id, Model objModel){
        Tarea objTarea = this.objTareaService.findById(id);
        objModel.addAttribute("tarea", objTarea);
        objModel.addAttribute("action", "/edit/" + id);
        return "viewForm";
    }

    @PostMapping("edit/{id}")
    public String updateTarea(@PathVariable Long id,@ModelAttribute Tarea objTarea){
        this.objTareaService.update(id, objTarea);
        return "redirect:/";
    }
}
