/**
 * DatabaseProperties.java, 2021/16/07
 * 
 */
package image.properties;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import image.utils.Constant;

/**
 * @Description
 * Class đọc các thông tin thiết định kết nối tới database
 * 
 * @Author  : AI_TEAM
 * @Version : 1.0
 */
public class DatabaseProperties {
    // lưu các cặp <key, value> trong file properties
    private static Map<String, String> mapDBProperties = new HashMap<String, String>();
    static {
        try {
            // tạo đối tượng kiểu Properties
            Properties properties = new Properties(); 
            // đọc file properties
            properties.load(new InputStreamReader(DatabaseProperties.class
                .getClassLoader()
                .getResourceAsStream(Constant.DATABASE_PATH), "UTF-8"));
            // lưu các giá trị key trong file properties					
            Enumeration<?> enumeration = properties.propertyNames(); 
            // true nếu vẫn còn phần tử, false nếu tất cả phần tử đã được lấy ra
            while (enumeration.hasMoreElements()) { 
                // key = key tiếp theo
                String key = (String) enumeration.nextElement(); 
                // lấy value tương ứng với key
                String value = properties.getProperty(key); 
                // thêm vào list
                mapDBProperties.put(key, value); 
            }
            // Reset lại đối tượng properties
            properties.clear();
        // Bắt và ghi log lỗi thu được
        } catch (IOException | SecurityException | NullPointerException e) {
            // Ghi log
            System.out.println("DatabaseProperties.java-static-" + e.getMessage());
        }
    }

    /**
     * lấy value tương ứng với key trong file properties
     * 
     * @param key tên key trong file properties
     * @return trả về value tương ứng với key
     */
    public static String getValueByKey(String key) {
        // Lấy ra giá trị ứng với key truyền vào
        String value = mapDBProperties.get(key);
        // Trả về giá trị key tương ứng
        return value;
    }

}
