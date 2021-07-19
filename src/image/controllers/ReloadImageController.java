/**
 * ReloadImageController.java, 2021/07/17
 * 
 */
package image.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import image.properties.LoadImageFromFile;
import image.utils.Constant;

/**
 * @Description 
 * 
 * @Author AI_TEAM
 * @Version 1.0
 */
public class ReloadImageController extends HttpServlet {
    /**
     * Thuộc tính định danh của Serializable class
     */
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            
            // Đọc ảnh từ thư mục [TestImage]
            LoadImageFromFile.loadImageEntityFromFile();
            
            resp.sendRedirect(req.getContextPath() + Constant.URL_LIST_IMAGE);
        } catch (Exception e) {
            e.printStackTrace();
            // Chuyển hướng đến màn hình lỗi
            resp.sendRedirect(req.getContextPath() + Constant.URL_SYS_ERR + Constant.REQUEST_MESSAGE + Constant.MSG006);
        }
    
    }
}
