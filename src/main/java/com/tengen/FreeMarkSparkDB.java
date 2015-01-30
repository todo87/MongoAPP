package com.tengen;

import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 16-Jan-2015.
 */
public class FreeMarkSparkDB {

    public static void main(String[] args) throws UnknownHostException {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFreemarker.class,"/");

        MongoClient client = new MongoClient(new ServerAddress("localhost",27017));

        DB database = client.getDB("course");
        final DBCollection collection = database.getCollection("hello");

        Spark.get("/", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                StringWriter writer = new StringWriter();
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");

                    DBObject doc = collection.findOne();

                    helloTemplate.process(doc, writer);

                    System.out.println(writer);

                } catch (Exception e) {
                    Spark.halt(500);
                    e.printStackTrace();
                }
                return writer;
            }
        });
    }


}
