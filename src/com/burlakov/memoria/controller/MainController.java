package com.burlakov.memoria.controller;

import com.burlakov.memoria.dao.*;
import com.burlakov.memoria.dao.exceptions.UserNotFoundException;
import com.burlakov.memoria.model.*;
import com.burlakov.memoria.system.Roles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Controller
public class MainController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(WebRequest webRequest) {
        MemoriaUserDAO dao = new MemoriaUserDAOImpl();
        MemoriaUserEntity user = null;
        try {
            user = dao.findUser(webRequest.getParameter("email"), webRequest.getParameter("password"));
        } catch (UserNotFoundException e) {
            return "redirect:denied";
        }
        //user = (MemoriaUserEntity) session.get(MemoriaUserEntity.class, user.getEmail());
        user.setIsOnline("Y");
        dao.createUser(user);
        //session.save(user);
        //session.getTransaction().commit();
        //session.close();
        webRequest.setAttribute("email", user.getEmail(), RequestAttributes.SCOPE_GLOBAL_SESSION);
        webRequest.setAttribute("role", user.getIdRole(), RequestAttributes.SCOPE_GLOBAL_SESSION);
        return "redirect:";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup() {
        return "signup";
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
        webRequest.setAttribute("role", user.getIdRole(), RequestAttributes.SCOPE_GLOBAL_SESSION);
        return "redirect:";
    }

    @RequestMapping("/")
    public String main() {
        return "index";
    }

