package json;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private ArrayList<JsonPair> lst;

    public JsonObject(JsonPair... jsonPairs) {
        lst = new ArrayList<>();
        for (JsonPair v: jsonPairs) {
            if (find(v.key) != null) {
                lst.remove(new JsonPair(v.key, find(v.key)));
            }
            lst.add(v);
        }
    }

    @Override
    public String toJson() {
        StringBuilder ret = new StringBuilder("{");
        Iterator<JsonPair> jsonIterator = lst.iterator();
        while (jsonIterator.hasNext()) {
            JsonPair val = jsonIterator.next();
            ret.append("'").append(val.key).append("'");
            ret.append(": ");

            ret.append(val.value.toJson());

            if (jsonIterator.hasNext()) {
                ret.append(", ");
            }
        }
        ret.append("}");
        return ret.toString();
    }

    public void add(JsonPair jsonPair) {
        if (find(jsonPair.key) != null) {
            lst.remove(new JsonPair(jsonPair.key, find(jsonPair.key)));
        }
        lst.add(jsonPair);
    }

    public Json find(String name) {
        for (JsonPair val: lst) {
            if (val.key.equals(name)) {
                return val.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject jsonob = new JsonObject();
        for (JsonPair val: lst) {
            for (String st: names) {
                if (val.key.equals(st)) {
                    jsonob.add(val);
                }
            }
        }
        return jsonob;
    }
}