import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class blib {

    private static String location = "output/";

    public static byte[] createBlob(String filename) throws empException {
        File fFile = new File(filename);
        byte bFile[] = new byte[(int) fFile.length()];
        try {
            FileInputStream fFIS = new FileInputStream(fFile);
            fFIS.read(bFile);
            fFIS.close();
        } catch (FileNotFoundException e) {
            throw new empException("ERROR. File not found");
        } catch (IOException errorIO) {
            throw new empException("ERROR. Creating blob");
        }

        return bFile;
    }

    public static void blobToFile(byte[] blob, String extension, String id) throws empException
    {
        try {
            if(extension.isEmpty()) return ;
            FileOutputStream stream = new FileOutputStream("output/" + id + "." + extension);
            stream.write(blob);
            stream.close();
        } catch (IOException e) {
            throw new empException("ERROR. Creating file from blob");
        }
    }

    public static Icon fileToIcon(String location) throws empException {
        Icon icon = null;
        try {
            Image img = ImageIO.read(new File(location));
            icon = new ImageIcon(img);
        } catch (IOException e) {
            throw new empException("ERROR. Converting file to icon");
        } finally { return icon; }
    }

    public static String getLocation() { return location; }

}
