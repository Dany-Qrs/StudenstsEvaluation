/*Program name: Students evaluation*/
//Author´s name:  Dani Cruz
/*Present statistics for a section of 20 students:
min, max, avg, most repeated, less repeated.
 We need to input your name and your evaluation result*/
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StudentsEvaluation {
    private static final Scanner scan = new Scanner(System.in);
    public static void printWelcome(){
        //variable for concatenation of "Kodigo"
        String myWelcome = "";
        myWelcome += " _  _     ___     ___      _     ___      ___\n";
        myWelcome += "| |/ /  /  _  \\  |  _ \\   | |  /  _  \\  /  _  \\\n";
        myWelcome += "|   /   | | | |  | | \\ \\  | |  | | |_|  | | | |\n";
        myWelcome += "|  /    | | | |  | |  | | | |  | |      | | | |\n";
        myWelcome += "|  \\    | | | |  | |  | | | |  | |___   | | | |\n";
        myWelcome += "|   \\   | !_! |  | |_/ /  | |  | |   |  | !_! |\n";
        myWelcome += "|_|\\_\\  \\ ___ /  |____/   |_|   \\____/  \\ ___ /\n";
        System.out.println(myWelcome);
    }
    public static void clearScreen(){
        //bucle for print spaces
        for(int i=0; i<50; i++){
            System.out.println();
        }
    }
    public static String chooseMenuOption(){
        //variable to get the menu option
        String option;
        System.out.println("GRADE EVALUATION");
        System.out.println("CHOOSE A OPTION");
        System.out.println("To save data press 1");
        System.out.println("To print statistics press 2");
        System.out.println("To exit the menu press 3");
        option = scan.nextLine();
        return option;
    }
    public static  boolean toAsk(String text){
        //variable to return True if it matches regex
        String exit;
        System.out.print("Do you want " + text +"? (y/yes) ");
        exit = scan.nextLine();
        Pattern pattern = Pattern.compile("^[Yy]([eE][sS])?|^[sS][iI]?");
        Matcher matcher = pattern.matcher(exit);
        return matcher.matches();
    }
    public static boolean validate(String text, String dataType){
        String regex = "";
        //regex matches names("Daniela", "Daniela Cruz", etc), decimals 0.1-10.00 and integer
        switch (dataType){
            case "capital" -> regex = "^([A-ZÁ-Ú][a-záéíóú]+)(\\s[A-ZÁ-Ú][a-záéíóú]+)*";
            case "decimal" -> regex = "^[0-9]\\.[0-9]{1,2}|^[0-9]|^10|^10.00|^10.0";
            case "number" -> regex = "^[0-9]+";
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
    public static StringBuilder sortScores(int len, List<Double> scores){
        StringBuilder bodyInfo = new StringBuilder();
        Double[] sorted = new Double[len];
        //convert ArrayList to Array
        scores.toArray(sorted);
        //bubble method
        for(int i=0; i < len -1; i++){
            for(int j=i+1; j< len; j++){
                if(sorted[i] > sorted[j]){
                    //auxiliary for not lose the value
                    double aux = sorted[i];
                    sorted[i]= sorted[j];
                    sorted[j] = aux;
                }
            }
        }
        bodyInfo.append("Min: ").append(sorted[0]).append("\t");
        bodyInfo.append("Max: ").append(sorted[len-1]).append("\t");
        return bodyInfo;
    }
    public static String findAvg(int numStudents,List<Double> scores){
        double avg = 0;
        String bodyInfo;
        for(double score: scores){
            avg += score;
        }
        //after accumulating, average grades
        bodyInfo = "Avg: "+String.format("%.2f", avg/numStudents)+"\t";
        return bodyInfo;
    }
    public static StringBuilder findRepeated(List<Double> scores){
        StringBuilder bodyInfo = new StringBuilder();
        Map<Double, Integer> repeated = new HashMap<>();
        int maxRepeated;
        int minRepeated;
        //if the key exists, accumulate, else initialize
        for(double aux: scores){
            if(repeated.containsKey(aux)){
                repeated.put(aux, repeated.get(aux)+1);
            }else{
                repeated.put(aux, 1);
            }
        }
        //find the maximum value
        maxRepeated = Collections.max(repeated.values());
        //find the minimum value
        minRepeated = Collections.min(repeated.values());
        //find the key of the maximum value
        List<Double> mostRepeat = repeated.entrySet().stream().filter(x -> Objects.equals(x.getValue(), maxRepeated))
                .map(Map.Entry::getKey).collect(Collectors.toList());
        //find the key of the minimum value
        List<Double> lessRepeat = repeated.entrySet().stream().filter(x -> Objects.equals(x.getValue(), minRepeated))
                .map(Map.Entry::getKey).collect(Collectors.toList());
        bodyInfo.append("Most repeat: ");
        //iterate to print in order (the most repeat)
        for (double most: mostRepeat){
            bodyInfo.append(most).append(", ");
        }
        bodyInfo.append("\tLess repeat: ");
        //iterate to print in order (the less repeat)
        for(double less: lessRepeat){
            bodyInfo.append(less).append(", ");
        }
        //iterate to print in order (all repeats)
        bodyInfo.append("\nRepetitions:\n");
        for (Map.Entry<Double, Integer> rep: repeated.entrySet()){
            bodyInfo.append(rep.getKey()).append(" :: ").append(rep.getValue()).append(" a times\n");
        }
        return bodyInfo;
    }
    public static StringBuilder saveListNames(List<String> names, List<Double> scores){
        StringBuilder bodyInfo = new StringBuilder();
        Iterator<String> name = names.iterator();
        Iterator<Double> score = scores.iterator();
        bodyInfo.append("NAME\tEVALUATION RESULT\n");
        //iterate to print in order (names, scores)
        while (name.hasNext() && score.hasNext()){
            bodyInfo.append(name.next()).append("\t");
            bodyInfo.append(score.next()).append("\n");
        }
        bodyInfo.append("\nSTATISTICS\n");
        return bodyInfo;
    }
    public static  void generateFile(String bodyInfo){
        try {
            if(toAsk("to save the data in a file")){
                FileWriter dataFile = new FileWriter("nuevo.txt");
                dataFile.write(bodyInfo);
                dataFile.close();
            }
        } catch (Exception e) {
            System.out.println("File error: "+e.getMessage());
        }
    }
    public static String requestData(){
        List<String> names = new ArrayList<>();
        List<Double> scores = new ArrayList<>();
        StringBuilder bodyInfo = new StringBuilder();
        int numStudents;
        int iterator = 1;
        String name;
        String score;
        System.out.print("Numbers of Students: ");
        name = scan.nextLine();
        //validate "numbers of students" to integer
        numStudents = validate(name, "number")? Integer.parseInt(name): 0;
        while (iterator <= numStudents){
            System.out.println(iterator+") _________________________________________");
            System.out.print("Name: ");
            name = scan.nextLine();
            System.out.print("Score: ");
            score = scan.nextLine();
            //validate "name" and "score"
            if(validate(name,"capital") && validate(score, "decimal")){
                scores.add(Double.parseDouble(score));
                names.add(name);
                iterator ++;
            }else{
                System.out.println("The name or score is incorrect");
            }
        }
        //processing statistics
        if(numStudents > 0){
            bodyInfo.append(saveListNames(names, scores));
            bodyInfo.append(findAvg(numStudents, scores));
            bodyInfo.append(sortScores(numStudents, scores));
            bodyInfo.append(findRepeated(scores));
            //generate a text file
            generateFile(bodyInfo.toString());
            return bodyInfo.toString();
        }
        return "";
    }

    public static void main(String[] args){
        //variable for print data
        String statistics = "";
        String option;
        printWelcome();
        while(true){
            option = chooseMenuOption();
            clearScreen();
            switch (option){
                            //fill the variable for print data
                case "1" : statistics = requestData();
                break;
                case "2" : if(statistics.equals("")) System.out.println("No data to display");
                            else System.out.println(statistics);
                break;
                case "3" : if(toAsk("to exit the program")) System.exit(0);
                break;
                default : System.out.println("The option is incorrect");
                break;
            }
            if(toAsk("to clean the screen")){
                clearScreen();
            }
        }
    }
}
