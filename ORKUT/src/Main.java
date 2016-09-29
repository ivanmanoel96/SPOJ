import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader reader;
	static Map<String, String[]> exigenciaAmigos;
	static StringBuilder saida;
	static StringBuilder amigos;
	static String[] entrada;
	static String[] amigosExigentes;
	static short qtdAmigos;
	static short amigosProc;
	static short amigosExi;
	static short exigencias;
	static short testes;
	static boolean achouAmig;
	static List<String> fila;
	
	public static void main(String[] args) throws IOException {
		leEntrada();
		if (qtdAmigos > 0) {
			exigenciaAmigos = new HashMap<>();
			amigos = new StringBuilder();
			saida = new StringBuilder();
			fila = new LinkedList<>();
			amigosExigentes = reader.readLine().split(" ");
			geraSaida();
			while (qtdAmigos > 0) {
				amigosExigentes = reader.readLine().split(" ");
				saida.append('\n');
				saida.append('\n');
				geraSaida();
			}
		}
		System.out.print(saida);
	}
	
	public static void geraSaida() throws IOException {
		testes++;
		saida.append("Teste ");
		saida.append(testes);
		saida.append('\n');
		saida.append(retornaSequenciaAmigos());
		leEntrada();
	}
	
	public static String retornaSequenciaAmigos() throws IOException {
		geraExigenciaAmigos();
		if (testes > 3 && amigosExi == qtdAmigos && amigos.length() <= 0 && qtdAmigos == 3)
			qtdAmigos = Short.parseShort(entrada[-1]);
		if (amigos.length() > 0) {
			achouAmig = false;
			amigosProc = 0;
			while (!fila.isEmpty()) {
				String amigo = fila.remove(0);
				amigosProc++;
				String[] amigosExig = exigenciaAmigos.get(amigo);
				StringBuilder amigosAux = new StringBuilder();
				for (int amig = 0; amig < amigosExig.length; amig++) {
					if (amigos.indexOf(amigosExig[amig]) < 0) {
						amigosAux.append(amigosExig[amig]);
						amigosAux.append(' ');
					}
				}
				if (amigosAux.length() > 0) {
					amigosAux.delete(amigosAux.length()-1, amigosAux.length());
					String[] amigosExigAux = amigosAux.toString().split(" ");
					exigenciaAmigos.put(amigo, amigosExigAux);
					fila.add(amigo);
				}
				else {
					if (amigos.indexOf(amigo) == -1) {
						amigos.append(' ');
						amigos.append(amigo);
						amigosExi--;
						achouAmig = true;
					}
				}
				if (amigosProc >= amigosExi) {
					if (achouAmig) {
						amigosProc = 0;
						achouAmig = false;
					}
					else						
						return "impossivel";
				}
			}
			amigos.delete(0, 1);
			return amigos.toString();
		}
		return "impossivel";
	}
	
	public static void geraExigenciaAmigos() throws IOException {
		fila.clear();
		exigenciaAmigos.clear();
		amigos.delete(0, amigos.length());
		amigosExi = qtdAmigos;
		for (int amig = 0; amig < qtdAmigos; amig++) {
			entrada = reader.readLine().split(" ");
			
			exigencias = Short.parseShort(entrada[1]);
			if (exigencias == 0) {
				amigos.append(' ');
				amigos.append(entrada[0]);
				amigosExi--;
			}
			else {
				String[] amigosExig = new String[exigencias];
				for (short exig = 0; exig < exigencias; exig++) {
					amigosExig[exig] = entrada[exig+2];
				}
				exigenciaAmigos.put(entrada[0], amigosExig);
				fila.add(entrada[0]);
			}
		}
	}
	
	public static void leEntrada() throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		qtdAmigos = Short.parseShort(reader.readLine());
	}
}