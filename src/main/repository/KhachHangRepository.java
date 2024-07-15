/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.repository;

import java.util.ArrayList;
import main.entity.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.config.DBConnect;

/**
 *
 * @author hangnt
 */
public class KhachHangRepository {

    //CRUD 
    public ArrayList<KhachHang> getAll() {
        // B1: Tao ra doi tuong ArrayList 
        ArrayList<KhachHang> lists = new ArrayList<>();
        // Code 
        // B2: Tao SQL 
        String sql = """
                     SELECT [Id]
                           ,[Ma]
                           ,[Ten]
                           ,[TenDem]
                           ,[Ho]
                           ,[NgaySinh]
                           ,[Sdt]
                           ,[DiaChi]
                           ,[ThanhPho]
                           ,[QuocGia]
                           ,[MatKhau]
                           ,[TrangThai]
                           ,[GioiTinh]
                       FROM [dbo].[KhachHang]
                     WHERE [TrangThai] = 1
                     """;
        // Xoa mem => Chi xoa truoc mat nguoi dung trong CSDL => Van con 
        // => PK : Khoa ngoai
        // B2: Ket noi vs SQL 
        // try..with resource
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery(); // Tra ra du lieu 1 bang 
            while (rs.next()) {
                // contructor khong tham so
                KhachHang kh = KhachHang.builder()
                        .id(rs.getInt(1))
                        .ma(rs.getString(2))
                        .ten(rs.getString(3))
                        .tenDem(rs.getString(4))
                        .ho(rs.getString(5))
                        .ngaySinh(rs.getDate(6))
                        .sdt(rs.getString(7))
                        .diaChi(rs.getString(8))
                        .thanhPho(rs.getString(9))
                        .quocGia(rs.getString(10))
                        .matKhau(rs.getString(11))
                        .trangThai(rs.getInt(12))
                        .gioiTinh(rs.getBoolean(13))
                        .build();
                // add vao list 
                lists.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }

    public boolean delete(Integer id) {
        String sql = """
              UPDATE [dbo].[KhachHang]
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

    public boolean add(KhachHang kh) {
        String sql = """
                 INSERT INTO [dbo].[KhachHang]
                            ([Ma]
                            ,[Ten]
                            ,[TenDem]
                            ,[Ho]
                            ,[NgaySinh]
                            ,[Sdt]
                            ,[DiaChi]
                            ,[ThanhPho]
                            ,[QuocGia]
                            ,[MatKhau]
                            ,[TrangThai]
                            ,[GioiTinh])
                      VALUES
                            (?,?,?,?,?,?,?,?,?,?,1,?)
               """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kh.getMa());
            ps.setObject(2, kh.getTen());
            ps.setObject(3, kh.getTenDem());
            ps.setObject(4, kh.getHo());
            ps.setObject(5, kh.getNgaySinh());
            ps.setObject(6, kh.getSdt());
            ps.setObject(7, kh.getDiaChi());
            ps.setObject(8, kh.getThanhPho());
            ps.setObject(9, kh.getQuocGia());
            ps.setObject(10, kh.getMatKhau());
            ps.setObject(11, kh.getGioiTinh());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean update(KhachHang kh, Integer id) {
        String sql = """
                UPDATE [dbo].[KhachHang]
                    SET [Ma] = ?
                       ,[Ten] = ?
                       ,[Sdt] = ?
                       ,[DiaChi] = ?
                       ,[ThanhPho] = ?
                       ,[QuocGia] = ?
                       ,[GioiTinh] = ?
                  WHERE ID = ?
               """;
        int check = 0;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kh.getMa());
            ps.setObject(2, kh.getTen());
            ps.setObject(3, kh.getSdt());
            ps.setObject(4, kh.getDiaChi());
            ps.setObject(5, kh.getThanhPho());
            ps.setObject(6, kh.getQuocGia());
            ps.setObject(7, kh.getGioiTinh());
            ps.setObject(8, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
}
