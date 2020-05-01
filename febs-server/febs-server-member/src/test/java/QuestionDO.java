import cc.mrbird.febs.common.entity.member.Question;


import java.io.Serializable;
import java.util.List;


public class QuestionDO implements Serializable {

    private static final long serialVersionUID = 1716174520730326364L;

    private List<Question> lstQuestion;


    public List<Question> getLstQuestion() {
        return lstQuestion;
    }

    public void setLstQuestion(List<Question> lstQuestion) {
        this.lstQuestion = lstQuestion;
    }
}