    @RequestMapping("/label")
    public String label(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null)
            return "label";
        else {
            return "denied";
        }
    }

    @RequestMapping(value = "/label", method = RequestMethod.POST)
    public String addCommentary(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null) {
            String value = webRequest.getParameter("value");
            BigDecimal labelId = BigDecimal.valueOf(Long.valueOf(webRequest.getParameter("labelId")));
            CommentaryEntity commentaryEntity = new CommentaryEntity();
            commentaryEntity.setEmail((String) webRequest.getAttribute("email", RequestAttributes.SCOPE_GLOBAL_SESSION));
            commentaryEntity.setIdParentLabel(labelId);
            commentaryEntity.setValue(value);
            CommentaryDAO commentaryDAO = new CommentaryDAOImpl();
            commentaryDAO.createCommentary(commentaryEntity);
            return "redirect:label?labelId=" + webRequest.getParameter("labelId");
        } else {
            return "denied";
        }
    }

    @RequestMapping(value = "/delete_commentary")
    public void deleteCommentary(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null) {
            BigDecimal commentaryId = BigDecimal.valueOf(Long.valueOf(webRequest.getParameter("commentaryId")));
            CommentaryDAO commentaryDAO = new CommentaryDAOImpl();
            commentaryDAO.deleteCommentary(commentaryId);
            //return "redirect:";
        }
        //return "denied";
    }

    @RequestMapping(value = "/edit_commentary", method = RequestMethod.POST)
    public void editCommentary(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null) {
            BigDecimal commentaryId = BigDecimal.valueOf(Long.valueOf(webRequest.getParameter("commentaryId")));
            String newText = webRequest.getParameter("newText");
            CommentaryDAO commentaryDAO = new CommentaryDAOImpl();
            commentaryDAO.renameCommentary(commentaryId, newText);
            //return "redirect:";
        }
        //return "denied";
    }

    @RequestMapping(value = "/delete_label")
    public void deleteLabel(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null) {
            BigDecimal labelId = BigDecimal.valueOf(Long.valueOf(webRequest.getParameter("labelId")));
            LableDAO lableDAO = new LableDAOImpl();
            lableDAO.deleteLable(labelId);
            //return "redirect:";
        }
        //return "denied";
    }

    @RequestMapping(value = "/edit_label", method = RequestMethod.POST)
    public void editLabel(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null) {
            BigDecimal labelId = BigDecimal.valueOf(Long.valueOf(webRequest.getParameter("labelId")));
            String newName = webRequest.getParameter("newName");
            LableDAO lableDAO = new LableDAOImpl();
            lableDAO.renameLable(labelId, newName);
            //return "redirect:";
        }
        //return "denied";
    }

    @RequestMapping("/panel")
    public String adminPanel(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)).equals(Roles.ADMIN))
            return "admin_panel";
        else {
            return "denied";
        }
    }

    @RequestMapping("/desk")
    public String desk(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null)
            return "desk";
        else {
            return "denied";
        }
    }

    @RequestMapping("/denied")
    public String denied() {
        return "denied";
    }

    @RequestMapping("/add_desk")
    public String addDesk(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null)
            return "add_desk";
        else {
            return "denied";
        }
    }

    @RequestMapping(value = "/add_desk", method = RequestMethod.POST)
    public void addDeskPost(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null) {
            String name = webRequest.getParameter("name");
            DeskEntity deskEntity = new DeskEntity();
            deskEntity.setName(name);
            DeskDAO deskDAO = new DeskDAOImpl();
            deskDAO.createDesk(deskEntity);
            DeskUserDAO deskUserDAO = new DeskUserDAOImpl();
            DeskUsersEntity deskUsersEntity = new DeskUsersEntity();
            deskUsersEntity.setEmail((String) (webRequest.getAttribute("email", RequestAttributes.SCOPE_GLOBAL_SESSION)));
            deskUsersEntity.setIdDesk(deskEntity.getIdDesk());
            deskUserDAO.createDeskUser(deskUsersEntity);
        }
    }

    @RequestMapping(value = "/delete_desk")
    public String deleteDesk(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null) {
            BigDecimal idDesk = BigDecimal.valueOf(Long.valueOf(webRequest.getParameter("idDesk")));
            DeskDAO deskDAO = new DeskDAOImpl();
            deskDAO.deleteDesk(idDesk);
            return "redirect:";
        }
        return "denied";
    }

    @RequestMapping("/add_category")
    public String addCategory(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null)
            return "add_category";
        else {
            return "denied";
        }
    }

    @RequestMapping(value = "/add_category", method = RequestMethod.POST)
    public String addCategoryPost(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null) {
            String name = webRequest.getParameter("name");
            BigDecimal idDesk = BigDecimal.valueOf(Long.valueOf(webRequest.getParameter("idDesk")));
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(name);
            categoryEntity.setIdDesk(idDesk);
            CategoryDAO categoryDAO = new CategoryDAOImpl();
            categoryDAO.createCategory(categoryEntity);
            return "redirect:desk?idDesk=" + idDesk;
        }
        return "redirect:index";
    }

    @RequestMapping(value = "/delete_category", method = RequestMethod.POST)
    public void deleteCategoryPost(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null) {
            BigDecimal categoryId = BigDecimal.valueOf(Long.valueOf(webRequest.getParameter("categoryId")));
            CategoryDAO categoryDAO = new CategoryDAOImpl();
            CategoryEntity categoryEntity = categoryDAO.findCategory(categoryId);
            BigDecimal idDesk = categoryEntity.getIdDesk();
            categoryDAO.deleteCategory(categoryId);
            //return "redirect:desk?idDesk="+idDesk;
        }
        //return "redirect:index";
    }


    @RequestMapping("/add_label")
    public String addLabel(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null)
            return "add_label";
        else {
            return "redirect:denied";
        }
    }

    //    @ResponseBody
