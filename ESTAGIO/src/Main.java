import java.io.*;

public class Main {
	static BufferedReader reader;
	static short alunos;
	static short turmas;
	static short media;
	static short maiorMedia;
	static StringBuilder saida;
	static StringBuilder alunosTurma;
	static String[] entrada;
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		leAlunos();
		if (alunos > 0) {
			saida = new StringBuilder();
			alunosTurma = new StringBuilder();
			avaliaMedia();
			while (alunos > 0) {
				saida.append('\n');
				saida.append('\n');
				avaliaMedia();
			}
		}
		System.out.print(saida);
	}
	
	public static void avaliaMedia() throws IOException {
		turmas++;
		saida.append("Turma ");
		saida.append(turmas);
		saida.append('\n');
		maiorMedia = -1;
		for (short aluno = 0; aluno < alunos; aluno++) {
			entrada = reader.readLine().split(" ");
			media = Short.parseShort(entrada[1]);
			if (media >= maiorMedia) {
				if (media > maiorMedia)
					alunosTurma.delete(0, alunosTurma.length());
				
				maiorMedia = media;
				alunosTurma.append(entrada[0]);
				alunosTurma.append(' ');
			}
		}
		alunosTurma.delete(alunosTurma.length()-1, alunosTurma.length());
		saida.append(alunosTurma);
		leAlunos();
	}
	
	public static void leAlunos() throws IOException {
		alunos = Short.parseShort(reader.readLine());
	}
}
