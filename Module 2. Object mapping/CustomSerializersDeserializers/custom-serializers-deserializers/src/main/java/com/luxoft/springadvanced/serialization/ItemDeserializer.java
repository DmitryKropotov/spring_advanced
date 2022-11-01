package com.luxoft.springadvanced.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class ItemDeserializer extends StdDeserializer<Item> {

    private static final long serialVersionUID = 1883547683050039861L;

    public ItemDeserializer() {
        this(null);
    }

    public ItemDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Item deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        int id = (Integer) (node.get("id")).numberValue();
        String itemName = node.get("itemName").asText();
        int userId = (Integer) (node.get("owner")).numberValue();
        String userName = node.get("name").asText();

        return new Item(id, itemName, new User(userId, userName));
    }

}