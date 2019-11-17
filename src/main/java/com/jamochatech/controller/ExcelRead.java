package com.jamochatech.controller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.jamochatech.entity.CompanyTO;

/**
 * @author RAGHU
 *
 */
public class ExcelRead
{
	ArrayList<CompanyTO>		companyList	= new ArrayList<CompanyTO>();

	private static final Logger	OUT			= LoggerFactory.getLogger(ExcelRead.class);


	FileInputStream				inputStream;

	Workbook					workBook;

	int							n			= 1;

	public List<CompanyTO> readexcel(String bookName)
	{
		try
		{
			inputStream = new FileInputStream(bookName);
			String str = FilenameUtils.getExtension(bookName);
			if (str.equals("xls"))
			{
				workBook = new HSSFWorkbook(inputStream);
			}
			else if (str.equals("xlsx"))
			{
				workBook = new XSSFWorkbook(inputStream);
			}
			Sheet sheet = workBook.getSheetAt(0);
			int rows = sheet.getLastRowNum();
			Row row;
			String name;
			String businessType;
			String empSize;
			String comtype;
			String email;
			String country;
			String city;
			Date dateOfJoining1 = null;
			Date dateOfResigning1 = null;
			String dateOfJoining = null;
			String dateOfResigning=null;
			int row1;
			
			Cell cell;
			Cell cell1;
			Cell cell2;
			Cell cell3;
			Cell cell4;
			Cell cell5;
			Cell cell6;
			Cell cell7;
			Cell cell8;
			Cell cell9;
			Cell cell10;
			String operation;
			ExcelRead read = new ExcelRead();
			for (int j = 1; j <= rows; j++)
			{
				OUT.debug("-------------------------ROW {}  Details-----------------------------------", j);
				row = sheet.getRow(j);
				if (row != null)
				{
					cell = row.getCell(0);
					cell1 = row.getCell(1);
					cell2 = row.getCell(2);
					cell3 = row.getCell(3);
					cell4 = row.getCell(4);
					cell5 = row.getCell(5);
					cell6 = row.getCell(6);
					cell7 = row.getCell(7);
					cell8 = row.getCell(8);
					cell9 = row.getCell(9);
					cell10 = row.getCell(10);
					if (cell != null && cell1 != null && cell2 != null && cell3 != null && cell4 != null && cell5 != null && cell6 != null && cell7 != null && cell8 != null
							&& cell9 != null && cell10 != null)
					{
						operation = cell.getStringCellValue();
						if (read.operationvalid(operation))
						{
							if (operation.equalsIgnoreCase("ADD"))
							{
								CompanyTO company=new CompanyTO();
								int id = (int) cell1.getNumericCellValue();
								row1 = j;
								name = cell2.getStringCellValue();
								businessType = cell3.getStringCellValue();
								empSize = cell4.getStringCellValue();
								comtype = cell5.getStringCellValue();
								email = cell6.getStringCellValue();
								country = cell7.getStringCellValue();
								city = cell8.getStringCellValue();
								company.setRow(j);
								try
								{
									dateOfJoining = cell9.getStringCellValue();
									dateOfResigning = cell10.getStringCellValue();
								}
								catch (IllegalStateException i)
								{
									OUT.debug("Exception Occurs");
									OUT.debug("Enter Date in DD-MM-YYYY format");
								}
								catch (Exception e)
								{
									OUT.debug("Exception occurs");
								}
								read.validate(id,name, businessType, empSize, comtype, email, country, city, operation, row1, n, dateOfJoining, dateOfResigning);
							}
							else if (operation.equalsIgnoreCase("MOD"))
							{
								CompanyTO company=new CompanyTO();
								int id = (int) cell1.getNumericCellValue();
								row1 = j;
								company.setRow(j);
								name = cell2.getStringCellValue();
								businessType = cell3.getStringCellValue();
								empSize = cell4.getStringCellValue();
								comtype = cell5.getStringCellValue();
								email = cell6.getStringCellValue();
								country = cell7.getStringCellValue();
								city = cell8.getStringCellValue();
								try
								{
									dateOfJoining = cell9.getStringCellValue();
									dateOfResigning = cell10.getStringCellValue();
								}
								catch (IllegalStateException i)
								{
									OUT.debug("Exception Occurs");
									OUT.debug("Enter Date in DD-MM-YYYY format");
								}
								catch (Exception e)
								{
									OUT.error("{} occurs", e.getMessage());
								}
								
							read.validate(id,name, businessType, empSize, comtype, email, country, city, operation, row1, n, dateOfJoining, dateOfResigning);

							}
							else
							{
								CompanyTO company=new CompanyTO();
								int id = (int) cell1.getNumericCellValue();
								company.setRow(j);
								company.setId(id);
								company.setOperation(operation);
								companyList.add(company);
							}
						}
						else
						{
							OUT.error("Enter Valid Operation");
						}
					}
					else
					{
						OUT.error("Row# {} Cell is empty", j);
					}
				}
				else
				{
					OUT.error("Row# {} is empty", j);
				}
			}
		}
		catch (FileNotFoundException e)
		{
			OUT.error("{} occurs", e.getMessage());
		}
		catch (EncryptedDocumentException e)
		{
			OUT.error("EncryptedDocumentException occurs");
		}
		catch (IOException e)
		{
			OUT.error("IOException occurs");
		}
		catch (NullPointerException e)
		{
			OUT.error("Cell can not be empty");
		}

		finally
		{
			try
			{
				inputStream.close();
				workBook.close();
			}
			catch (IOException e)
			{
				OUT.error("Connection close Exception");
			}
		}
		return companyList;
	}

	private ArrayList<CompanyTO> validate(int id,String name, String businessType, String empSize, String comtype, String email, String country, String city, String operation, int row1,
			int n, String dateOfJoining, String dateOfResigning)
	{
		ExcelRead read1 = new ExcelRead();
		CompanyTO company = new CompanyTO();

		for (int i = 1; i <= n; i++)
		{
			company.setOperation(operation);
			company.setRow(row1);
			company.setId(id);
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

	boolean	status1;

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
