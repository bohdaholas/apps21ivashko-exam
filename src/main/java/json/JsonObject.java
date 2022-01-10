package json;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private final LinkedList<JsonPair> jsonPairsList = new LinkedList<>();

    public JsonObject(JsonPair... jsonPairs) {
        jsonPairsList.addAll(Arrays.asList(jsonPairs));
    }

    @Override
    public String toJson() {
        List<String> formattedPairs = new LinkedList<>();
        String delimiter = ": ";
        for (JsonPair jsonPair : jsonPairsList) {
            String formattedPair = "'" + jsonPair.key + "'" + delimiter + jsonPair.value.toJson();
            formattedPairs.add(formattedPair);
        }
        return "{" + String.join(", ", formattedPairs) + "}";
    }

    public void add(JsonPair jsonPair) {
        jsonPairsList.add(jsonPair);
    }

    public Json find(String name) {
        for (JsonPair jsonPair : jsonPairsList) {
            if (jsonPair.key.equals(name)) {
                return jsonPair.value;
            }
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject projection = new JsonObject();
        for (String name : names) {
            for (JsonPair jsonPair : jsonPairsList) {
                if (Objects.equals(jsonPair.key, name)) {
                    projection.add(jsonPair);
                }
            }
        }
        return projection;
    }
}
