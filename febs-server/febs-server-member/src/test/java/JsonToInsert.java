import cc.mrbird.febs.common.entity.member.Question;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonToInsert {
    public static void main(String[] args) {
        buildInsertSqlFromJSON("D:\\work\\FEBS-Cloud\\febs-server\\febs-server-member\\src\\test\\java\\questions.json");
    }
    public static void buildInsertSqlFromJSON(String jsonFileName) {
        File file = new File(jsonFileName);
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
        } finally {
            scanner.close();
        }
        QuestionDO bean = JSON.parseObject(buffer.toString(), QuestionDO.class);
        try {
            String lineSeparator = System.getProperty("line.separator","\n");
            String outFileName = "D:\\work\\FEBS-Cloud\\febs-server\\febs-server-member\\src\\test\\java\\output.sql";
            File outputFile = new File(outFileName);
            if (outputFile.exists()){
                outputFile.delete();
            }
            FileWriter writer = new FileWriter(outFileName, true);
            writer.write(buildInsertSql(bean.getLstQuestion()));
            writer.write(lineSeparator);
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String buildInsertSql(List<Question> lstQuestion) {
        StringBuilder insertSql = new StringBuilder();
        if (CollectionUtils.isNotEmpty(lstQuestion)) {
            for (Question question : lstQuestion) {
                insertSql.append("insert into t_question(id,question,answera,answerb,rightanswer) values(")
                        .append("'" + question.getId() + "', '" + question.getQuestion() + "', '" + question.getAnswera() + "', '" + question.getAnswerb() + "', '" + question.getRightanswer() + "');\n");
            }
        }
        return insertSql.toString();
    }
}
