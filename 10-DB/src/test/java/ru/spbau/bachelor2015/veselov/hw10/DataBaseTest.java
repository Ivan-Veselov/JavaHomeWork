package ru.spbau.bachelor2015.veselov.hw10;

import org.jetbrains.annotations.NotNull;
import org.junit.After;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

        assertThat(database.addNewEntry(name1, phone1), is(equalTo(DataBase.Respond.ENTRY_ALREADY_EXISTS)));
        assertThat(database.addNewEntry(name2, phone2), is(equalTo(DataBase.Respond.ENTRY_ALREADY_EXISTS)));
        assertThat(database.addNewEntry(name1, phone2), is(equalTo(DataBase.Respond.ENTRY_ALREADY_EXISTS)));
        assertThat(database.addNewEntry(name2, phone1), is(equalTo(DataBase.Respond.ENTRY_ALREADY_EXISTS)));
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

        assertThat(database.deleteEntry(name3, phone1), is(equalTo(DataBase.Respond.NO_SUCH_ENTRY)));
        assertThat(database.deleteEntry(name1, phone3), is(equalTo(DataBase.Respond.NO_SUCH_ENTRY)));
        assertThat(database.deleteEntry(name3, phone3), is(equalTo(DataBase.Respond.NO_SUCH_ENTRY)));

        assertThat(database.deleteEntry(name1, phone1), is(equalTo(DataBase.Respond.OK)));
        assertThat(database.deleteEntry(name1, phone2), is(equalTo(DataBase.Respond.OK)));
        assertQueryResult(database.getAllEntries(), name1Entries);

        assertThat(database.addNewEntry(name1, phone1), is(equalTo(DataBase.Respond.OK)));
        assertThat(database.addNewEntry(name1, phone2), is(equalTo(DataBase.Respond.OK)));
        assertQueryResult(database.getAllEntries(), allEntries);
    }

    @Test
    public void testChangeName() throws Exception {
        fillDataBase();

        assertThat(database.changeName(name3, phone1, name1), is(equalTo(DataBase.Respond.NO_SUCH_ENTRY)));
        assertThat(database.changeName(name1, phone3, name1), is(equalTo(DataBase.Respond.NO_SUCH_ENTRY)));
        assertThat(database.changeName(name3, phone3, name1), is(equalTo(DataBase.Respond.NO_SUCH_ENTRY)));

        assertThat(database.changeName(name1, phone1, name2), is(equalTo(DataBase.Respond.ENTRY_ALREADY_EXISTS)));
        assertThat(database.changeName(name2, phone2, name1), is(equalTo(DataBase.Respond.ENTRY_ALREADY_EXISTS)));

        assertThat(database.changeName(name1, phone1, name3), is(equalTo(DataBase.Respond.OK)));
        assertThat(database.changeName(name1, phone2, name3), is(equalTo(DataBase.Respond.OK)));

        assertQueryResult(database.getAllEntries(), Arrays.asList(
                                                        new QuasiPhoneBookEntry(name2, phone1),
                                                        new QuasiPhoneBookEntry(name2, phone2),
                                                        new QuasiPhoneBookEntry(name3, phone1),
                                                        new QuasiPhoneBookEntry(name1, phone2)));
    }

    @Test
    public void testChangePhone() throws Exception {
        fillDataBase();

        assertThat(database.changePhone(name1, phone3, phone1), is(equalTo(DataBase.Respond.NO_SUCH_ENTRY)));
        assertThat(database.changePhone(name3, phone1, phone3), is(equalTo(DataBase.Respond.NO_SUCH_ENTRY)));
        assertThat(database.changePhone(name3, phone3, phone1), is(equalTo(DataBase.Respond.NO_SUCH_ENTRY)));

        assertThat(database.changePhone(name1, phone1, phone2), is(equalTo(DataBase.Respond.ENTRY_ALREADY_EXISTS)));
        assertThat(database.changePhone(name2, phone2, phone1), is(equalTo(DataBase.Respond.ENTRY_ALREADY_EXISTS)));

        assertThat(database.changePhone(name1, phone1, phone3), is(equalTo(DataBase.Respond.OK)));
        assertThat(database.changePhone(name2, phone1, phone3), is(equalTo(DataBase.Respond.OK)));

        assertQueryResult(database.getAllEntries(), Arrays.asList(
                                                        new QuasiPhoneBookEntry(name1, phone2),
                                                        new QuasiPhoneBookEntry(name1, phone3),
                                                        new QuasiPhoneBookEntry(name2, phone2),
                                                        new QuasiPhoneBookEntry(name2, phone3)));
    }

    private void assertQueryResult(@NotNull List<PhoneBookEntry> result, @NotNull List<QuasiPhoneBookEntry> expected) {
        Collections.sort(result, phoneBookEntryComparator);
        assertThat(result, is(equalTo(expected)));
    }

    private void fillDataBase() {
        for (QuasiPhoneBookEntry entry : allEntries) {
            assertThat(database.addNewEntry(entry.getName(), entry.getPhone()), is(equalTo(DataBase.Respond.OK)));
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
