package ru.menkin.store;

import org.springframework.beans.factory.annotation.*;
import org.springframework.orm.hibernate4.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;
import ru.menkin.models.*;

import java.util.*;

/**
 * @author Menkin
 * @since 10.11.2015
 */
@Repository
public class SpringStorage implements Storage<Player>{

    private final HibernateTemplate template;

    @Autowired
    public SpringStorage(final HibernateTemplate template){
            this.template = template;
    }

    @Override
    public Collection<Player> values() {
        return (List)template.find("from Player");
    }

    public Collection<Player> values(String key, String order) {
        return (List)template.find(String.format("from Player order by %s", key), order);
    }

    //FlushMode readOnly
//    @Transactional
    @Override
    public int add(Player player) {
        return (int)template.save(player);
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
