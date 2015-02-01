package com.tengen.w1;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 17-Jan-2015.
 */
public class SparkFormHandling {

    public static void main(String[] args) {
        // Configure Freemarker
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFormHandling.class,"/");

        // Configure routes
        Spark.get("/", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                try {
                    Map<String, Object> fruitsMap = new HashMap<String, Object>();
                    fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));
                    Template fruitPickerTemplate = configuration.getTemplate("fruitPicker.ftl");
                    StringWriter writer = new StringWriter();
                    fruitPickerTemplate.process(fruitsMap, writer);
                    return(writer);
                } catch (Exception e) {
                    Spark.halt(500);
                    return null;
                }
            }
        });

        Spark.post("/favorite_fruit", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                final String fruit = request.queryParams("fruit");
                if (fruit == null){
                    return "Why don't you pick one ?";
                }else{
                    return "Your favorite fruit is " + fruit;
                }
            }
        });
    }
}