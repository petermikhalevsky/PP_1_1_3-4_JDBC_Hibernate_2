package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String query = """
                CREATE TABLE IF NOT EXISTS `users` (
                `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                `name` VARCHAR(45) NOT NULL,
                `lastname` VARCHAR(45) NOT NULL,
                `age` INT NOT NULL)
                """;

        try (Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            session.createNativeQuery(query).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Table creation Error!");
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()){
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Table dropping Error!");
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            //session.persist(user);
            session.save(user);
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Saving User Error!");
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM users WHERE id = " + id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("User deleting Error!");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Session session = Util.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            list = session.createQuery("FROM User"
                     ).getResultList();
            session.getTransaction().commit();

        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Cleaning table Error!");
        }
    }
}
