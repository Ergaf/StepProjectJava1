package ergaf.step.utils.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWorker {

    public static <T> void serialize(String fileName, List<T> objects) {

        try {
            FileOutputStream fos = new FileOutputStream(getBasePath() + fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objects);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            Logger.error("serialize exception");
            ioe.printStackTrace();
        }
    }

    public static <T> ArrayList<T> deserialize(String fileName) {

        ArrayList<T> objects = new ArrayList<T>();

        File file = new File(getBasePath() + fileName);
        if (!file.exists()) {
            return objects;
        }

        try {
            FileInputStream fis = new FileInputStream(getBasePath() + fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            objects = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            Logger.error("class or file not found exception");
            System.out.println("Class or file not found");
            e.printStackTrace();
            return objects;
        } catch (IOException ioe) {
            Logger.error("io exception");
            ioe.printStackTrace();
            return objects;
        }

        return objects;
    }

    public static boolean unlinkData(String fileName) {
        File f= new File(getBasePath() + fileName);
        return f.delete();
    }

    private static String getBasePath() {
        return System.getProperty("user.dir") + "/storage/";
    }
}
