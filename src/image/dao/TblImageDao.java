/**
 * TblImageDao.java, 2021/07/16
 * 
 */
package image.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import image.entities.ImageEntity;
import image.utils.Constant;

/**
 * @Description 
 * Class chứa các phương thức thao tác với database và lấy ra thông tin
 * của bảng [tbl_image]
 * 
 * @Author AI_TEAM
 * @Version 1.0
 */
public class TblImageDao extends BaseDao {

    /**
     * Khởi tạo đối tượng BaseDao để lấy về các phương thức <br>
     * - Đóng kết nối <br>
     * - Mở kết nối
     */
    public TblImageDao() {
        // Khởi tạo đối tượng BaseDaoImpl
        super();
    }

    public int getTotalImage() throws ClassNotFoundException, SQLException {
        // Tạo biến lưu kết quả trả về
        int total = 0;
        try {
            // Mở kết nối tới database
            openConnection();
            // Tạo câu truy vấn
            StringBuilder query = new StringBuilder();
            query.append(" SELECT COUNT(img.image_id) as total ")
                 .append(" FROM `tbl_image` img; ");
            // Sử dụng PreparedStatement để lưu câu truy vấn gửi tới DB
            PreparedStatement preState = conn.prepareStatement(query.toString());
            // Thực thi câu truy vấn
            ResultSet rs = preState.executeQuery();
            // Nếu có dữ liệu trả về
            if (rs.next()) {
                // Lưu giá trị trả về cho biến total
                total = rs.getInt("total");
            }
        // Bắt và xử lý các exception thu được
        } catch (ClassNotFoundException | SQLException e) {
            // Ném lỗi về cho controller
            throw e;
        } finally {
            // Đóng kết nối
            closeConnection();
        }
        // Trả về kết quả
        return total;
    }
    
    public List<ImageEntity> getListImage(int offset,
                                          int limitRecord, 
                                          String sortType,
                                          String sortByImgName,
                                          String sortByAlternateText) throws ClassNotFoundException, SQLException {
        // Khai báo một danh sách lưu giá trị trả về
        List<ImageEntity> listImage = new ArrayList<ImageEntity>();
        try {
            // Mở kết nối tới database
            openConnection();
            // ---------------- Tạo truy vấn ---------------- 
            StringBuilder query = new StringBuilder(" SELECT ");
            query.append(" image_id as imageId, ")
                 .append(" image_name as imageName, ")
                 .append(" alternate_text as alternateText ")
                 .append(" FROM `tbl_image` ");
           
            // Kiểu sắp xếp [sortType]
            switch (sortType) {
            case Constant.DB_IMG_NAME:
                query.append(" ORDER BY ")
                     .append(" image_name ")
                     .append(sortByImgName)
                     .append(", image_id ")
                     .append(Constant.ASC)
                     .append(", alternate_text ")
                     .append(Constant.ASC);
                break;
            case Constant.DB_ALTERNATE_TEXT:
                query.append(" ORDER BY ")
                     .append(" alternate_text ")
                     .append(sortByAlternateText)
                     .append(", image_id ")
                     .append(Constant.ASC)
                     .append(", image_name ")
                     .append(Constant.ASC);
                break;
            default:
                query.append(" ORDER BY ")
                     .append(" image_id ")
                     .append(Constant.ASC)
                     .append(", image_name ")
                     .append(Constant.ASC)
                     .append(", alternate_text ")
                     .append(Constant.ASC);
                break;
            }
            // Giới hạn số record lấy ra
            query.append(" LIMIT ?, ?; ");
            // Sử dụng PreparedStatement để lưu câu truy vấn gửi tới DB
            PreparedStatement preState = conn.prepareStatement(query.toString());
            // Tạo biến chỉ số tự tăng
            int index = 1;
            // Lưu giá trị offset vào câu truy vấn
            preState.setInt(index++, offset);
            // Lưu giá trị limit vào câu truy vấn
            preState.setInt(index++, limitRecord);
            // ---------------- Thực thi truy vấn ---------------- 
            ResultSet rs = preState.executeQuery();
            // Lấy về danh sách user
            while (rs.next()) {
                // Khởi tạo đối tượng UserInfo
                ImageEntity image = new ImageEntity();
                // Thiết lập các thuộc tính cho ảnh
                image.setImageId(rs.getInt(Constant.IMAGE_ID));
                image.setImageName(rs.getString(Constant.IMAGE_NAME));
                image.setAlternateText(rs.getString(Constant.ALTERNATE_TEXT));
                // Thêm user vào danh sách trả về
                listImage.add(image);
            }
        // Bắt và xử lý các exception thu được
        } catch (ClassNotFoundException | SQLException e) {
            // Ném lỗi về cho controller
            throw e;
        } finally {
            // Đóng kết nối
            closeConnection();
        }
        // Trả về kết quả
        return listImage;
    }
    
