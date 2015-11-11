package ru.menkin.store;

import org.hibernate.service.spi.*;
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
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public Player get(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void edit(Player player) {

    }
}
