package domain;

import json.JsonNumber;
import json.JsonObject;
import json.JsonPair;
import json.JsonString;
import json.Jsonable;

/**
 * Created by Andrii_Rodionov on 1/5/2017.
 */
public class BasicStudent implements Jsonable {

    protected String name;
    protected String surname;
    protected Integer year;

    public BasicStudent() {
    }

    public BasicStudent(String name, String surname, Integer year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject jsonObject = new JsonObject(
                new JsonPair("name", new JsonString(name)),
                new JsonPair("surname", new JsonString(surname)),
                new JsonPair("year", new JsonNumber(year))
        );

        return jsonObject;
    }
}