package com.burlakov.memoria.controller;

import com.burlakov.memoria.dao.DAOTemplate;
import com.burlakov.memoria.dao.MemoriaUserDAO;
import com.burlakov.memoria.dao.MemoriaUserDAOImpl;
import com.burlakov.memoria.model.MemoriaUserEntity;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.math.BigDecimal;

@Controller
public class MainController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(WebRequest webRequest){
        MemoriaUserDAO dao = new MemoriaUserDAOImpl();
        Session session = DAOTemplate.getSession();
        session.beginTransaction();
        MemoriaUserEntity user = dao.findUser(webRequest.getParameter("email"),webRequest.getParameter("password"));
        user = (MemoriaUserEntity) session.get(MemoriaUserEntity.class, user.getEmail());
        user.setIsOnline("Y");
        session.save(user);
        session.getTransaction().commit();
        session.close();
        webRequest.setAttribute("email", user.getEmail(), RequestAttributes.SCOPE_GLOBAL_SESSION);
        return "redirect:success";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String requestMethodPost(WebRequest webRequest) {
        String email = webRequest.getParameter("email");
        String name = webRequest.getParameter("name");
        String password = webRequest.getParameter("passwd");
        MemoriaUserEntity user = new MemoriaUserEntity();
        user.setName(name);
        user.setEmail(email);
        user.setIdRole(BigDecimal.valueOf(2));
        user.setPassword(password);
        MemoriaUserDAO dao = new MemoriaUserDAOImpl();
        dao.createUser(user);
        //MemoriaUserEntity user = dao.findUser(webRequest.getParameter("email"),webRequest.getParameter("password"));
        webRequest.setAttribute("email", user.getEmail(), RequestAttributes.SCOPE_GLOBAL_SESSION);
        return "redirect:success";
    }

    @RequestMapping("/")
    public String main(){
        return "index";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(){
        return "success";
    }


}