/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Connect;
import model.ContractModel;
import model.NhanSuModel;

/**
 *
 * @author Windows
 */
public class nhanSuController {
     public List<NhanSuModel> getAll(){
        List<NhanSuModel> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

         try {
            Connect mc = new Connect();
            conn = mc.getConnection();
                  if (conn != null) {
                System.out.println("Kết nối cơ sở dữ liệu thành công");
                stmt = conn.createStatement();
                rs = stmt.executeQuery("SELECT * FROM nhan_vien");

                while (rs.next()) {
                    NhanSuModel nhanSu = new NhanSuModel();
                    nhanSu.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                    nhanSu.setMaSo(rs.getString("ma_so"));
                    nhanSu.setHoTen(rs.getString("ho_ten"));
                    nhanSu.setNgaySinh(rs.getDate("ngay_sinh"));
                    nhanSu.setGioiTinh(rs.getString("gioi_tinh"));
                    nhanSu.setDiaChi(rs.getString("dia_chi"));
                    nhanSu.setSoDienThoai(rs.getString("so_dien_thoai"));
                    nhanSu.setEmail(rs.getString("email"));
                    nhanSu.setTrinhDoHocVan(rs.getString("trinh_do_hoc_van"));
                    nhanSu.setMaPhongBan(rs.getInt("ma_phong_ban"));
                    nhanSu.setMaChucVu(rs.getInt("ma_chuc_vu"));
                    nhanSu.setNgayVaoLam(rs.getDate("ngay_vao_lam"));
                    nhanSu.setTinhTrang(rs.getString("tinh_trang"));
                    list.add(nhanSu);
                }
            } else {
                System.out.println("Kết nối cơ sở dữ liệu thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return list;
    }
     // tìm theo họ và tên
    public List<NhanSuModel> searchByHoTen(String hoTen) {
        List<NhanSuModel> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Connect mc = new Connect();
            conn = mc.getConnection();
            if (conn != null) {
                String query = "SELECT * FROM nhan_vien WHERE ho_ten LIKE ?";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, "%" + hoTen + "%");
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    NhanSuModel nhanSu = new NhanSuModel();
                    nhanSu.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                    nhanSu.setMaSo(rs.getString("ma_so"));
                    nhanSu.setHoTen(rs.getString("ho_ten"));
                    nhanSu.setNgaySinh(rs.getDate("ngay_sinh"));
                    nhanSu.setGioiTinh(rs.getString("gioi_tinh"));
                    nhanSu.setDiaChi(rs.getString("dia_chi"));
                    nhanSu.setSoDienThoai(rs.getString("so_dien_thoai"));
                    nhanSu.setEmail(rs.getString("email"));
                    nhanSu.setTrinhDoHocVan(rs.getString("trinh_do_hoc_van"));
                    nhanSu.setMaPhongBan(rs.getInt("ma_phong_ban"));
                    nhanSu.setMaChucVu(rs.getInt("ma_chuc_vu"));
                    nhanSu.setNgayVaoLam(rs.getDate("ngay_vao_lam"));
                    nhanSu.setTinhTrang(rs.getString("tinh_trang"));
                    list.add(nhanSu);
                }
            } else {
                System.out.println("Kết nối cơ sở dữ liệu thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    // Phương thức mới để lấy danh sách nhân sự theo trạng thái
    public List<NhanSuModel> getByTinhTrang(String selectedStatus) {
        List<NhanSuModel> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Connect mc = new Connect();
            conn = mc.getConnection();
            if (conn != null) {
                stmt = conn.createStatement();
                String query;
                if (selectedStatus.equals("Tất cả trạng thái")) {
                    query = "SELECT * FROM nhan_vien";
                } else {
                    String tinhTrang = selectedStatus.equals("Đang làm việc") ? "Dang_lam" : "Da_nghi";
                    query = "SELECT * FROM nhan_vien WHERE tinh_trang = '" + tinhTrang + "'";
                }
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    NhanSuModel nhanSu = new NhanSuModel();
                    nhanSu.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                    nhanSu.setMaSo(rs.getString("ma_so"));
                    nhanSu.setHoTen(rs.getString("ho_ten"));
                    nhanSu.setNgaySinh(rs.getDate("ngay_sinh"));
                    nhanSu.setGioiTinh(rs.getString("gioi_tinh"));
                    nhanSu.setDiaChi(rs.getString("dia_chi"));
                    nhanSu.setSoDienThoai(rs.getString("so_dien_thoai"));
                    nhanSu.setEmail(rs.getString("email"));
                    nhanSu.setTrinhDoHocVan(rs.getString("trinh_do_hoc_van"));
                    nhanSu.setMaPhongBan(rs.getInt("ma_phong_ban"));
                    nhanSu.setMaChucVu(rs.getInt("ma_chuc_vu"));
                    nhanSu.setNgayVaoLam(rs.getDate("ngay_vao_lam"));
                    nhanSu.setTinhTrang(rs.getString("tinh_trang"));
                    list.add(nhanSu);
                }
            } else {
                System.out.println("Kết nối cơ sở dữ liệu thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
     public boolean insertNhanVienAndContract(NhanSuModel nhanSu, ContractModel contract) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet generatedKeys = null;

        try {
            Connect mc = new Connect();
            conn = mc.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false); // Bắt đầu giao dịch

                // Chuẩn bị các giá trị cho nhan_vien
                String maSo = nhanSu.getMaSo() != null ? "'" + nhanSu.getMaSo() + "'" : "''";
                String hoTen = nhanSu.getHoTen() != null ? "'" + nhanSu.getHoTen() + "'" : "''";
                String gioiTinh = nhanSu.getGioiTinh() != null ? "'" + nhanSu.getGioiTinh() + "'" : "''";
                String ngaySinh = nhanSu.getNgaySinh() != null ? "'" + nhanSu.getNgaySinh().toString() + "'" : "NULL";
                String diaChi = nhanSu.getDiaChi() != null ? "'" + nhanSu.getDiaChi() + "'" : "''";
                String soDienThoai = nhanSu.getSoDienThoai() != null ? "'" + nhanSu.getSoDienThoai() + "'" : "''";
                String email = nhanSu.getEmail() != null ? "'" + nhanSu.getEmail() + "'" : "''";
                String trinhDoHocVan = nhanSu.getTrinhDoHocVan() != null ? "'" + nhanSu.getTrinhDoHocVan() + "'" : "''";
                String maPhongBan = String.valueOf(nhanSu.getMaPhongBan());
                String maChucVu = String.valueOf(nhanSu.getMaChucVu());
                String ngayVaoLam = nhanSu.getNgayVaoLam() != null ? "'" + nhanSu.getNgayVaoLam().toString() + "'" : "NULL";
                String tinhTrang = nhanSu.getTinhTrang() != null ? "'" + nhanSu.getTinhTrang() + "'" : "'Dang_lam'";

                // Câu lệnh SQL cho nhan_vien
                String sqlNhanVien = "INSERT INTO nhan_vien (ma_so, ho_ten, ngay_sinh, gioi_tinh, dia_chi, so_dien_thoai, email, trinh_do_hoc_van, ma_phong_ban, ma_chuc_vu, ngay_vao_lam, tinh_trang) VALUES (" +
                                    maSo + ", " +
                                    hoTen + ", " +
                                    ngaySinh + ", " +
                                    gioiTinh + ", " +
                                    diaChi + ", " +
                                    soDienThoai + ", " +
                                    email + ", " +
                                    trinhDoHocVan + ", " +
                                    maPhongBan + ", " +
                                    maChucVu + ", " +
                                    ngayVaoLam + ", " +
                                    tinhTrang + ")";

                stmt = conn.createStatement();
                stmt.executeUpdate(sqlNhanVien, Statement.RETURN_GENERATED_KEYS);

                // Lấy ma_nhan_vien vừa tạo
                generatedKeys = stmt.getGeneratedKeys();
                int maNhanVien = 0;
                if (generatedKeys.next()) {
                    maNhanVien = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Không thể lấy ma_nhan_vien!");
                }

                // Chuẩn bị các giá trị cho hop_dong
                String loaiHopDong = contract.getLoaiHopDong() != null ? "'" + contract.getLoaiHopDong().name() + "'" : "NULL";
                String ngayBatDau = contract.getNgayBatDau() != null ? "'" + contract.getNgayBatDau().toString() + "'" : "NULL";
                String ngayKetThuc = contract.getNgayKetThuc() != null ? "'" + contract.getNgayKetThuc().toString() + "'" : "NULL";
                String ngayKy = contract.getNgayKy() != null ? "'" + contract.getNgayKy().toString() + "'" : "NULL";
                String trangThai = contract.getTrangThai() != null ? "'" + contract.getTrangThai().name() + "'" : "'Con_hieu_luc'";
                String luongCoBan = contract.getLuongCoBan() != null ? "'" + contract.getLuongCoBan().toString() + "'" : "NULL";

                // Câu lệnh SQL cho hop_dong
                String sqlHopDong = "INSERT INTO hop_dong (ma_nhan_vien, loai_hop_dong, ngay_bat_dau, ngay_ket_thuc, ngay_ky, trang_thai, luong_co_ban) VALUES (" +
                                  maNhanVien + ", " +
                                  loaiHopDong + ", " +
                                  ngayBatDau + ", " +
                                  ngayKetThuc + ", " +
                                  ngayKy + ", " +
                                  trangThai + ", " +
                                  luongCoBan + ")";

                stmt.executeUpdate(sqlHopDong);

                conn.commit(); // Hoàn tất giao dịch
                return true;
            }
        } catch (SQLException e) {
            try {
                if (conn != null) {
                    conn.rollback(); // Hủy giao dịch nếu có lỗi
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (generatedKeys != null) generatedKeys.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
     public boolean updateNhanVien(NhanSuModel nhanSu) {
        try {
            Connect mc = new Connect();
            Connection conn = mc.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "UPDATE nhan_vien SET " +
                        "ho_ten = '" + nhanSu.getHoTen() + "', " +
                        "ngay_sinh = '" + nhanSu.getNgaySinh() + "', " +
                        "gioi_tinh = '" + nhanSu.getGioiTinh() + "', " +
                        "dia_chi = '" + nhanSu.getDiaChi() + "', " +
                        "so_dien_thoai = '" + nhanSu.getSoDienThoai() + "', " +
                        "email = '" + nhanSu.getEmail() + "', " +
                        "trinh_do_hoc_van = '" + nhanSu.getTrinhDoHocVan() + "', " +
                        "ma_phong_ban = " + nhanSu.getMaPhongBan() + ", " +
                        "ma_chuc_vu = " + nhanSu.getMaChucVu() + ", " + 
                        "ngay_vao_lam = '" + nhanSu.getNgayVaoLam() + "', " +
                        "tinh_trang = '" + nhanSu.getTinhTrang() + "' " +
                        "WHERE ma_nhan_vien = " + nhanSu.getMaNhanVien();

            int result = stmt.executeUpdate(sql);
            conn.close();

            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
     
    }
     public void delete(int id) {
        try {
           Connect mc = new Connect();
           Connection conn = mc.getConnection();
           Statement stmt = conn.createStatement();
             String sql = "DELETE FROM nhan_vien WHERE ma_nhan_vien = " + id;
             int result = stmt.executeUpdate(sql);
             if (result > 0) {
                    System.out.println("Xóa thành công user có id = " + id);
                } else {
                    System.out.println("Không tìm thấy user để xóa.");
                }
             conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
    public List<NhanSuModel> getNhanVienMoi6Thang() {
        List<NhanSuModel> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Connect mc = new Connect();
            conn = mc.getConnection();
            if (conn != null) {
                // Tính ngày cách hiện tại 6 tháng
                LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
                Date sqlDate = Date.valueOf(sixMonthsAgo);

                String query = "SELECT * FROM nhan_vien WHERE ngay_vao_lam >= ?";
                pstmt = conn.prepareStatement(query);
                pstmt.setDate(1, sqlDate);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    NhanSuModel nhanSu = new NhanSuModel();
                    nhanSu.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                    nhanSu.setMaSo(rs.getString("ma_so"));
                    nhanSu.setHoTen(rs.getString("ho_ten"));
                    nhanSu.setNgaySinh(rs.getDate("ngay_sinh"));
                    nhanSu.setGioiTinh(rs.getString("gioi_tinh"));
                    nhanSu.setDiaChi(rs.getString("dia_chi"));
                    nhanSu.setSoDienThoai(rs.getString("so_dien_thoai"));
                    nhanSu.setEmail(rs.getString("email"));
                    nhanSu.setTrinhDoHocVan(rs.getString("trinh_do_hoc_van"));
                    nhanSu.setMaPhongBan(rs.getInt("ma_phong_ban"));
                    nhanSu.setMaChucVu(rs.getInt("ma_chuc_vu"));
                    nhanSu.setNgayVaoLam(rs.getDate("ngay_vao_lam"));
                    nhanSu.setTinhTrang(rs.getString("tinh_trang"));
                    list.add(nhanSu);
                }
            } else {
                System.out.println("Kết nối cơ sở dữ liệu thất bại");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    public List<NhanSuModel> getAllBaoCao() {
        List<NhanSuModel> list = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Connect mc = new Connect();
            conn = mc.getConnection();
            if (conn != null) {
                System.out.println("Kết nối cơ sở dữ liệu thành công");
                stmt = conn.createStatement();
                // Thực hiện join với bảng phong_ban để lấy tên phòng ban
                String query = "SELECT nv.*, pb.ten_phong_ban " +
                               "FROM nhan_vien nv " +
                               "LEFT JOIN phong_ban pb ON nv.ma_phong_ban = pb.ma_phong_ban";
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    NhanSuModel nhanSu = new NhanSuModel();
                    nhanSu.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                    nhanSu.setMaSo(rs.getString("ma_so"));
                    nhanSu.setHoTen(rs.getString("ho_ten"));
                    nhanSu.setNgaySinh(rs.getDate("ngay_sinh"));
                    nhanSu.setGioiTinh(rs.getString("gioi_tinh"));
                    nhanSu.setDiaChi(rs.getString("dia_chi"));
                    nhanSu.setSoDienThoai(rs.getString("so_dien_thoai"));
                    nhanSu.setEmail(rs.getString("email"));
                    nhanSu.setTrinhDoHocVan(rs.getString("trinh_do_hoc_van"));
                    nhanSu.setMaPhongBan(rs.getInt("ma_phong_ban"));
                    nhanSu.setMaChucVu(rs.getInt("ma_chuc_vu"));
                    nhanSu.setNgayVaoLam(rs.getDate("ngay_vao_lam"));
                    nhanSu.setTinhTrang(rs.getString("tinh_trang"));
                    nhanSu.setTenPhongBan(rs.getString("ten_phong_ban")); // Điền tên phòng ban
                    list.add(nhanSu);
                }
            } else {
                System.out.println("Kết nối cơ sở dữ liệu thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
