package vn.hoidanit.laptopshop.controller.admin;

import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        List<User> arrUser1 = this.userService.getAllUser();
        List<User> arrUser2 = this.userService.getUserByEmail("1nywodu@mailinator.com");
        model.addAttribute("users1", arrUser1);
        model.addAttribute("users2", arrUser2);
        return "hello";
    }

    @GetMapping("/admin/user/create")
    public String creatUser(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/user-create";
    }

    @PostMapping(value = "/admin/user/list")
//    public String creatUserPost(@ModelAttribute("newUser") @Valid User user,
//                                BindingResult newUserBindingResult,
//                                @RequestParam("hoidanitFile") MultipartFile file) {
    public String creatUserPost(@ModelAttribute("newUser") User user,
                                @RequestParam("hoidanitFile") MultipartFile file) {
//        List<FieldError> errors = newUserBindingResult.getFieldErrors();
//        for (FieldError error : errors) {
//            System.out.println(error.getField() + " - " + error.getDefaultMessage());
//        }
//        if (newUserBindingResult.hasErrors()) {
//            return "admin/user/user-create";
//        }
        String avatar = this.uploadService.handleSaveFileUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setAvatar(avatar);
        user.setPassword(hashPassword);
//        user.setRole(this.userService.getRoleByName(user.getRole().getName()));
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