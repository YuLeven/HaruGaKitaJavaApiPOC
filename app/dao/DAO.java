package dao;

/**
 * Copyright 2017, Haru ga Kita! - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Yuri Levenhagen <yurileven@gmail.com>, 2017-01-07
 */
public interface DAO<T> {
    T create(T entity);
    void delete(T entity);
    T update(T entity);
    T find(Long ID);
}
