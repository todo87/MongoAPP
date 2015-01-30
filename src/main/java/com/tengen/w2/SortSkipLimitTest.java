package com.tengen.w2;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by Admin on 25-Jan-2015.
 */
public class SortSkipLimitTest {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient mongo = new MongoClient();
        DB db = mongo.getDB("course");
        DBCollection lines = db.getCollection("DotNotationTest");
        lines.drop();
        Random random = new Random();


        //insert 10 lines with random start and end points
        for (int i=0; i<10; i++){
            lines.insert(
                    new BasicDBObject("_id",i)
                            .append("start",
                                    new BasicDBObject("x", random.nextInt(90)+10)
                                              .append("y", random.nextInt(90)+10)
                            )
                            .append("end",
                                    new BasicDBObject("x", random.nextInt(90)+10)
                                              .append("y", random.nextInt(90)+10))
            );
        }

        QueryBuilder builder = QueryBuilder.start("start.x").greaterThan(50);

        DBCursor cursor = lines.find()
                .sort(new BasicDBObject("start.x",1).append("start.y",-1))
                .skip(2).limit(10);

        try{
            while(cursor.hasNext()){
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }finally {
            cursor.close();
        }

    }
}
