package com.jamochatech.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.jamochatech.entity.CompanyTO;
import com.jamochatech.entity.ServicingIndustry;
import com.jamochatech.util.MyBatisUtil;

@Repository
public class CompanyMapper
{

	public void saveCompany(CompanyTO company, String[] serv)
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			session.insert("insertCompany", company);
			ServicingIndustry s = new ServicingIndustry();
			for (int i = 0; i < serv.length; i++)
			{
				s.setId(company.getId());
				s.setServicesIndustry(serv[i]);
				session.insert("insertServices", s);
			}
			session.commit();
		}

		finally
		{
			session.close();
		}
	}

	public void updateCompany(CompanyTO company, String[] serv)
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			ServicingIndustry s = new ServicingIndustry();
			session.update("updateCompany", company);
			session.delete("deleteCompany1", company.getId());
			for (int i = 0; i < serv.length; i++)
			{
				s.setId(company.getId());
				s.setServicesIndustry(serv[i]);
				session.insert("insertServices", s);
			}
			session.commit();
		}
		finally
		{
			session.close();
		}
	}

	public void deleteCompany(int id)
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			session.delete("deleteCompany", id);
			session.delete("deleteCompany1", id);
			session.commit();
		}

		finally
		{
			session.close();
		}
	}

	public List<CompanyTO> getAllCompanyDetails()
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> companyList = session.selectList("getAllCompanyDetails");
			session.commit();
			return companyList;
		}

		finally
		{
			session.close();
		}
	}

	public List<CompanyTO> getAllDetails()
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> companyList = session.selectList("getAllDetails");
			session.commit();
			return companyList;
		}

		finally
		{
			session.close();
		}
	}

	public List<CompanyTO> getAllCompanyDetails1()
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> companyList1 = session.selectList("getAllCompanyDetails1");
			session.commit();
			return companyList1;
		}

		finally
		{
			session.close();
		}
	}

	public CompanyTO findById(int id)
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			CompanyTO company = session.selectOne("findById", id);
			session.commit();
			return company;
		}
		finally
		{
			session.close();
		}
	}

	public List<CompanyTO> findById1(int id)
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> companyList1= session.selectList("findById1", id);
			session.commit();
			return companyList1;
		}
		finally
		{
			session.close();
		}
	}
	public CompanyTO validById(int id)
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			CompanyTO company = session.selectOne("validId", id);
			session.commit();
			return company;
		}
		finally
		{
			session.close();
		}
	}

	public List<CompanyTO> getCompById1(int id)
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> companyList1 = session.selectList("getById", id);
			session.commit();
			return companyList1;
		}
		finally
		{
			session.close();
		}
	}

	public List<ServicingIndustry> getCompById2(int id)
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<ServicingIndustry> li = session.selectList("getById1", id);
			session.commit();
			return li;
		}
		finally
		{
			session.close();
		}
	}

	public List<CompanyTO> getAllBarDetails()
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> barlist = session.selectList("getAllBarDetails");
			session.commit();
			return barlist;
		}

		finally
		{
			session.close();
		}
	}

	public List<CompanyTO> getAllServicingIndustry()
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> multilist = session.selectList("multiBarDetails");
			session.commit();
			return multilist;
		}

		finally
		{
			session.close();
		}
	}

	public List<CompanyTO> getCalendar()
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> calendarview = session.selectList("calendarview");
			session.commit();
			return calendarview;
		}

		finally
		{
			session.close();
		}
	}

	public List<CompanyTO> getMonthDetails()
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> monthview = session.selectList("monthview");
			session.commit();
			return monthview;
		}

		finally
		{
			session.close();
		}
	}
	
	public List<CompanyTO> exportDetails()
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> companyList1 = session.selectList("exportDetails");
			session.commit();
			return companyList1;
		}

		finally
		{
			session.close();
		}
	}
	
	public List<CompanyTO> getAllColumn(CompanyTO company)
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> companyList1 = session.selectList("getAllColumn",company);
			session.commit();
			return companyList1;
		}

		finally
		{
			session.close();
		}
	}
	public List<CompanyTO> getcard1()
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> companyList1 = session.selectList("card1");
			session.commit();
			return companyList1;
		}

		finally
		{
			session.close();
		}
	}
	public List<CompanyTO> getcard2()
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> companyList1 = session.selectList("card2");
			session.commit();
			return companyList1;
		}

		finally
		{
			session.close();
		}
	}
	public List<CompanyTO> getcard3()
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> companyList1 = session.selectList("card3");
			session.commit();
			return companyList1;
		}

		finally
		{
			session.close();
		}
	}
	public List<CompanyTO> getcard4()
	{
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		try
		{
			List<CompanyTO> companyList1 = session.selectList("card4");
			session.commit();
			return companyList1;
		}

		finally
		{
			session.close();
		}
	}
}