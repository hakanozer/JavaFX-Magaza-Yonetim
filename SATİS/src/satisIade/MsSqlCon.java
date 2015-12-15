package satisiptal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class MsSqlCon {

    String userName = "sa";
    String password = "12345";
    String dbName = "mavi;";
    String url = "jdbc:sqlserver://WISSENSC104-14\\SQLEXPRESS;databaseName=";
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    Connection conn = null;
    Statement st = null;
    ResultSet rs;
            
    // PreparedStatement kullanımı
    PreparedStatement prSt = null;
    
    public MsSqlCon () {
        try {
            if(conn ==  null) {
                Class.forName(driver);
                conn = DriverManager.getConnection(url+dbName, userName, password);
                st = conn.createStatement();
                
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Bağlantı Hatası : " + e);
        }
        
    }
    
   
    public void kapat(){
        try {
            conn.close();
            st.close();
        } catch (Exception e) {
            System.err.println("Bağlantı Kapatma Hatası :" + e);
        }
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
