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
import java.util.stream.Collectors;

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

    public static void infoRequest(int numStudents,List<String> names, List<Double> scores){
        Scanner scan = new Scanner(System.in);
        int iterator = 0;
        while (iterator < numStudents){
            System.out.println("_________________________________________________");
            System.out.print("Estudiante: ");
            names.add(scan.next());
            System.out.print("Puntuación: ");
            scores.add(scan.nextDouble());
            iterator ++;
        }
    }

    public static List arraySort(int numStudents, List<Double> scores){
        List<Double> scoreSort = new ArrayList<>();
        Double[] scoreAux = new Double[numStudents];
        scoreAux= scores.toArray(scoreAux);
        for(int i=0; i< scoreAux.length-1; i++){
            for(int j=i+1; j< scoreAux.length; j++){
                if(scoreAux[i] > scoreAux[j]){
                    double aux = scoreAux[i];
                    scoreAux[i]= scoreAux[j];
                    scoreAux[j] = aux;
                }
            }
            scoreSort.add(scoreAux[i]);
        }
        return scoreSort;
    }

    public static double avgFind(int numStudents,List<Double> scores){
        double avg = 0;
        for(double score: scores){
            avg += score;
        }
        return avg / numStudents;
    }

    public static StringBuilder repeatFind(List<Double> scores){
        StringBuilder bodyInfo = new StringBuilder();
        Map<Double, Integer> repeatScore = new HashMap<>();
        int maxRepeat, minRepeat;
        for(double aux: scores){
            if(repeatScore.containsKey(aux)){
                repeatScore.put(aux, repeatScore.get(aux)+1);
            }else{
                repeatScore.put(aux, 1);
            }
        }
        maxRepeat = Collections.max(repeatScore.values());
        minRepeat = Collections.min(repeatScore.values());
        List<Double> maxScoreRepeat = repeatScore.entrySet().stream().filter(x -> Objects.equals(x.getValue(), maxRepeat))
                                    .map(Map.Entry::getKey).collect(Collectors.toList());
        List<Double> minScoreRepeat = repeatScore.entrySet().stream().filter(x -> Objects.equals(x.getValue(), minRepeat))
                .map(Map.Entry::getKey).collect(Collectors.toList());
        bodyInfo.append("Most repeat: "+maxScoreRepeat+"\t");
        bodyInfo.append("Less repeat: "+minScoreRepeat+ "\t");
        bodyInfo.append("repeat: "+repeatScore+ "\n");
        return bodyInfo;
    }

    public static StringBuilder infoBuilder(List<String> names, List<Double> scores, double maxScore, double minScore, double avgScore, String repeatScore){
        StringBuilder bodyInfo = new StringBuilder();
        Iterator<String> name = names.iterator();
        Iterator<Double> score = scores.iterator();
        bodyInfo.append("STUDENTS SCORES\n");
        while (name.hasNext() && score.hasNext()){
            bodyInfo.append(name.next()+" ");
            bodyInfo.append(score.next()+"\n");
        }
        bodyInfo.append("\nSTADISTICS\n");
        bodyInfo.append("Avg: "+avgScore+"\t");
        bodyInfo.append("Min: "+minScore+"\t");
        bodyInfo.append("Max: "+maxScore+"\t");
        bodyInfo.append(repeatScore);
        return bodyInfo;
    }

    public static  void generateFile(List<String> names, List<Double> scores, double maxScore, double minScore, double avgScore, String repeatScore){
        try {
            FileWriter dataFile = new FileWriter("nuevo.txt");
            dataFile.write(infoBuilder(names, scores, maxScore, minScore, avgScore, repeatScore).toString());
            dataFile.close();
        } catch (Exception e) {
            System.out.println("Se produjo un error al generar el archivo: "+e.getMessage());
        }
    }

    public static void main(String[] args){
        welcomePrint();
        // ESTE ES UN COMENTARIO PARA MICHAEL


        // COMENTARIO 22222222222222222222222 ***************************************************
        /*Scanner scan = new Scanner(System.in);
        List<Double> scores = new ArrayList<>();
        List<Double> scoreSort = new ArrayList<>();
        List<String> names = new ArrayList<>();
        String mostLessScore;
        double minScore, maxScore, avgScore;
        int numStudents;
        System.out.print("Cantidad de alumnos: ");
        numStudents = scan.nextInt();
        infoRequest(numStudents, names, scores);
        scoreSort = arraySort(numStudents, scores);
        maxScore = scoreSort.get(scoreSort.size()-1);
        minScore = scoreSort.get(0);
        avgScore = avgFind(numStudents, scores);
        mostLessScore = repeatFind(scores).toString();
        generateFile(names, scores, maxScore, minScore, avgScore, mostLessScore);*/
    }
}
