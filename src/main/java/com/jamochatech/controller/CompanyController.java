package com.jamochatech.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.jamochatech.dao.CompanyMapper;
import com.jamochatech.entity.CompanyTO;
import com.jamochatech.entity.ServicingIndustry;

/**
 * @author RAGHU
 *
 */

@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController
{
	@Autowired
	CompanyMapper				companyMapper;

	private static final Logger	OUT				= LoggerFactory.getLogger(CompanyController.class);

	private static final String	SUMMARY			= "redirect:/CompanySummaryScreen";

	private static final String	COMPANYSUMMARY	= "CompanySummaryScreen";

	private static final String	ERROR			= "CompanyErrorScreen";

	private static final String	SCREEN			= "CompanyScreen";

	private static final String	COMPANYLIST		= "companyLis1";

	private static final String	DATEFORMAT		= "MM/dd/yyyy";

	private static final String	DATEFORMAT1		= "yyyy-MM-dd";

	String[]					serv1			= { "Banking" };

	ArrayList<Date>				al5				= new ArrayList<Date>();

	private static final String	CALENDARLIST	= "calendarList";

	private static final String	CALENDARJSP		= "CalendarJSP";

	/**
	 * @param m
	 * @param request
	 * @return
	 */
	@GetMapping("/CompanyScreen")
	public String showform(Model m, HttpServletRequest request)
	{
		m.addAttribute("command", new CompanyTO());
		return SCREEN;
	}

	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/export")
	public ModelAndView getExcel()
	{
		List<CompanyTO> comList = companyMapper.getAllCompanyDetails();
		return new ModelAndView(new ExcelReportView(), "comList", comList);
	}

	/**
	 * @param comp
	 * @param m
	 * @param file
	 * @param session
	 * @param request
	 * @return
	 * @throws InvalidFormatException
	 * @throws Exception
	 */
	@PostMapping(value = "/savefile1")
	public String upload(@ModelAttribute("comp") CompanyTO comp, Model m, @RequestParam("file") CommonsMultipartFile file, HttpSession session, HttpServletRequest request)
	{
		String path = session.getServletContext().getRealPath("/");
		String filename = file.getOriginalFilename();
		ExcelRead e = new ExcelRead();
		String filepath = path + "" + filename;
		List<CompanyTO> list = e.readexcel(filepath);
		String operation;
		int id;
		CompanyTO c1;
		CompanyTO comp1;
		OUT.debug("---------------------------OPERATIONS-------------------------------------");
		for (CompanyTO c : list)
		{
			operation = c.getOperation();
			if (operation.equalsIgnoreCase("ADD"))
			{

				companyMapper.saveCompany(c, serv1);
				OUT.debug("Row#  {}: Operation ADD,CompanyDetails Added successfully", c.getRow());

			}
			else if (operation.equalsIgnoreCase("MOD"))
			{
				id = c.getId();
				c1 = companyMapper.findById(id);
				if (c1 != null)
				{

					companyMapper.updateCompany(c, serv1);
					OUT.debug("Row#   {}:Operation: MOD,CompanyDetails Modified successfully", c.getRow());
				}
				else
				{
					OUT.debug("Row#   {}:Operation: MOD,CompanyDetails validation failed", c.getRow());
				}
			}
			else
			{
				id = c.getId();
				comp1 = companyMapper.findById(id);
				if (comp1 != null)
				{
					companyMapper.deleteCompany(id);
					OUT.debug("Row#    {}:Operation:DEL,CompanyDetails Deleted Successfully", c.getRow());
				}
				else
				{
					OUT.debug("Row#     {}:Operation: DEL,CompanyDetails validation failed", c.getRow());
				}

			}

		}
		return SUMMARY;
	}

	/**
	 * @param m
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	@GetMapping("/CompanySummaryScreen")
	public String viewemp(Model m, HttpServletRequest request)
	{
		List<CompanyTO> list = companyMapper.getAllCompanyDetails();
		m.addAttribute(COMPANYLIST, list);
		return COMPANYSUMMARY;
	}

	/**
	 * @param m
	 * @param request
	 * @return
	 * @throws Exception
	 */

	@GetMapping("/dash")
	public String dashboard(Model m, HttpServletRequest request)
	{
		return "CompanyCharts";
	}

	@GetMapping("/da")
	public String dashbod(Model m, HttpServletRequest request)
	{
		return "da";
	}

	@GetMapping("/dash1")
	public String dashboard1(Model m, HttpServletRequest request)
	{
		List<CompanyTO> bar = companyMapper.getAllBarDetails();
		request.setAttribute("barlist", bar);
		return "Columnchart";
	}

	@GetMapping("/dash2")
	public String dashboard2(Model m, HttpServletRequest request)
	{
		List<CompanyTO> list1 = companyMapper.getAllCompanyDetails1();
		request.setAttribute("companyList1", list1);
		return "Piechart";
	}

	@GetMapping("/dash3")
	public String dashboard3(Model m, HttpServletRequest request)
	{
		List<CompanyTO> bar = companyMapper.getAllBarDetails();
		List<CompanyTO> multilist = companyMapper.getAllServicingIndustry();
		request.setAttribute("multilist", multilist);
		request.setAttribute("barlist", bar);
		return "Linechart";
	}

	@GetMapping("/dash4")
	public String dashboard4(Model m, HttpServletRequest request)
	{
		List<CompanyTO> multilist = companyMapper.getAllServicingIndustry();
		request.setAttribute("multilist", multilist);
		return "Multiseriescharts";
	}

	@SuppressWarnings("deprecation")

	@GetMapping("/dash5")
	public String dashboard5(Model m, HttpServletRequest request) throws ParseException
	{
		int resigndate = 0;
		int joindate;
		Date resign1;
		String regdate;
		String closuredate;
		Date resign2;
		int comp = 0;
		String date = "9/30/2019";
		Date resign = new SimpleDateFormat(DATEFORMAT).parse(date);
		List<CompanyTO> calendarList = new ArrayList<CompanyTO>();
		int day = resign.getDate();
		List<CompanyTO> calendarlist = companyMapper.getCalendar();
		for (int i = 1; i <= day; i++)
		{
			for (CompanyTO company : calendarlist)
			{
				regdate = company.getDateOfJoining();
				resign1 = new SimpleDateFormat(DATEFORMAT).parse(regdate);
				joindate = resign1.getDate();
				if (!company.getDateOfResigning().equals(""))
				{
					closuredate = company.getDateOfResigning();
					resign2 = new SimpleDateFormat(DATEFORMAT).parse(closuredate);
					resigndate = resign2.getDate();
				}
				if (i >= joindate && company.getDateOfResigning().equals(""))
				{
					comp++;
				}
				else if (i >= joindate && i <= resigndate)
				{
					comp++;
				}
			}
			CompanyTO company = new CompanyTO();
			company.setEmpCount(comp);
			company.setDay("Day " + i);
			comp = 0;
			calendarList.add(company);
		}
		m.addAttribute(CALENDARLIST, calendarList);
		return CALENDARJSP;
	}

	@SuppressWarnings("deprecation")
	@GetMapping("/dash6")
	public String dashboard6(@RequestParam("startdate") String startdate, @RequestParam("closuredate") String closuredate, Model m) throws ParseException
	{
		List<CompanyTO> calendar1 = new ArrayList<CompanyTO>();
		List<CompanyTO> calendarlist = companyMapper.getCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat(DATEFORMAT1);
		Date startDate = formatter.parse(startdate);
		Date endDate = formatter.parse(closuredate);
		int comp = 0;
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		String sdate;
		String cdate;
		Date dates = null;
		Date datec = null;
		CompanyTO company1;
		for (Date date = start.getTime(); start.before(end) || start.equals(end); start.add(Calendar.DATE, 1), date = start.getTime())
		{
			for (CompanyTO company : calendarlist)
			{
				sdate = company.getDateOfJoining();
				cdate = company.getDateOfResigning();

				if (!sdate.equals(""))
				{
					dates = new SimpleDateFormat(DATEFORMAT).parse(sdate);
				}
				if (!cdate.equals(""))
				{
					datec = new SimpleDateFormat(DATEFORMAT).parse(cdate);
				}
				if (!sdate.equals(""))
				{
					if (company.getDateOfResigning().equals("") && (dates.before(date) || dates.equals(date)))
					{
						comp++;
					}
					else if ((dates.before(date) || dates.equals(date)) && (datec.after(date) || datec.equals(date)))
					{
						comp++;
					}
				}
			}
			company1 = new CompanyTO();
			company1.setEmpCount(comp);
			company1.setDay("Day " + date.getDate());
			comp = 0;
			calendar1.add(company1);
		}
		m.addAttribute(CALENDARLIST, calendar1);
		return CALENDARJSP;
	}

	@GetMapping("/dash7")
	public String dashboard7(@RequestParam("val") String val, @RequestParam("startdate") String startdate, @RequestParam("closuredate") String closuredate, Model m,
			HttpServletRequest request) throws ParseException
	{
		List<CompanyTO> calendar1 = new ArrayList<CompanyTO>();
		List<CompanyTO> calendarlist = companyMapper.getCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat(DATEFORMAT1);
		Date startDate = formatter.parse(startdate);
		Date endDate = formatter.parse(closuredate);
		int comp = 0;
		int res = 0;
		String sdate;
		String cdate;
		Date dates = null;
		Date datec = null;
		CompanyTO company1;
		Calendar calendar;
		Calendar cal1;
		Calendar cal2;
		while (startDate.before(endDate) || startDate.equals(endDate))
		{
			calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			cal1 = Calendar.getInstance();
			cal1.setTime(startDate);
			cal2 = Calendar.getInstance();
			cal2.setTime(startDate);
			cal1.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			cal2.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
			for (CompanyTO company : calendarlist)
			{
				sdate = company.getDateOfJoining();
				cdate = company.getDateOfResigning();
				if (!sdate.equals(""))
				{
					dates = new SimpleDateFormat(DATEFORMAT).parse(sdate);
				}
				if (!cdate.equals(""))
				{
					datec = new SimpleDateFormat(DATEFORMAT).parse(cdate);
				}
				if (!sdate.equals(""))
				{
					if (company.getDateOfResigning().equals("") && (dates.before(startDate) || dates.equals(startDate)))
					{
						comp++;
					}
					else if ((dates.before(startDate) || dates.equals(startDate)) && (datec.equals(cal2.getTime()) || datec.after(cal2.getTime()))
							&& (datec.before(cal1.getTime()) || datec.equals(cal1.getTime()) || datec.after(cal1.getTime())))
					{
						comp++;
					}
				}
				if (datec != null && (datec.before(cal1.getTime()) || datec.equals(cal1.getTime())) && (datec.after(cal2.getTime()) || datec.equals(cal2.getTime())))
				{
					res++;
					comp--;
				}
			}
			if (startDate.equals(cal1.getTime()) || startDate.equals(endDate))
			{
				String month = startDate.toString();
				company1 = new CompanyTO();
				company1.setDay(month.substring(3, 7) + month.substring(23, 28));
				company1.setEmpCount(comp);
				company1.setRes(res);
				calendar1.add(company1);
				comp = 0;
				res = 0;
			}
			calendar.add(Calendar.DATE, 1);
			startDate = calendar.getTime();
			comp = 0;
			res = 0;
		}
		request.setAttribute("callist", calendar1);
		m.addAttribute(CALENDARLIST, calendar1);
		return CALENDARJSP;
	}

	@GetMapping("/dash8")
	public String dashboard8(@RequestParam("startdate") String startdate, @RequestParam("closuredate") String closuredate, Model m, HttpServletRequest request)
			throws ParseException
	{
		List<CompanyTO> calendar1 = new ArrayList<CompanyTO>();
		List<CompanyTO> calendarlist = companyMapper.getCalendar();
		SimpleDateFormat formatter = new SimpleDateFormat(DATEFORMAT1);
		Date startDate = formatter.parse(startdate);
		Date endDate = formatter.parse(closuredate);
		int comp = 0;
		int res = 0;
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		String sdate;
		String cdate;
		Date dates;
		Date datec;
		CompanyTO company1;
		for (Date date = start.getTime(); start.before(end) || start.equals(end); start.add(Calendar.DATE, 1), date = start.getTime())
		{
			for (CompanyTO company : calendarlist)
			{
				sdate = company.getDateOfJoining();
				cdate = company.getDateOfResigning();
				dates = new SimpleDateFormat(DATEFORMAT).parse(sdate);
				datec = null;
				if (!cdate.equals(""))
				{
					datec = new SimpleDateFormat(DATEFORMAT).parse(cdate);
				}

				if (cdate.equals("") && (dates.before(date) || dates.equals(date)))
				{
					comp++;
				}
				else if ((dates.before(date) || dates.equals(date)) && (datec.after(date) || datec.equals(date)))
				{
					comp++;
				}

				if (!sdate.equals("") && !cdate.equals("") && datec.equals(date))
				{
					res++;
					comp--;
				}
			}
			company1 = new CompanyTO();
			company1.setEmpCount(comp);
			company1.setRes(res);
			SimpleDateFormat formatter1 = new SimpleDateFormat(DATEFORMAT);
			String strDate = formatter1.format(date);
			company1.setDay(strDate);
			calendar1.add(company1);
			comp = 0;
			res = 0;
		}
		request.setAttribute("callist", calendar1);
		m.addAttribute(CALENDARLIST, calendar1);
		return CALENDARJSP;
	}

	@PostMapping("/dash20")
	public String dashboard20(@RequestParam("od1") String od1, Model m)
	{
		m.addAttribute("od1", od1);
		return "Stackedbar";
	}

	@PostMapping("/dash21")
	public String dashboard21(@RequestParam("od1") String od1, Model m)
	{
		m.addAttribute("od1", od1);
		return "Stackedbar2";
	}

	/**
	 * @param m
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	@GetMapping("/compscreen")
	public String viewEmployee2(Model m)
	{
		List<CompanyTO> list = companyMapper.getAllCompanyDetails();
		m.addAttribute(COMPANYLIST, list);
		return COMPANYSUMMARY;
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@GetMapping(value = "/login")
	public String login(HttpServletRequest request, HttpServletResponse response)
	{
		String name = request.getParameter("name");
		HttpSession session = request.getSession();
		session.setAttribute("name", name);
		return SUMMARY;
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@PostMapping(value = "/logout")
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session = request.getSession();
		session.removeAttribute("name");
		session.invalidate();
		response.sendRedirect("/MyBatisCrud1");
	}

	/**
	 * @param id
	 * @param m
	 * @param request
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public String edit(@PathVariable String id, Model m, HttpServletRequest request)
	{
		try
		{
			if (id.matches("^[a-zA-Z]*$"))
			{
				return ERROR;
			}
			else
			{
				int id1 = Integer.parseInt(id);
				CompanyTO comp = companyMapper.findById(id1);
				if (comp != null)
				{
					String str = "";
					List<ServicingIndustry> list = companyMapper.getCompById2(id1);
					StringBuilder str1 = new StringBuilder();
					for (int i = 0; i < list.size(); i++)
					{
						str1.append(list.get(i).getServicesIndustry()).append(",");
					}
					str = str1.toString();
					OUT.debug(str);
					request.setAttribute("serv", str);
					m.addAttribute("command", comp);
					m.addAttribute("city", comp.getCity());
					m.addAttribute("service", str);
					return SCREEN;
				}
				else
				{
					return ERROR;
				}
			}
		}
		catch (Exception e)
		{
			OUT.debug(e.getMessage());
			return ERROR;
		}
	}

	/**
	 * @param comp
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/back")
	public String editsave1(@ModelAttribute("comp") CompanyTO comp, HttpServletRequest request)
	{
		return SUMMARY;
	}

	/**
	 * @param comp
	 * @param request
	 * @param ser
	 * @return
	 * @throws Exception
	 */

	@PostMapping(value = "/editsave")
	public String editsave(@ModelAttribute("comp") CompanyTO comp, HttpServletRequest request, @ModelAttribute("ser") ServicingIndustry ser)
	{
		String[] servi = request.getParameterValues("service");
		for (int i = 0; i < servi.length; i++)
		{
			OUT.debug(servi[i]);
		}

		if (companyMapper.findById(comp.getId()) == null)
		{
			companyMapper.saveCompany(comp, servi);
			return "redirect:/CompanySummaryScreen?insert=true";
		}
		else
		{
			companyMapper.updateCompany(comp, servi);
			return "redirect:/CompanySummaryScreen?update=true";
		}

	}

	/**
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/deleteemp/{id}")
	public String delete(@PathVariable int id, HttpServletRequest request)
	{
		companyMapper.deleteCompany(id);
		return "redirect:/CompanySummaryScreen?delete=true";
	}

	/**
	 * @param id
	 * @param m
	 * @param request
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/RowDetails/{id}")
	public String rowDetails(@PathVariable int id, Model m, HttpServletRequest request, HttpServletResponse res)
	{
		List<CompanyTO> list = companyMapper.getCompById1(id);
		m.addAttribute("companyList1", list);
		return "RowDetails1";
	}

	/**
	 * @param m
	 * @param name
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * @throws Exception
	 */
	Properties	prop	= new Properties();

	String		user	= "";

	@GetMapping(value = "/returnListInResponse.web")
	public String returnListInResponse(Model m, String name) throws IOException
	{
		List<CompanyTO> list = companyMapper.exportDetails();
		ObjectMapper mapper = new ObjectMapper();
		mapper.writerWithDefaultPrettyPrinter();
		String propFileName = "/config.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		if (inputStream != null)
		{
			prop.load(inputStream);
		}
		else
		{
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}
		user = prop.getProperty("path");
		try
		{
			mapper.writeValue(new File(user), list);
		}
		catch (JsonGenerationException e)
		{
			OUT.debug("JsonGenerationException Occurs");
		}
		catch (JsonMappingException e)
		{
			OUT.debug("JsonMappingException Occurs");
		}
		catch (IOException e)
		{
			OUT.debug("IOException Occurs");
		}
		catch (Exception e)
		{
			OUT.debug("Exception1 Occurs");
		}
		finally
		{
			inputStream.close();
		}
		OUT.debug("JSON Write successful!!");
		m.addAttribute(COMPANYLIST, list);
		return COMPANYSUMMARY;
	}

	/**
	 * @param comp
	 * @param m
	 * @param file
	 * @param session
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * @throws Exception
	 */
	@PostMapping(value = "/savefile2")
	public String jsonUpload(@RequestParam("file") CommonsMultipartFile file, HttpSession session) throws IOException
	{
		String path = session.getServletContext().getRealPath("/");
		String filename = file.getOriginalFilename();
		JSONRead js = new JSONRead();
		List<CompanyTO> list = null;
		String operation;
		int id;
		CompanyTO comp1;
		CompanyTO c1;
		list = js.jsonread(path + "" + filename);
		OUT.debug("---------------------------OPERATIONS-------------------------------------");
		for (CompanyTO c : list)
		{
			operation = c.getOperation();
			if (operation.equalsIgnoreCase("ADD"))
			{
				companyMapper.saveCompany(c, serv1);
				OUT.debug("Row#  {}: Operation ADD,CompanyDetails Added successfully", c.getRow());
			}
			else if (operation.equalsIgnoreCase("MOD"))
			{
				id = c.getId();
				c1 = companyMapper.findById(id);
				if (c1 != null)
				{
					companyMapper.updateCompany(c, serv1);
					OUT.debug("Row#   {}:Operation: MOD,CompanyDetails Modified successfully", c.getRow());
				}
				else
				{
					OUT.debug("Row#   {}:Operation: MOD,CompanyDetails validation failed", c.getRow());
				}
			}
			else
			{
				id = c.getId();
				comp1 = companyMapper.findById(id);
				if (comp1 != null)
				{
					companyMapper.deleteCompany(id);
					OUT.debug("Row#    {}:Operation:DEL,CompanyDetails Deleted Successfully", c.getRow());
				}
				else
				{
					OUT.debug("Row#     {}:Operation: DEL,CompanyDetails validation failed", c.getRow());
				}
			}

		}
		return SUMMARY;
	}

	@GetMapping(value = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CompanyTO>> getCountries()
	{
		List<CompanyTO> listOfCountries = companyMapper.getAllCompanyDetails();
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(listOfCountries);
	}

	@PostMapping(value = "/insertall", headers = "Accept=application/json", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> insert(@RequestBody CompanyTO company1)
	{
		CompanyTO company = new CompanyTO();
		company.setName(company1.getName());
		company.setBusinessType(company1.getBusinessType());
		company.setEmpSize(company1.getEmpSize());
		company.setComType(company1.getComType());
		company.setEmailAddress(company1.getEmailAddress());
		company.setCountry(company1.getCountry());
		company.setCity(company1.getCity());
		company.setAddress(company1.getAddress());
		company.setDateOfJoining(company1.getDateOfJoining());
		company.setDateOfResigning(company1.getDateOfResigning());
		String servlist = company1.getServiceslist();

		String[] str = servlist.split(",");

		String[] ar = new String[str.length];
		for (int i = 0; i < str.length; i++)
		{
			ar[i] = str[i];
		}

		companyMapper.saveCompany(company, ar);
		return ResponseEntity.ok().body("COMPANY DETAILS INSERTED SUCCESSFULLY");
	}

	@DeleteMapping(value = "/deleteall/{id}", headers = "Accept=application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") int id)
	{
		if (companyMapper.findById(id) != null)
		{
			companyMapper.deleteCompany(id);
			return ResponseEntity.ok().body("COMPANY DETAILS DELETED SUCCESSFULLY");
		}
		else
		{
			return ResponseEntity.ok().body("COMPANY DETAILS DELETION FAILED");
		}
	}

	@PutMapping(value = "/updateall/{id}", headers = "Accept=application/json", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@RequestBody CompanyTO company1)
	{
		CompanyTO company = new CompanyTO();
		int id = company1.getId();
		company.setName(company1.getName());
		company.setBusinessType(company1.getBusinessType());
		company.setEmpSize(company1.getEmpSize());
		company.setComType(company1.getComType());
		company.setEmailAddress(company1.getEmailAddress());
		company.setCountry(company1.getCountry());
		company.setCity(company1.getCity());
		company.setAddress(company1.getAddress());
		company.setDateOfJoining(company1.getDateOfJoining());
		company.setDateOfResigning(company1.getDateOfResigning());
		String servlist = company1.getServiceslist();

		String[] str = servlist.split(",");

		String[] ar = new String[str.length];
		for (int i = 0; i < str.length; i++)
		{
			ar[i] = str[i];
		}

		if (companyMapper.findById(id) != null)
		{
			company.setId(id);
			companyMapper.updateCompany(company, ar);
			return ResponseEntity.ok().body("COMPANY DETAILS UPDATED SUCCESSFULLY");
		}
		else
		{
			return ResponseEntity.ok().body("COMPANY DETAILS UPDATION FAILED");
		}
	}

	@GetMapping(value = "/getbyid/{id}", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CompanyTO> get(@PathVariable("id") int id)
	{
		if (companyMapper.findById(id) != null)
		{
			CompanyTO comp = new CompanyTO();
			String serviceslist = "";
			List<CompanyTO> list = companyMapper.findById1(id);
			for (CompanyTO c : list)
			{
				comp.setId(c.getId());
				comp.setName(c.getName());
				comp.setBusinessType(c.getBusinessType());
				comp.setEmpSize(c.getEmpSize());
				comp.setComType(c.getComType());
				comp.setContactNumber(c.getContactNumber());
				comp.setEmailAddress(c.getEmailAddress());
				comp.setCountry(c.getCountry());
				comp.setCity(c.getCity());
				comp.setAddress(c.getAddress());
				comp.setDateOfJoining(c.getDateOfJoining());
				comp.setDateOfResigning(c.getDateOfResigning());
				comp.setServices(c.getServices());
				List<ServicingIndustry> l = comp.getServices();

				for (int i = 0; i < l.size() - 1; i++)
				{
					serviceslist += comp.getServices().get(i).getServicesIndustry() + ",";

				}
				int a = l.size();
				serviceslist += comp.getServices().get(a - 1).getServicesIndustry();
				comp.setServiceslist(serviceslist);

			}
			return ResponseEntity.ok().body(comp);
		}
		return null;
	}

	@SuppressWarnings("unused")
	@PostMapping("/chartdata")
	public void charttable1(@RequestParam("val") String data, HttpServletRequest request) throws IOException
	{
		@SuppressWarnings("resource")
		XMLSlideShow ppt = new XMLSlideShow();
		String[] str = data.split("raghu");
		for (int i = 0; i < str.length; i++)
		{
			byte[] imagedata = DatatypeConverter.parseBase64Binary(str[i].substring(str[i].indexOf(',') + 1));
			XSLFSlide slide = ppt.createSlide();
			XSLFPictureData pd = ppt.addPicture(imagedata, XSLFPictureData.PictureType.PNG);
			XSLFPictureShape pic = slide.createPicture(pd);
			String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/");
			OutputStream os = new FileOutputStream(dataDirectory + "AllChar199.pptx");
			ppt.write(os);
		}
	}

	@GetMapping("/downloads")
	public void charttable(HttpServletResponse response, HttpServletRequest request) throws IOException
	{
		String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/");
		String fileName = "AllChar199.pptx";
		Path file = Paths.get(dataDirectory, fileName);
		if (Files.exists(file))
		{
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			try
			{
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			}
			catch (Exception ex)
			{
				OUT.debug("Exception Occurs");
			}
		}

	}

	@GetMapping("/email")
	public String email111(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("message") String sub)
	{
		final String username = "raghunagaraj8760@gmail.com";
		final String password = "kjasghThayammafljkahgdsfjhgdjsfh".substring(6, 14);
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator()
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(username, password);
			}
		});

		try
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(sub);
			message.setText("PFA");
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			messageBodyPart = new MimeBodyPart();
			String file = "C:\\Users\\RAGHU\\Desktop\\CompanyMaster (25).xls";
			String fileName = "CompanyMaster (25).xls";
			DataSource source = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
		}
		catch (MessagingException e)
		{
			OUT.debug("Exception Occurs");
		}
		return SUMMARY;
	}

	@GetMapping("/card1")
	@ResponseBody
	public String card1(Model m, HttpServletRequest request)
	{
		List<CompanyTO> a = companyMapper.getcard1();
		return a.get(0).getEmpSize();
	}

	@GetMapping("/card2")
	@ResponseBody
	public int card2(Model m, HttpServletRequest request)
	{
		List<CompanyTO> a = companyMapper.getcard2();
		float f=Float.parseFloat(a.get(0).getEmpSize());
		return (int)f;
	}

	@GetMapping("/card3")
	@ResponseBody
	public String card3(Model m, HttpServletRequest request)
	{
		List<CompanyTO> a = companyMapper.getcard3();
		return a.get(0).getEmpSize();
	}

	@GetMapping("/card4")
	@ResponseBody
	public String card4(Model m, HttpServletRequest request)
	{
		List<CompanyTO> a = companyMapper.getcard4();
		return a.get(0).getEmpSize();
	}

}