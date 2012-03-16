package org.vaadin.teemu.jsoncontainer;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.vaadin.data.Container;

public interface JsonContainer extends Container.Indexed, Container.Sortable,
        Container.Filterable, Container.SimpleFilterable {

    public static class Factory {

        /**
         * Creates a new instance of JsonContainer for the given JSON string.
         * 
         * @throws IllegalArgumentException
         *             if the given JSON string cannot be parsed.
         * @param json
         *            JSON data for populating the container.
         * @return new JsonContainer instance populated with the given data.
         * 
         */
        public static JsonContainer newInstance(String json) {
            JsonParser parser = new JsonParser();
            try {
                JsonElement parsedJsonData = parser.parse(json);
                return new IndexedJsonContainer(parsedJsonData);
            } catch (JsonSyntaxException e) {
                throw new IllegalArgumentException(
                        "Cannot parse the given JSON: \"" + json + "\"");
            }
        }
    }

}
