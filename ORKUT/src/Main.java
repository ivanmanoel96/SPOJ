import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader reader;
	static StringBuilder saida;
	static String[] entrada;	
	static short totalAmigos;
	static short exigencias;
	static short testes;
	static List<String> pessoas;
	static List<String> amigos;
	static StringBuilder exigenciasPessoa;
	static StringBuilder sequenciaAmigos;

	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		lePessoas();
		if (totalAmigos > 0) {
			saida = new StringBuilder();
			exigenciasPessoa = new StringBuilder();
			sequenciaAmigos = new StringBuilder();
			pessoas = new ArrayList<>();
			amigos = new LinkedList<>();
			geraSaida();
			while (totalAmigos > 0) {				
				saida.append('\n');
				saida.append('\n');
				exigenciasPessoa.delete(0, exigenciasPessoa.length());
				sequenciaAmigos.delete(0, sequenciaAmigos.length());
				pessoas.clear();				
				amigos.clear();
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
		if (amigos.size() > 0) {
			while (!amigos.isEmpty()) {
				String nomeAmigo = amigos.remove(0);
				sequenciaAmigos.append(nomeAmigo);
				sequenciaAmigos.append(" ");			
				String exigencias = exigenciasPessoa.toString();
				if (exigencias.length() > 0) {
					exigencias = exigencias.replace(" "+nomeAmigo+" ", "");					
					exigenciasPessoa.delete(0, exigenciasPessoa.length());
					entrada = exigencias.split("\n");
					for (short pessoa = 0; pessoa < entrada.length; pessoa++) {
						String[] exigenciasAmigo = entrada[pessoa].toString().split(";");
						if (exigenciasAmigo.length == 1)
							amigos.add(pessoas.get(Short.parseShort(exigenciasAmigo[0])));
						else {
							exigenciasPessoa.append(entrada[pessoa]);
							exigenciasPessoa.append("\n");
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
		entrada = reader.readLine().split(" ");
		for (short pessoa = 0; pessoa < entrada.length; pessoa++) {
			pessoas.add(entrada[pessoa]);
		}
		geraExigenciasAmigo();
	}
	
	public static void geraExigenciasAmigo() throws IOException {
		for (int pessoa = 0; pessoa < totalAmigos; pessoa++) {
			entrada = reader.readLine().split(" ");
			
			exigencias = Short.parseShort(entrada[1]);		
			if (exigencias > 0) {
				exigenciasPessoa.append(pessoas.indexOf(entrada[0]));
				exigenciasPessoa.append(";");
				for (int exigencia = 0; exigencia < exigencias; exigencia++) {
					exigenciasPessoa.append(" ");
					exigenciasPessoa.append(entrada[exigencia+2]);
					exigenciasPessoa.append(" ");
				}
				exigenciasPessoa.append("\n");
			}
			else
				amigos.add(entrada[0]);
		}
	}
	
	public static void lePessoas() throws IOException {		
		totalAmigos = Short.parseShort(reader.readLine());
	}
}
