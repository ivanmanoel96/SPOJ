import java.io.*;

public class Main {
	static BufferedReader reader;
	static short[] numVoos;
	static String[] entrada;
	static short aeroportos;
	static short voos;
	static short numTeste;
	static short maiorCong;
	static StringBuilder saida;
	static StringBuilder voosCong;
	static boolean primeiro;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));		
		
		saida = new StringBuilder();
		voosCong = new StringBuilder();
		numVoos = new short[101];
		
		entrada = reader.readLine().split(" ");
		aeroportos = Short.parseShort(entrada[0]);
		voos = Short.parseShort(entrada[1]);
		
		primeiro = true;
		while (voos > 0) {
			if (!primeiro) {
				saida.append('\n');
				saida.append('\n');
			}
			primeiro = false;
			numTeste++;		
			saida.append("Teste ");
			saida.append(numTeste);
			saida.append('\n');
			
			for (short aero = 0; aero < voos; aero++) {
				entrada = reader.readLine().split(" ");
				
				numVoos[Short.parseShort(entrada[0])]++;
				numVoos[Short.parseShort(entrada[1])]++;
			}
			
			maiorCong = 0;
			for (short aero = 1; aero <= aeroportos; aero++) {
				if (numVoos[aero] >= maiorCong) {
					if (numVoos[aero] > maiorCong) {
						voosCong.delete(0, voosCong.length());
						maiorCong = numVoos[aero];
					}
					
					voosCong.append(aero);
					voosCong.append(' ');
				}
				numVoos[aero] = 0;
			}
			voosCong.delete(voosCong.length()-1, voosCong.length());
			saida.append(voosCong);
			
			entrada = reader.readLine().split(" ");			
			aeroportos = Short.parseShort(entrada[0]);
			voos = Short.parseShort(entrada[1]);
		}
		System.out.println(saida);
	}
}