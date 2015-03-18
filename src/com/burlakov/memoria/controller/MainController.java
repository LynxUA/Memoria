package com.burlakov.memoria.controller;

import com.burlakov.memoria.dao.*;
import com.burlakov.memoria.model.CategoryEntity;
import com.burlakov.memoria.model.DeskEntity;
import com.burlakov.memoria.model.DeskUsersEntity;
import com.burlakov.memoria.model.MemoriaUserEntity;
import com.burlakov.memoria.model.LableEntity;
import com.burlakov.memoria.system.Roles;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
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
        webRequest.setAttribute("role", user.getIdRole(), RequestAttributes.SCOPE_GLOBAL_SESSION);
        return "redirect:";
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
        return "redirect:";
    }

    @RequestMapping("/")
    public String main(){
        return "index";
    }



    @RequestMapping("/panel")
    public String adminPanel(WebRequest webRequest){
        if((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)).equals(Roles.ADMIN))
            return "admin_panel";
        else{
            return "denied";
        }
    }

    @RequestMapping("/desk")
    public String desk(WebRequest webRequest){
        if((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION))!=null)
            return "desk";
        else{
            return "denied";
        }
    }

    @RequestMapping("/add_desk")
    public String addDesk(WebRequest webRequest){
        if((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION))!=null)
            return "add_desk";
        else{
            return "denied";
        }
    }
    //!!!!!!!!!!!!!!!
    @RequestMapping(value = "/add_desk", method = RequestMethod.POST)
    public void addDeskPost(WebRequest webRequest){
        if((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION))!=null){
            String name = webRequest.getParameter("name");
            DeskEntity deskEntity = new DeskEntity();
            deskEntity.setName(name);

            if(deskEntity.getName()!=null)
                System.out.println(deskEntity.getName());
            else
                System.out.println("null");

            DeskDAO deskDAO = new DeskDAOImpl();
            deskDAO.createDesk(deskEntity);
            DeskUserDAO deskUserDAO = new DeskUserDAOImpl();
            DeskUsersEntity deskUsersEntity = new DeskUsersEntity();
            deskUsersEntity.setEmail((String)(webRequest.getAttribute("email", RequestAttributes.SCOPE_GLOBAL_SESSION)));
            deskUsersEntity.setIdDesk(deskEntity.getIdDesk());
            deskUserDAO.createDeskUser(deskUsersEntity);
        }
    }

    @RequestMapping("/add_category")
    public String addCategory(WebRequest webRequest){
        if((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION))!=null)
            return "add_category";
        else{
            return "denied";
        }
    }

    @RequestMapping(value="/add_category", method = RequestMethod.POST)
    public String addCategoryPost(WebRequest webRequest){
        if((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION))!=null){
            String name = webRequest.getParameter("name");
            BigDecimal idDesk = BigDecimal.valueOf(Long.valueOf(webRequest.getParameter("idDesk")));
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(name);
            categoryEntity.setIdDesk(idDesk);
            CategoryDAO categoryDAO = new CategoryDAOImpl();
            categoryDAO.createCategory(categoryEntity);
            return "redirect:desk?idDesk="+idDesk;
        }
        return "redirect:index";
    }

    @RequestMapping("/add_label")
    public String addLabel(WebRequest webRequest){
        if((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION))!=null)
            return "add_label";
        else{
            return "denied";
        }
    }

    @RequestMapping(value="/add_label", method = RequestMethod.POST)
    public void addLabelPost(WebRequest webRequest){
        if((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION))!=null){
            String name = webRequest.getParameter("name");
            BigDecimal idCategory = BigDecimal.valueOf(Long.valueOf(webRequest.getParameter("categoryId")));
            LableEntity labelEntity = new LableEntity();
            labelEntity.setName(name);
            labelEntity.setIdCategory(idCategory);
            LableDAO labelDAO = new LableDAOImpl();
            labelDAO.createLable(labelEntity);
        }


    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success(){
        return "success";
    }

    @RequestMapping("/exit")
    public void exit(WebRequest webRequest){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true); // true == allow create
        if (session != null) {
            session.invalidate();
        }
    }

}