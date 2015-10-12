package ru.menkin.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.menkin.models.Player;

import java.util.Collection;

/**
 * @author Menkin
 */
public class HibernateStorage
{
    private final SessionFactory factory;

    public HibernateStorage() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public Collection<Player> values() {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from Player").list();
        } finally {
            tx.commit();
            session.close();
        }
    }

    public int add(final Player player) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(player);
            return player.getId();
        } finally {
            tx.commit();
            session.close();
        }
    }
}
