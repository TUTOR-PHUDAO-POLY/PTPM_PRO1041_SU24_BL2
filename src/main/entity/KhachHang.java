/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author hangnt
 */
// @: annotation 
@Getter
@Setter
@AllArgsConstructor // contructor all tham so
@NoArgsConstructor // contructor k tham so
@Builder
@ToString
// khong duoc su @Data 
public class KhachHang {

    private Integer id;
    
    private String ma;
    
    private String tenDem;
    
    private String ho;
    
    private String ten;
    
    private Date ngaySinh;
    
    private String sdt;
    
    private String diaChi;
    
    private String thanhPho;
    
    private String quocGia;
    
    private String matKhau;
    
    private Integer trangThai;
    
    private Boolean gioiTinh;
}
