package tests.rest.services;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tests.model.AnswerDTO;
import tests.model.Question;
import tests.model.Test;
import tests.model.TestDTO;
import tests.persistence.QuestionHibernateRepo;
import tests.persistence.TestHibernateRepo;

@RestController
@RequestMapping("/test")
public class Controller {

    private ApplicationContext factory = new ClassPathXmlApplicationContext("spring-config.xml");
    private QuestionHibernateRepo qRepo = (QuestionHibernateRepo) factory.getBean("questionRepo");
    private TestHibernateRepo tRepo = (TestHibernateRepo) factory.getBean("testRepo");

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<?> getById(@RequestBody String id) {
        JsonObject jsonId = new Gson().fromJson(id, JsonObject.class);
        int finalId = Integer.parseInt(String.valueOf(jsonId.entrySet().iterator().next().getValue()));
        Test test = tRepo.findOne(finalId);
        if (test == null)
            return new ResponseEntity<>("Test not found", HttpStatus.NOT_FOUND);
        else {
            TestDTO dto = new TestDTO();
            dto.setTestID(test.getID());
            dto.setQuestions(new Question[] {test.getQ1(), test.getQ2(), test.getQ3(), test.getQ4(), test.getQ5()});
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/answer", method = RequestMethod.POST)
    public ResponseEntity<?> getResults(@RequestBody String answer) {
        int score = 0;
        JsonObject jsonAnswer = new Gson().fromJson(answer, JsonObject.class);
        for (var each : jsonAnswer.getAsJsonArray("answers")) {
            int finalId = each.getAsJsonObject().get("id").getAsInt();
            System.out.println(finalId);
            boolean answerFinal = each.getAsJsonObject().get("answer").getAsBoolean();
            if (qRepo.isCorrect(finalId, answerFinal)) {
                score += 1;
            }
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }
}

// {ID: 1, ANSWR: TRUE}