package com.luxoft.springadvanced.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ItemSerializer extends StdSerializer<Item> {

    private static final long serialVersionUID = 6739170890621978901L;

    public ItemSerializer() {
        this(null);
    }

    public ItemSerializer(Class<Item> t) {
        super(t);
    }

    @Override
    public void serialize(Item value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("itemName", value.getItemName());
        jgen.writeNumberField("owner", value.getOwner().getId());
        jgen.writeStringField("name", value.getOwner().getName());
        jgen.writeEndObject();
    }

}