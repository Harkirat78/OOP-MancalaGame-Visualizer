package mancala;

import java.io.*;

/**
 * Saver class provides methods to save and load Serializable objects.
 */
public class Saver {
    
    /**
     * Saves a Serializable object to a file.
     *
     * @param toSave The object to save.
     * @param filename The name of the file to save the object to.
     * @throws IOException If an I/O error occurs during saving.
     */
    public static void saveObject(Serializable toSave, String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("assets/" + filename))) {
            out.writeObject(toSave);
        }
    }

    /**
     * Loads a Serializable object from a file.
     *
     * @param filename The name of the file to load the object from.
     * @return The loaded Serializable object.
     * @throws IOException If an I/O error occurs during loading.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    public static Serializable loadObject(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("assets/" + filename))) {
            return (Serializable) in.readObject();
        }
    }
}
