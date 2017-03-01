package ru.spbau.bachelor2015.veselov.hw10;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DataBaseTest {
    private DataBase database = new DataBase("DataBase-test");

    private final String name1 = "name1";

    private final String name2 = "name2";

    private final String name3 = "name3";

    private final String phone1 = "phone1";

    private final String phone2 = "phone2";

    private final String phone3 = "phone3";

    private final List<QuasiPhoneBookEntry> allEntries = Arrays.asList(
            new QuasiPhoneBookEntry(name1, phone1),
            new QuasiPhoneBookEntry(name1, phone2),
            new QuasiPhoneBookEntry(name2, phone1),
            new QuasiPhoneBookEntry(name2, phone2));

    private final List<QuasiPhoneBookEntry> name1Entries = Arrays.asList(
            new QuasiPhoneBookEntry(name1, phone1),
            new QuasiPhoneBookEntry(name1, phone2));

    private final List<QuasiPhoneBookEntry> name2Entries = Arrays.asList(
            new QuasiPhoneBookEntry(name2, phone1),
            new QuasiPhoneBookEntry(name2, phone2));

    private final List<QuasiPhoneBookEntry> phone1Entries = Arrays.asList(
            new QuasiPhoneBookEntry(name1, phone1),
            new QuasiPhoneBookEntry(name2, phone1));

    private final List<QuasiPhoneBookEntry> phone2Entries = Arrays.asList(
            new QuasiPhoneBookEntry(name1, phone2),
            new QuasiPhoneBookEntry(name2, phone2));

    private Comparator<PhoneBookEntry> phoneBookEntryComparator =
            (e1, e2) -> {
                int nameComparisonResult = e1.getName().compareTo(e2.getName());
                if (nameComparisonResult != 0) {
                    return nameComparisonResult;
                }

                return e1.getPhone().compareTo(e2.getPhone());
            };

    @After
    public void tearDown() throws Exception {
        database.drop();
    }

    @Test
    public void testAddition() throws Exception {
        fillDataBase();

        assertEquals(DataBase.Respond.ENTRY_ALREADY_EXISTS, database.addNewEntry(name1, phone1));
        assertEquals(DataBase.Respond.ENTRY_ALREADY_EXISTS, database.addNewEntry(name2, phone2));
        assertEquals(DataBase.Respond.ENTRY_ALREADY_EXISTS, database.addNewEntry(name1, phone2));
        assertEquals(DataBase.Respond.ENTRY_ALREADY_EXISTS, database.addNewEntry(name2, phone1));
    }

    @Test
    public void testGetAllEntries() throws Exception {
        assertQueryResult(database.getAllEntries(), Collections.emptyList());

        fillDataBase();

        assertQueryResult(database.getAllEntries(), allEntries);
    }

    @Test
    public void testFindByName() throws Exception {
        fillDataBase();

        assertQueryResult(database.findByName(name1), name1Entries);
        assertQueryResult(database.findByName(name2), name2Entries);
        assertQueryResult(database.findByName(name3), Collections.emptyList());
    }

    @Test
    public void testFindByPhone() throws Exception {
        fillDataBase();

        assertQueryResult(database.findByPhone(phone1), phone1Entries);
        assertQueryResult(database.findByPhone(phone2), phone2Entries);
        assertQueryResult(database.findByPhone(phone3), Collections.emptyList());
    }

    @Test
    public void testDeletion() throws Exception {
        fillDataBase();

        assertEquals(DataBase.Respond.NO_SUCH_ENTRY, database.deleteEntry(name3, phone1));
        assertEquals(DataBase.Respond.NO_SUCH_ENTRY, database.deleteEntry(name1, phone3));
        assertEquals(DataBase.Respond.NO_SUCH_ENTRY, database.deleteEntry(name3, phone3));

        assertEquals(DataBase.Respond.OK, database.deleteEntry(name1, phone1));
        assertEquals(DataBase.Respond.OK, database.deleteEntry(name1, phone2));
        assertQueryResult(database.getAllEntries(), name2Entries);

        assertEquals(DataBase.Respond.OK, database.addNewEntry(name1, phone1));
        assertEquals(DataBase.Respond.OK, database.addNewEntry(name1, phone2));
        assertQueryResult(database.getAllEntries(), allEntries);
    }

    @Test
    public void testChangeName() throws Exception {
        fillDataBase();

        assertEquals(DataBase.Respond.NO_SUCH_ENTRY, database.changeName(name3, phone1, name1));
        assertEquals(DataBase.Respond.NO_SUCH_ENTRY, database.changeName(name1, phone3, name1));
        assertEquals(DataBase.Respond.NO_SUCH_ENTRY, database.changeName(name3, phone3, name1));

        assertEquals(DataBase.Respond.ENTRY_ALREADY_EXISTS, database.changeName(name1, phone1, name2));
        assertEquals(DataBase.Respond.ENTRY_ALREADY_EXISTS, database.changeName(name2, phone2, name1));

        assertEquals(DataBase.Respond.OK, database.changeName(name1, phone1, name3));
        assertEquals(DataBase.Respond.OK, database.changeName(name1, phone2, name3));

        assertQueryResult(database.getAllEntries(), Arrays.asList(
                                                        new QuasiPhoneBookEntry(name2, phone1),
                                                        new QuasiPhoneBookEntry(name2, phone2),
                                                        new QuasiPhoneBookEntry(name3, phone1),
                                                        new QuasiPhoneBookEntry(name3, phone2)));
    }

    @Test
    public void testChangePhone() throws Exception {
        fillDataBase();

        assertEquals(DataBase.Respond.NO_SUCH_ENTRY, database.changePhone(name1, phone3, phone1));
        assertEquals(DataBase.Respond.NO_SUCH_ENTRY, database.changePhone(name3, phone1, phone3));
        assertEquals(DataBase.Respond.NO_SUCH_ENTRY, database.changePhone(name3, phone3, phone1));

        assertEquals(DataBase.Respond.ENTRY_ALREADY_EXISTS, database.changePhone(name1, phone1, phone2));
        assertEquals(DataBase.Respond.ENTRY_ALREADY_EXISTS, database.changePhone(name2, phone2, phone1));

        assertEquals(DataBase.Respond.OK, database.changePhone(name1, phone1, phone3));
        assertEquals(DataBase.Respond.OK, database.changePhone(name2, phone1, phone3));

        assertQueryResult(database.getAllEntries(), Arrays.asList(
                                                        new QuasiPhoneBookEntry(name1, phone2),
                                                        new QuasiPhoneBookEntry(name1, phone3),
                                                        new QuasiPhoneBookEntry(name2, phone2),
                                                        new QuasiPhoneBookEntry(name2, phone3)));
    }

    private void assertQueryResult(@NotNull List<PhoneBookEntry> result, @NotNull List<QuasiPhoneBookEntry> expected) {
        Collections.sort(result, phoneBookEntryComparator);
        assertEquals(expected, result);
    }

    private void fillDataBase() {
        for (QuasiPhoneBookEntry entry : allEntries) {
            assertEquals(DataBase.Respond.OK, database.addNewEntry(entry.getName(), entry.getPhone()));
        }
    }

    private static class QuasiPhoneBookEntry {
        private final String name;

        private final String phone;

        public QuasiPhoneBookEntry(@NotNull String name, @NotNull String phone) {
            this.name = name;
            this.phone = phone;
        }

        public @NotNull String getName() {
            return name;
        }

        public @NotNull String getPhone() {
            return phone;
        }

        @Override
        public boolean equals(Object object) {
            if (!(object instanceof PhoneBookEntry)) {
                return false;
            }

            PhoneBookEntry other = (PhoneBookEntry) object;
            return name.equals(other.getName()) && phone.equals(other.getPhone());
        }
    }
}
