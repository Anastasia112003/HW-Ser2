import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        GameProgress game1 = new GameProgress(31, 2, 7, 89);
        GameProgress game2 = new GameProgress(7, 43, 5, 90);
        GameProgress game3 = new GameProgress(56, 2, 13, 7);
        saveGames("C://Games//saveGames//game1.dat", game1);
        saveGames("C://Games//saveGames//game2.dat", game2);
        saveGames("C://Games//saveGames//game3.dat", game3);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("C://Games//saveGames//game1.dat");
        arrayList.add("C://Games//saveGames//game2.dat");
        arrayList.add("C://Games//saveGames//game3.dat");
        zipFiles("C://Games//saveGames//zip.zip", arrayList);
        File game1Dat = new File("C://Games//saveGames//game1.dat");
        File game2Dat = new File("C://Games//saveGames//game2.dat");
        File game3Dat = new File("C://Games//saveGames//game3.dat");
        if (game1Dat.delete()) System.out.println("Файл \"game1.dat\" удален");
        if (game2Dat.delete()) System.out.println("Файл \"game2.dat\" удален");
        if (game3Dat.delete()) System.out.println("Файл \"game3.dat\" удален");
    }

    public static void saveGames(String path, GameProgress game) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(game);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zip.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zip.write(fis.read());
                    }
                    zip.closeEntry();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
