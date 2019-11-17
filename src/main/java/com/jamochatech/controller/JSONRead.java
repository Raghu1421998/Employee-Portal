package com.jamochatech.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.JsonParseException;
import com.jamochatech.entity.CompanyTO;

/**
 * @author RAGHU
 *
 */
public class JSONRead
{
	ArrayList<CompanyTO>		companyList	= new ArrayList<CompanyTO>();

	private static final Logger	OUT			= LoggerFactory.getLogger(JSONRead.class);


	public List<CompanyTO> jsonread(String workbookName) throws IOException
	{

		try
		{
			String data = "";
			int n = 1;
			ObjectMapper mapper = new ObjectMapper();
			JSONRead read1 = new JSONRead();
			data = FileUtils.readFileToString(new File(workbookName), Charset.forName("utf-8"));
			CompanyTO[] company = mapper.readValue(data, CompanyTO[].class);
			String name;
			String businessType;
			String empSize;
			String comtype;
			String email;
			String country;
			String city;
			String dateOfJoining;
			String dateOfResigning;
			int row1;
			int id;
			String operation;
			for (int i = 0; i < company.length; i++)
			{
				OUT.debug("ROW {} Details", i + 1);
				operation = company[i].getOperation();
				if (read1.operationvalid(operation))
				{
					if (operation.equalsIgnoreCase("ADD"))
					{
						CompanyTO company1=new CompanyTO();
						name = company[i].getName();
						company1.setRow(i);
						businessType = company[i].getBusinessType();
						empSize = company[i].getEmpSize();
						comtype = company[i].getComType();
						email = company[i].getEmailAddress();
						country = company[i].getCountry();
						city = company[i].getCity();
						dateOfJoining=company[i].getDateOfJoining();
						dateOfResigning=company[i].getDateOfResigning();
						row1 = i;
						companyList = read1.jsonValidate(name, businessType, empSize, comtype, email, country, city, operation, row1, n,dateOfJoining,dateOfResigning);
					}
					else if (operation.equalsIgnoreCase("MOD"))
					{
						CompanyTO company1=new CompanyTO();
						name = company[i].getName();
						id = company[i].getId();
						company1.setId(id);
						company1.setRow(i);
						businessType = company[i].getBusinessType();
						empSize = company[i].getEmpSize();
						comtype = company[i].getComType();
						email = company[i].getEmailAddress();
						country = company[i].getCountry();
						city = company[i].getCity();
						dateOfJoining=company[i].getDateOfJoining();
						dateOfResigning=company[i].getDateOfResigning();
						row1 = i;
						companyList = read1.jsonValidate(name, businessType, empSize, comtype, email, country, city, operation, row1,n,dateOfJoining,dateOfResigning);
					}
					else
					{
						CompanyTO company1=new CompanyTO();
						id = company[i].getId();
						company1.setRow(i);
						company1.setId(id);
						company1.setOperation(operation);
						companyList.add(company1);
					}
				}
				else
				{
					OUT.debug("Invalid Operation");
					OUT.debug("Enter Valid Operation");
				}
			}
		}
		catch (JsonParseException e)
		{
			OUT.debug("JsonParseException Occurs");
		}

		return companyList;

	}

	private ArrayList<CompanyTO> jsonValidate(String name, String businessType, String empSize, String comtype, String email, String country, String city, String operation,
			int row1, int n,String dateOfJoining,String dateOfResigning)
	{
		JSONRead read1 = new JSONRead();
		CompanyTO company = new CompanyTO();
		for (int i = 1; i <= n; i++)
		{
			company.setOperation(operation);
			company.setRow(row1);
			if (read1.namevalid(name))
			{
				company.setName(name);
			}
			else
			{
				OUT.debug("Invalid Name");
				OUT.debug("Enter Valid Name");
				continue;
			}
			if (read1.businessvalid(businessType))
			{
				company.setBusinessType(businessType);
			}
			else
			{
				OUT.debug("Invalid BusinessType");
				OUT.debug("Enter Valid BusinessType");
				continue;
			}
			if (read1.empsizevalid(empSize))
			{
				company.setEmpSize(empSize);
			}
			else
			{
				OUT.debug("Invalid Empsize");
				OUT.debug("Enter Valid Empsize");
				continue;
			}
			if (read1.comTypevalid(comtype))
			{
				company.setComType(comtype);
			}
			else
			{
				OUT.debug("Invalid CompanyType");
				OUT.debug("Enter Valid CompanyType");
				continue;
			}
			if (read1.emailvalid(email))
			{
				company.setEmailAddress(email);
			}
			else
			{
				OUT.debug("Invalid Email");
				OUT.debug("Enter Valid Email");
				continue;
			}
			if (read1.countryvalid(country))
			{
				company.setCountry(country);
			}
			else
			{
				OUT.debug("Invalid Country");
				OUT.debug("Enter Valid Country");
				continue;
			}
			if (read1.cityValid(city, country))
			{
				company.setCity(city);
			}
			else
			{
				OUT.debug("Invalid City");
				OUT.debug("Enter Valid City");
				continue;
			}
			if (read1.dojvalid(dateOfJoining))
			{
				company.setDateOfJoining(dateOfJoining);
			}
			else
			{
				OUT.debug("Invalid Date");
				OUT.debug("Enter Valid Date Format(MM/DD/YYYY)");
				continue;
			}

			if (read1.dorvalid(dateOfResigning))
			{
				company.setDateOfResigning(dateOfResigning);
			}
			else
			{
				OUT.debug("Invalid Date");
				OUT.debug("Enter Valid Date Format(MM/DD/YYYY)");
				continue;
			}
			
			companyList.add(company);
		}
		return companyList;
	}

