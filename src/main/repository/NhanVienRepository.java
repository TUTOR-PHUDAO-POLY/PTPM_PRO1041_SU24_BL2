/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.config.DBConnect;
import main.response.NhanVienResponse;

/**
 *
 * @author hangnt
 */
public class NhanVienRepository {

    public ArrayList<NhanVienResponse> getAll() {
        // B1: Tao ra doi tuong ArrayList 
        ArrayList<NhanVienResponse> lists = new ArrayList<>();
        // Code 
        // B2: Tao SQL 
        String sql = """
                     SELECT dbo.NhanVien.Id, dbo.NhanVien.Ma, 
                     dbo.NhanVien.Ten,
                     dbo.NhanVien.DiaChi, dbo.NhanVien.Sdt, 
                     dbo.NhanVien.GioiTinh, 
                     dbo.ChucVu.Ma AS Expr1, dbo.ChucVu.Ten AS Expr2
                     FROM     dbo.ChucVu INNER JOIN
                                       dbo.NhanVien
                     ON dbo.ChucVu.Id = dbo.NhanVien.IdCV
                     """;

        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery(); // Tra ra du lieu 1 bang 
            while (rs.next()) {
                NhanVienResponse response = NhanVienResponse.builder()
                            // tu cham not cac thuoc tinh 
                        .id(rs.getInt(1))
                        .maNhanVien(rs.getString(2))
                        .tenNhanVien(rs.getString(3))
                        .diaChi(rs.getString(4))
                        .soDienThoai(rs.getString(5))
                        .gioiTinh(rs.getBoolean(6))
                        .maChucVu(rs.getString(7))
                        .tenChucVu(rs.getString(8))
                        .build();
                lists.add(response);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }
}
