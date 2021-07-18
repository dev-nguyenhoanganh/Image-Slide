/**
 * MessageProperties.java, 2021/07/16
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
 * Đọc và lấy các thông báo từ file
 * 
 * @Author AI_TEAM
 * @Version 1.0
 */
public class MessageProperties {

    // lưu các cặp <key, value> trong file properties
    private static Map<String, String> mapMsgProperties = new HashMap<String, String>();
    static {
        try {
            // tạo đối tượng kiểu Properties
            Properties properties = new Properties();
            // đọc file properties
            properties.load(new InputStreamReader(DatabaseProperties.class
                .getClassLoader()
                .getResourceAsStream(Constant.MSG_PATH), "UTF-8"));

            // lưu các giá trị key trong file properties
            Enumeration<?> enumeration = properties.propertyNames();
            // true nếu vẫn còn phần tử, false nếu tất cả phần tử đã được lấy ra
            while (enumeration.hasMoreElements()) {
                // key = key tiếp theo
                String key = (String) enumeration.nextElement();
                // lấy value tương ứng với key
                String value = properties.getProperty(key);
                // thêm vào list
                mapMsgProperties.put(key, value);
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
        String value = mapMsgProperties.get(key);
        // Trả về giá trị key tương ứng
        return value;
    }
}
