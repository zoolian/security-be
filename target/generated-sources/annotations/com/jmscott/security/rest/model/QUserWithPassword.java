package com.jmscott.security.rest.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserWithPassword is a Querydsl query type for UserWithPassword
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserWithPassword extends EntityPathBase<UserWithPassword> {

    private static final long serialVersionUID = 1124338012L;

    public static final QUserWithPassword userWithPassword = new QUserWithPassword("userWithPassword");

    public final QUser _super = new QUser(this);

    //inherited
    public final NumberPath<Integer> age = _super.age;

    //inherited
    public final CollectionPath<com.mongodb.DBRef, com.mongodb.QDBRef> DBRefRoles = _super.DBRefRoles;

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final BooleanPath enabled = _super.enabled;

    //inherited
    public final StringPath firstName = _super.firstName;

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final StringPath lastName = _super.lastName;

    public final StringPath password = createString("password");

    //inherited
    public final CollectionPath<Role, QRole> roles = _super.roles;

    //inherited
    public final StringPath username = _super.username;

    public QUserWithPassword(String variable) {
        super(UserWithPassword.class, forVariable(variable));
    }

    public QUserWithPassword(Path<? extends UserWithPassword> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserWithPassword(PathMetadata metadata) {
        super(UserWithPassword.class, metadata);
    }

}

