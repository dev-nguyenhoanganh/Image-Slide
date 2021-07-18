/**
 * SystemErrorController.java, 2021/07/17
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
 * Xử lý hiển thị các thông báo lỗi
 * 
 * @Author AI_TEAM
 * @Version 1.0
 */
public class SystemErrorController extends HttpServlet {

    /**
     * Thuộc tính định danh của Serializable class
     */
    private static final long serialVersionUID = 1L;

    /**
     * Xử lý hiển thị màn hình thông báo lỗi, trong các trường hợp: 
     * <li> Xảy ra exception trong quá trình chạy. Hiển thị mã lỗi MSG006
     * 
     * @param req - request browser gửi về controller
     * @param resp - respone controller gửi về browser
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy mã lỗi từ trên request về
        String messageCode = req.getParameter(Constant.MESSAGE);
        // Đọc câu message từ file .properties
        String message = MessageProperties.getValueByKey(messageCode);
        // Đẩy message lên request để hiển thị lên màn System_Error 
        req.setAttribute(Constant.MESSAGE, message);
        // Forward dữ liệu tới System_Error.jsp để hiển thị lên browser
        req.getRequestDispatcher(Constant.SYS_ERR).forward(req, resp);
    }
}