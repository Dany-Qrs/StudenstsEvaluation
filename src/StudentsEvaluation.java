/*Program name: Students evaluation*/
//Author´s name:  Dani Cruz
/*Present statistics for a section of 20 students:
min, max, avg, most repeated, less repeated.
 We need to input your name and your evaluation result*/
import java.io.FileWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StudentsEvaluation {

    public static void welcomePrint(){
        String myWelcome = "";
        myWelcome += " _  _     ___     ___      _     ___      ___\n";
        myWelcome += "| |/ /  /  _  \\  |  _ \\   | |  /  _  \\  /  _  \\\n";
        myWelcome += "|   /   | | | |  | | \\ \\  | |  | | |_|  | | | |\n";
        myWelcome += "|  /    | | | |  | |  | | | |  | |      | | | |\n";
        myWelcome += "|  \\    | | | |  | |  | | | |  | |___   | | | |\n";
        myWelcome += "|   \\   |  _  |  | |_/ /  | |  | |   |  |  _  |\n";
        myWelcome += "|_|\\_\\  \\_____/  |____/   |_|   \\____/  \\_____/\n";
        System.out.println(myWelcome);
    }

    public static boolean textValid(String text, String cad){
        String regex = cad.equals("cad") ? "^\\S[\\w\\s]+":"^[0-9]\\.[0-9]{1,2}|^[0-9]|^10|^10.00|^10.0";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    public static void infoRequest(int numStudents,List<String> names, List<Double> scores){
        Scanner scan = new Scanner(System.in);
        String name, score;
        int iterator = 1;
        while (iterator <= numStudents){
            System.out.println(iterator+") _________________________________________");
            System.out.print("Name: ");
            name = scan.next();
            System.out.print("Score: ");
            score = scan.next();
            if(textValid(name,"cad") && textValid(score, "decimal")){
                names.add(name);
                scores.add(Double.parseDouble(score));
                iterator ++;
            }else{
                System.out.println("The name or score is incorrect");
            }
        }
    }

    public static List<Double> arraySort(int numStudents, List<Double> scores){
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
        bodyInfo.append("Most repeat: ").append(maxScoreRepeat).append("\t");
        bodyInfo.append("Less repeat: ").append(minScoreRepeat).append("\t");
        return bodyInfo;
    }

    public static StringBuilder infoBuilder(List<String> names, List<Double> scores, double maxScore, double minScore, double avgScore, String repeatScore){
        StringBuilder bodyInfo = new StringBuilder();
        Iterator<String> name = names.iterator();
        Iterator<Double> score = scores.iterator();
        bodyInfo.append("NAME\tEVALUATION RESULT\n");
        while (name.hasNext() && score.hasNext()){
            bodyInfo.append(name.next()).append("\t");
            bodyInfo.append(score.next()).append("\n");
        }
        bodyInfo.append("\nSTATISTICS\n");
        bodyInfo.append("Avg: ").append(avgScore).append("\t");
        bodyInfo.append("Min: ").append(minScore).append("\t");
        bodyInfo.append("Max: ").append(maxScore).append("\t");
        bodyInfo.append(repeatScore);
        return bodyInfo;
    }

    public static  void generateFile(List<String> names, List<Double> scores, double maxScore, double minScore, double avgScore, String repeatScore){
        try {
            FileWriter dataFile = new FileWriter("nuevo.txt");
            dataFile.write(infoBuilder(names, scores, maxScore, minScore, avgScore, repeatScore).toString());
            dataFile.close();
        } catch (Exception e) {
            System.out.println("File error: "+e.getMessage());
        }
    }

    public static void main(String[] args){
        welcomePrint();
        Scanner scan = new Scanner(System.in);
        List<Double> scores = new ArrayList<>();
        List<Double> scoreSort;
        List<String> names = new ArrayList<>();
        String mostLessScore;
        double minScore, maxScore, avgScore;
        int numStudents;
        System.out.print("Numbers of Students: ");
        numStudents = scan.nextInt();
        infoRequest(numStudents, names, scores);
        scoreSort = arraySort(numStudents, scores);
        maxScore = scoreSort.get(scoreSort.size()-1);
        minScore = scoreSort.get(0);
        avgScore = avgFind(numStudents, scores);
        mostLessScore = repeatFind(scores).toString();
        generateFile(names, scores, maxScore, minScore, avgScore, mostLessScore);
    }
}
