import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader reader;
	static String[] entrada;
	static StringBuilder saida;
	static short[] numPedagios;
	static short[] cidadesSort;
	static List<Short> fila;
	static Map<Short, short[]> adjacencias;
	static short cidades;
	static short estradas;
	static short cidadeSaida;
	static short pedagios;
	static short testes;
	static short cidadePai;
	static short cidadeFilho;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		leEntrada();
		if (cidades > 0) {
			saida = new StringBuilder();
			fila = new LinkedList<>();
			adjacencias = new HashMap<Short, short[]>();
			geraPegadios();
			while (cidades > 0) {
				saida.append('\n');
				saida.append('\n');
				geraPegadios();
			}
		}
		System.out.print(saida);
	}
	
	public static void leEntrada() throws IOException {
		entrada = reader.readLine().split(" ");
		cidades = Short.parseShort(entrada[0]);
		estradas = Short.parseShort(entrada[1]);
		cidadeSaida = Short.parseShort(entrada[2]);
		pedagios = Short.parseShort(entrada[3]);
	}
	
	public static void geraPegadios() throws IOException {
		geraAdjacencias();
		numPedagios = new short[cidades+1];
		cidadesSort = new short[cidades+1];
		fila.add(cidadeSaida);
		while (!fila.isEmpty()) {
			cidadePai = fila.remove(0);
    		if (numPedagios[cidadePai] < pedagios) {
    			short[] adjacenc = adjacencias.get(cidadePai);
    			if (adjacenc != null) 
		    		for (short adj = 0; adj < adjacenc.length; adj++) {
		    			cidadeFilho = adjacenc[adj];
		    			if (cidadeFilho > 0 & cidadeFilho != cidadeSaida & cidadeFilho != cidadePai)
		    				if (numPedagios[cidadeFilho] == 0) {
		    					cidadesSort[cidadeFilho] = cidadeFilho;
			    				numPedagios[cidadeFilho] = (short)(numPedagios[cidadePai]+1);
				    			fila.add(cidadeFilho);
		    				}
		    		}
    		}
		}
		geraSaida();
	}
	
	public static void geraSaida() throws IOException {
		testes++;
		saida.append("Teste ");
		saida.append(testes);
		saida.append('\n');
		Arrays.sort(cidadesSort);
		for (short i = 0; i < cidadesSort.length; i++) 
			if (cidadesSort[i] > 0) {
	            saida.append(cidadesSort[i]);
	            saida.append(' ');
	        }
		saida.delete(saida.length()-1, saida.length());
		leEntrada();
	}
	
	public static void geraAdjacencias() throws IOException {
		adjacencias.clear();
		for (short estradaAtual = 0; estradaAtual < estradas; estradaAtual++) {
			entrada = reader.readLine().split(" ");
			cidadePai = Short.parseShort(entrada[0]);
			cidadeFilho = Short.parseShort(entrada[1]);
			insereAdjancenca(cidadePai, cidadeFilho);
			insereAdjancenca(cidadeFilho, cidadePai);
		}
	}
	
	public static void insereAdjancenca(short cidadePai, short cidadeFilho) {
		if (cidadeFilho != cidadeSaida) {
			short[] adjacenc = adjacencias.get(cidadePai);
			if (adjacenc == null)
				adjacenc = new short[cidades];
			
			for (short adj = 0; adj < adjacenc.length; adj++) {
				if (adjacenc[adj] == 0) {
					adjacenc[adj] = cidadeFilho;
					adjacencias.put(cidadePai, adjacenc);
					break;
				}				
				if (adjacenc[adj] == cidadeFilho)
					break;
			}
		}		
	}
}
