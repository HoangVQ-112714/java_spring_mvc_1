package vn.hoidanit.laptopshop.controller.admin;

import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final ServletContext servletContext;

    public UserController(UserService userService, ServletContext servletContext) {
        this.userService = userService;
        this.servletContext = servletContext;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUser1 = this.userService.getAllUser();
        List<User> arrUser2 = this.userService.getUserByEmail("1nywodu@mailinator.com");
        model.addAttribute("users1", arrUser1);
        model.addAttribute("users2", arrUser2);
        return "hello";
    }

    @RequestMapping("/admin/user/create")
    public String creatUser(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/user-create";
    }

    @RequestMapping(value = "/admin/user/list", method = RequestMethod.POST)
    public String creatUserPost(Model model, @ModelAttribute("newUser") User user) {
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user/list";
    }

    @RequestMapping("/admin/user/list")
    public String getAdminCreatUserPage(Model model) {
        List<User> arrUser = this.userService.getAllUser();
        model.addAttribute("users", arrUser);
        return "admin/user/user-list";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserDetail(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/user-detail";
    }

    @RequestMapping("/admin/update/{id}")
    public String updateUser(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/user-update";
    }

    @PostMapping("/admin/user/{id}")
    public String updateUserPost(Model model, @ModelAttribute("user") User userInfo, @PathVariable long id) {
        User user = this.userService.getUserById(userInfo.getId());
        if (user != null) {
            user.setName(userInfo.getName());
            user.setAddress(userInfo.getAddress());
            user.setPhone(userInfo.getPhone());
            user.setEmail(user.getEmail());
            this.userService.handleSaveUser(user);
        }
        return "redirect:/admin/user/list";
    }

    @RequestMapping("/admin/delete/{id}")
    public String deleteUser(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        if (user != null) {
            this.userService.deleteUser(id);
        }
        return "redirect:/admin/user/list";
    }
}