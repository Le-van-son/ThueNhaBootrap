package com.example.myhouse.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

//@All Mini test:
//Thông tin nhà bao gồm:
//- Tên của căn nhà (Không được để trống)
//- Địa chỉ (Không được để trống)
//- Số lượng phòng ngủ () :1-10 phòng
//- Số lượng phòng tắm (): 1-3 phòng
//- Giá tiền theo ngày(VNĐ)()
//- Ảnh: nếu không đăng thì có ảnh mặc định . Chỉ cho chọn file jpeg, png.
//Các trường phải validate.
//Làm các chức năng (API, Ajax) (Giao diện dùng bootstrap):
//- Thêm nhà
//- Hiểm thị danh sách nhà
//Nâng cao: phân trang.
@Entity
public class Myhouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Not null!")
    private String name;
    @NotBlank(message = "Not null!")
    private String address;
    @Range(min=1,max=10,message = "Quantity between (1-10) please!")
    private int quantityBedroom;
    @Range(min=1,max=3,message = "Quantity between (1-3) please!")
    private int quantityBathroom;
    @Pattern(regexp = ".*((.png)|(.jpg))" , message = "Chỉ được upfile png hoặc jpg")
    private String image;
    private double price;

    public Myhouse() {
    }

    public Myhouse(int id, String name, String address, int quantityBedroom, int quantityBathroom, double price, String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.quantityBedroom = quantityBedroom;
        this.quantityBathroom = quantityBathroom;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getQuantityBedroom() {
        return quantityBedroom;
    }

    public void setQuantityBedroom(int quantityBedroom) {
        this.quantityBedroom = quantityBedroom;
    }

    public int getQuantityBathroom() {
        return quantityBathroom;
    }

    public void setQuantityBathroom(int quantityBathroom) {
        this.quantityBathroom = quantityBathroom;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
