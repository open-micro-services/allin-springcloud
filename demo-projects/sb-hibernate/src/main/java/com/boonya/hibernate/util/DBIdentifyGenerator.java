package com.boonya.hibernate.util;

import org.hibernate.boot.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import java.io.Serializable;

public class DBIdentifyGenerator extends IdentityGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws MappingException {
        Object id = DBSnowflake.getNextId();
        if (id != null) {
            return (Serializable) id;
        }
        return super.generate(session, object);
    }
}
