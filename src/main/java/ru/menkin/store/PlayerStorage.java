package ru.menkin.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import ru.menkin.models.*;

import java.util.Collection;

/**
 * @author Menkin
 * @since 17.10.2015
 */

@Repository
public class PlayerStorage implements Storage<Player> {
    private final SessionFactory factory;

    public PlayerStorage(){
        factory = new Configuration().configure().buildSessionFactory();
    }

    public interface Command<T>{
        T process(Session session);
    }

    //repeatable code
    //the same in HibernateStorage
    private <T> T transaction(final Command<T> command){
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try{
            return command.process(session);
        }finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public Collection<Player> values() {
        return transaction(((Session session) -> session.createQuery("from Player").list()));
    }

    @Override
    public int add(Player player) {
        return transaction(session -> {
            session.save(player);
            return player.getId();
        });
    }

    @Override
    public void edit(Player player) {
        transaction(session -> {
            session.update(player);
            return null;
        });
    }

    @Override
    public Player get(int id) {
        return transaction(new Command<Player>() {
            @Override
            public Player process(Session session) {
                return session.get(Player.class, id);
            }
        });
    }

    @Override
    public void delete(int id) {
        transaction(new Command() {
            @Override
            public Object process(Session session) {
                session.delete(get(id));
                return null;
            }
        });
    }

    @Override
    public void close() {
        this.factory.close();
    }

}
