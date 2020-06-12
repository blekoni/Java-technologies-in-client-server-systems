package first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CurrencyWS {

	
	public List<String> getCoeficient(String currency1,String currency2)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306//java;create=true;user=root;password=root");
			ResultSet rs = con.createStatement().executeQuery("Select coeficient,"
					+ "bank_name from currency where type1='"+currency1+"' and type2='"+currency2+"'");
			List<String> bankInfo = new ArrayList<String>();
			while(rs.next()) 
			{
				bankInfo.add("Bank : "+rs.getString(2)+" currency: "+rs.getDouble(1));
			}	
			rs.close();
			con.close();
			return bankInfo;
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
}
