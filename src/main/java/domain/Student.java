package domain;

import json.JsonArray;
import json.JsonBoolean;
import json.JsonNumber;
import json.JsonObject;
import json.JsonPair;
import json.JsonString;
import json.Tuple;


/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {

    protected Tuple<String, Integer>[] exams;

    public Student(String name, String surname,
                   Integer year,
                   Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = exams;
    }

    @Override
    public JsonObject toJsonObject() {

        JsonObject[] jarr = new JsonObject[exams.length];
        int i = 0;
        for (Tuple val: exams) {
            JsonObject jpb;
            if ((Integer)val.value >= 3) {
                jpb = new JsonObject(
                        new JsonPair("course",
                                new JsonString((String)val.key)),
                        new JsonPair("mark",
                                new JsonNumber((Integer)val.value)),
                        new JsonPair("passed", new JsonBoolean(true))
                );
            } else {
                jpb = new JsonObject(
                        new JsonPair("course",
                                new JsonString((String)val.key)),
                        new JsonPair("mark",
                                new JsonNumber((Integer)val.value)),
                        new JsonPair("passed", new JsonBoolean(false))
                );
            }
            jarr[i] = jpb;
            i++;
        }
        JsonObject jsonObject = new JsonObject(
                new JsonPair("name", new JsonString(name)),
                new JsonPair("surname", new JsonString(surname)),
                new JsonPair("year", new JsonNumber(year)),
                new JsonPair("exams", new JsonArray(jarr))
        );

        return jsonObject;
    }
}