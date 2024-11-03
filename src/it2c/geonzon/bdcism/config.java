package it2c.geonzon.bdcism;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class config {

    public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC"); // Load the SQLite JDBC driver
            con = DriverManager.getConnection("jdbc:sqlite:bcdis_db.db"); // Establish connection
            System.out.println("\n");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e);
        }
        return con;
    }
     public void add(String sql, String... values) {
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

       
            for (int i = 0; i < values.length; i++) {
                pstmt.setString(i + 1, values[i]); 
            }

            pstmt.executeUpdate();
            System.out.println("Record added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }

    public void view(String sqlQuery, String[] columnHeaders, String[] columnNames) {
        // Check that columnHeaders and columnNames arrays are the same length
        if (columnHeaders.length != columnNames.length) {
            System.out.println("Error: Mismatch between column headers and column names.");
            return;
        }

        try (Connection conn = config.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
             ResultSet rs = pstmt.executeQuery()) {

            // Print the headers dynamically
            StringBuilder headerLine = new StringBuilder();
            headerLine.append("---------------------------------------------------------------------------------------------------------------\n| ");
            for (String header : columnHeaders) {
                headerLine.append(String.format("%-20s | ", header)); // Adjust formatting as needed
            }
            headerLine.append("\n---------------------------------------------------------------------------------------------------------------");

            System.out.println(headerLine.toString());

            // Print the rows dynamically based on the provided column names
            while (rs.next()) {
                StringBuilder row = new StringBuilder("| ");
                for (String colName : columnNames) {
                    String value = rs.getString(colName);
                    row.append(String.format("%-20s | ", value != null ? value : "")); // Adjust formatting
                }
                System.out.println(row.toString());
            }
            System.out.println("---------------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Error retrieving records: " + e.getMessage());
        }
    }
     public void update(String sql, Object... values) {
        try (Connection conn = this.connectDB(); // Use the connectDB method
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Loop through the values and set them in the prepared statement dynamically
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) values[i]); // If the value is Integer
                } else if (values[i] instanceof Double) {
                    pstmt.setDouble(i + 1, (Double) values[i]); // If the value is Double
                } else if (values[i] instanceof Float) {
                    pstmt.setFloat(i + 1, (Float) values[i]); // If the value is Float
                } else if (values[i] instanceof Long) {
                    pstmt.setLong(i + 1, (Long) values[i]); // If the value is Long
                } else if (values[i] instanceof Boolean) {
                    pstmt.setBoolean(i + 1, (Boolean) values[i]); // If the value is Boolean
                } else if (values[i] instanceof java.util.Date) {
                    pstmt.setDate(i + 1, new java.sql.Date(((java.util.Date) values[i]).getTime())); // If the value is Date
                } else if (values[i] instanceof java.sql.Date) {
                    pstmt.setDate(i + 1, (java.sql.Date) values[i]); // If it's already a SQL Date
                } else if (values[i] instanceof java.sql.Timestamp) {
                    pstmt.setTimestamp(i + 1, (java.sql.Timestamp) values[i]); // If the value is Timestamp
                } else {
                    pstmt.setString(i + 1, values[i].toString()); // Default to String for other types
                }
            }

            pstmt.executeUpdate();
            System.out.println("Record updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    
}
     
// Add this method in the config class
public void delete(String sql, Object... values) {
    try (Connection conn = this.connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Loop through the values and set them in the prepared statement dynamically
        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof Integer) {
                pstmt.setInt(i + 1, (Integer) values[i]); // If the value is Integer
            } else {
                pstmt.setString(i + 1, values[i].toString()); // Default to String for other types
            }
        }

        pstmt.executeUpdate();
        System.out.println("Record deleted successfully!");
    } catch (SQLException e) {
        System.out.println("Error deleting record: " + e.getMessage());
    }
    }
    
    //citizen id  checker
   public boolean citIdExists(String citizenId) {
    String sql = "SELECT COUNT(*) FROM tbl_citizen WHERE ctzn_id = ?";
    try (Connection conn = connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, citizenId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0; 
        }
    } catch (SQLException e) {
        System.out.println("Error checking citizen ID: " + e.getMessage());
    }
    return false; 
}
   //request id checker
   public boolean reqIdExists(String requestId) {
    String sql = "SELECT COUNT(*) FROM tbl_request WHERE req_id = ?";
    try (Connection conn = connectDB();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, requestId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (SQLException e) {
        System.out.println("Error checking request ID: " + e.getMessage());
    }
    return false; 
}

public ResultSet getData(String sql) {
    ResultSet rst = null;
    try {
        Connection conn = connectDB();
        Statement stmt = conn.createStatement();
        rst = stmt.executeQuery(sql);
    } catch (SQLException e) {
        System.out.println("Error retrieving data: " + e.getMessage());
    }
    return rst;
}

public void upnotif(String requestId, String status) {
    String updateQuery = "UPDATE tbl_request SET req_status = ? WHERE req_id = ?";
    String selectQuery = "SELECT * FROM tbl_request r INNER JOIN tbl_citizen z ON r.ctzn_id  = z.ctzn_id WHERE req_id = ?";
    String insertQuery = "INSERT INTO tbl_notif (logs) VALUES (?)";

    try (Connection conn = connectDB();
         PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
         PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
         PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

        conn.setAutoCommit(false);

        selectStmt.setString(1, requestId);
        ResultSet rs = selectStmt.executeQuery();

        String docType = null;
        String fname = null;
        String lname = null;
        if (rs.next()) {
            docType = rs.getString("doc_type");
            fname = rs.getString("f_name");
            lname = rs.getString("l_name");
        }

        updateStmt.setString(1, status);
        updateStmt.setString(2, requestId);
        updateStmt.executeUpdate();

        String reqq = "Your Document Request ID " + requestId + " '" + docType + "' "+ "of "+ fname +" "+lname + " has been " + status;

        insertStmt.setString(1, reqq);
        insertStmt.executeUpdate();

        conn.commit();
        System.out.println("Updated Succesfuly \n");
    } catch (SQLException e) {
        System.out.println("Error during transaction: " + e.getMessage());
     
    }
}


}



