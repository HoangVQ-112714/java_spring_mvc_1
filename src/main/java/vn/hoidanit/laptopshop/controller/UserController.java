package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUser = this.userService.getUserByEmail("1nywodu@mailinator.com");
        model.addAttribute("users", arrUser);
        model.addAttribute("eric", "test");
        model.addAttribute("eric2", "say hi..");
        return "hello";
    }

    @RequestMapping("/admin/user/create")
    public String creatUser(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/user-create";
    }

    @RequestMapping(value = "/admin/user", method = RequestMethod.POST)
    public String creatUserPost(Model model, @ModelAttribute("newUser") User user) {
        this.userService.handleSaveUser(user);
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getAdminCreatUserPage(Model model) {
        List<User> arrUser = this.userService.getUserByEmail("1nywodu@mailinator.com");
        model.addAttribute("users", arrUser);
        return "admin/user/user-list";
    }
}