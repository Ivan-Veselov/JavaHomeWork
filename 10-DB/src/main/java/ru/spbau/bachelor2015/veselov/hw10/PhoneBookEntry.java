package ru.spbau.bachelor2015.veselov.hw10;

import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Class which represents an entry in a phone book database. One entry consists of a name and a phone number.
 * Format of a phone number isn't specified, so it's just a String field.
 */
@Entity
public class PhoneBookEntry {
    @Id
    private final ObjectId id;

    private final String name;
    private final String phone;

    /**
     * Creates an empty object. Used by morphia.
     */
    public PhoneBookEntry() {
        id = null;
        name = null;
        phone = null;
    }

    /**
     * Creates a brand new entry which has a unique new id.
     *
     * @param name a person name.
     * @param phone a phone number.
     */
    public PhoneBookEntry(@NotNull String name, @NotNull String phone) {
        this.id = new ObjectId();

        this.name = name;
        this.phone = phone;
    }

    /**
     * Creates an entry with a specified id. Must be used to reference existing entry in a database.
     *
     * @param id an id of an entry in a database.
     * @param name a person name.
     * @param phone a phone number.
     */
    public PhoneBookEntry(@NotNull ObjectId id, @NotNull String name, @NotNull String phone) {
        this.id = id;

        this.name = name;
        this.phone = phone;
    }

    /**
     * Returns id of an entry.
     */
    public @NotNull ObjectId getId() {
        return id;
    }

    /**
     * Returns a person name.
     */
    public @NotNull String getName() {
        return name;
    }

    /**
     * Returns a phone number.
     */
    public @NotNull String getPhone() {
        return phone;
    }
}
