package com.jamochatech.entity;

import java.util.ArrayList;
import java.util.List;

public class CompanyTO
{
	private int						id;

	private String					name;

	private String					businessType;

	private String					empSize;

	private String					comType;

	private String					contactNumber;

	private String					emailAddress;

	private List<ServicingIndustry>	services	= new ArrayList<ServicingIndustry>();

	private String					country;

	private String					city;

	private String					address;

	private String					operation;

	private int						row;

	private String					dateOfJoining;

	private String					dateOfResigning;

	private int						empCount;

	private String					day;

	private String					month;

	private int						res;
	private String serviceslist;

	public String getServiceslist()
	{
		return serviceslist;
	}

	public void setServiceslist(String serviceslist)
	{
		this.serviceslist = serviceslist;
	}

	public int getRes()
	{
		return res;
	}

	public void setRes(int res)
	{
		this.res = res;
	}

	public String getDay()
	{
		return day;
	}

	public void setDay(String day)
	{
		this.day = day;
	}

	public String getMonth()
	{
		return month;
	}

	public void setMonth(String month)
	{
		this.month = month;
	}

	public int getEmpCount()
	{
		return empCount;
	}

	public void setEmpCount(int empCount)
	{
		this.empCount = empCount;
	}

	public int getRow()
	{
		return row;
	}

	public String getDateOfJoining()
	{
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining)
	{
		this.dateOfJoining = dateOfJoining;
	}

	public String getDateOfResigning()
	{
		return dateOfResigning;
	}

	public void setDateOfResigning(String dateOfResigning)
	{
		this.dateOfResigning = dateOfResigning;
	}

	public void setRow(int row)
	{
		this.row = row;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getBusinessType()
	{
		return businessType;
	}

	public void setBusinessType(String businessType)
	{
		this.businessType = businessType;
	}

	public String getEmpSize()
	{
		return empSize;
	}

	public void setEmpSize(String empSize)
	{
		this.empSize = empSize;
	}

	public String getComType()
	{
		return comType;
	}

	public void setComType(String comType)
	{
		this.comType = comType;
	}

	public String getContactNumber()
	{
		return contactNumber;
	}

	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}

	public String getEmailAddress()
	{
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}

	public List<ServicingIndustry> getServices()
	{
		return services;
	}

	public void setServices(List<ServicingIndustry> services)
	{
		this.services = services;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getOperation()
	{
		return operation;
	}

	public void setOperation(String operation)
	{
		this.operation = operation;
	}

}
