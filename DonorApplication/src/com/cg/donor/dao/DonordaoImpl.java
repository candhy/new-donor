package com.cg.donor.dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cg.donor.bean.DonorBean;
import com.cg.donor.exception.DonorException;
import com.cg.donor.util.dbConnection;

public class DonordaoImpl implements IDonorDao{

 
	
  @Override
  public String addDonor(DonorBean donor) throws DonorException, ClassNotFoundException, IOException, SQLException {
		Connection connection=dbConnection.getconnection();
		ResultSet resultset=null;
		PreparedStatement preparedstatement=null;
		String donorid=null;
		int queryresult=0;
		
		try
		{
			preparedstatement=connection.prepareStatement("insert into Donor_Details values(donorId_sequence.nextVal,?,?,?,Sysdate,?)");
			preparedstatement.setString(1, donor.getDonorname());//that 1 represent the first question mark,
			preparedstatement.setString(2,donor.getAddress() );
			preparedstatement.setString(3, donor.getPhonenumber());
			preparedstatement.setDouble(4, donor.getDonorAmount());
			preparedstatement.executeUpdate();
			Statement st=null;
			st=connection.createStatement();
			resultset=st.executeQuery("select * from Donor_Details");
			while(resultset.next())
				{ 
				donorid=resultset.getString(1);
				}
			                       /*donorid=resultset.getString(2);
			                        donorid=resultset.getString(3);
			                        donorid=resultset.getString(4);
			                        donorid=resultset.getString(5);*/
			                                                       
			}
	  catch(Exception sql)
		    {
			System.out.println(sql);
			sql.printStackTrace();
		    }
		
		    return donorid;
      }

	
	
	
	@Override
	public DonorBean viewDonorDetails(String donorid) throws DonorException, SQLException, ClassNotFoundException, IOException {
		Connection connection=dbConnection.getconnection();
		ResultSet resultset=null;
		Statement st=null;
		DonorBean donorBean=new DonorBean();
		st=connection.createStatement();
		resultset=st.executeQuery("select *from donor_details where donor_id="+donorid+"");
		
		while(resultset.next())
		{
		donorBean.setDonorid(resultset.getString(1));
		donorBean.setDonorname(resultset.getString(2));
		donorBean.setAddress(resultset.getString(3));
		donorBean.setPhonenumber(resultset.getString(4));
		donorBean.setDonationdate(resultset.getDate(5));
		donorBean.setDonorAmount(resultset.getDouble(6));
		}
		return donorBean;
	}

	
	
	@Override
	public List retrieveAll() throws DonorException, ClassNotFoundException, IOException, SQLException {

		Connection connection=dbConnection.getconnection();
		int donorCount = 0;
		
		PreparedStatement ps=null;
		ResultSet resultset = null;
		
		List<DonorBean> donorList=new ArrayList<DonorBean>();
		try
		{
			ps=connection.prepareStatement("SELECT * FROM donor_details");
			resultset=ps.executeQuery();
			
			while(resultset.next())
			{	
				DonorBean donorbean=new DonorBean();
				donorbean.setDonorid(resultset.getString(1));
				donorbean.setDonorname(resultset.getString(2));
				donorbean.setAddress(resultset.getString(3));
				donorbean.setPhonenumber(resultset.getString(4));
				donorbean.setDonationdate(resultset.getDate(5));
				donorbean.setDonorAmount(resultset.getDouble(6));
				donorList.add(donorbean);
				
				donorCount++;
			}			
			
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			throw new DonorException("Tehnical problem occured. Refer log");
		}
		
		finally
		{
			try 
			{
				resultset.close();
				ps.close();
				connection.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				throw new DonorException("Error in closing db connection");

			}
		}
		
		if( donorCount == 0)
			return null;
		else
			return donorList;
	
		

}
}