package com.tengen.w2;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by Admin on 25-Jan-2015.
 */
public class FindCriteriaTest2 {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection coll = db.getCollection("findCriteriaTest");
        coll.drop();
        Random ran = new Random();

        // insert 10 docs with two random integers
        for (int i=0; i<10; i++){
            coll.insert(
                    new BasicDBObject("x", ran.nextInt(2))
                            .append("y", ran.nextInt(100))
                            .append("z", ran.nextInt(1000)));
        }

        DBObject query = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(70).get();

        DBObject query1 = new BasicDBObject("x",0).append("y", new BasicDBObject("$gt",10).append("$lt",90));

        System.out.println("\nFind all");
        DBCursor cur = coll.find(query,
                new BasicDBObject("y",true).append("_id",false));
        try{
            while(cur.hasNext()) System.out.println(cur.next());
        }finally {
            cur.close();
        }

        System.out.println("\n");

    }
}
