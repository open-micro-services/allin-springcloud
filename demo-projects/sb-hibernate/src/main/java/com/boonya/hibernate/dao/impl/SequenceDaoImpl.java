package com.boonya.hibernate.dao.impl;

import com.boonya.hibernate.dao.SequenceDao;
import com.boonya.hibernate.entity.Sequence;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class SequenceDaoImpl implements SequenceDao<Sequence> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Sequence add(Sequence sequence) {
        entityManager.persist(sequence);
        return sequence;
    }
}
