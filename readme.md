# Hướng dẫn setup môi trường

## Mục lục

> - [1, Cài Tomcat Server](#1-cài-tomcat-server)
> - [2, Cài JDK cho ứng dụng web](#2-cài-jdk-cho-ứng-dụng-web)


### **1, Cài Tomcat Server**

B1: Truy cập vào trang chủ của tomcat [https://tomcat.apache.org/download-80.cgi](https://tomcat.apache.org/download-80.cgi) và tải phần mềm về:

- Nếu chạy trên linux thì tải File: [tar.gz](https://tomcat.apache.org/download-80.cgi)<br>
- Nếu chạy trên windown thì tải file: [zip](https://tomcat.apache.org/download-80.cgi)

![Trang chủ tomcat](images/Apache%20Tomcat%20Download.png)

B2: Sau khi tải về thành công, giải nén file đó sẽ thấy cấu trúc gồm có các thư mục sau:

![Giải nén Apache Tomcat](images/Unzip%20Apache%20Tomcat.png)

B3: Ứng dụng web được nén vào file `Image Slide.war`, để deploy ứng dụng lên server cần copy file này vào thư mục `apache-tomcat-8.5.65\webapps` như sau:

![Deploy Web Application](images/Deploy%20Web%20Application.png)

B4: Mở terminal trỏ tới thư mục `apache-tomcat-8.5.65\bin`, và chạy server:
- `./startup.sh` với Linux
- `startup` với windown

![Start Tomcat Server](images/Start%20Tomcat%20Server.png)

## 2, Cài JDK cho ứng dụng web

B1: Kiểm tra xem JDK đã được cài đặt trên máy chưa

```Shell input

$ sudo apt update
$ java -version
```
```Shell ouput
Command 'java' not found, but can be installed with:

apt install default-jre
apt install openjdk-11-jre-headless
apt install openjdk-8-jre-headless
```
