package in.sp.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.sp.dbconnection.DBConnection;

public class AdminRepository {

    public String getAdminPasswordByEmail(String email) throws Exception {

        PreparedStatement ps = DBConnection.getInstance()
                .getConnection()
                .prepareStatement(
                        "SELECT password FROM admins WHERE email=?"
                );

        ps.setString(1, email);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getString("password");
        }

        return null;
    }
}