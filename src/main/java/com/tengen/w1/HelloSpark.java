package com.tengen.w1;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by Admin on 16-Jan-2015.
 */
public class HelloSpark {

    public static void main(String[] args) {
        Spark.get("/",new Route(){
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "hello world spark";
            }
        });
    }

}
