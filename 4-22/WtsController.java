package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.WtsEntity;
import com.example.demo.form.WtsForm;
import com.example.demo.service.SubjectService;  
@Controller
public class WtsController {

   
    @Autowired
    private SubjectService subjectService;      

    @GetMapping("/wts/list")
    public String wtsList(Model model) {
        
        List<WtsEntity> wtslist = subjectService.searchAll();  
       
        model.addAttribute("wtslist", wtslist);                
        return "wts/list";
    }

    @GetMapping("/wts/add")
    public String wtsAdd(Model model) {
        model.addAttribute("wtsRequest", new WtsForm());
        return "wts/add";  // templates/wts/add.html
    }
    
    @PostMapping("/wts/create")
    public String wtsCreate(@Validated @ModelAttribute("wtsRequest") WtsForm wtsRequest,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            
            
            List<String> errorList = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("wtsRequest", new WtsForm());
            model.addAttribute("validationError", errorList);
            return "wts/add";
        }

       
        subjectService.create(wtsRequest);
        return "redirect:/wts/list";
    }
    
    @GetMapping("/wts/{id}")
    public String wtsDetail(@PathVariable Integer id, Model model) {
        WtsEntity server = subjectService.findById(id);
        model.addAttribute("server", server);
        return "wts/detail";
    }
    
    @GetMapping("/wts/{id}/edit")
    public String wtsEdit(@PathVariable Integer id, Model model) {
        WtsEntity server = subjectService.findById(id);
        WtsForm wtsUpdateRequest = new WtsForm();
        wtsUpdateRequest.setId(server.getId());
        wtsUpdateRequest.setServerName(server.getServerName());
        wtsUpdateRequest.setMaker(server.getMaker());
        wtsUpdateRequest.setPrice(server.getPrice());
        model.addAttribute("wtsUpdateRequest", wtsUpdateRequest);
        return "wts/edit";
    }
    
    @PostMapping("/wts/update")
    public String wtsUpdate(@Validated @ModelAttribute("wtsUpdateRequest") WtsForm wtsUpdateRequest,
                            BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            List<String> errorList = new ArrayList<>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "wts/edit";
        }
        subjectService.update(wtsUpdateRequest);
        return String.format("redirect:/wts/%d", wtsUpdateRequest.getId());
    }
    
    @GetMapping("/wts/{id}/delete")
    public String wtsDelete(@PathVariable Integer id, Model model) {
        
        subjectService.delete(id);
        return "redirect:/wts/list";
    }
 }
