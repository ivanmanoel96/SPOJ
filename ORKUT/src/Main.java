import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader reader;
	static Map<String, String[]> amigoExigencias;
	static StringBuilder saida;
	static StringBuilder listaPessoas;
	static StringBuilder listaAmigos;
	static StringBuilder listaExigenciasAmigo;
	static String[] entrada;	
	static String[] exigenciasAmigo;
	static short pessoas;
	static short exigencias;
	static short testes;
	static boolean achouAmigo;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		
		leEntrada();
		if (pessoas > 0) {
			amigoExigencias = new HashMap<>();
			saida = new StringBuilder();
			listaAmigos = new StringBuilder();
			listaExigenciasAmigo = new StringBuilder();
			geraSaida();
			while (pessoas > 0) {				
				saida.append('\n');
				saida.append('\n');
				listaAmigos.delete(0, listaAmigos.length());
				geraSaida();				
			}
		}
		System.out.print(saida);
	}
	
	public static void geraPessoas() throws IOException {
		entrada = reader.readLine().split(" ");
	
		listaPessoas = new StringBuilder();
		for (int pessoa = 0; pessoa < entrada.length; pessoa++) {
			listaPessoas.append(entrada[pessoa]);
			listaPessoas.append(" ");
		}
	}	
	
	public static void geraSaida() throws IOException {
		testes++;
		saida.append("Teste ");
		saida.append(testes);
		saida.append('\n');
		saida.append(retornaSequencia());
		leEntrada();
	}
	
	public static String retornaSequencia() throws IOException {
		geraPessoas();
		geraExigencias();
		
		if (!achouAmigo)
			return "impossivel";
		
		String amigo;
		while (listaPessoas.length() > 0) {
			achouAmigo = false;
			entrada = listaPessoas.toString().split(" ");
			for (int pessoa = 0; pessoa < entrada.length; pessoa++) {
				amigo = entrada[pessoa];
				exigenciasAmigo = amigoExigencias.get(amigo);
				
				for (int amigoExigencia = 0; amigoExigencia < exigenciasAmigo.length; amigoExigencia++) {
					if (listaAmigos.indexOf(exigenciasAmigo[amigoExigencia]) < 0) {
						listaExigenciasAmigo.append(exigenciasAmigo[amigoExigencia]);
						listaExigenciasAmigo.append(' ');
					}
					else
						achouAmigo = true;
				}
				
				if (listaExigenciasAmigo.length() > 0) {
					listaExigenciasAmigo.delete(listaExigenciasAmigo.length()-1, listaExigenciasAmigo.length());
					exigenciasAmigo = listaExigenciasAmigo.toString().split(" ");
					amigoExigencias.put(amigo, exigenciasAmigo);
					listaExigenciasAmigo.delete(0, listaExigenciasAmigo.length());
				}
				else
					if (listaAmigos.indexOf(amigo) == -1) {						
						listaAmigos.append(amigo);
						listaAmigos.append(' ');
						listaPessoas.delete(listaPessoas.indexOf(amigo), listaPessoas.indexOf(amigo) + amigo.length()+1);
					}
			}
			if (!achouAmigo)
				return "impossivel"; 
		}
		listaAmigos.delete(listaAmigos.length()-1, listaAmigos.length());
		return listaAmigos.toString();		
	}
	
	public static void geraExigencias() throws IOException {
		entrada = reader.readLine().split(" ");
		
		listaPessoas = new StringBuilder();
		for (int pessoa = 0; pessoa < entrada.length; pessoa++) {
			listaPessoas.append(entrada[pessoa]);
			listaPessoas.append(" ");
		}
		
		amigoExigencias.clear();
		achouAmigo = false;
		for (int pessoa = 0; pessoa < pessoas; pessoa++) {
			entrada = reader.readLine().split(" ");
			
			exigencias = Short.parseShort(entrada[1]);
			if (exigencias == 0) {
				achouAmigo = true;
				listaAmigos.append(entrada[0]);
				listaAmigos.append(" ");
				listaPessoas.delete(listaPessoas.indexOf(entrada[0]), listaPessoas.indexOf(entrada[0]) + entrada[0].length()+1);
			}
			else {
				exigenciasAmigo = new String[exigencias];
				for (short exigencia = 0; exigencia < exigencias; exigencia++)
					exigenciasAmigo[exigencia] = entrada[exigencia+2];

				amigoExigencias.put(entrada[0], exigenciasAmigo);
			}
		}
	}
	
	public static void leEntrada() throws IOException {
		pessoas = Short.parseShort(reader.readLine());
	}
}
