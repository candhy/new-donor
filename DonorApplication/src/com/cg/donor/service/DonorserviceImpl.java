package com.cg.donor.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.donor.bean.DonorBean;
import com.cg.donor.dao.DonordaoImpl;
import com.cg.donor.dao.IDonorDao;
import com.cg.donor.exception.DonorException;

public class DonorserviceImpl implements IDonorService {
	IDonorDao donordao=new DonordaoImpl();

	
	@Override
	public String addDonor(DonorBean donor) throws DonorException, ClassNotFoundException, IOException, SQLException {
        String donorseq;
        donorseq=donordao.addDonor(donor);
		return donorseq;
	}
	@Override
	public DonorBean viewDonorDetails(String donorid) throws DonorException, ClassNotFoundException, SQLException, IOException {
		System.out.println(donorid);
		DonorBean donorBean=new DonorBean();
		 donorBean=donordao.viewDonorDetails(donorid);
		// System.out.println(donorbean);
		return donorBean;
	}
	@Override
	public List retrieveAll() throws DonorException, ClassNotFoundException, IOException, SQLException {
		donordao=new DonordaoImpl();
		List<DonorBean> donorList=null;
		donorList=donordao.retrieveAll();
		return donorList;
		
	}


public boolean validateDonor(DonorBean bean)throws DonorException
{
	List<String> validationErrors=new ArrayList<String>();
	
	
	if(!(isValidName(bean.getDonorname())))
	{
		validationErrors.add("\\nDonor name should be Alphabets and minimum 3 characters long!\\n");
	}
	if(!(isValidAddress(bean.getAddress())))
	{
		validationErrors.add("\\n Address should be greater than 5 characters!\\n");
	}
	if(!(isValidphonenumber(bean.getPhonenumber())))
	{
		validationErrors.add("\\nphone number should be in 10 digits");
	}
	if(!(isValidAmount(bean.getDonorAmount())))
	{
		validationErrors.add("\\n Amount should be positive");
	}
	/*if(!(isValidID(bean.getDonorid())))
	{
		validationErrors.add("\\nID should be in 4 numbers");
	}*/
	
	if(!validationErrors.isEmpty())
	{
		//throw new DonorException(validationErrors +"");
		return false;
		
}return true;
}
/*private boolean isValidID(String donorid) {
	Pattern idPattern=Pattern.compile("[0-9]{1,4}");
	Matcher idMatcher=idPattern.matcher(donorid);
    if(idMatcher.matches())
     {
	return true;
    }else
	return false;
    }*/


private boolean isValidName(String donorname) {
	Pattern namePattern=Pattern.compile("^[A-Za-z]{3,}$");
	Matcher nameMatcher=namePattern.matcher(donorname);
	return nameMatcher.matches();
     }


private boolean isValidAmount(double donorAmount) {
	return donorAmount > 0;
	// TODO Auto-generated method stub
	
}

private boolean isValidAddress(String address) {
	// TODO Auto-generated method stub
	return (address.length()>6);
	
}

private boolean isValidphonenumber(String phonenumber) {
	// TODO Auto-generated method stub
	Pattern phonePattern=Pattern.compile("^[6-9]{1}[0-9]{9}$");
    Matcher phoneMatcher=phonePattern.matcher(phonenumber);
	return phoneMatcher.matches();

	
}
}



















