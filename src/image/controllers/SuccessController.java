/**
 * SuccessController.java, 2021/07/17
 * 
 */
package image.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import image.properties.MessageProperties;
import image.utils.Constant;

/**
 * @Description 
 * Controller xử lý hiển thị màn thông báo thành công
 * 
 * @Author AI_TEAM
 * @Version 1.0
 */
public class SuccessController extends HttpServlet {

    /**
     * Thuộc tính định danh của Serializable class
     */
    private static final long serialVersionUID = 1L;

    /**
     * Xử lý hiển thị trong các trường hợp: 
     * <li> Nhập link trực tiếp tới controller
     * <li> Thêm, sửa, xóa thành công
     * 
     * @param req - request browser gửi về controller
     * @param resp - respone controller gửi về browser
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Lấy messageCode từ trên request
            String messageCode = req.getParameter(Constant.MESSAGE);
            // Đọc câu message từ file .properties
            String message = MessageProperties.getValueByKey(messageCode);
            // Đẩy message lên request để hiển thị 
            req.setAttribute(Constant.MESSAGE, message);
            // Điều hướng tới màn ADM006 để hiển thị
            req.getRequestDispatcher(Constant.SUCCESS).forward(req, resp);
        // Bắt các lỗi có thể xảy ra
        } catch (Exception e) {
            // Chuyển tiếp tới màn hình hiển thị lỗi [System_Error.jsp]
            resp.sendRedirect(req.getContextPath() + Constant.URL_SYS_ERR + Constant.REQUEST_MESSAGE + Constant.MSG006);
        }
    }

}