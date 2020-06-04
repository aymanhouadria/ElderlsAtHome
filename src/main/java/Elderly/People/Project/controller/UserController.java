package Elderly.People.Project.controller;

import javax.servlet.http.HttpSession;

import Elderly.People.Project.dao.RequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import Elderly.People.Project.model.UserDetails;
import Elderly.People.Project.dao.UserDao;


@Controller
@RequestMapping("user")
public class UserController {
    private UserDao userDao;

    @Autowired
    public void setSociDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/list")
    public String listSocis(HttpSession session, Model model) {
        if (session.getAttribute("user") == null)
        {
            model.addAttribute("user", new UserDetails());
            HttpSession nextURL;
            session.setAttribute("nextURL", "/request/listCas");
            return "login";
        }
        model.addAttribute("users", userDao.listAllUsers());
        return "request/listCas";
    }



}