    /**
     * 
     * @param imageNames
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void insertImageToDatabase(String[] imageNames) throws SQLException, ClassNotFoundException {
        try {
            if (imageNames != null) {
                openConnection();
                PreparedStatement preState = conn.prepareStatement("DELETE FROM `tbl_image`;");
                preState.execute();
                
                preState = conn.prepareStatement("ALTER TABLE `tbl_image` AUTO_INCREMENT = 1;");
                preState.execute();
                
                // Tạo câu truy vấn tới DB
                StringBuilder query = new StringBuilder();
                query.append("INSERT INTO `tbl_image`(`image_name`) ")
                     .append(" VALUES ");
                     
                for (String image : imageNames) {
                    query.append("('").append(image).append("'),\n");
                }
                // Thiết lập mệnh đề prepareStatement để gửi truy vấn tới DB
                preState = conn.prepareStatement(query.substring(0, query.length() - 2));
                // Thực thi truy vấn
                preState.execute();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            closeConnection();
        }
    }
    
    /**
     * 
     * @param listImage
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void insertImageToDatabase(ArrayList<ImageEntity> listImage) throws SQLException, ClassNotFoundException {
        try {
            if (!listImage.isEmpty()) {
                openConnection();
                PreparedStatement preState = conn.prepareStatement("DELETE FROM `tbl_image`;");
                preState.execute();
                
                preState = conn.prepareStatement("ALTER TABLE `tbl_image` AUTO_INCREMENT = 1;");
                preState.execute();
                
                // Tạo câu truy vấn tới DB
                StringBuilder query = new StringBuilder();
                query.append("INSERT INTO `tbl_image`(`image_name`, `alternate_text`) ")
                     .append(" VALUES ");
                     
                for (ImageEntity image : listImage) {
                    query.append("('")
                         .append(image.getImageName())
                         .append("', '")
                         .append(image.getAlternateText())
                         .append("'),\n");
                }
                // Thiết lập mệnh đề prepareStatement để gửi truy vấn tới DB
                preState = conn.prepareStatement(query.substring(0, query.length() - 2));
                // Thực thi truy vấn
                preState.execute();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw e;
        } finally {
            closeConnection();
        }
    }
    
    /**
     * 
     * @param imageEntity
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean updateImage(ImageEntity imageEntity) throws SQLException, ClassNotFoundException {
        // Tạo tham số lưu kết quả truy vấn
        boolean isSuccess = false;
        try {
            openConnection();
            // Tạo câu truy vấn
            StringBuilder query = new StringBuilder();
            query.append(" UPDATE `tbl_image` SET ")
                 .append(" image_name = ?, ")
                 .append(" alternate_text = ? ")
                 .append(" WHERE image_id = ? ;");
            // Thiết lập mệnh đề prepareStatement để gửi truy vấn tới DB
            PreparedStatement preState = conn.prepareStatement(query.toString());
            // Tạo chỉ số tự tăng
            int index = 1;
            // Thiết lập các tham số cho mệnh đề prepareStatement
            preState.setString(index++, imageEntity.getImageName());
            preState.setString(index++, imageEntity.getAlternateText());
            preState.setInt(index++, imageEntity.getImageId());
            // Thực thi truy vấn
            int row = preState.executeUpdate();
            // Nếu câu truy vấn thay đổi dữ liệu trong DB
            if (row > 0) {
                // update thành công
                isSuccess = true;
            }
        // Bắt các exeption có thể xảy ra
        } catch (SQLException | ClassNotFoundException e) {
            // Ném lỗi về cho controller
            throw e;
        } finally {
            closeConnection();
        }
        // Trả về kết quả truy vấn
        return isSuccess;
    }
    
    /**
     * 
     * @param imageId
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteImage(int imageId) throws SQLException, ClassNotFoundException {
        try {
            openConnection();
            // Tạo câu truy vấn
            String query = "DELETE FROM tbl_image WHERE image_id = ? ;";
            // Sử dụng prepareStatement để thực thi truy vấn
            PreparedStatement preState = conn.prepareStatement(query);
            // Thiết lập tham số cho câu truy vấn
            preState.setInt(1, imageId);
            preState.executeUpdate();
        // Bắt và xử lý các lỗi thu được
        } catch (SQLException | ClassNotFoundException e) {
            // Ném lỗi về cho controller
            throw e;
        } finally {
            closeConnection();
        }
    }

    
    
}
