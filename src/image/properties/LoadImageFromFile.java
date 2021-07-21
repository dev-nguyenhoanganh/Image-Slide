/**
 * LoadImageFromFile.java, 2021/07/17
 * 
 */
package image.properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import image.dao.TblImageDao;
import image.entities.ImageEntity;
import image.utils.Constant;

/**
 * @Description 
 * 
 * @Author AI_TEAM
 * @Version 1.0
 */
public class LoadImageFromFile {
    private static ArrayList<ImageEntity> listImage = new ArrayList<ImageEntity>();
    
    public static boolean updateImage(ImageEntity image) throws IOException {
        boolean updateSuccess = false;
        try {
            int index = listImage.indexOf(image);
            if (index != -1) {
                listImage.set(index, image);
                updateSuccess = convertListToFile();
            }
        } catch (IOException e) {
            throw e;
        }
        
        return updateSuccess;
    }
    
//    /**
//     * 
//     *
//     */
//    public static List<String> loadImageFromDir() {
//        List<String> listImageDir = new ArrayList<String>();
//        File f = new File(Constant.IMG_DIR_PATH);
//        String[] pathnames = f.list();
//        listImageDir = Arrays.asList(pathnames);
//        return listImageDir;
//    }
    
    /**
     * 
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void loadImageEntityFromFile() throws IOException, SQLException, ClassNotFoundException {
        try {
            listImage = convertFileToList();
            
            TblImageDao imageDao = new TblImageDao();
            imageDao.insertImageToDatabase(listImage);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            throw e;
        }
    }
    
    /**
     * 
     * @return
     * @throws IOException
     */
    public static ArrayList<ImageEntity> convertFileToList() throws IOException {
        ArrayList<ImageEntity> list = new ArrayList<ImageEntity>();
        try {
            String data = readFile(Constant.LIST_IMG_PATH);
            String[] dataLine = data.split(Constant.LINE_DELIMITER);
            for (int i = 0; i < dataLine.length; i++) {
                int index = dataLine[i].indexOf(Constant.IMAGE_DELIMITER);
            	if (index != -1) {
            		ImageEntity image = new ImageEntity();
            		image.setImageName(dataLine[i].substring(0, index));
                    image.setAlternateText(dataLine[i].substring(index + Constant.IMAGE_DELIMITER.length()));
                    list.add(image);
                }
            }
        } catch (IOException e) {
            throw e;
        }
        return list;
    }

    /**
     * @param path
     * @return
     * @throws IOException 
     */
    private static String readFile(String path) throws IOException {
        String data = "";
        try {
            File file = new File(path);
            if (!file.exists()) {
                return null;
            }
            
            Path p = Paths.get(path);
            List<String> list = Files.readAllLines(p, StandardCharsets.UTF_8);
            for (String str : list) {
                data += str + "\n";
            }
        } catch (IOException e) {
            throw e;
        }
        return data;
    }

    /**
     * 
     * @return
     * @throws IOException 
     */
    public static boolean convertListToFile() throws IOException {
        StringBuilder data = new StringBuilder();
        for (ImageEntity image : listImage) {
            data.append(image.toString());
        }
        return writeToFile(data.toString(), Constant.LIST_IMG_PATH);
    }
    
    /**
     * 
     * @param input
     * @param path
     * @return
     * @throws IOException 
     */
    private static boolean writeToFile(String input, String path) throws IOException {
        boolean writeSuccess = false;;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream fO = new FileOutputStream(file);
            byte[] byteArr = input.getBytes("UTF-8");
            fO.write(byteArr);
            fO.close();
            writeSuccess = true;
        } catch (IOException e) {
            throw e;
        } 
        return writeSuccess;
    }
}
