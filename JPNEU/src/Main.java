import java.io.*;

public class Main {
	static BufferedReader reader;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(Short.parseShort(reader.readLine())-Short.parseShort(reader.readLine()));
	}

}