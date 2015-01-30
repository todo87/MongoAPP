package com.tengen.w2;

import com.mongodb.BasicDBObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Admin on 25-Jan-2015.
 */
public class DocumentRepresentationTest {

    public static void main(String[] args) {
        BasicDBObject doc = new BasicDBObject();
        doc.put("username","jyemin");
        doc.put("birthDate", new Date(1243241233));
        doc.put("programmer", true);
        doc.put("age", 27);
        doc.put("languages", Arrays.asList("Java","C++"));
        doc.put("address", new BasicDBObject("street","20 Main").append("town","Westfied").append("zip","56789"));
    }
}
