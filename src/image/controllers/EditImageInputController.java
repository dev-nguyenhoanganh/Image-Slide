/**
 * EditImageInputController.java, 2021/07/17
 * 
 */
package image.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import image.logic.TblImageLogic;
import image.utils.Common;
import image.utils.Constant;

/**
 * @Description 
 * Controller xử lý hiển thị màn hình nhập liệu chức năng Edit
 *
 * @Author AI_TEAM
 * @Version 1.0
 */
public class EditImageInputController extends HttpServlet {

    /**
     * Thuộc tính định danh của Serializable class
     */
    private static final long serialVersionUID = 1L;

    /**
     * Phương thức thực hiện điều khiển luồng chạy của chương trình trong 
     * trường hợp 
     * <li> Click button [edit] trên màn listImage
     * 
     * @param req - request browser gửi về controller
     * @param resp - respone controller gửi về browser
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            TblImageLogic imgLogic = new TblImageLogic();
            String imageName = req.getParameter(Constant.IMAGE_NAME);
            String alternateText = req.getParameter(Constant.ALTERNATE_TEXT);
            int imageId = Common.convertStringToInt(req.getParameter(Constant.IMAGE_ID), Constant.DEFAULT_INT);
            boolean isSuccess = imgLogic.updateImage(imageId, imageName, alternateText);
            if (isSuccess) {
                resp.sendRedirect(req.getContextPath() + Constant.URL_LIST_IMAGE + "?action=back");
            } else {
                resp.sendRedirect(req.getContextPath() + Constant.URL_SYS_ERR + Constant.REQUEST_MESSAGE + Constant.MSG006);
            }
        // Bắt các lỗi có thể xảy ra
        } catch (Exception e) {
            e.printStackTrace();
         // Chuyển tiếp tới màn hình hiển thị lỗi [System_Error.jsp]
            resp.sendRedirect(req.getContextPath() + Constant.URL_SYS_ERR + Constant.REQUEST_MESSAGE + Constant.MSG006);
        }
    }
    
}