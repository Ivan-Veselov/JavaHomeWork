package ru.spbau.bachelor2015.veselov.hw03;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Interface of a class which can serialize and deserialize itself.
 */
public interface SelfSerializable {
    /**
     * Serializes object by writing data into a given stream.
     *
     * @param out stream where data will be written
     * @throws IOException any exception thrown by the underlying OutputStream.
     */
    void serialize(OutputStream out) throws IOException;

    /**
     * Deserializes object by reading data from a given stream.
     *
     * @param in stream to read data from
     * @throws IOException any of the usual Input/Output related exceptions.
     * @throws ClassNotFoundException class of a serialized object cannot be found.
     */
    void deserialize(InputStream in) throws IOException, ClassNotFoundException;
}
