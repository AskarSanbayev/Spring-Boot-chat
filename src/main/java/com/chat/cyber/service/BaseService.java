package com.chat.cyber.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<ENTITY extends Serializable, ID> {

    List<ENTITY> findAll();

    void deleteById(ID id);

    ENTITY findById(ID id);
}
