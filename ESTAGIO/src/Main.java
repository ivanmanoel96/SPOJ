import java.io.*;

public class Main {
	static BufferedReader reader;
	static short alunos;
	static short turmas;
	static boolean primeiro;
	static short media;
	static short maiorMedia;
	static StringBuilder saida;
	static StringBuilder alunosTurma;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		saida = new StringBuilder();
		alunosTurma = new StringBuilder();
		primeiro = true;
		geraMedias();
	}
	
	public static void geraMedias() throws IOException {
		alunos = Short.parseShort(reader.readLine());
		if (alunos == 0) {
			System.out.println(saida);
			System.exit(0);
		}
		if (!primeiro) {
			saida.append('\n');
			saida.append('\n');
		}
		turmas++;
		saida.append("Turma ");
		saida.append(turmas);
		saida.append('\n');
		String[] entrada;
		maiorMedia = 0;
		for (short alun = 0; alun < alunos; alun++) {
			entrada = reader.readLine().split(" ");
			media = Short.parseShort(entrada[1]);
			if (media <= 100 & media >= maiorMedia) {
				if (media > maiorMedia)
					alunosTurma.delete(0, alunosTurma.length());
				
				maiorMedia = media;
				alunosTurma.append(Short.parseShort(entrada[0]));
				alunosTurma.append(' ');
			}
		}
		alunosTurma.delete(alunosTurma.length()-1, alunosTurma.length());
		saida.append(alunosTurma);
		primeiro = false;
		geraMedias();
	}
}