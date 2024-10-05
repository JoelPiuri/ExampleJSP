package com.example.dao;

import com.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDAO {

    private SessionFactory factory;

    public UserDAO() {
        factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
    }

    // Método para obtener todos los usuarios
    public List<User> getAllUsers() {
        Session session = null;
        List<User> users = null;
        try {
            session = factory.openSession(); // Usar openSession en lugar de getCurrentSession
            session.beginTransaction();

            users = session.createQuery("from User", User.class).getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close(); // Cerrar la sesión
            }
        }
        return users;
    }

    // Método para guardar o actualizar un usuario
    public void saveUser(User user) {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();

            session.saveOrUpdate(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // Método para eliminar un usuario
    public void deleteUser(int userId) {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();

            User user = session.get(User.class, userId);
            if (user != null) {
                session.delete(user);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // Método para obtener un solo usuario
    public User getUser(int userId) {
        Session session = null;
        User user = null;
        try {
            session = factory.openSession();
            session.beginTransaction();

            user = session.get(User.class, userId);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }
}
