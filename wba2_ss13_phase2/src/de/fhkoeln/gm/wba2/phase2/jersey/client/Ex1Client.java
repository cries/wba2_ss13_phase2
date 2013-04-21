package de.fhkoeln.gm.wba2.phase2.jersey.client;

import java.net.URL;
import java.util.Scanner;

public class Ex1Client {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new URL("http://localhost:4711/temperatur/kueche").openStream());
		while(scanner.hasNextLine()){
			System.out.println(scanner.nextLine());
		}
		scanner.close();
	}
}
