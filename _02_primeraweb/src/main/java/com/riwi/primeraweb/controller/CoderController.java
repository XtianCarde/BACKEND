package com.riwi.primeraweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.riwi.primeraweb.entity.Coder;
import com.riwi.primeraweb.service.CoderService;

@Controller
@RequestMapping("/")
public class CoderController {
    @Autowired
    private CoderService objCoderService;

    // Find All
    @GetMapping
    public String showViewCoders(Model objModel,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "2") int size){
        Page<Coder> listCoders = this.objCoderService.findAllPaginable(page - 1,size);
        objModel.addAttribute("listCoders", listCoders); 
        objModel.addAttribute("currentPage", page);
        objModel.addAttribute("totalPage", listCoders.getTotalPages());
        return "viewCoders";
    }

    // Crear
    @GetMapping("form")
    public String showViewForm(Model objModel){
        objModel.addAttribute("coder", new Coder());
        objModel.addAttribute("action", "/create-coder");
        return "viewForm";
    }
    @PostMapping("create-coder")
    public String createCoder(@ModelAttribute Coder objCoder){
        this.objCoderService.create(objCoder);
        return "redirect:/";
    }


    // Eliminar
    // PathVariable sirve para obtener el id en una url solo si es de tipo path (ejm: /delete/10) donde 10 es dinamico
    @GetMapping("delete/{id}")
    public String deleteCoder(@PathVariable Long id){
        this.objCoderService.delete(id);
        return "redirect:/";
    }

    // Actualizar
    @GetMapping("update/{id}")
    public String updateCoder(@PathVariable Long id,Model objModel){
        Coder objCoder = this.objCoderService.findById(id);
        objModel.addAttribute("coder", objCoder);
        objModel.addAttribute("action", "/edit/" + id);
        return "viewForm";
    } 

    @PostMapping("edit/{id}")
    public String updateCoder(@PathVariable Long id,@ModelAttribute Coder objCoder){
        this.objCoderService.update(id, objCoder);
        return "redirect:/";
    }



}
