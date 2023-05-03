		HƯỚNG DẪN THIẾT LẬP VÀ CÀI ĐẶT


================PHẦN MỀN VÀ CÔNG CỤ CẦN THIẾT================
 1. ECLIPSE
        ![image](https://user-images.githubusercontent.com/132371246/235821064-b5c76e42-54fc-4dfc-8790-068a791fe5a8.png)

 2. XAMPP CONTROL PANEL
        ![image](https://user-images.githubusercontent.com/132371246/235821093-60e36c82-8586-4b3c-b3f2-07588dede34d.png)

                              ========== Nhập và Chạy Dự án Thông qua Eclipse ==========
 Bước 0: Mở Eclipse. [Cài đặt, nếu chưa cài đặt.]
 
 Bước 1:  Nhấp vào Tệp > Import> Git > Project form Git(with smart import) > coppy Url >Past link:
 https://github.com/huynhpham2270/javal.git vào phần Clone URI > Next > Chọn vị trí lưu trữ > Next > Finish
   ![image](https://user-images.githubusercontent.com/132371246/235825642-5d518eec-392d-49af-8929-7946fd74afda.png)
   

 Bước 2: Chuột phải vào project > Buil Path > Configure Buid Path > Java Buid Path > Libraries > Modulepath >
	Add External JARs > Chọn assets > Chọn mysql-conector-j-8.0.32
  
  
![image](https://user-images.githubusercontent.com/132371246/235821255-47207cfc-ff22-4423-9984-6e30266b57aa.png)

Bước 3: Mở Xampp > Chọn Start ở Apache và MySQL > Admin ở MySQL  > New > Khởi tạo bảng  dữ liệu của tên và score

![image](https://user-images.githubusercontent.com/132371246/235821288-81684747-6cc2-4bd3-ab5e-6c635b8da4a6.png)

![image](https://user-images.githubusercontent.com/132371246/235821337-12faa684-37fa-451e-80eb-9be0366a4b0e.png)

Bước 4: Vào bên trong src/flappybird/main và cập nhật giá trị của chi tiết cơ sở dữ liệu theo cách sử dụng của bạn, như host, db.username và db.password 

![image](https://user-images.githubusercontent.com/132371246/235821472-517fd368-e10e-46c0-b58d-06c674c96f62.png)


Bước 5: Chuột phải vào project > Run as > Chơi game > Nhập tên > END.

![image](https://user-images.githubusercontent.com/132371246/235821565-0c21a830-bdb8-409f-b83b-8a1a4abbb1bd.png)

![image](https://user-images.githubusercontent.com/132371246/235821623-ee61ea5f-f3a0-47b6-b4fe-addb20380ec5.png)
