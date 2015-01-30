package com.tengen.w2;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by Admin on 31-Jan-2015.
 */
public class FindAndModifyTest {

    public static void main(String[] args) throws UnknownHostException {
        DBCollection coll = createCollection("findAndModify");
        coll.drop();

        final String counterId = "abc";
        int first;
        int numNeeded;

        numNeeded = 2;
        first = getRange(counterId, numNeeded, coll);
        System.out.println("Range " + first + "-" + (first + numNeeded - 1));

        numNeeded = 3;
        first = getRange(counterId, numNeeded, coll);
        System.out.println("Range " + first + "-" + (first + numNeeded - 1));

        numNeeded = 10;
        first = getRange(counterId, numNeeded, coll);
        System.out.println("Range " + first + "-" + (first + numNeeded - 1));

        System.out.println();

        printCollection(coll);

    }

    //procedura atomica
    public static int getRange(String id, int range, DBCollection coll){
        DBObject doc = coll.findAndModify(
                new BasicDBObject("_id", id), null, null, false,
                new BasicDBObject("$inc", new BasicDBObject("counter",range)),
                true, true);
        return (Integer) doc.get("counter") - range + 1;
    }

    public static DBCollection createCollection(String coll) throws  UnknownHostException {
        MongoClient mongo = new MongoClient();
        DB db = mongo.getDB("course");
        return db.getCollection(coll);
    }

    public static void printCollection(DBCollection coll) {
        DBCursor cur = coll.find();
        try {
            while (cur.hasNext()) System.out.println(cur.next());
        } finally {
            cur.close();
        }

    }
}
