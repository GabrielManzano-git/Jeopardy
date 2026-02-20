package game;
public class JeopardyQuestion {
    private String question;
    private String answer;

    public JeopardyQuestion(String question, String answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }

}
