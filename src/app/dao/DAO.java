package dao;

import java.util.List;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-07
 */
public interface DAO<T> {
    T create(T entity);
    void delete(T entity);
    T update(T entity);
    T find(Long ID);
    List<T> findAll();
}
