package laofuzi.java.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ExcelSample {
	public static void main(String[] args) throws IOException {
		Reader in = new InputStreamReader(new FileInputStream("/Users/lx/data/tuopin.csv"),"utf-8");
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
		for (CSVRecord record : records) {
			System.out.print(record.get(0));
			System.out.print(record.get(1));
			System.out.print(record.get(2));
			System.out.println(record.get(3));
		}
	}
}
