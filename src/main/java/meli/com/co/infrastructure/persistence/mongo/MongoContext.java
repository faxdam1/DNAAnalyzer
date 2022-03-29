package meli.com.co.infrastructure.persistence.mongo;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import meli.com.co.infrastructure.persistence.mongo.model.ClienteModel;
import javax.inject.Inject;
import javax.inject.Singleton;

@Factory
public class MongoContext {

    @Value("${mongodb.database}")
    private String databaseName;

    @Inject
    private MongoClient mongoClient;

    @Singleton
    MongoCollection<ClienteModel> getMongoCollectionCliente(){
        return mongoClient.getDatabase(databaseName).getCollection("clientes", ClienteModel.class);
    }

}