//    public String plaintext(HttpServletResponse response) {
//        response.setContentType("text/plain");
//        response.setCharacterEncoding("UTF-8");
//        return "TEXT";
//    }
    @RequestMapping(value = "/add_label", method = RequestMethod.POST)
    @ResponseBody
    public String addLabelPost(WebRequest webRequest) {
        if ((webRequest.getAttribute("role", RequestAttributes.SCOPE_GLOBAL_SESSION)) != null) {
            String name = webRequest.getParameter("name");
            BigDecimal idCategory = BigDecimal.valueOf(Long.valueOf(webRequest.getParameter("categoryId")));
            System.out.println(name);
            System.out.println(idCategory);
            LableEntity labelEntity = new LableEntity();
            labelEntity.setName(name);
            labelEntity.setIdCategory(idCategory);
            LableDAO labelDAO = new LableDAOImpl();
            labelDAO.createLable(labelEntity);
            System.out.println(labelEntity.getIdLabel().toPlainString());
            return labelEntity.getIdLabel().toPlainString();

        }
        return "";


    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String success() {
        return "success";
    }

    @RequestMapping("/exit")
    public String exit() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true); // true == allow create
        if (session != null) {
            session.invalidate();
        }
        return "redirect:";
    }

    @RequestMapping("/user_info")
    public String userInfo() {
        return "user_info";
    }

    @RequestMapping(value = "/add_to_desk", method = RequestMethod.POST)
    public String addToDesk(WebRequest webRequest) {
        BigDecimal idDesk = BigDecimal.valueOf(Double.valueOf(webRequest.getParameter("idDesk")));
        String email = webRequest.getParameter("email");
        DeskUserDAO deskUserDAO = new DeskUserDAOImpl();
        DeskUsersEntity deskUsersEntity = new DeskUsersEntity();
        deskUsersEntity.setEmail(email);
        deskUsersEntity.setIdDesk(idDesk);
        try {
            deskUserDAO.createDeskUser(deskUsersEntity);
        }catch(Exception e){
            return "redirect:denied";
        }
        return "redirect:desk?idDesk=" + idDesk;
    }

    @RequestMapping(value = "/add_friend")
    public String addFriend(WebRequest webRequest) {
        String email = webRequest.getParameter("email");
        FriendsEntity friends = new FriendsEntity();
        friends.setEmail1((String) webRequest.getAttribute("email", RequestAttributes.SCOPE_GLOBAL_SESSION));
        friends.setEmail2(email);
        FriendsDAO friendsDAO = new FriendsDAOImpl();
        friendsDAO.createFriends(friends);
        return "redirect:user_info?email=" + email;
    }


    @RequestMapping(value = "/send_message", method = RequestMethod.GET)
    public String sendMessage() {
        return "send_message";
    }

    @RequestMapping(value = "/send_message", method = RequestMethod.POST)
    public String sendMessagePost(WebRequest webRequest) {
        String sender = (String) webRequest.getAttribute("email", RequestAttributes.SCOPE_GLOBAL_SESSION);
        String reciever = webRequest.getParameter("email");
        System.out.println("sender "+sender);
        System.out.println("reciever "+reciever);
        String text = webRequest.getParameter("text");
        PrivateMessagesEntity message = new PrivateMessagesEntity();
        message.setSender(sender);
        message.setReciever(reciever);
        message.setText(text);
        message.setMsgDate(Timestamp.valueOf(LocalDateTime.now()));
        PrivateMessagesDAO messagesDAO = new PrivateMessagesDAOImpl();
        messagesDAO.sendMessage(message);
        return "redirect:messages?type=out";
    }

    @RequestMapping(value = "/messages")
    public String messages() {
        return "messages";
    }

    @RequestMapping(value = "/remove_friend")
    public String removeFriend(WebRequest webRequest) {
        String email1 = (String) webRequest.getAttribute("email", RequestAttributes.SCOPE_GLOBAL_SESSION);
        String email2 = webRequest.getParameter("email");
        FriendsDAO friendsDAO = new FriendsDAOImpl();
        FriendsEntity friends1 = friendsDAO.findFriends(email1, email2);
        friendsDAO.deleteFriends(friends1);
        return "redirect:user_info?email=" + email2;
    }

    @RequestMapping(value = "/friends")
    public String friends(WebRequest webRequest) {
        return "friends";

    }
    @RequestMapping(value = "/desk_statistics")
    public String deskStatistics(WebRequest webRequest) {
        return "desk_statistics";

    }

    @RequestMapping(value = "/user_posts")
    public String userStatistics(WebRequest webRequest) {
        return "user_posts";

    }

    @RequestMapping(value = "/user_friends")
    public String userFriends(WebRequest webRequest) {
        return "user_friends";

    }

    @RequestMapping(value = "/top_users")
    public String topUsers(WebRequest webRequest) {
        return "top_users";

    }

    @RequestMapping(value = "/top_category")
    public String topCategory(WebRequest webRequest) {
        return "top_category";

    }
}