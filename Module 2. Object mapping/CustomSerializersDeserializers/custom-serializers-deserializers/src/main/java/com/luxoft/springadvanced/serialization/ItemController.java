package com.luxoft.springadvanced.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @GetMapping
    public Item getItem() {
        return new Item(1, "Item name", new User(1, "Item owner"));
    }

    @GetMapping("plain")
    public String getItemString() throws JsonProcessingException {
        Item myItem = new Item(1, "theItem", new User(2, "theUser"));
        String serializedItem = new ObjectMapper().writeValueAsString(myItem);
        return serializedItem;
    }
}
