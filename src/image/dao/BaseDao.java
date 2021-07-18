/**
 * BaseDao.java, 2021/07/16
 * 
 */
package image.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import image.properties.DatabaseProperties;
import image.utils.Constant;

/**
 * @Description 
 * Class chứa các phương thức chung để thao tác với database
 * 
 * @Author AI_TEAM
 * @Version 1.0
 */
public class BaseDao {

 // ----------------- Connection Parameter -----------------
    protected Connection conn;
    
    /**
     * Khởi tạo giá trị ban đầu cho các thuộc tính
     */
    public BaseDao() {
        this.conn     = null;
    }

    /**
     * Mở kết nối tới database
     * 
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void openConnection() throws SQLException, ClassNotFoundException {
        // Tạo đường URL kết nối tới database
        StringBuilder connectionUrl = new StringBuilder();
        // ----------------- Database Parameter -----------------
        // Đọc các tham số từ file [Database.properties]
        String urlMysql     = DatabaseProperties.getValueByKey(Constant.URL_MYSQL);
        String hostName     = DatabaseProperties.getValueByKey(Constant.HOST_NAME);
        String port         = DatabaseProperties.getValueByKey(Constant.PORT);
        String databaseName = DatabaseProperties.getValueByKey(Constant.DATABASE_NAME);
        String queryEncode  = DatabaseProperties.getValueByKey(Constant.QUERY_ENCODE);
        String userName     = DatabaseProperties.getValueByKey(Constant.USER_NAME);
        String password     = DatabaseProperties.getValueByKey(Constant.PASSWORD);
        // Bổ sung các trường thông tin vào URL
        connectionUrl.append(urlMysql)
                     .append(hostName)
                     .append(":")
                     .append(port)
                     .append("/")
                     .append(databaseName)
                     .append("?")
                     .append(queryEncode);
        // "jdbc:mysql://localhost:3306/tbl_image?useUnicode=true&characterEncoding=utf-8"
        try {
            // Lấy về connection kết nối với database
            conn = getConnection(connectionUrl.toString(), userName, password);
        // Bắt và xử lý các lỗi xảy ra
        } catch (SQLException | ClassNotFoundException e) {
            // Ném lỗi về cho controller
            throw e;
        }
    }

    /**
     * Đóng kết nối tới database
     * 
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        try {
            // Nếu connection hiện tại khác null và chưa được đóng
            if (conn != null && !conn.isClosed()) {
                // Đóng connection
                conn.close();
            }
        // Bắt và xử lý các exception thu được
        } catch (SQLException e) {
            // Ném lỗi về cho controller
            throw e;
        }
    }
    
    public List<String> getListColumn() throws ClassNotFoundException, SQLException {
        // Tạo một danh sách lưu giá trị trả về
        List<String> result = new ArrayList<String>();
        try {
            // Mở kết nối tới DB
            openConnection();
            // Tạo câu truy vấn
            StringBuilder query = new StringBuilder();
            query.append(" SELECT DISTINCT `COLUMN_NAME` as column_name ")
                 .append(" FROM `INFORMATION_SCHEMA`.`COLUMNS` ")
                 .append(" WHERE TABLE_SCHEMA = ? ;");
            // Sử dụng PreparedStatement để lưu câu truy vấn gửi tới DB
            PreparedStatement preState = conn.prepareStatement(query.toString());
            // Tạo biến chỉ số tự tăng
            int index = 1;
            // Lấy về giá trị tên database từ file .properties
            String database = DatabaseProperties.getValueByKey(Constant.DATABASE_NAME);
            // Lưu tên database vào câu truy vấn
            preState.setString(index, database);
            // Thực thi câu truy vấn
            ResultSet rs = preState.executeQuery();
            // Nạp tên colum vào danh sách [result]
            while (rs.next()) {
                // Lấy tên của cột
                String columnName = rs.getString(Constant.DB_COLUMN_NAME);
                // Thêm tên cột vào danh sách
                result.add(columnName);
            }
        // Bắt và xử lý các exception thu được
        } catch (SQLException e) {
            // Ném lỗi về cho controller
            throw e;
        } finally {
            // Đóng kết nối
            closeConnection();
        }
        // Trả về danh sách tên cột
        return result;
    }
    
    // ----------------------- Private Layer ----------------------- 
    
    /**
     * Lấy về Connection để kết nối với database
     * 
     * @param dbURL    - Đường dẫn tới database
     * @param userName - Tên đăng nhập
     * @param password - Mật khẩu đăng nhập
     * @return {Connection} - Đối tượng mở kết nối tới database
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private Connection getConnection(String dbURL, String userName, String password)
            throws SQLException, ClassNotFoundException {
        try {
            // Lấy tên driver từ trong file [Database.properties]
            String driverName   = DatabaseProperties.getValueByKey(Constant.DRIVER_NAME);
            // Đăng ký driver cho JDBC
            Class.forName(driverName);
            // Lấy về connection để kết nối với database
            conn = DriverManager.getConnection(dbURL, userName, password);
        // Bắt và xử lý các exception thu được
        } catch (ClassNotFoundException | SQLException e) {
            // Ném lỗi về cho controller
            throw e;
        }
        // Trả connection về cho phương thức
        return conn;
    }
}
