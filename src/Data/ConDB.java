package Data;
import java.sql.*;
public class ConDB {
	public  static String ResultStream() throws SQLException, ClassNotFoundException {
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://192.168.200.139:3306/GSXYJG";
        //MySQL配置时的用户名
        String user = "preps";
        //MySQL配置时的密码
        String password = "preps_rsgis_lmars_222";
        //遍历查询结果集
            Class.forName(driver);
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            Statement statement = con.createStatement();
            String sql = "select F_BD_LONGITUDE,F_BD_LATITUDE from KEY_ENTITY_INFO";
            ResultSet rs = statement.executeQuery(sql); 
			String json = Data2Jason.resultSetToJson(rs);
            rs.close();
            return json;
    }
	/*public static void main(String[] args) throws SQLException, ClassNotFoundException {
		String rstream = ConDB.ResultStream();
	    System.out.println(rstream);
	}*/
}
