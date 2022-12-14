package com.spring.file.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.spring.file.model.Employee;

public class CSVHelper {
	public static String TYPE = "text/csv";
	  static String[] HEADERs = { "id", "name", "lastName", "email","phone" };

	  public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static List<Employee> csvToTutorials(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<Employee> empList = new ArrayList<Employee>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	      for (CSVRecord csvRecord : csvRecords) {
	    	  System.out.println(csvRecord.get(0));// 
	    	  Employee emp = new Employee(
	              Long.parseLong(csvRecord.get("id")),
	              csvRecord.get("name"),
	              csvRecord.get("lastName"),
	              csvRecord.get("email"),
	              Integer.parseInt(csvRecord.get("phone"))// "123" -> 123
	            );

	    	  empList.add(emp);
	      }

	      return empList;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }

	  public static ByteArrayInputStream tutorialsToCSV(List<Employee> emplist) {
	    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL).withHeader(HEADERs);

	    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
	        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
	      for (Employee emp : emplist) {
	        List<String> data = Arrays.asList(
	              String.valueOf(emp.getId()),// 123l-> "123"
	              emp.getName(),
	              emp.getLastName(),
	              emp.getEmail(),
	              String.valueOf(emp.getPhone())// 123 -> "123"
	            );

	        csvPrinter.printRecord(data);
	      }

	      csvPrinter.flush();
	      return new ByteArrayInputStream(out.toByteArray());
	    } catch (IOException e) {
	      throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
	    }
	  }

}
