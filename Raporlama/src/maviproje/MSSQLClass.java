package maviproje;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MSSQLClass {

    Connection con = null;
    Statement st;
    ResultSet rs;
    CallableStatement cs;

    String useName = "sa";
    String password = "12345";
    String dbName = "mavi;";
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://localhost:1433;databaseName=";

    public MSSQLClass() {
        try {
            if (con == null) {
                Class.forName(driver);
                con = DriverManager.getConnection(url + dbName, useName, password);
                st = con.createStatement();
                System.out.println("baglandı...");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("baglantı hatası: " + e);
        }
    }

    //Özel baglantı Constructure
    public MSSQLClass(String dbName, String userName, String password) {

        this.dbName = dbName;
        this.useName = userName;
        this.password = password;

        try {
            if (con == null) {
                Class.forName(driver);
                con = DriverManager.getConnection(url + dbName, useName, password);
                st = con.createStatement();
                System.out.println("baglandı...");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("baglantı hatası: " + e);
        }
    }

    public ResultSet preProGetir(String proName) {
        try {
            cs = con.prepareCall("{call " + proName + "()}");
            rs = cs.executeQuery();
        } catch (Exception e) {
            System.err.println("preProGetir hatası: " + e);
        }
        return rs;
    }

    public ResultSet preTarihGetir(String proName, String d1, String d2) {
        try {
            cs = con.prepareCall("{call " + proName + "(?,?)}");
            cs.setString(1, d1);
            cs.setString(2, d2);
            rs = cs.executeQuery();
        } catch (Exception e) {
            System.err.println("preProGetir hatası: " + e);
        }
        return rs;
    }

    public void createReport() {
        /* JasperReportBuilder report = DynamicReports.report();//a new report
         report
         .columns(
         Columns.column("Customer Id", "id", DataTypes.integerType()),
         Columns.column("First Name", "first_name", DataTypes.stringType()),
         Columns.column("Last Name", "last_name", DataTypes.stringType()),
         Columns.column("Date", "date", DataTypes.dateType()))
         .title(//title of the report
         Components.text("SimpleReportExample")
         .setHorizontalAlignment(HorizontalAlignment.CENTER))
         .pageFooter(Components.pageXofY())//show page number on the page footer
         .setDataSource("SELECT id, first_name, last_name, date FROM customers", 
         connection);

         try {
         //show the report
         report.show();

         //export the report to a pdf file
         report.toPdf(new FileOutputStream("report.pdf"));
         } catch (DRException e) {
         e.printStackTrace();
         }*/
    }
}
