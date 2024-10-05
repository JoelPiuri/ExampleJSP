package com.example.servlets;

import com.example.dao.UserDAO;
import com.example.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {

    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            action = "list";
        }

        switch (action) {
            case "list":
                listUsers(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            default:
                listUsers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        saveUser(request, response);
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.getAllUsers();

        // Verificar que la lista de usuarios no esté vacía
        System.out.println("Usuarios recuperados: " + users.size());
        for (User user : users) {
            System.out.println("id" + user.getId()+  "Usuario: " + user.getName() + " - " + user.getEmail());
        }

        request.setAttribute("users", users);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }


    private void saveUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        userDAO.saveUser(user);
        response.sendRedirect("UserServlet?action=list");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        userDAO.deleteUser(userId);
        response.sendRedirect("UserServlet?action=list");
    }
}
