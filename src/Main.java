import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pr = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try{
            conn = DB.conectedDB();
            /*pr = conn.prepareStatement("INSERT INTO seller"
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES"
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            pr.setString(1, "Carl Purple");
            pr.setString(2, "Carl@gmail.com");
            pr.setDate(3, new java.sql.Date(sdf.parse("27/01/2001").getTime()));
            pr.setDouble(4, 7200.00);
            pr.setInt(5, 4);
            */

            pr = conn.prepareStatement("INSERT INTO department (Name) VALUES ('D1'), ('D2')",
                    Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = pr.executeUpdate();

            if (rowsAffected > 0 ){
                ResultSet rs = pr.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! ID= " + id);
                }
            }else {
                System.out.println("No rows affected");
            }



        }//catch (SQLException | ParseException e){
          //  throw new DbException(e.getMessage());
       // }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStantment(pr);
            DB.closePreparedStatement(pr);
            DB.closeConnection();
        }
    }
}