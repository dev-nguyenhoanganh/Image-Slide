/**
 * ListImageController.java, 2021/05/09
 * 
 */
package image.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import image.entities.ImageEntity;
import image.logic.TblImageLogic;
import image.properties.LoadImageFromFile;
import image.properties.MessageProperties;
import image.utils.Common;
import image.utils.Constant;

/**
 * @Description 
 * Xử lý hiển thị danh sách các ảnh
 * 
 * @Author AI_TEAM
 * @Version 1.0
 */
public class ListImageController extends HttpServlet {

    /**
     * Thuộc tính định danh của Serializable class
     */
    private static final long serialVersionUID = 1L;

    static {
        try {
            LoadImageFromFile.loadImageEntityFromFile();
        } catch (ClassNotFoundException | SQLException | IOException e) {
        	e.printStackTrace();
            System.out.println("File list_image.txt bị lỗi");
        }
    }
    
    /**
     * Phương thức thực hiện điều khiển luồng chạy của chương trình trong 
     * trường hợp 
     * <li> Nhập link trực tiếp tới controller
     * <li> Click link   [paging]
     * <br><br>
     * 
     * @param req  - request browser gửi về controller
     * @param resp - respone controller phản hồi cho browser
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Lấy về session từ trên request
            HttpSession session = req.getSession();
            // ----------------- Khởi tạo giá trị ban đầu -----------------
            // Các đối tượng Logic
            TblImageLogic  imgLogic  = new TblImageLogic();
            // Tham số
            String message         = Constant.DEFAULT_STRING;
            int currentPage        = Constant.DEFAULT_PAGE;
            int offset             = Constant.DEFAULT_INT;
            int totalImage		   = Constant.DEFAULT_INT;
            int totalPage	       = Constant.DEFAULT_INT;
            int limitRecord        = Common.getLimitRecord();
            int limitPage          = Common.getLimitPage();
            String sortType        = Constant.DEFAULT_SORT_TYPE;
            String sortValue       = Constant.ASC;
            String sortByImgName   = Constant.ASC;
            String imageDir  = Constant.IMG_DIR_PATH;
            String sortByAlternateText  = Constant.ASC;
            List<ImageEntity> listImage = new ArrayList<ImageEntity>();
            List<Integer>    listPaging = new ArrayList<Integer>();
            // Lấy về trường hợp hiển thị từ request
            String action = req.getParameter(Constant.ACTION);
            // Xử lý trường hợp action = null
            if (action == null) {
                // Gán action = default
                action = Constant.DEFAULT_ACTION;
            }
            // Chia các trường hợp để hiển thị màn ADM002
            switch (action) {
            // Trường hợp [sắp xếp]
            case Constant.ACTION_SORT:
                // Lấy giá trị từ trên request
                sortType  = req.getParameter(Constant.SORT_TYPE);
                sortValue = req.getParameter(Constant.SORT_VALUE);
                break;
            // Trường hợp [paging]
            case Constant.ACTION_PAGING:
                // Lấy giá trị trang hiện tại
                sortType  = req.getParameter(Constant.SORT_TYPE);
                sortValue = req.getParameter(Constant.SORT_VALUE);
                currentPage = Common.convertStringToInt(req.getParameter(Constant.CURRENT_PAGE), Constant.DEFAULT_PAGE);
                // Thoát khỏi case
                break;
            // Trường hợp [back]
            case Constant.ACTION_BACK:
                // Lấy các giá trị từ trên session
                sortType  = (String) session.getAttribute(Constant.SORT_TYPE);
                sortValue = (String) session.getAttribute(Constant.SORT_VALUE);
                currentPage = (int) session.getAttribute(Constant.CURRENT_PAGE);
                // Thoát khỏi case
                break;
            // Trường hợp hiển thị default
            default:
                break;
            }
            // Nếu sortType và SortValue hợp lệ thì gán giá trị cho hạng mục
            if (imgLogic.checkSortTypeAndSortValue(sortType, sortValue)) {
                // Xếp theo năng lực tiếng nhật
                if (Constant.DB_IMG_NAME.equals(sortType)) {
                    // Gán giá trị cho sortByCodeLevel
                    sortByImgName = sortValue;
                }
                // Xếp theo ngày hết hạn
                else if (Constant.DB_ALTERNATE_TEXT.equals(sortType)) {
                    // Gán giá trị cho sortByEndDate
                    sortByAlternateText = sortValue;
                }
            }
            // ------------------ Thao tác với DB ------------------
            // Lấy về tổng số ảnh có trong hệ thống
            totalImage = imgLogic.getTotalImage();
            // Nếu có user
            if (totalImage > 0) {
                // Lấy ra tổng số page
                totalPage = Common.getTotalPage(totalImage, limitRecord);
                // Nếu page hiện tại không đúng
                if (currentPage > totalPage || currentPage <= 0) {
                    // Đưa page hiện tại về default page
                    currentPage = totalPage;
                }
                // Lấy danh sách paging
                listPaging = Common.getListPaging(totalImage, limitRecord, currentPage);
                // Lấy giá trị cho offset
                offset = Common.getOffset(currentPage, limitRecord);
                // Lấy ra image tìm được
                listImage = imgLogic.getListImage(offset, limitRecord, sortType, sortByImgName,
                        sortByAlternateText);
            } else {
                // lấy câu thông báo từ file Properties
                message = MessageProperties.getValueByKey(Constant.MSG005);
            }
            // ---------------- Gán lên request ----------------
            req.setAttribute(Constant.LIST_PAGING , listPaging);
            req.setAttribute(Constant.MESSAGE     , message);
            req.setAttribute(Constant.LIST_IMAGE  , listImage);
            req.setAttribute(Constant.SORT_TYPE   , sortType);
            req.setAttribute(Constant.SORT_VALUE  , sortValue);
            req.setAttribute(Constant.TOTAL_PAGE  , totalPage);
            req.setAttribute(Constant.LIMIT_PAGE  , limitPage);
            req.setAttribute(Constant.IMAGE_DIR   , imageDir);
            req.setAttribute(Constant.CURRENT_PAGE, currentPage);
            req.setAttribute(Constant.SORT_BY_IMAGE_NAME , sortByImgName);
            req.setAttribute(Constant.SORT_BY_ALTER_TEXT , sortByAlternateText);
            // ---------------- Gán lên Session ----------------
            session.setAttribute(Constant.CURRENT_PAGE, currentPage);
            session.setAttribute(Constant.SORT_TYPE   , sortType);
            session.setAttribute(Constant.SORT_VALUE  , sortValue);
            // Forward dữ liệu tới ADM002.jsp để hiển thị lên browser
            req.getRequestDispatcher(Constant.SCR001).forward(req, resp);
        // Bắt exception tổng
        } catch (Exception e) {
            e.printStackTrace();
            // Chuyển tiếp tới màn hình hiển thị lỗi [System_Error.jsp]
            resp.sendRedirect(req.getContextPath() + Constant.URL_SYS_ERR + Constant.REQUEST_MESSAGE + Constant.MSG006);
        }
    }
    
}