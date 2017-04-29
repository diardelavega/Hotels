package demo;

import java.io.FileNotFoundException;
import java.io.IOException;

import operations.ReadHotelFiles;

public class Demo {

	public static void main(String[] args) throws IOException {
		ReadHotelFiles rhf = new ReadHotelFiles();
		rhf.readHotelData();

	}

}
