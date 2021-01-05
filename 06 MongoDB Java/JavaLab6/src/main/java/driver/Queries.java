package driver;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.lang.reflect.Array;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.*;

public class Queries {

    private MongoDatabase database;

    public Queries() {
        MongoClient client = MongoClients.create();
        database = client.getDatabase("social_network");
    }


    public void  findUsersWithInvalidNames() {

        MongoCollection<Document> collection = database.getCollection("user");

        FindIterable<Document> resultDocuments = collection.find(
                and(
                        or(

                                exists("surname", false),
                                and(
                                        exists("name", false),
                                        new Document("confirmed", true)
                                )

                        )
                )
        );


        for (Document document : resultDocuments) {
            System.out.println(document.toJson());
        }

    }

    public void addNewUser() {

        MongoCollection<Document> collection = database.getCollection("user");

        /*
db.user.insert(
    {
        name : 'Max2',
        surname : 'Some Max2',
        age : 27,
        sex : 'Man',
        confirmed : false,
        job : 'postman'
    }
    );

 */
        Document document = new Document("name", "Tester")
                .append("surname", "Some Tester")
                .append("age", 27)
                .append("sex", "Man")
                .append("confirmed", false)
                .append("hobbies", Arrays.asList("football", "games"));
        collection.insertOne(document);
    }

    public void addCity(String id, String cityId) {

        MongoCollection<Document> collection = database.getCollection("user");

        collection.updateOne(
                new BasicDBObject("_id", new ObjectId(id)),
                new BasicDBObject("$set", new BasicDBObject(
                        "city", new BasicDBObject("city",
                            new BasicDBObject("from", cityId)
                        )
                ))
        );
    }

    public void deleteUserById(String id) {

        MongoCollection<Document> collection = database.getCollection("user");

        collection.deleteOne(new BasicDBObject("_id", new ObjectId(id)));
    }
}
