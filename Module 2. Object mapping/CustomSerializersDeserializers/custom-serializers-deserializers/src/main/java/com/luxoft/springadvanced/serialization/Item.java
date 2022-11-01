package com.luxoft.springadvanced.serialization;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Objects;

@JsonSerialize(using = ItemSerializer.class)
@JsonDeserialize(using = ItemDeserializer.class)
public class Item {
    private int id;
    private String itemName;
    private User owner;

    public Item() {
    }

    public Item(int id, String itemName, User owner) {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Objects.equals(itemName, item.itemName) &&
                Objects.equals(owner, item.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemName, owner);
    }


    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", owner=" + owner +
                '}';
    }


}