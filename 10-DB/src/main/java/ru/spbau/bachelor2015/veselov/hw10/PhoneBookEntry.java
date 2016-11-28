package ru.spbau.bachelor2015.veselov.hw10;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Class which represents an entry in a phone book database. One entry consists of a name and a telephone number.
 * Format of a telephone number isn't specified, so it's just a String field.
 */
@Entity
public class PhoneBookEntry {
    @Id
    private final ObjectId id;

    private final String name;
    private final String telephone;

    /**
     * Creates a brand new entry which has a unique new id.
     *
     * @param name a person name.
     * @param telephone a telephone number.
     */
    public PhoneBookEntry(String name, String telephone) {
        this.id = new ObjectId();

        this.name = name;
        this.telephone = telephone;
    }

    /**
     * Creates an entry with a specified id. Must be used to reference existing entry in a database.
     *
     * @param id an id of an entry in a database.
     * @param name a person name.
     * @param telephone a telephone number.
     */
    public PhoneBookEntry(ObjectId id, String name, String telephone) {
        this.id = id;

        this.name = name;
        this.telephone = telephone;
    }

    /**
     * Returns id of an entry.
     */
    public ObjectId getId() {
        return id;
    }

    /**
     * Returns a person name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a telephone number.
     */
    public String getTelephone() {
        return telephone;
    }
}
