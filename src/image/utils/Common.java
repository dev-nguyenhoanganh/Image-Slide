/**
 * Common.java, 2021/07/15
 * 
 */
package image.utils;

import java.util.ArrayList;
import java.util.List;

import image.properties.ConfigProperties;

/**
 * @Description
 * Định nghĩa các phương thức dùng chung trong toàn bộ chương trình
 *
 * @Author AI_TEAM
 * @Version 1.0
 */
public class Common {
    
    /**
     * Tạo chuỗi các paging dựa vào dữ liệu có trong DB
     * 
     * @param totalImage - Tổng số ảnh có trong DB
     * @param limit      - Giới hạn số hình ảnh được hiển thị trong bảng
     * @param currentPage - Trang hiện tại
     * @return Danh sách các trang cần hiển thị ở chuỗi paging theo 
     *         trang hiện tại
     */
    public static List<Integer> getListPaging(int totalImage, int limit, int currentPage) {
        // Tạo một listPaging để lưu kết quả trả về
        List<Integer> listPaging = new ArrayList<Integer>();
        // Tính ra tổng số page cần để hiển thị
        int totalPage = getTotalPage(totalImage, limit);
        // Số page tối đa được hiển thị trên 1 paging
        int limitPage = getLimitPage();
        // Tính ra page đầu tiên hiển thị trong chuỗi paging
        int startPage = limitPage * ((currentPage - 1) / limitPage) + 1;
        // Tính ra page cuối cùng hiển thị trong chuỗi paging
        int endPage = startPage + limitPage;
        // Duyệt từ page đầu tới page cuối
        for (int i = startPage; i < endPage; i++) {
            // Nếu page hiện tại nhỏ hơn totalPage
            if (i <= totalPage) {
                // Thêm page hiện tại vào danh sách
                listPaging.add(i);
            // Trường hợp không nhỏ hơn totalPage
            } else {
                // Thoát khỏi vòng lặp
                break;
            }
        }
        // Trả về chuỗi các paging
        return listPaging;
    }
    
    /**
     * Phương thức ép kiểu [chuỗi] về [số]
     * 
     * @param number - Chuỗi ký tự gồm toàn các số
     * @param defaultValue - Giá trị default khi chuỗi đầu vào không phải là số
     * @return Số sau khi ép kiểu <br>
     *         Nếu chuỗi nhập vào không phải số sẽ trả về <b>defaultValue</b>
     */
    public static int convertStringToInt(String number, int defaultValue) {
        // Tạo 1 biến để lưu giá trị trả về
        int result;
        // Nếu chuỗi truyền vào là số
        try {
            // Ép kiểu và nạp giá trị và biến [result]
            result = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            // Nếu xảy ra lỗi thì gán defaultValue cho result
            result = defaultValue;
        }
        // Trả về kết quả
        return result;
    }
    
    /**
     * Tính tổng số trang tương ứng với các user trong DB
     * 
     * @param totalImage  - Tổng số ảnh trong DB
     * @param limitRecord - Giới hạn số ảnh được hiển thị trên 1 trang
     * @return Tổng số trang cần để hiển thị hết danh sách user
     */
    public static int getTotalPage(int totalImage, int limitRecord) {
        // Tổng số page cần để hiển thị hết danh sách các user
        int totalPage = 0;
        // Chia lấy phần nguyên của TotalUser cho limit
        totalPage = totalImage / limitRecord;
        // Chia lấy phần dư lớn hơn 1 record
        if (totalImage % limitRecord >= 1) {
            // Tăng giá trị của totalPage lên 1
            totalPage++;
        }
        // Trả về kết quả totalPage tính được
        return totalPage;
    }
    
    /**
     * Lấy giá trị giới hạn record từ file .properties
     * 
     * @return [int] - số record
     */
    public static int getLimitRecord() {
        // Lấy dữ liệu từ file config.properties
        return convertStringToInt(ConfigProperties.getValueByKey(Constant.LIMIT_RECORD), Constant.DEFAULT_PAGE);
    }
    
    /**
     * Lấy giá trị giới hạn số page hiển thị trong paging từ file .properties
     * 
     * @return [int] - số page hiển thị 
     */
    public static int getLimitPage() {
        // Lấy dữ liệu từ file config.properties
        return convertStringToInt(ConfigProperties.getValueByKey(Constant.LIMIT_PAGE), Constant.DEFAULT_PAGE);
    }
    
    /**
     * Lấy vị trí data cần lấy
     * 
     * @param currentPage - Trang hiện tại
     * @param limitRecord - Giới hạn số ảnh được hiển thị trên 1 trang
     * @return vị trí cần lấy
     */
    public static int getOffset(int currentPage, int limitRecord) {
        // Xử lý logic để tính ra vị trí cần lấy dựa vào currentPage và limit
        return limitRecord * (currentPage - 1);
    }
    
    /**
     * So sánh 2 chỗi ký tự với nhau
     * 
     * @param str1 - Chuỗi thứ nhất
     * @param str2 - Chuỗi thứ hai
     * @return true  - 2 chuỗi giống nhau <br>
     *         false - 2 chuỗi khác nhau
     */
    public static boolean compareString(String str1, String str2) {
        // Nếu [str1] khác null và bằng với [str2]
        if (str1 != null && str1.equals(str2)) {
            // Trả về true
            return true;
        }
        // Trả về false
        return false;
    }
    
    /**
     * Phương thức kiểm tra chuỗi có rỗng hay không
     * 
     * @param str - chuỗi cần kiểm tra
     * @return true : Chuỗi rỗng hoặc null <br>
     *         false: Chuỗi không rỗng hoặc null
     */
    public static boolean checkEmpty(String str) {
        // Khởi tạo tham số lưu kết quả trả về
        boolean isEmpty = false;
        // Nếu chuỗi rỗng hoặc null
        if (str == null || str.isEmpty()) {
            // Thay đổi kết quả trả về thành true
            isEmpty = true;
        }
        // Trả về kết quả kiểm tra được
        return isEmpty;
    }
}
