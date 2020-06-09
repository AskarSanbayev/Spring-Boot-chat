package com.chat.cyber.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<ENTITY extends Serializable, ID> {

    List<ENTITY> findAll();

    void deleteById(ID id);

    void create(ENTITY entity);

    void update(ENTITY entity);

    ENTITY findById(ID id);
}
