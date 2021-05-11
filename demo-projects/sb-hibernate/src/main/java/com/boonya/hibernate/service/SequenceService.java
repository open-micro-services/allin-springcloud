package com.boonya.hibernate.service;

import com.boonya.hibernate.dao.SequenceDao;
import com.boonya.hibernate.entity.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceService {

    @Autowired
    SequenceDao sequenceDao;

    public synchronized Sequence add(Sequence sequence) {
        return sequenceDao.add(sequence);
    }
}
