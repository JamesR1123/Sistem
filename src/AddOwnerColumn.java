import Config.Conf;
import java.sql.Connection;
import java.sql.Statement;

public class AddOwnerColumn {
    public static void main(String[] args) {
        try {
            Conf db = new Conf();
            Connection conn = db.connectDB();
            Statement stmt = conn.createStatement();
            
            String sql = "ALTER TABLE tbl_Condo ADD COLUMN owner_id INTEGER REFERENCES tbl_Users(U_id)";
            stmt.execute(sql);
            
            System.out.println("owner_id column added successfully to tbl_Condo!");
            
            conn.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Note: If column already exists, you can ignore this error.");
        }
    }
}
