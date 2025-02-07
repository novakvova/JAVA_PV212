package org.example;

import org.example.entities.Genre;

public class Main {

    //user neon: valim34536@rykone.com
    //password neon: valim34536@rykone.comQ
//postgresql://neondb_owner:npg_fkWVUtH6e4mq@ep-summer-smoke-a28kfphw-pooler.eu-central-1.aws.neon.tech/neondb?sslmode=require



    public static void main(String[] args) {
        var session = HibernateUtil.getSession();
        try {
            session.beginTransaction();

            var genre = new Genre();
            genre.setName("Сталкер 2");
//            session.save(genre);
            session.persist(genre);

            session.getTransaction().commit();
        } catch(Exception ex) {
            System.out.println("Щось пішло не так! "+ ex.getMessage());
        }
    }
}