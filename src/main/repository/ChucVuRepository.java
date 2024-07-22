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
import main.entity.ChucVu;
import main.response.NhanVienResponse;

/**
 *
 * @author lucvanluat
 */
public class ChucVuRepository {
    
    public ArrayList<ChucVu> getAll() {
        // B1: Tao ra doi tuong ArrayList 
        ArrayList<ChucVu> lists = new ArrayList<>();
        // Code 
        // B2: Tao SQL 
        String sql = """
                     SELECT Id, Ma, Ten FROM ChucVu;
                     """;

        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery(); // Tra ra du lieu 1 bang 
            while (rs.next()) {
                 lists.add(new ChucVu(rs.getInt(1),
                                      rs.getString(2),
                                     rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return lists;
    }
    
}
