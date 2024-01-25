package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Ivan", "Ivanov", (byte) 25);
        userDaoHibernate.saveUser("Petr", "Petrov", (byte) 35);
        userDaoHibernate.saveUser("Roman", "Romanov", (byte) 40);
        userDaoHibernate.saveUser("Anton", "Antonov", (byte) 30);
        userDaoHibernate.saveUser("Sergey", "Baranov", (byte) 90);
        System.out.println(userDaoHibernate.getAllUsers());
        userDaoHibernate.removeUserById(3);
        System.out.println(userDaoHibernate.getAllUsers());
        userDaoHibernate.cleanUsersTable();
        System.out.println(userDaoHibernate.getAllUsers());
        userDaoHibernate.dropUsersTable();

    }
}
