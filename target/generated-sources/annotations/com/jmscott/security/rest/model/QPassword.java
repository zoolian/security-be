package com.jmscott.security.rest.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPassword is a Querydsl query type for Password
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPassword extends EntityPathBase<Password> {

    private static final long serialVersionUID = 1229693707L;

    public static final QPassword password1 = new QPassword("password1");

    public final StringPath id = createString("id");

    public final StringPath password = createString("password");

    public final StringPath personId = createString("personId");

    public final StringPath userId = createString("userId");

    public QPassword(String variable) {
        super(Password.class, forVariable(variable));
    }

    public QPassword(Path<? extends Password> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPassword(PathMetadata metadata) {
        super(Password.class, metadata);
    }

}

