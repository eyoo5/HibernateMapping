package utilities;

import model.Address;
import model.Cohort;
import model.Department;
import model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;

public class DBUtility {
    public DBUtility(){};
    public static Session getConnection() {
        String dbPassword = System.getenv("hibernate_password");
        String dbUsername = System.getenv("hibernate_username");
        String dbURL = System.getenv("dbURL");

        Configuration configuration = new Configuration();
        //setting password using .env file:
        configuration.setProperty("hibernate.connection.password", dbPassword);
        configuration.setProperty("hibernate.connection.username", dbUsername);
        configuration.setProperty("hibernate.connection.url", dbURL);

        //adding mapping class:
        configuration.addAnnotatedClass(Department.class);
        configuration.addAnnotatedClass(Teacher.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Cohort.class);
        configuration.configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
        Session session = factory.openSession();
        return session;
    }
}
