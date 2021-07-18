# ------------------------------------------------------- 
# @File_name : Manage User Script.sql                   |
# @Date      : [16/07/2021]                             |
# @Author    : Nguyễn Hoàng Anh                         |
# @Version   : 1.0                                      |
# -------------------------------------------------------
# @Description                                          |
# Các câu lệnh tạo database, tạo bảng và                | 
# chèn dữ liệu vào database                             |
# -------------------------------------------------------

# Xóa database 
DROP DATABASE `image_slider`;

# Tạo database `image_slider`
CREATE DATABASE `image_slider` 
CHARACTER SET utf8;

# Hiển thị danh sách các database sẵn có
SHOW DATABASES;

# Trỏ tới database để sử dụng
USE `image_slider`;

# Tạo mới bảng `tbl_image`
CREATE TABLE `tbl_image` (
	`image_id` INT NOT NULL AUTO_INCREMENT,
    `image_name` VARCHAR(255) NOT NULL,
    `alternate_text` VARCHAR(255),
    PRIMARY KEY (`image_id`)
);

# Chèn dữ liệu vào bảng [tbl_image]
INSERT INTO 
  `tbl_image`(`image_name`, `alternate_text`)
VALUES
  ('1.jpg', 'Số 2020'),
  ('2.jpg', 'Bánh tráng nướng'),
  ('3.jpg', 'Desktop keyboard'),
  ('4.jpg', 'Lẩu King BBQ'),
  ('5.jpg', 'Roman Synkevych vXInUOv1n84unsplash'),
  ('6.jpg', 'Wolfgang Hasselmann'),
  ('7.jpg', 'Work Desk1'),
  ('8.jpg', 'Các cuộn chỉ nhiều màu sắc'),
  ('9.jpg', 'Workspace 2'),
  ('10.jpg', 'Anastasiia Vasileva YYA7pjakDv0unsplash'),
  ('11.jpg', 'Anita Austvika PfMDvyzryk4'),
  ('12.jpg', 'Anthony Espinosa Eulwt9kn4h0unsplash'),
  ('13.jpg', 'Chuttersnap hCt5rmDYfMQunsplash')
;

INSERT INTO 
  `tbl_image`(`image_name`)
VALUES
  ('1.jpg'),
  ('2.jpg'),
  ('3.jpg'),
  ('4.jpg'),
  ('5.jpg'),
  ('6.jpg'),
  ('7.jpg'),
  ('8.jpg'),
  ('9.jpg'),
  ('10.jpg'),
  ('11.jpg'),
  ('12.jpg'),
  ('13.jpg')
;

INSERT INTO 
  `tbl_image`(`image_name`, `alternate_text`, `image_content`)
VALUES
  ('1.jpg', 'Số 2020', LOAD_FILE('F:/Servlet/Image Slide/WebContent/views/images/TestImage/1.jpg'));

# 'F:\Servlet\Image Slide\WebContent\views\images\TestImage'
select LOAD_FILE('F:/Servlet/Image Slide/WebContent/views/images/TestImage/11.jpg');
select LOAD_FILE('F:\Servlet\Image Slide\WebContent\views\images\TestImage\11.jpg');

select LOAD_FILE('\tmp\picture');

help LOAD_FILE;

# Reset biến tự tăng
ALTER TABLE `tbl_image` AUTO_INCREMENT = 1;

# Lấy dữ liệu từ bảng `tbl_image`
SELECT * FROM `tbl_image`;

# Lấy dữ liệu từ bảng `tbl_image`
SELECT 
	image_id as imageId,
    image_name as imageName,
    alternate_text as alternateText
FROM `tbl_image`
ORDER BY image_id ASC, image_name ASC, alternate_text ASC
LIMIT 0, 3;

# Xoá dữ liệu trong bảng `tbl_image`
DELETE FROM `tbl_image`;

# Thiết định lại safe_update của DB
SET SQL_SAFE_UPDATES = 0;


# Lấy tổng image trong bảng tbl_image
SELECT COUNT(img.image_id) as total
FROM `tbl_image` img;

# Tạo user mới để test
CREATE USER 'tester'@'localhost' IDENTIFIED BY '0000';

# Cấp quyền cho tester này
GRANT SELECT, INSERT, UPDATE, DELETE, ALTER ON `image_slider`.tbl_image TO 'tester'@'localhost';