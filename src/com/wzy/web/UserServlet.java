package com.wzy.web;

import com.wzy.entity.UserInfo;
import com.wzy.factory.ObjectFactory;
import com.wzy.services.UserInfoService;
import com.wzy.services.impl.UserInfoServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet", urlPatterns = "*.user")
public class UserServlet extends HttpServlet {

    private UserInfoService userInfoService = new UserInfoServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/user/login.user")) {
            userLogin(request, response);
        }
        if (path.equals("/user/exit.user")) {
            userExit(request, response);
        }
        if (path.equals("/user/register.user")) {
            userRegister(request, response);
        }


    }


    protected void userExit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("userInfo");
        request.getSession().removeAttribute("cart");
        response.sendRedirect(request.getContextPath() + "/list.jsp");
    }

    protected void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + "-----" + password);

        boolean isExsit = userInfoService.isExist(username);
        if (!isExsit) {
            request.setAttribute("msg", "用户名不存在，请重新输入！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            boolean isCorrect = userInfoService.isCorrect(username, password);
            if (isCorrect) {
                UserInfo userInfo = userInfoService.queryUserInfoByUsername(username);
                request.getSession().setAttribute("userInfo", userInfo);
                response.sendRedirect(request.getContextPath() + "/list.jsp");
            } else {
                request.setAttribute("msg", "用户密码错误，请重新输入！");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }


    protected void userRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setPhone(phone);
        userInfo.setAddress(address);

        System.out.println(userInfo);
        boolean isExsit = userInfoService.isExist(username);
        if (isExsit) {
            request.setAttribute("msg", "用户名已存在，请重新输入！");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } else {
            if (userInfoService.addUserInfo(userInfo)) {
                request.setAttribute("msg", "注册成功，请登录！");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
