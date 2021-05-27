/*Program name: Students evaluation*/
//Author´s name:  Dani Cruz
/*Present statistics for a section of 20 students:
min, max, avg, most repeated, less repeated.
 We need to input your name and your evaluation result*/
import java.io.File;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

public class StudentsEvaluation {
    public static void printWelcome(){
        StringBuilder myWelcome = new StringBuilder();
        myWelcome.append(" _  _    ____    ___      _     ___     ____\n");
        myWelcome.append("| |/ /  | || |  |  _ \\   | |  /  _  \\  | || |\n");
        myWelcome.append("|   /   | || |  | | \\ \\  | |  | | |_|  | || |\n");
        myWelcome.append("|  /    | || |  | |  | | | |  | |      | || |\n");
        myWelcome.append("|  \\    | || |  | |  | | | |  | |___   | || |\n");
        myWelcome.append("|   \\   | || |  | |_/ /  | |  | |   |  | || |\n");
        myWelcome.append("|_|\\_\\  |____|  |____/   |_|   \\____/  |____|\n");
        System.out.println(myWelcome);
    }
    public static void infoRequest(){

        System.out.println("");
    }
    public static void main(String[] args){
        //printWelcome();
        Scanner scan = new Scanner(System.in);
        System.out.print("Cantidad de alumnos: \t");
        int numStudents = scan.nextInt();
        Map<Integer, Map> students = new HashMap<Integer, Map>();
        int iterator = 1;

        while (iterator <= numStudents){
            Map<String, Double> student = new HashMap<String, Double>();
            String name; Double score;
            System.out.println("_________________________________________________");
            System.out.print("Estudiante: ");
            name =  scan.next();
            System.out.print("Puntuación: ");
            score = scan.nextDouble();
            student.put(name, score);
            students.put(iterator, student);
            iterator ++;
        }

        System.out.println("¿Desea guardar los datos en un archivo de texto? presiona y/n");
        if(scan.next().equals("y")){
            try {
                FileWriter dataFile = new FileWriter("nuevo.txt");
                for(Map.Entry<Integer, Map> entry: students.entrySet()){
                    for(Object r: entry.getValue().entrySet()){
                        System.out.println();
                        dataFile.write(r.toString()+"\n");
                    }
                }
                dataFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
