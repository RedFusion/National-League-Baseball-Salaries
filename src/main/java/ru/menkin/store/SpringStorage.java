package ru.menkin.store;

import org.springframework.beans.factory.annotation.*;
import org.springframework.orm.hibernate4.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import ru.menkin.models.*;

import java.util.*;

@Repository
public class SpringStorage implements Storage{

    private final HibernateTemplate template;

    @Autowired
    public SpringStorage(final HibernateTemplate template){
            this.template = template;
    }

    @Override
    public Collection<Player> values() {
        return (List)template.find("from Player");
    }

    @Override
    public int add(Player player) {
        this.template.save(player);
        return player.getId();
    }

    @Override
    public void close() {
        template.getSessionFactory().close();
    }

    @Override
    public Player get(int id) {
        return template.get(Player.class, id);
    }

    @Override
    public void delete(int id) {
        template.getSessionFactory().getCurrentSession().delete(new Player(id, null, null, null, null));
    }

    @Override
    public void edit(Player player) {
        template.update(player);
    }
}
