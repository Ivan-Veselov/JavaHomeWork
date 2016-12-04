package ru.spbau.bachelor2015.veselov.hw10;

import com.mongodb.MongoClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

/**
 * A class which performs interaction with mongo database with a given name.
 */
public class DataBase {
    private final Datastore datastore;

    /**
     * Creates a link to mongo database which has a given name.
     *
     * @param databaseName a database name.
     */
    public DataBase(String databaseName) {
        Morphia morphia = new Morphia();
        morphia.mapPackage("ru.spbau.bachelor2015.veselov.hw10");

        datastore = morphia.createDatastore(new MongoClient(), databaseName);
    }

    /**
     * Adds new entry to the database. Does nothing if entry with given name and phone already exists.
     *
     * @param name a person name.
     * @param phone a phone number.
     * @return ENTRY_ALREADY_EXISTS if entry already exists, OK otherwise.
     */
    public @NotNull Respond addNewEntry(@NotNull String name, @NotNull String phone) {
        if (getEntry(name, phone) != null) {
            return Respond.ENTRY_ALREADY_EXISTS;
        }

        datastore.save(new PhoneBookEntry(name, phone));
        return Respond.OK;
    }

    /**
     * Returns all entries with a given name.
     *
     * @param name a person name.
     * @return a list of entries, where each entry's name equals to given one.
     */
    public @NotNull List<PhoneBookEntry> findByName(@NotNull String name) {
        return datastore.createQuery(PhoneBookEntry.class).field("name").equal(name).asList();
    }

    /**
     * Returns all entries with a given phone.
     *
     * @param phone a phone number.
     * @return a list of entries, where each entry's phone equals to given one.
     */
    public @NotNull List<PhoneBookEntry> findByPhone(@NotNull String phone) {
        return datastore.createQuery(PhoneBookEntry.class).field("phone").equal(phone).asList();
    }

    /**
     * Deletes entry from the database.
     *
     * @param name a person name.
     * @param phone a phone number.
     * @return NO_SUCH_ENTRY if there is no entry with given name and phone in the database, OK otherwise.
     */
    public @NotNull Respond deleteEntry(@NotNull String name, @NotNull String phone) {
        PhoneBookEntry entry = getEntry(name, phone);
        if (entry == null) {
            return Respond.NO_SUCH_ENTRY;
        }

        datastore.delete(entry);
        return Respond.OK;
    }

    /**
     * Changes person name of a particular entry which determined by it's name and phone fields.
     *
     * @param oldName old person name.
     * @param phone a phone number.
     * @param newName new person name.
     * @return NO_SUCH_ENTRY if there is no entry with given old name and phone in the database, ENTRY_ALREADY_EXISTS if
     *         there is an entry with given new name and phone in the database, OK otherwise.
     */
    public @NotNull Respond changeName(@NotNull String oldName, @NotNull String phone, @NotNull String newName) {
        PhoneBookEntry entry = getEntry(oldName, phone);
        if (entry == null) {
            return Respond.NO_SUCH_ENTRY;
        }

        if (getEntry(newName, phone) != null) {
            return Respond.ENTRY_ALREADY_EXISTS;
        }

        datastore.save(new PhoneBookEntry(entry.getId(), newName, entry.getPhone()));
        return Respond.OK;
    }

    /**
     * Changes phone number of a particular entry which determined by it's name and phone fields.
     *
     * @param name a person name.
     * @param oldPhone old phone number.
     * @param newPhone new phone number.
     * @return NO_SUCH_ENTRY if there is no entry with given name and old phone in the database, ENTRY_ALREADY_EXISTS if
     *         there is an entry with given name and new phone in the database, OK otherwise.
     */
    public @NotNull Respond changePhone(@NotNull String name, @NotNull String oldPhone, @NotNull String newPhone) {
        PhoneBookEntry entry = getEntry(name, oldPhone);
        if (entry == null) {
            return Respond.NO_SUCH_ENTRY;
        }

        if (getEntry(name, newPhone) != null) {
            return Respond.ENTRY_ALREADY_EXISTS;
        }

        datastore.save(new PhoneBookEntry(entry.getId(), entry.getName(), newPhone));
        return Respond.OK;
    }

    /**
     * Returns a list of all entries in the database.
     */
    public @NotNull List<PhoneBookEntry> getAllEntries() {
        return datastore.createQuery(PhoneBookEntry.class).asList();
    }

    /**
     * Drops the database.
     */
    public void drop() {
        datastore.getDB().dropDatabase();
    }

    private @Nullable PhoneBookEntry getEntry(@NotNull String name, @NotNull String phone) {
        List<PhoneBookEntry> list = datastore.createQuery(PhoneBookEntry.class)
                                                            .field("name").equal(name)
                                                            .field("phone").equal(phone)
                                                            .asList();
        if (list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    public enum Respond { OK, NO_SUCH_ENTRY, ENTRY_ALREADY_EXISTS };
}
