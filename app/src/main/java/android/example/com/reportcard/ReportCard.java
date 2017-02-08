package android.example.com.reportcard;

public class ReportCard {
    public static final int MAXIMUM_MARKS = 100;
    public static final int GRADE_A_SCORE = 85;
    public static final int GRADE_B_SCORE = 70;
    public static final int GRADE_C_SCORE = 60;
    public static final int GRADE_D_SCORE = 40;
    private String[] subjects;
    private double[] scores;
    private char[] grades;
    private char finalGrade;

    public ReportCard(String[] subjects, double[] scores) {
        this.subjects = subjects;
        setMarks(scores);
        setGrades();
    }
    private void setGrades(){
        grades = new char[scores.length];
        for(int i=0;i<scores.length;i++){
            grades[i] = getGrade(scores[i]);
        }
        setFinalGrade();
    }
    private char getGrade(double score){
        if(score>=GRADE_A_SCORE){
            return 'A';
        }
        else if(score>=GRADE_B_SCORE){
            return 'B';
        }
        else if(score>=GRADE_C_SCORE){
            return 'C';
        }
        else if(score>=GRADE_D_SCORE){
            return 'D';
        }
        else{
            return 'F';
        }
    }

    private void setMarks(double[] scores){
        for(double score : scores){
            if(score>=MAXIMUM_MARKS){
                throw new IllegalArgumentException("Invalid Score. Score in Subject can not be above "+MAXIMUM_MARKS);
            }
        }
        this.scores = scores;
    }

    private void setFinalGrade(){
        for(int i=0;i<grades.length;i++){
            if(grades[i]=='F'){
                finalGrade = 'F';
                return;
            }
        }
        double totalScore = 0;
        for(double score : scores){
            totalScore += score;
        }
        double finalPercantage = (totalScore*100) / (subjects.length * MAXIMUM_MARKS);
        finalGrade = getGrade(finalPercantage);
    }

    @Override
    public String toString() {
        StringBuilder reportCard = new StringBuilder();
        reportCard.append("Report Card { \n");
        for(int i=0;i<subjects.length;i++){
            reportCard.append(subjects[i]+" : "+grades[i]+ "\n");
        }
        reportCard.append("Final Grade : "+finalGrade+"\n");
        reportCard.append("}");
        return reportCard.toString();
    }
}
