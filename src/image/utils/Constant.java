/**
 * Constant.java, 2021/07/15
 * 
 */
package image.utils;

import image.properties.ConfigProperties;

/**
 * @Description
 * Định nghĩa các giá trị hằng cho chương trình
 * 
 * @Author AI Team
 * @Version 1.0
 */
public class Constant {

    // -----------------     Mapping URL    -----------------
    // Controller
    public static final String URL_LIST_IMAGE        = "/listImage.do";
    public static final String URL_SYS_ERR           = "/systemError.do";
    public static final String URL_SUCCESS           = "/success.do";
    // JSP
    public static final String SCR001  = "/views/jsp/SCR001.jsp";
    public static final String SYS_ERR = "/views/jsp/System_Error.jsp";
    public static final String SUCCESS = "/views/jsp/Success.jsp";
    

    // -----------------   Properies Path   -----------------
    public static final String CONFIG_PATH   = "image/properties/config.properties";
    public static final String DATABASE_PATH = "image/properties/database.properties";
    public static final String MSG_PATH      = "image/properties/message_vi.properties";
    public static final String LIST_IMG_PATH = ConfigProperties.getValueByKey("LIST_IMG_PATH").replace("\\","/");
    public static final String IMG_DIR_PATH  = ConfigProperties.getValueByKey("IMG_DIR_PATH");
    
    // ----------------- Properies Parameter ----------------
    public static final String URL_MYSQL     = "URL_MYSQL";
    public static final String HOST_NAME     = "HOST_NAME";
    public static final String PORT          = "PORT";
    public static final String DATABASE_NAME = "DATABASE_NAME";
    public static final String QUERY_ENCODE  = "QUERY_ENCODE";
    public static final String USER_NAME     = "USER_NAME";
    public static final String PASSWORD      = "PASSWORD";
    public static final String DRIVER_NAME   = "DRIVER_NAME";
    
    // ------------------- List Parameter -------------------
    // SCR001
    public static final String IMAGE_DELIMITER   = ConfigProperties.getValueByKey("IMAGE_DELIMITER");
    public static final String LIMIT_RECORD      = "LIMIT_RECORD";
    public static final String LIMIT_PAGE        = "LIMIT_PAGE";
    public static final String LINE_DELIMITER    = "\n";
    // Default Param
    public static final int    DEFAULT_PAGE      = 1;
    public static final int    DEFAULT_IMG_ID    = 0;
    public static final int    DEFAULT_INT       = 0;
    public static final String DEFAULT_STRING    = "";
    public static final String DEFAULT_ACTION    = "default";
    public static final String DEFAULT_SORT_TYPE = "image_id";
    
    public static final String ACTION        = "action";
    public static final String ACTION_SORT   = "sort";
    public static final String ACTION_PAGING = "paging";
    public static final String ACTION_BACK   = "back";
    // Image Object
    public static final String IMAGE_ENTITY   = "imageEntity";
    public static final String IMAGE_ID       = "imageId";
    public static final String IMAGE_NAME     = "imageName";
    public static final String IMAGE_DIR      = "imageDir";
    public static final String IMAGES         = "images";
    public static final String ALTERNATE_TEXT = "alternateText";
    // List
    public static final String LIST_ERROR   = "listError";
    public static final String LIST_PAGING  = "listPaging";
    public static final String LIST_IMAGE   = "listImage";
    // Request Param
    public static final String TOTAL_PAGE   = "totalPage";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String MESSAGE      = "message";
    public static final String DESC         = "DESC";
    public static final String ASC          = "ASC";
    public static final String SORT_TYPE    = "sortType";
    public static final String SORT_VALUE   = "sortValue";
    public static final String SORT_BY_IMAGE_NAME = "sortByImgName";
    public static final String SORT_BY_ALTER_TEXT = "sortByAlternateText";
    
    public static final String DB_IMG_NAME        = "image_name";
    public static final String DB_ALTERNATE_TEXT  = "alternate_text";
    public static final String DB_COLUMN_NAME     = "column_name";
    
    // Không có bức ảnh nào được tìm thấy
    public static final String MSG005 = "MSG005";
    // Hệ thống đang có lỗi
    public static final String MSG006 = "MSG006";
    // Load file thành công
    public static final String MSG007 = "MSG007";
    // Sửa thành công
    public static final String MSG002 = "MSG002";
    public static final String REQUEST_MESSAGE = "?message=";
    
    
}