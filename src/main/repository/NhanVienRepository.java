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
import main.entity.NhanVien;
import main.request.FindNhanVien;
import main.request.NhanVienRequest;
import main.response.NhanVienResponse;

/**
 *
 * @author hangnt
 */
public class NhanVienRepository {

    public ArrayList<NhanVienResponse> getAll(FindNhanVien fnv) {
        // B1: Tao ra doi tuong ArrayList 
        ArrayList<NhanVienResponse> lists = new ArrayList<>();
        // Code 
        // B2: Tao SQL 
        String sql = """
                     SELECT dbo.NhanVien.Id, 
                     dbo.NhanVien.Ma, 
                     dbo.NhanVien.Ten,
                     dbo.NhanVien.DiaChi, 
                     dbo.NhanVien.Sdt, 
                     dbo.NhanVien.GioiTinh, 
                     dbo.ChucVu.Ma AS Expr1, 
                     dbo.ChucVu.Ten AS Expr2
                     FROM     dbo.ChucVu INNER JOIN
                                       dbo.NhanVien
                     ON dbo.ChucVu.Id = dbo.NhanVien.IdCV
                     WHERE [TrangThai] = 1
                     AND dbo.NhanVien.GioiTinh LIKE ?
                     AND dbo.ChucVu.Id = ?
                     AND (dbo.NhanVien.Ma LIKE ? 
                         OR dbo.NhanVien.Ten LIKE ?
                         OR dbo.NhanVien.DiaChi LIKE ?
                         OR dbo.NhanVien.Sdt LIKE ?)
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, fnv.getGioiTinh());
            ps.setObject(2, fnv.getIdCV());
            ps.setObject(3, "%"+fnv.getKeySearch()+"%");
            ps.setObject(4, "%"+fnv.getKeySearch()+"%");
            ps.setObject(5, "%"+fnv.getKeySearch()+"%");
            ps.setObject(6, "%"+fnv.getKeySearch()+"%");
            ResultSet rs = ps.executeQuery(); // Tra ra du lieu 1 bang 
            while (rs.next()) {
                NhanVienResponse response = NhanVienResponse.builder()
                        // tu cham not cac thuoc tinh 
                        .id(rs.getInt(1))
                        .maNhanVien(rs.getString(2))
                        .tenNhanVien(rs.getString(3))
                        .diaChi(rs.getString(4))
                        .soDienThoai(rs.getString(5))
                        .gioiTinh(rs.getString(6))
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

    public boolean delete(Integer id) {
        String sql = """
              UPDATE [dbo].[NhanVien]
                  SET 
                     [TrangThai] = 0
                WHERE id=?
               """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean add(NhanVienRequest nv) {
        String sql = """
              INSERT INTO NhanVien 
                     (Ma, 
                     Ten,
                     Sdt,
                     GioiTinh,
                     DiaChi,
                     IdCV,
                     TrangThai) 
                     VALUES(?, ?, ?, ?, ?,?,1);
               """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nv.getMa());
            ps.setObject(2, nv.getTen());
            ps.setObject(3, nv.getSdt());
            ps.setObject(4, nv.getGioiTinh());
            ps.setObject(5, nv.getDiaChi());
            ps.setObject(6, nv.getIdCV());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

}
