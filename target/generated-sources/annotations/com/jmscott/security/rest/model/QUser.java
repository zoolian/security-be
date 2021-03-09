package com.jmscott.security.rest.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 933137467L;

    public static final QUser user = new QUser("user");

    public final QPerson _super = new QPerson(this);

    public final CollectionPath<com.mongodb.DBRef, com.mongodb.QDBRef> DBRefRoles = this.<com.mongodb.DBRef, com.mongodb.QDBRef>createCollection("DBRefRoles", com.mongodb.DBRef.class, com.mongodb.QDBRef.class, PathInits.DIRECT2);

    //inherited
    public final DatePath<java.time.LocalDate> dob = _super.dob;

    //inherited
    public final StringPath email = _super.email;

    public final BooleanPath enabled = createBoolean("enabled");

    //inherited
    public final StringPath firstName = _super.firstName;

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final StringPath lastName = _super.lastName;

    public final CollectionPath<Role, QRole> roles = this.<Role, QRole>createCollection("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

