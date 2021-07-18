/**
 * TblImageLogic.java, 2021/07/16
 * 
 */
package image.logic;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import image.dao.TblImageDao;
import image.entities.ImageEntity;
import image.properties.LoadImageFromFile;
import image.utils.Common;
import image.utils.Constant;

/**
 * @Description 
 * 
 * @Author AI_TEAM
 * @Version 1.0
 */
public class TblImageLogic {

    /**
     * 
     * @param sortType
     * @param SortValue
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean checkSortTypeAndSortValue(String sortType, String SortValue) throws SQLException, ClassNotFoundException {
        // Tạo 1 biến lưu kết quả kiểm tra
        boolean isCorrect = false;
        try {
            // Kiểm tra sortValue
            isCorrect = Common.compareString(Constant.ASC, SortValue) || Common.compareString(Constant.DESC, SortValue);
            // Nếu giá trị của sortValue hợp lệ
            if (isCorrect) {
                // Tạo đối tượng ImageDao để truy cập tới DB
                TblImageDao imgDao = new TblImageDao();
                // Lấy về danh sách các cột có trong DB
                List<String> listColumn = imgDao.getListColumn();
                // Kiểm tra giá trị của sortType
                isCorrect = listColumn.contains(sortType);
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Ném lỗi về controller
            throw e;
        }
        // Trả về kết quả kiểm tra
        return isCorrect;
    }
    
    /**
     * 
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public int getTotalImage() throws ClassNotFoundException, SQLException {
        // Khởi tạo đối tượng [TblImageDao] để tương tác với database
        TblImageDao imgDao = new TblImageDao();
        // Khởi tạo biến lưu giá trị của tổng số user
        int total = 0;
        try {
            // Lấy giá trị tổng user có trong DB
            total = imgDao.getTotalImage();
        // Bắt và xử lý các exception thu được
        } catch (ClassNotFoundException | SQLException e) {
            // Ném lỗi về cho controller xử lý
            throw e;
        }
        // Trả về kết quả tìm được
        return total;
    }


    /**
     * @param offset
     * @param limitRecord
     * @param sortType
     * @param sortByImgName
     * @param sortByAlternateText
     * @return
     */
    public List<ImageEntity> getListImage(int offset, int limitRecord, String sortType, String sortByImgName,
            String sortByAlternateText)
            throws ClassNotFoundException, SQLException {
        // Khai báo một danh sách lưu giá trị trả về
        List<ImageEntity> listImage = new ArrayList<ImageEntity>();
        // Khởi tạo đối tượng [TblImageDao] để tương tác với database
        TblImageDao imageDao = new TblImageDao();
        try {
            // Lấy danh sách user có trong DB
            listImage = imageDao.getListImage(offset, limitRecord, sortType, sortByImgName,
                    sortByAlternateText);
        // Bắt và xử lý các exception thu được
        } catch (ClassNotFoundException | SQLException e) {
            // Ném lỗi về cho controller xử lý
            throw e;
        }
        // Trả về danh sách user tìm được
        return listImage;
    }
    
    /**
     * 
     * @param imageId
     * @param imageName
     * @param alternateText
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException
     */
    public boolean updateImage(int imageId, String imageName, String alternateText) throws ClassNotFoundException, SQLException, IOException {
        boolean isSuccess = true;
        try {
            // Khởi tạo đối tượng [TblUserDao] để tương tác với database
            TblImageDao imageDao = new TblImageDao();
            ImageEntity image = new ImageEntity();
            image.setImageId(imageId);
            image.setImageName(imageName);
            image.setAlternateText(alternateText);
            isSuccess = LoadImageFromFile.updateImage(image);
            if (isSuccess) {
                imageDao.updateImage(image);
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw e;
        }
        return isSuccess;
    }
    
}
