import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader reader;
	static StringBuilder saida;
	static String[] entrada;	
	static short amigos;
	static short exigencias;
	static short testes;
	static String[] pessoas;
	static List<String> fila;
	static StringBuilder exigenciasPessoa;
	static StringBuilder sequenciaAmigos;

	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		
		lePessoas();
		if (amigos > 0) {
			saida = new StringBuilder();
			exigenciasPessoa = new StringBuilder();
			sequenciaAmigos = new StringBuilder();
			fila = new LinkedList<>();
			geraSaida();
			while (amigos > 0) {				
				saida.append('\n');
				saida.append('\n');
				exigenciasPessoa.delete(0, exigenciasPessoa.length());
				sequenciaAmigos.delete(0, sequenciaAmigos.length());
				fila.clear();
				geraSaida();				
			}
		}
		System.out.print(saida);
	}
	
	public static void geraPessoas() throws IOException {
		entrada = reader.readLine().split(" ");
	}	
	
	public static void geraSaida() throws IOException {
		testes++;
		saida.append("Teste ");
		saida.append(testes);
		saida.append('\n');
		saida.append(retornaSequenciaAmigos());		
		lePessoas();
	}
	
	public static String retornaSequenciaAmigos() throws IOException {
		geraAmigos();
		if (!fila.isEmpty()) {
			while (!fila.isEmpty()) {
				String nomeAmigo = fila.remove(0);
				sequenciaAmigos.append(nomeAmigo);
				sequenciaAmigos.append(' ');
				String exigencias = exigenciasPessoa.toString();
				if (exigencias.length() > 0) {
					exigencias = exigencias.replace(' '+nomeAmigo+' ', "");
					exigenciasPessoa.delete(0, exigenciasPessoa.length());
					entrada = exigencias.split("\n");
					for (short pessoa = 0; pessoa < entrada.length; pessoa++) {
						String[] exigenciasAmigo = entrada[pessoa].toString().split(" ");
						if (exigenciasAmigo.length == 1)
							fila.add(exigenciasAmigo[0]);
						else {
							exigenciasPessoa.append(entrada[pessoa]);
							exigenciasPessoa.append('\n');
						}
					}
				}
			}
			if (exigenciasPessoa.length() > 0)
				return "impossivel";
			
			sequenciaAmigos.delete(sequenciaAmigos.length()-1, sequenciaAmigos.length());
			return sequenciaAmigos.toString();
		}
		return "impossivel";
	}
	
	public static void geraAmigos() throws IOException {
		pessoas = reader.readLine().split(" ");
		geraExigencias();
	}
	
	public static void geraExigencias() throws IOException {
		for (short pessoa = 0; pessoa < amigos; pessoa++) {
			entrada = reader.readLine().split(" ");
			exigencias = Short.parseShort(entrada[1]);		
			if (exigencias > 0) {
				exigenciasPessoa.append(entrada[0]);
				for (short exigencia = 0; exigencia < exigencias; exigencia++) {
					exigenciasPessoa.append(' ');
					exigenciasPessoa.append(entrada[exigencia+2]);
					exigenciasPessoa.append(' ');
				}
				exigenciasPessoa.append('\n');
			}
			else
				fila.add(entrada[0]);
		}
	}
	
	public static void lePessoas() throws IOException {		
		amigos = Short.parseShort(reader.readLine());
	}
}
