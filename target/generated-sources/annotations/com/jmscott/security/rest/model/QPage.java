package com.jmscott.security.rest.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPage is a Querydsl query type for Page
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPage extends EntityPathBase<Page> {

    private static final long serialVersionUID = 932971263L;

    public static final QPage page = new QPage("page");

    public final StringPath description = createString("description");

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final ListPath<Role, QRole> roles = this.<Role, QRole>createList("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public QPage(String variable) {
        super(Page.class, forVariable(variable));
    }

    public QPage(Path<? extends Page> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPage(PathMetadata metadata) {
        super(Page.class, metadata);
    }

}

