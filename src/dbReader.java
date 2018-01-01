import java.sql.*;
import java.util.ArrayList;

public class dbReader {

    private Connection conn;

    public dbReader (String filename) throws empException
    {
        String url = "jdbc:sqlite:" + filename;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new empException("ERROR. Initializing class dbReader");
        }
    }

    private static String getExtension(String filename)
    {
        int pos = filename.lastIndexOf(".");
        if(pos < 0) return "";
        return filename.substring(pos+1);
    }

    public void insertData(int id, String name, String surname, String profilePic) throws empException
    {
        String sql = "INSERT INTO user(id, name, surname, profilePic, extension) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);

            byte profilePicBlob[] = profilePic.isEmpty() ? null : blib.createBlob(profilePic);

            pstm.setInt(1, id);
            pstm.setString(2, name);
            pstm.setString(3, surname);
            pstm.setBytes(4, profilePicBlob);
            pstm.setString(5, getExtension(profilePic));

            pstm.executeUpdate();
        } catch (SQLException SQLError) {
            throw new empException("ERROR. Inserting values into database");
        }
    }

    public ArrayList<user> readAllData() throws empException
    {
        ArrayList<user> userList = new ArrayList<>();
        try {
            Statement stm = conn.createStatement();
            String query = "SELECT * FROM user";
            ResultSet rs = stm.executeQuery(query);

            while(rs.next())
            {
                userList.add(new user(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBytes("profilePic"),
                        rs.getString("extension")));
            }

        } catch (SQLException e) {
            throw new empException("ERROR. Querying database");
        }

        return userList;
    }

    public void deleteUser(int id) throws empException
    {
        String sql = "DELETE FROM user WHERE id=" + id;
        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new empException("ERROR. Trying to delete user.");
        }
    }
}
