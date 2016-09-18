import java.io.*;

public class Main {
	static BufferedReader reader;
	static String[] entrada;
	static short pedagios;
	static short quilometros;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));		

		entrada = reader.readLine().split(" ");		
		quilometros = Short.parseShort(entrada[0]);
		pedagios = (short)(quilometros/Short.parseShort(entrada[1]));
		
		entrada = reader.readLine().split(" ");
		System.out.print(quilometros*Short.parseShort(entrada[0])+pedagios*Short.parseShort(entrada[1]));
	}
}