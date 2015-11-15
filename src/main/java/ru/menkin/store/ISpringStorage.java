package ru.menkin.store;

import org.springframework.transaction.annotation.*;
import ru.menkin.models.*;

@Transactional(readOnly = false)
public interface ISpringStorage extends Storage<Player>{

}
