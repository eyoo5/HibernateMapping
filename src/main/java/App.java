import model.Department;
import model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
//        manyToOne();
        oneToMany();
    }


    public static void manyToOne(){
        //Using .env version:
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
        configuration.configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();


        //creating departments
        Department dept1 = new Department("IT");
        Department dept2 = new Department("HR");

        //creating teacher
        Teacher t1 = new Teacher("1000","MHaseeb",dept1);
        Teacher t2 = new Teacher("2220","Shahparan",dept1);
        Teacher t3 = new Teacher("3000","James",dept1);
        Teacher t4 = new Teacher("40000","Joseph",dept2);
        //Storing Departments in database
        session.persist(dept1);
        session.persist(dept2);
        //Storing teachers  in database
        session.persist(t1);
        session.persist(t2);
        session.persist(t3);
        session.persist(t4);
        transaction.commit();
    }


    public static void oneToMany(){
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
        configuration.configure();

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        //creating teacher
        Teacher t1 = new Teacher("1000","MHaseeb");
        Teacher t2 = new Teacher("2220","Shahparan");
        Teacher t3 = new Teacher("3000","James");
        Teacher t4 = new Teacher("40000","Joseph");
        Teacher t5 = new Teacher("200","Ali");

        //Add teacher entity object to the list
        ArrayList<Teacher> teachersList = new ArrayList<>();
        teachersList.add(t1);
        teachersList.add(t2);
        teachersList.add(t3);
        teachersList.add(t4);
        teachersList.add(t5);
        session.persist(t1);
        session.persist(t2);
        session.persist(t3);
        session.persist(t4);
        session.persist(t5);
        //Creating Department
        Department department = new Department();
        department.setDeptName("Development");
        department.setTeacherList(teachersList);
        //Storing Department
        session.persist(department);
        transaction.commit();
    }


}
