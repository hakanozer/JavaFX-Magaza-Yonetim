package SatisEkrani;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class DB {

    String userName = "sa";
    String password = "123456";
    String dbName = "mavi;";
    String url = "jdbc:sqlserver://GAMZEKUCUKCOLAK;databaseName=";
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    ResultSet rs;
    Connection conn = null;
    Statement st = null;

    // PreparedStatement kullanımı
    PreparedStatement prSt = null;

    public DB() {
        try {
            if (conn == null) {
                Class.forName(driver);
                conn = DriverManager.getConnection(url + dbName, userName, password);
                st = conn.createStatement();
                System.out.println("Bağlantı Başarılı");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Bağlantı Hatası : " + e);
        }

    }

    // bağlantı kapat
    public void kapat() {
        try {
            conn.close();
            st.close();
        } catch (Exception e) {
            System.err.println("Bağlantı Kapatma Hatası :" + e);
        }
    }

    // özel bağlantı kurucu methodu
    public DB(String dbName, String userName, String password) {
        this.dbName = dbName;
        this.userName = userName;
        this.password = password;
        try {
            if (conn == null) {
                Class.forName(driver);
                conn = DriverManager.getConnection(url + dbName, userName, password);
                st = conn.createStatement();
                System.out.println("Bağlantı Başarılı");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Bağlantı Hatası : " + e);
        }
    }

    // procedure fonksiyonu
    public ResultSet proGetir(String proName) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery("call " + proName);
        } catch (Exception ex) {
            System.err.println("Pro Getirme Hatası : " + ex);
        }
        return rs;
    }

    CallableStatement stmt;

    // procedure PreparedStatement kullanımı

    public ResultSet preProGetir(String proName, String ara) {
        ResultSet rs = null;
        try {
            stmt = conn.prepareCall("{ Call " + proName + "(?) }");
            stmt.setString(1, ara);
            rs = stmt.executeQuery();
        } catch (Exception ex) {
            System.err.println("Pro Getirme Hatası : " + ex);
        }
        return rs;
    }

//    public ResultSet updateTerziDurum(String proName, int terziDurum , int  ){
//        ResultSet rs = null;
//        try {
//            stmt = conn.prepareCall("{ Call " + proName + "(?) }");
//            stmt.setString(1, ara);
//            rs = stmt.executeQuery();
//        } catch (Exception ex) {
//            System.err.println("Pro Getirme Hatası : "+ ex);
//        }
//        return rs;
//    }
    public ResultSet sepetEkle(String proName, String refCode, int perID, String barkod) {
        ResultSet rs = null;
        try {
            stmt = conn.prepareCall("{ Call " + proName + "(?,?,?) }");
            stmt.setString(1, refCode);
            stmt.setInt(2, perID);
            stmt.setString(3, barkod);
            rs = stmt.executeQuery();
        } catch (Exception ex) {
            System.err.println("Sepet Ekleme Hatası : " + ex);
        }
        return rs;
    }

    //data ekleme
    public int dataEkle(String tableName, String[] dizi) {
        int sonuc = -1;
        try {
            String sorgu = "insert into " + tableName + " values('" + dizi[0] + "'";
            for (int i = 1; i < dizi.length; i++) {
                sorgu += ",'" + dizi[i] + "'";
            }
            sorgu += ")";
            sonuc = st.executeUpdate(sorgu);
        } catch (Exception e) {
            System.err.println("Ekleme hatası : " + e);
        }
        return sonuc;
    }

    //MSSQL fonksiyon tetiklenmesi
    public int fncTetikle() {
        int sonuc = 0;
        try {
            stmt = conn.prepareCall("{? = call islem(1,2)}");
            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.executeQuery();
            sonuc = stmt.getInt(1);
        } catch (Exception e) {
            System.err.println("Fonksiyon Tetiklenme Hatası : " + e);
        }
        return sonuc;
    }

    public ResultSet dataGetir() {
        ResultSet rs = null;
        try {
//            where barkodNo = " +"'"+ barkodNo+"' ----- String barkodNo
            rs = st.executeQuery("Select * from urunler");
        } catch (Exception e) {
            System.out.println("Data Getir Hatası :  " + e);
        }

        return rs;
    }

    public ResultSet barkodKontrol(String barkodNo) {
        ResultSet rs = null;
        try {
            rs = st.executeQuery("select barkodNo from urunler where  barkodNo = '" + barkodNo + "'");
        } catch (Exception e) {
            System.out.println("Barkod Kontrol Hatası : " + e);
        }

        return rs;
    }

    
     
    public void urunIadeYap(String urunID,String faturaNo, String fiyat)
    {
        try {
            
            procedurFatura = conn.prepareCall("{ Call urunIadeIslemi(?,?,?) }");
            procedurFatura.setString(1, faturaNo);
            procedurFatura.setString(2, urunID);
            procedurFatura.setString(3, fiyat);
            procedurFatura.executeQuery();
             
        } catch (Exception e) {
        }
        
    
    
    }
    
     CallableStatement procedurFatura;
    public ResultSet faturaGetir(String faturaNo)
    { 
        try {
            procedurFatura = conn.prepareCall("{ Call faturaUrunleriniGetir(?) }");
            procedurFatura.setString(1, faturaNo);
            rs = procedurFatura.executeQuery();
            

        } catch (Exception e) {
        }
    return rs;
    }
    CallableStatement procedurKGrisi;
    String admin="admin";
    public ResultSet yoneticiGirisi(String kullaniciAdi,String sifresi)
    {
        try {
            procedurKGrisi = conn.prepareCall("{ Call kullaniciGiris(?,?,?) }");
            procedurKGrisi.setString(1, kullaniciAdi);
            procedurKGrisi.setString(2, sifresi);
            procedurKGrisi.setString(3, admin);
            rs=procedurKGrisi.executeQuery();
            
        } catch (Exception e) {
        }
            
    return rs;
    }
    
    
    
    
    
    
    
    
}
