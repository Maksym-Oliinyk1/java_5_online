package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.entity.User;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.UserService;

import java.util.List;

@Controller()
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final AccountService accountService;

    public UserController(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @GetMapping("/new")
    public String redirectToNewUser(Model model) {
        model.addAttribute("user", new User());
        return "user_new";
    }

    @PostMapping("/new")
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:/users/new";
    }

    @GetMapping()
    public String findAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users_all";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        accountService.deleteAllByUserId(userId);
        userService.delete(userId);
        return "redirect:/users";
    }
}

