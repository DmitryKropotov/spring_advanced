package com.luxoft.springadvanced.serialization;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerializationDeserializationTest {

    @Test
    @DisplayName("Given an Item, when it is serialized and deserialized, then the deserialized Item will be equal to the initial Item")
    public void testSerializationDeserialization() throws IOException {
        Item myItem = new Item(1, "theItem", new User(2, "theUser"));
        String serialized = new ObjectMapper().writeValueAsString(myItem);
        System.out.println(serialized);
        Item readValue = new ObjectMapper().readValue(serialized, Item.class);
        assertEquals(myItem, readValue);
    }

    @Test
    @DisplayName("Given an Item, when it is custom serialized and deserialized, then the deserialized Item will be equal to the initial Item")
    public void testCustomSerialization() throws IOException {
        Item myItem = new Item(1, "theItem", new User(2, "theUser"));

        ObjectMapper mapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Item.class, new ItemSerializer());
        simpleModule.addDeserializer(Item.class, new ItemDeserializer());
        mapper.registerModule(simpleModule);

        String serialized = mapper.writeValueAsString(myItem);
        System.out.println(serialized);
        Item readValue = mapper.readValue(serialized, Item.class);
        assertEquals(myItem, readValue);

    }

}
