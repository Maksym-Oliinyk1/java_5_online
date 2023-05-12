package ua.com.alevel.dao;

import ua.com.alevel.entity.BaseEntity;

import java.util.Collection;
import java.util.Optional;

public interface BaseDao<E extends BaseEntity> {

    void create(E e);

    void update(E e);

    void delete(Long id);

    Optional<E> findById(Long id);

    Collection<E> findAll();
}
