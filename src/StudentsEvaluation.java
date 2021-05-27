/*Program name: Students evaluation*/
//Author´s name:  Dani Cruz
/*Present statistics for a section of 20 students:
min, max, avg, most repeated, less repeated.
 We need to input your name and your evaluation result*/
import java.io.File;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class StudentsEvaluation {
    public static void welcomePrint(){
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
        List<String> names = new ArrayList<>();
        List<Double> scores = new ArrayList<>();
        Double minScore;
        Double maxScore;
        Double avgScore;
        Double mostRepeatScore;
        Double lessRepeatScore;
        int iterator = 1;

        while (iterator <= numStudents){
            System.out.println("_________________________________________________");
            System.out.print("Estudiante: ");
            names.add(scan.next());
            System.out.print("Puntuación: ");
            scores.add(scan.nextDouble());
            iterator ++;
        }

        System.out.println("¿Desea guardar los datos en un archivo de texto? presiona y/n");
        if(scan.next().equals("y")){
            try {
                FileWriter dataFile = new FileWriter("nuevo.txt");
                Iterator<String> name = names.iterator();
                Iterator<Double> score = scores.iterator();
                Double acumAvg = 0.0;
                Double[] mostRepeat;
                for(int i=0; i< scores.size(); i++){
                    System.out.println(scores.get(i));
                }
                StringBuilder bodyInfo = new StringBuilder();
                bodyInfo.append("Student Score\n");
                while (name.hasNext() && score.hasNext()){
                    bodyInfo.append(name.next()+" ");
                    bodyInfo.append(score.next()+"\n");
                }
                avgScore = acumAvg / numStudents;

                bodyInfo.append("\nSTADISTICS\n");
                bodyInfo.append("Avg: "+avgScore+"\t");

                dataFile.write(bodyInfo.toString());
                dataFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
