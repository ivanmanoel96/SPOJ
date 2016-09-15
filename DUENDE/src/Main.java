import java.io.*;

public class Main {
	static short[][] caverna;
	static short[] distancias;
	static StringBuilder fila;
	static short linhas;
	static short colunas;
	static short linhaAtual;
	static short colunaAtual;
	static BufferedReader reader;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		String[] entrada = reader.readLine().split(" ");
        short[] saida = new short[entrada.length];
        for (short column = 0; column < entrada.length; column++)
            saida[column] = Short.parseShort(entrada[column]);
		
		linhas = saida[0];
		colunas = saida[1];
        caverna = new short[linhas][colunas];
        
        for (short linha = 0; linha < linhas; linha++) {
        	entrada = reader.readLine().split(" ");
            saida = new short[entrada.length];
            for (short coluna = 0; coluna < entrada.length; coluna++) {
                saida[coluna] = Short.parseShort(entrada[coluna]);
                if (saida[coluna] == 3) {
    				linhaAtual = linha;
    				colunaAtual = coluna;
    			}
            }
            
            caverna[linha] = saida;
        }
        
        distancias = new short[Short.parseShort(linhas+""+colunas)];
		fila = new StringBuilder();
		fila.append(linhaAtual);
		fila.append(colunaAtual);
		fila.append(' ');
		
        while (fila.length() > 0) {        	
        	String result = fila.toString().split(" ")[0];
    		fila.delete(0, result.length()+1);
    		linhaAtual = Short.parseShort(String.valueOf(result.charAt(0)));
        	colunaAtual = Short.parseShort(String.valueOf(result.charAt(1)));
        	
        	if (linhaAtual-1 >= 0)
        		visitaProximaSala((short)(linhaAtual-1), colunaAtual);
        	
        	if (colunaAtual-1 >= 0)
        		visitaProximaSala(linhaAtual, (short)(colunaAtual-1));
        	
        	if (linhaAtual+1 < linhas)
        		visitaProximaSala((short)(linhaAtual+1), colunaAtual);
        	
        	if (colunaAtual+1 < colunas)
        		visitaProximaSala(linhaAtual, (short)(colunaAtual+1));
        }
    }
	
	public static void visitaProximaSala(short linha, short coluna) {
		if (caverna[linha][coluna] != 2 & caverna[linha][coluna] != 3) {
			distancias[Short.parseShort(linha+""+coluna)] = (short)(distancias[Short.parseShort(linhaAtual+""+colunaAtual)]+1);
			if (caverna[linha][coluna] == 0) {
				System.out.println(distancias[Short.parseShort(linha+""+coluna)]);
				System.exit(0);
			}
			fila.append(linha);
			fila.append(coluna);
			fila.append(' ');
			caverna[linha][coluna] = 3;
		}
	}
}