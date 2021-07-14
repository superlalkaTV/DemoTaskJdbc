package ua.maksym.bataiev.demotaskjdbc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.maksym.bataiev.demotaskjdbc.dao.UserDAO;
import ua.maksym.bataiev.demotaskjdbc.model.User;

@Controller
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserDAO userDAO;

  @GetMapping()
  public String getUsersView(@RequestParam(required = false) Long id, Model model) {
    if (id != null) {
      User user = userDAO.getUserById(id);
      model.addAttribute("user", user);
      return "userInfo";
    }
    List<User> list = userDAO.getAllUsers();
    model.addAttribute("users", list);
    return "usersPage";
  }

  @PostMapping("/create")
  public String createUser(@ModelAttribute("user") @Validated User user,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "createUser";
    }
    userDAO.createUser(user);
    return "redirect:/users";
  }

  @GetMapping("/new")
  public String newPerson(Model model) {
    model.addAttribute("user", new User());
    return "createUser";
  }

  @PostMapping("/delete/{id}")
  public String deleteUser(@PathVariable Long id) {
    userDAO.deleteUser(id);
    return "redirect:/users";
  }

}