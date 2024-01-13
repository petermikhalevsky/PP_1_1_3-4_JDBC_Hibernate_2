package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Ivan", "Ivanov", (byte) 25);
        userDao.saveUser("Petr", "Petrov", (byte) 35);
        userDao.saveUser("Roman", "Romanov", (byte) 40);
        userDao.saveUser("Anton", "Antonov", (byte) 30);
        System.out.println(userDao.getAllUsers());
        userDao.removeUserById(3);
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        System.out.println(userDao.getAllUsers());
        userDao.dropUsersTable();
        //System.out.println(userDao.getAllUsers());
    }
}
