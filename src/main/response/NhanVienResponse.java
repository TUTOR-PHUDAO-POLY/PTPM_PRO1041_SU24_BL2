/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.response;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class NhanVienResponse {
    // dto => data tranfer object
    // request & response
    // response : Du lieu tra ra (Muon tra ra gi thi liet ke o day)
    //Liet ke cac truong ma cac ban muon tra ra
    private Integer id;
    
    private String maNhanVien;
    
    private String tenNhanVien;
    
    private String soDienThoai;
    
    private String diaChi;
    
    private String tenChucVu;
    
    private String gioiTinh;
    
    private String maChucVu;
    
}
