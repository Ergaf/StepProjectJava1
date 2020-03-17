package ergaf.step.io;

import ergaf.step.Logger;
import ergaf.step.flight.Flight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWorker {

    public static void serialize(String fileName, List<Flight> flights) {

        try {
            FileOutputStream fos = new FileOutputStream(getBasePath() + fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(flights);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
//            Logger.error("serialize exception");
            ioe.printStackTrace();
        }
    }

    public static ArrayList<Flight> deserialize(String fileName) {

        ArrayList<Flight> flights = new ArrayList<>();

        File file = new File(getBasePath() + fileName);
        if (!file.exists()) {
            return flights;
        }

        try {
            FileInputStream fis = new FileInputStream(getBasePath() + fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            flights = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
//            Logger.error("class or file not found exception");
            System.out.println("Class or file not found");
            e.printStackTrace();
            return flights;
        } catch (IOException ioe) {
//            Logger.error("io exception");
            ioe.printStackTrace();
            return flights;
        }

        return flights;
    }

    private static String getBasePath() {
        return System.getProperty("user.dir") + "/storage/";
    }
}
