package pl.coderslab;

import pl.coderslab.User;
import pl.coderslab.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/list")
public class ListofUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        List<User> users = userDao.findAll();
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/blank.jsp").forward(request, response);
    }
}

