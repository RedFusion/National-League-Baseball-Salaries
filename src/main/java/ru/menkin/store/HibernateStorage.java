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

public class HibernateStorage implements Storage<Player>
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

    @Override
    public void close() {
        this.factory.close();
    }

    @Override
    public Player get(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.get(Player.class, id);
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.delete(new Player(id, null, null, null, null));
        }finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public void edit(Player player) {
        final Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(player);
        } finally {
            tx.commit();
            session.close();
        }
    }
}