	private boolean namevalid(String name)
	{
		if (name != null && name.length() <= 50 && name.length() >= 5)
		{
			OUT.debug(name);
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean businessvalid(String businessType)
	{
		ArrayList<String> al1 = new ArrayList<String>();
		al1.add("BPO");
		al1.add("Agriculture");
		al1.add("IT Hardware");
		al1.add("IT Software");
		al1.add("Banking");
		al1.add("Financial Services");
		int k = 0;
		for (String str1 : al1)
		{
			if (str1.equalsIgnoreCase(businessType))
			{
				k++;
			}
		}
		if (k == 0 || businessType == null)
		{
			return false;
		}
		else
		{
			OUT.debug(businessType);
			return true;
		}
	}

	private boolean empsizevalid(String empSize)
	{
		String regex = "\\d+";
		if (empSize != null && empSize.matches(regex))
		{
			OUT.debug(empSize);
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean comTypevalid(String comtype)
	{
		ArrayList<String> al2 = new ArrayList<String>();
		al2.add("Employeer");
		al2.add("Consultant");
		int l = 0;
		for (String str : al2)
		{
			if (str.equalsIgnoreCase(comtype))
			{
				l++;
			}
		}
		if (l == 0 || comtype == null)
		{
			return false;
		}
		else
		{
			OUT.debug(comtype);
			return true;
		}
	}

	private boolean emailvalid(String email)
	{
		Pattern valid = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = valid.matcher(email);

		if (matcher.find() && email != null)
		{
			OUT.debug(email);
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean countryvalid(String country)
	{
		ArrayList<String> a = new ArrayList<String>();
		a.add("India");
		a.add("US");
		a.add("UK");
		int m = 0;
		for (String str : a)
		{
			if (str.equalsIgnoreCase(country))
			{
				m++;
			}
		}
		if (m == 0 || country == null)
		{
			return false;
		}
		else
		{
			OUT.debug(country);
			return true;
		}
	}

	boolean	status2	= false;

	boolean	status3	= true;

	int		t		= 0;

	private boolean cityValid(String city, String country)
	{
		String[] st = { "Chicago", "New York" };
		String[] str1 = { "London", "Manchester" };
		String[] str2 = { "Delhi", "Bangalore" };

		if (country.equalsIgnoreCase("US"))
		{
			for (int i1 = 0; i1 < st.length; i1++)
			{
				if (city.equalsIgnoreCase(st[i1]))
				{
					t++;
					break;
				}
			}
		}
		else if (country.equalsIgnoreCase("UK"))
		{
			for (int j1 = 0; j1 < str1.length; j1++)
			{
				if (city.equalsIgnoreCase(str1[j1]))
				{
					t++;
					break;
				}
			}
		}
		else if (country.equalsIgnoreCase("India"))
		{
			for (int k1 = 0; k1 < str2.length; k1++)
			{
				if (city.equalsIgnoreCase(str2[k1]))
				{
					t++;
					break;
				}
			}
		}
		if (t > 0 && city != null)
		{
			OUT.debug(city);
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean operationvalid(String operation)
	{
		ArrayList<String> al = new ArrayList<String>();
		al.add("ADD");
		al.add("DEL");
		al.add("MOD");
		int i = 0;
		for (String str : al)
		{
			if (str.equalsIgnoreCase(operation))
			{
				i++;
			}
		}
		return (i > 0 && operation != null) ? status3 : status2;
	}
	private boolean dojvalid(String dateOfJoining)
	{
		if (dateOfJoining != null)
		{
			String valid = "^((((0[13578])|(1[02]))[\\/]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\\/]?(([0-2][0-9])|(30)))|(02[\\/]?[0-2][0-9]))[\\/]?\\d{4}$";
			return dateOfJoining.matches(valid);
		}
		else
		{
			return false;
		}
	}

	private boolean dorvalid(String dateOfResigning)
	{
		String valid = "^((((0[13578])|(1[02]))[\\/]?(([0-2][0-9])|(3[01])))|(((0[469])|(11))[\\/]?(([0-2][0-9])|(30)))|(02[\\/]?[0-2][0-9]))[\\/]?\\d{4}$";
		return dateOfResigning.matches(valid);
	}
}