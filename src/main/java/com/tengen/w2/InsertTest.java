package com.tengen.w2;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Admin on 25-Jan-2015.
 */
public class InsertTest {
    public static void main(String[] args) throws UnknownHostException {

        MongoClient client = new MongoClient();
        DB courseDB = client.getDB("course");
        DBCollection coll = courseDB.getCollection("findTest");
        coll.drop();

        // inserts 10 documents with a random integer as the value of field "x"
        for (int i=0; i<10; i++){
            coll.insert(new BasicDBObject("x",new Random().nextInt(100)));
        }

        System.out.println("\n");

        System.out.println("Find one:");
        DBObject one = coll.findOne();
        System.out.println(one);

        System.out.println("\nFind all");
        DBCursor cur = coll.find();
        try{
            while(cur.hasNext()) System.out.println(cur.next());
        }finally {
            cur.close();
        }

        System.out.println("\nCout:");
        long count = coll.count();
        System.out.println(count);

        System.out.println("\n");

    }
}
