import java.io.*;

public class Main {
	static short[][] cave;
	static short[] distance;
	static StringBuilder queue;
	static short lines;
	static short columns;
	static short currentLine;
	static short currentColumn;
	static BufferedReader reader;
	static String result;
	
	public static void main(String[] args) throws IOException {
		readInput();
		
        distance = new short[lines+columns];
		queue = new StringBuilder();
		queue.append(currentLine);
		queue.append(currentColumn);
		queue.append(" ");
		
        while (queue.length() > 0) {        	
        	result = queue.toString().split(" ")[0];
    		queue.delete(0, result.length()+1);
    		currentLine = Short.parseShort(String.valueOf(result.charAt(0)));
        	currentColumn = Short.parseShort(String.valueOf(result.charAt(1)));
        	
        	if (currentColumn-1 >= 0)
        		visitNextRoom(currentLine, currentColumn-1);
        	
        	if (currentLine+1 < lines)
        		visitNextRoom(currentLine+1, currentColumn);
        	
        	if (currentColumn+1 < columns)
        		visitNextRoom(currentLine, currentColumn+1);

        	if (currentLine-1 >= 0)
        		visitNextRoom(currentLine-1, currentColumn);
        }
    }
	
	public static void readInput() throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		String[] input = reader.readLine().split(" ");
        
		lines = Short.parseShort(input[0]);
		columns = Short.parseShort(input[1]);
        cave = new short[lines][columns];
        
        for (short line = 0; line < lines; line++) {
        	input = reader.readLine().split(" ");
            short[] output = new short[input.length];
            for (short column = 0; column < input.length; column++) {
                output[column] = Short.parseShort(input[column]);
                if (output[column] == 3) {
    				currentLine = line;
    				currentColumn = column;
    			}
            }
            cave[line] = output;
        }
	}
	
	public static void visitNextRoom(int line, int column) {
		if (cave[line][column] != 3 & cave[line][column] != 2) {
			distance[line+column] = (short)(distance[currentLine+currentColumn]+1);
			if (cave[line][column] == 0) {
				System.out.println(distance[line+column]);
				System.exit(0);
			}
			queue.append(line);
			queue.append(column);
			queue.append(' ');
			cave[line][column] = 3;
		}
	}
}
