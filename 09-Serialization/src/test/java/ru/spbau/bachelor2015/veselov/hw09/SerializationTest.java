package ru.spbau.bachelor2015.veselov.hw09;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;

public class SerializationTest {
    @Test
    public void test() throws Exception {
        String string = "This is\n\ta String\n\robject.";
        SimpleClass c = new SimpleClass(true,
                                        Character.MAX_VALUE,
                                        Byte.MAX_VALUE,
                                        Short.MAX_VALUE,
                                        Integer.MAX_VALUE,
                                        Long.MAX_VALUE,
                                        Float.MAX_VALUE,
                                        Double.MIN_VALUE,
                                        string);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Serialization.serialize(c, outputStream);

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        c = Serialization.deserialize(inputStream, SimpleClass.class);

        assertEquals(true, c.getBooleanField());
        assertEquals(Character.MAX_VALUE, c.getCharField());
        assertEquals(Byte.MAX_VALUE, c.getByteField());
        assertEquals(Short.MAX_VALUE, c.getShortField());
        assertEquals(Integer.MAX_VALUE, c.getIntField());
        assertEquals(Long.MAX_VALUE, c.getLongField());
        assertEquals(Float.MAX_VALUE, c.getFloatField(), 0);
        assertEquals(Double.MIN_VALUE, c.getDoubleField(), 0);
        assertEquals(string, c.getStringField());
    }

    private static class SimpleClass {
        private boolean booleanField;
        private char charField;
        private byte byteField;
        private short shortField;
        private int intField;
        private long longField;
        private float floatField;
        private double doubleField;
        private String stringField;

        public SimpleClass() {
        }

        public SimpleClass(boolean booleanField,
                           char charField,
                           byte byteField,
                           short shortField,
                           int intField,
                           long longField,
                           float floatField,
                           double doubleField,
                           String stringField) {
            this.booleanField = booleanField;
            this.charField = charField;
            this.byteField = byteField;
            this.shortField = shortField;
            this.intField = intField;
            this.longField = longField;
            this.floatField = floatField;
            this.doubleField = doubleField;
            this.stringField = stringField;
        }

        public boolean getBooleanField() {
            return booleanField;
        }

        public char getCharField() {
            return charField;
        }

        public byte getByteField() {
            return byteField;
        }

        public short getShortField() {
            return shortField;
        }

        public int getIntField() {
            return intField;
        }

        public long getLongField() {
            return longField;
        }

        public float getFloatField() {
            return floatField;
        }

        public double getDoubleField() {
            return doubleField;
        }

        public String getStringField() {
            return stringField;
        }
    }
}