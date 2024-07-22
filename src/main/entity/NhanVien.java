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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NhanVien {

    private Integer id;

    private String ma;

    private String ten;

    private String tenDem;

    private String ho;

    private Date ngaySinh;

    private String sdt;

    private String diaChi;

    private String thanhPho;

    private String quocGia;

    private String matKhau;

    private Integer trangThai;

    private String gioiTinh;

}
