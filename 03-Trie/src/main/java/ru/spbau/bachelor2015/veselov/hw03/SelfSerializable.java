package ru.spbau.bachelor2015.veselov.hw03;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Interface of a class which can serialize and deserialize itself.
 */
public interface SelfSerializable {
    void serialize(OutputStream out) throws IOException;
    void deserialize(InputStream in) throws IOException, ClassNotFoundException;
}
