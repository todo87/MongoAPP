package com.tengen.w2;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Admin on 30-Jan-2015.
 */
public class UpdateRemoveTest {
    public static void main(String[] args) throws UnknownHostException {

        DBCollection coll = createCollection("updateTest");
        coll.drop();
        Random random = new Random();

        List<String> names = Arrays.asList("alice", "bobby", "cathy", "david", "ethan");
        for(String name:names){
            coll.insert(new BasicDBObject("_id",name));
        }

        // practic inlocuieste inregistrarea din baza
        coll.update(new BasicDBObject("_id","alice"),
                new BasicDBObject("age",24));

        // modifica/adauga atribute la inregistrare (practic asta este update)
        coll.update(new BasicDBObject("_id","alice"),
                new BasicDBObject("$set", new BasicDBObject("gender","Female")));

        // nu face nimic pt ca nu exista documentul (ca sa faca ceva trebuie facut upsert)
        coll.update(new BasicDBObject("_id","fane"),
                new BasicDBObject("$set", new BasicDBObject("gender","M")));

        // upsert :-> daca exista face update daca nu creeaza
        coll.update(new BasicDBObject("_id","frank"),
                new BasicDBObject("$set", new BasicDBObject("gender","M")),true,false);

        // multi update
        coll.update(new BasicDBObject(),
                new BasicDBObject("$set", new BasicDBObject("title","Dr.")),false,true);

        // remove
        coll.remove(new BasicDBObject("_id","alice"));

        printCollection(coll);
    }

    public static DBCollection createCollection(String coll) throws UnknownHostException {
        MongoClient mongo = new MongoClient();
        DB db = mongo.getDB("course");
        return db.getCollection(coll);
    }

    public static void printCollection(DBCollection coll){
        DBCursor cur = coll.find();
        try{
            while(cur.hasNext()) System.out.println(cur.next());
        }finally {
            cur.close();
        }

    }
}
