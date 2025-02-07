package org.example;

import org.example.entities.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    //визивається автоматично коли ми використовуємо цей клас
    static {
        try {
            var config = new Configuration()
                    .configure(); //автоатично читає файл hibernate.cfg.xml
            config.addAnnotatedClass(Genre.class);
            sessionFactory = config.buildSessionFactory();
            System.out.println("----Підклчюення успішне-----");
        } catch (Exception e) {
            System.out.println("Помилка підключення до БД! "+e.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        if(sessionFactory!=null) {
            sessionFactory.close();
        }
    }
}
