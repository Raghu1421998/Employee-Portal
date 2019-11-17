package com.jamochatech.util;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jamochatech.controller.CompanyController;

public class MyBatisUtil
{

	private MyBatisUtil()
	{
		throw new IllegalStateException("MyBatisUtil class");
	}

	private static SqlSessionFactory	sqlSessionFactory;

	private static final Logger			logger	= LoggerFactory.getLogger(CompanyController.class);
	static
	{
		Reader reader;
		try
		{
			String resource = "com/jamochatech/resources/mybatis-config.xml";
			reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}
		catch (IOException e)
		{
			logger.debug("context", e);
		}
	}

	public static SqlSessionFactory getSqlSessionFactory()
	{
		return sqlSessionFactory;
	}
}