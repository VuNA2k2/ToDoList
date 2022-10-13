# ToDoList
<<<<<<< HEAD
## Tải app.

=======
## Tải app
>>>>>>> 48f92dfbf68e895eaac6962eb8400f948830f239
- Tải app ở đây [To Do List](https://drive.google.com/file/d/1RNOKPxvw9yIf9oHpaQz_w6MtdFnKzAos/view?usp=sharing)
## Ứng dụng To Do List android viết bằng kotlin.
Kotlin, Android studio, Room Database, MVVM, AlarmManager, Live Data, Navigation Component, Compact CalendarView, SwipeLayout, ...
## Ảnh chụp màn hình và mô tả chức năng.
- Ban đầu mới vào chưa có task nào được tạo sẽ có một màn hình chờ.

![image](https://user-images.githubusercontent.com/78639062/192481834-9e03d020-22ad-4ed1-a688-6456fc8115fa.png)

- Khi nhấn vào nút add trên màn hình một bottom sheet dialog sẽ hiện lên để thêm thông tin của task mới.

![image](https://user-images.githubusercontent.com/78639062/192481647-10f73576-5df8-4e8c-b1e5-0f7ac74ab24d.png)

- Khi có một task đã được tạo.

![image](https://user-images.githubusercontent.com/78639062/192481614-a1257e8d-c396-4fce-9257-b43949941292.png)

- Khi vuốt một item từ phải sang trái sẽ xuất hiện 2 nút edit và delete.

![image](https://user-images.githubusercontent.com/78639062/192481627-05405955-71dc-4f3b-be91-0bd570dc97c4.png)

- Khi nhấn vào nút edit bottom sheet tương tự như thao tác thêm task mới sẽ hiện lên với những thông tin có sẵn của task và một vài thao tác khác.

![image](https://user-images.githubusercontent.com/78639062/192481663-1c80ebd6-53c6-4a28-84df-4677b3edd711.png)

- Khi thực hiện chức năng search

![image](https://user-images.githubusercontent.com/78639062/192482142-4fd21807-4852-4027-9736-fb991451a47c.png)

- Khi nhấn vào nút delete task đó sẽ bị chuyển vào thùng rác, nhưng chưa bị xóa vĩnh viễn. Chúng ta vẫn có thể khôi phục task ở thùng rác.

![image](https://user-images.githubusercontent.com/78639062/192481945-49e62159-6e64-424d-a933-8b470cb819b4.png)

- Khi vuốt item ở thùng rác từ phải sang trái sẽ xuất hiện nút restore và nút delete. Khi restore task đó sẽ quay trở lại và delete sẽ xóa vĩnh viễn task đó.

![image](https://user-images.githubusercontent.com/78639062/192482277-b746f90f-17b6-4947-92f7-78d41fd21f77.png)

- Khi thùng rác rỗng.

![image](https://user-images.githubusercontent.com/78639062/192482987-fd834f65-0895-425a-a4b6-ab6e6c92fbbf.png)

- Tab lịch khi mới chuyển sang sẽ mặc định là ngày hiện tại.

![image](https://user-images.githubusercontent.com/78639062/192483208-3a4fd585-834f-4c09-9f3f-0353108afc61.png)

- Một ngày không có task nào.

![image](https://user-images.githubusercontent.com/78639062/192483789-58a0e208-35bd-455f-ac07-59780b8db3ce.png)

- Ngày có task. Khi thay đổi trạng thái của switch trên item sẽ bật hoặc tắt thông báo.

![image](https://user-images.githubusercontent.com/78639062/192483992-b00bb04d-384f-4a7c-8360-1f15be42e88d.png)

- Thông báo trước một tiếng deadline của task.

![image](https://user-images.githubusercontent.com/78639062/192487717-f4dde672-0df9-4a8f-bbbc-a9f67e36b73a.png)

## Chức năng phát triển sau.

![image](https://user-images.githubusercontent.com/78639062/192484120-90eb1ac0-a1ee-4796-a878-1fcefae5925e.png)

![image](https://user-images.githubusercontent.com/78639062/192484225-d3eadcc5-f21c-4bd3-a71f-438c724dd792.png)

## Kiến trúc code MVVM.
![image](https://user-images.githubusercontent.com/78639062/192488315-78b135df-4fa3-4d05-b25b-00845692875e.png)

### Model.
- Chứa thông tin của các entities.

![image](https://user-images.githubusercontent.com/78639062/192489038-88e8e9aa-5344-4dc7-8883-ec5b0d55542a.png)

### Database.
- Chứa database và các logic CRUD.

![image](https://user-images.githubusercontent.com/78639062/192488433-7f87bef1-9e0b-4d84-a2e5-9c5b3e285a57.png)

### View.
- Mỗi view chứa một file kotlin khai báo view và các adapter, viewmodel của từng view.

![image](https://user-images.githubusercontent.com/78639062/192489345-f14ec671-49cb-45fe-9313-6d13d6debb65.png)

### File App.kt.
- Tạo push notification.
### Contants.
- Chứa các hằng số của project.

![image](https://user-images.githubusercontent.com/78639062/192490245-aaf49a1f-5530-4145-8c84-33ea0b3a62a2.png)

### Broadcast.
- Nhận thông tin từ alarm manager để push notification.

![image](https://user-images.githubusercontent.com/78639062/192490222-be247a65-8d96-4e55-9a2a-797f97974f50.png)

# Kiến thức áp dụng.
- Live data
- Room database
- View model
- Alarm manager
- Broadcast receiver
- Push notification
- Compact Calendar View
- MVVM


