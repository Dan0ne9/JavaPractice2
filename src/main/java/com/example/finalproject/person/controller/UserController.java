package com.example.finalproject.person.controller;

import com.example.finalproject.person.dto.UserRequest;
import com.example.finalproject.person.dto.UserResponse;
import com.example.finalproject.person.entity.User;
import com.example.finalproject.person.exceptions.UserNotFoundException;
import com.example.finalproject.person.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@AllArgsConstructor
@Controller
public class UserController {

    private UserService userService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }
    @GetMapping("/showNewPersonForm")
    public String showNewPersonForm(Model model) {
        User user = new User();
        model.addAttribute("person", user);
        return "new_person";
    }
    @PostMapping("/savePerson")
    public String saveEmployee(@ModelAttribute("person") User user) {
        userService.savePerson(user);
        return "redirect:/";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable ( value = "id") Long id, Model model) throws UserNotFoundException {
        User user = userService.getUserById(id);
        model.addAttribute("person", user);
        return "update_person";
    }
    @GetMapping("/deletePerson/{id}")
    public String deletePerson(@PathVariable (value = "id") Long id) {
        this.userService.deleteUserById(id);
        return "redirect:/";
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page < User > page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List < User > listPersons = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listPersons", listPersons);
        return "index";
    }

}
