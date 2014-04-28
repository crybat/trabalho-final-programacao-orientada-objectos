package pt.iul.dcti.poo.persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Deserializer<T> {

    @SuppressWarnings("unchecked")
    public T deserialize(String fileName) throws IOException,
            ClassNotFoundException {

        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);

        T obj = (T) ois.readObject();
        ois.close();
        return obj;
    }

}
