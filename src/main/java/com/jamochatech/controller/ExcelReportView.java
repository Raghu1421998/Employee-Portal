package com.jamochatech.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.jamochatech.entity.CompanyTO;

public class ExcelReportView extends AbstractXlsView
{
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		response.setHeader("Content-Disposition", "attachment;filename=\"CompanyMaster.xls\"");
		@SuppressWarnings("unchecked")
		List<CompanyTO> comList = (List<CompanyTO>) model.get("comList");
		Sheet sheet = workbook.createSheet("Student Data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Operation");
		header.createCell(1).setCellValue("ID");
		header.createCell(2).setCellValue("Name");
		header.createCell(3).setCellValue("Business Type");
		header.createCell(4).setCellValue("Employee Size");
		header.createCell(5).setCellValue("Company Type");
		header.createCell(6).setCellValue("Email ID");
		header.createCell(7).setCellValue("Country");
		header.createCell(8).setCellValue("City");
		header.createCell(9).setCellValue("DOJ");
		header.createCell(10).setCellValue("DOR");
		int rowNum = 1;
		Row row;
		for (CompanyTO comp : comList)
		{
			row = sheet.createRow(rowNum++);
			row.createCell(1).setCellValue(comp.getId());
			row.createCell(2).setCellValue(comp.getName());
			row.createCell(3).setCellValue(comp.getBusinessType());
			row.createCell(4).setCellValue(comp.getEmpSize());
			row.createCell(5).setCellValue(comp.getComType());
			row.createCell(6).setCellValue(comp.getEmailAddress());
			row.createCell(7).setCellValue(comp.getCountry());
			row.createCell(8).setCellValue(comp.getCity());
			row.createCell(9).setCellValue(comp.getDateOfJoining());
			row.createCell(10).setCellValue(comp.getDateOfResigning());
		}
	}
}
