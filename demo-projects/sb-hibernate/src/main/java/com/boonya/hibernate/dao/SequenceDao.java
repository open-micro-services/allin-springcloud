package com.boonya.hibernate.dao;

import com.boonya.hibernate.entity.Sequence;

public interface SequenceDao<T> {

    Sequence add(Sequence sequence);
}
