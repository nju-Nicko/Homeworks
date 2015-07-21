package edu.nju.Homeworks.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoHelperImpl implements DaoHelper{
	
	@Override
	public Connection getConnection(){
		Connection c=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost:3306/homeworks", "root", "2014nlz@nju");
		}catch(Exception e){
			e.printStackTrace();
		}
		return c;
	}
	
	@Override
	public void closeConnection(Connection c){
		if(c!=null)
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void closePreparedStatement(PreparedStatement stmt) {
		// TODO Auto-generated method stub
		if(stmt!=null)
		{
			try
			{
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closeResultSet(ResultSet result) {
		// TODO Auto-generated method stub
		if(result!=null)
		{
			try
			{
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
