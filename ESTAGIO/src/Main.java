import java.io.*;

public class Main {
	static BufferedReader reader;
	static short alunos;
	static short turmas;
	static short media;
	static short maiorMedia;
	static StringBuilder saida;
	static String[] entrada;
	static short alunIni;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		saida = new StringBuilder();
		
		alunos = Short.parseShort(reader.readLine());		
		avaliaMedia();
		while (alunos > 0) {
			saida.append('\n');
			saida.append('\n');
			avaliaMedia();
		}
		System.out.print(saida);
	}
	
	public static void avaliaMedia() throws IOException {
		turmas++;
		saida.append("Turma ");
		saida.append(turmas);
		saida.append('\n');
		maiorMedia = -1;
		alunIni = (short)saida.length();
		for (short aluno = 0; aluno < alunos; aluno++) {
			entrada = reader.readLine().split(" ");
			media = Short.parseShort(entrada[1]);
			if (media >= maiorMedia) {
				if (media > maiorMedia)
					saida.delete(alunIni, saida.length());
				
				maiorMedia = media;
				saida.append(entrada[0]);
				saida.append(' ');
			}
		}
		saida.delete(saida.length()-1, saida.length());
		alunos = Short.parseShort(reader.readLine());
	}
}
