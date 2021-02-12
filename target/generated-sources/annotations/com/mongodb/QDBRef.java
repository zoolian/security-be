package com.mongodb;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDBRef is a Querydsl query type for DBRef
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QDBRef extends BeanPath<DBRef> {

    private static final long serialVersionUID = 1878594828L;

    public static final QDBRef dBRef = new QDBRef("dBRef");

    public final StringPath collectionName = createString("collectionName");

    public final StringPath databaseName = createString("databaseName");

    public final SimplePath<Object> id = createSimple("id", Object.class);

    public QDBRef(String variable) {
        super(DBRef.class, forVariable(variable));
    }

    public QDBRef(Path<? extends DBRef> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDBRef(PathMetadata metadata) {
        super(DBRef.class, metadata);
    }

}

