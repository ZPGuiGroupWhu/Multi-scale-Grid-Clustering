package Data;
import java.sql.*;
public class ConDB {
	public  static String ResultStream() throws SQLException, ClassNotFoundException {
        //����Connection����
        Connection con;
        //����������
        String driver = "com.mysql.jdbc.Driver";
        //URLָ��Ҫ���ʵ����ݿ���mydata
        String url = "jdbc:mysql://192.168.200.139:3306/GSXYJG";
        //MySQL����ʱ���û���
        String user = "preps";
        //MySQL����ʱ������
        String password = "preps_rsgis_lmars_222";
        //������ѯ�����
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
