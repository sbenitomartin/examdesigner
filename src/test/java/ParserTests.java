import controller.MainApp;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.ExamParser;
import util.FileUtil;
import util.ImageUtil;
import util.TestQuestionParser;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParserTests {

    private static String examJsonString;
    private static String questionJsonString;

    @BeforeAll
    static void setup() {
        examJsonString = FileUtil.readFile(Paths.get("src/test/resources/testExam.json"));
        questionJsonString = FileUtil.readFile(Paths.get("src/test/resources/testQuestion.json"));
        Application.launch(MainApp.class);
    }

    private Exam getExam() {
        Exam testExam = new Exam();

        testExam.setTitle("Examen parcial - Fundamentos de Seguridad");
        testExam.setSubject("Fundamentos de Seguridad");
        testExam.setModality("Ordinaria");
        testExam.setDuration(90);
        testExam.setWeight(20);
        testExam.setNumQuestions(20);
        testExam.setLogo(ImageUtil.getImage("iVBORw0KGgoAAAANSUhEUgAAAgAAAAIACAIAAAB7GkOtAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAA+DSURBVHhe7d27YhtHtkDR+f/fcq5UsXKljGcAENRbYzy6Gl211wrn2iJQOHV2i9YV//MPAEkCABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAsBIn04+f/785cvXk7er//7N9f9+/me/fjn9a6d/+frrJFwP63pa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ABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAASf/88z8iAKVPK0buKgAAAABJRU5ErkJggg=="));
        testExam.setExamDate(LocalDate.of(2015, 5, 4));
        testExam.setPublicationDate(LocalDate.of(2015, 5, 8));
        testExam.setReviewDate(LocalDate.of(2015, 5, 12));
        testExam.setNameField(true);
        testExam.setSurnameField(true);
        testExam.setIdNumberField(false);
        testExam.setGroupField(true);
        testExam.setInstructionDetails("Se permite el uso de calculadora.");

        ExamPart part = new ExamPart();
        part.setTitle("Parte Test");

        TestQuestion question1 = new TestQuestion();
        question1.setTitle("Si las distancias entre repeticiones de cadenas en el criptograma cifrado con algoritmo de Vigenère son 35, 125, 70:");
        Choice choice1 = new Choice();
        Choice choice2 = new Choice();
        Choice choice3 = new Choice();
        Choice choice4 = new Choice();

        choice1.setTitle("La longitud de la clave puede ser 7.");
        choice2.setTitle("La longitud de la clave puede ser 5.");
        choice3.setTitle("La longitud de la clave puede ser 15.");
        choice4.setTitle("La longitud de la clave puede ser 35.");
        HashMap<String, Choice> choices = new HashMap<>();
        choices.put("a", choice1);
        choices.put("b", choice2);
        choices.put("c", choice3);
        choices.put("d", choice4);

        question1.setChoices(choices);
        List<String> correctChoices = new ArrayList<>();
        correctChoices.add("La longitud de la clave puede ser 5.");
        question1.setCorrectChoices(correctChoices);

        ArrayList<Question> questions = new ArrayList<>();
        questions.add(question1);
        part.setQuestions(questions);

        ExamPart part2 = new ExamPart();
        part.setTitle("Parte a Desarrollar");

        EssayQuestion question2 = new EssayQuestion();
        question2.setTitle("CIFRADO DE FLUJO:");
        Section choice5 = new Section();
        Section choice6 = new Section();
        Section choice7 = new Section();
        Section choice8 = new Section();

        choice1.setTitle("Utiliza las columnas de la tabla que se muestra a continuación para describir los pasos necesarios para generar la secuencia cifrante del generador G1");
        choice2.setTitle("Escribe en la cabecera la operación que se realiza en el apartado 1.1. Muestra la secuencia obtenida en la pregunta 1.1 y su período.");
        choice3.setTitle("Respecto a la secuencia S1 obtenida en la pregunta 1.2. ¿Se trata de una m-secuencia? ¿Cumple esta secuencia S1 los tres postulados de Golomb? Justifica detalladamente tus respuestas (NOTA describe la autocorrelación fuera de fase sólo para las 5 primeras iteraciones)");
        choice4.setTitle("Se desea cifrar el mensaje M utilizando un nuevo generador G que aumenta la complejidad lineal de G1 uniendo en una operación XOR (suma mod 2) las secuencias cifrantes generadas por G1 y G2. Realiza las operaciones necesarias para obtener el cifrado de M");

        ArrayList<Section> choices2 = new ArrayList<>();
        choices2.add(choice5);
        choices2.add(choice6);
        choices2.add(choice7);
        choices2.add(choice8);

        ArrayList<Question> questions2 = new ArrayList<>();
        questions2.add(question2);

        part2.setQuestions(questions2);
        List<ExamPart> parts = new ArrayList<>();
        parts.add(part);
        parts.add(part2);
        testExam.setParts(parts);

        return testExam;
    }

    private TestQuestion getTestQuestion() {
        TestQuestion test = new TestQuestion();
        test.setTitle("Pregunta de test");
        test.setDuration(25);
        test.setWeight(12);

        ArrayList<ContentObject> contenido = new ArrayList<>();
        ContentObject uno = new ContentObject();
        uno.setContent("111111111");
        ContentObject dos = new ContentObject();
        dos.setContent(new ImageView(ImageUtil.getImage("iVBORw0KGgoAAAANSUhEUgAAAgAAAAIACAIAAAB7GkOtAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAA+DSURBVHhe7d27YhtHtkDR+f/fcq5UsXKljGcAENRbYzy6Gl211wrn2iJQOHV2i9YV//MPAEkCABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAsBIn04+f/785cvXk7er//7N9f9+/me/fjn9a6d/+frrJFwP63pa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ABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAASf/88z8iAKVPK0buKgAAAABJRU5ErkJggg==")));
        ContentObject tres = new ContentObject();
        tres.setContent("222222222");
        contenido.add(uno);
        contenido.add(dos);
        contenido.add(tres);
        test.setBodyObjects(contenido);

        return test;
    }

    @Test
    void generateExamJsonTest() {
        Exam testExam = getExam();
        String examJson = new ExamParser(testExam).toJson();
        //System.out.println(examJson);
        //assertEquals(examJsonString, examJson, "json files are not equal");
    }

    @Test
    void generateExamFromJsonTest() {
        //Exam testExam = getExam();
        ExamParser examParser = new ExamParser(examJsonString);
        Exam exam = examParser.parseExam();
        String examJson = new ExamParser(exam).toJson();
        //assertEquals(examJson, examJsonString, "exam objects are not equal");
    }

    @Test
    void generateQuestionJsonTest() {
        TestQuestion test = new TestQuestion();
        test.setTitle("Pregunta de test");
        test.setDuration(25);
        test.setWeight(12);

        ArrayList<ContentObject> contenido = new ArrayList<>();
        ContentObject uno = new ContentObject();
        uno.setContent("111111111");
        ContentObject dos = new ContentObject();
        dos.setContent(new ImageView(ImageUtil.getImage("iVBORw0KGgoAAAANSUhEUgAAAgAAAAIACAIAAAB7GkOtAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAAEnQAABJ0Ad5mH3gAAA+DSURBVHhe7d27YhtHtkDR+f/fcq5UsXKljGcAENRbYzy6Gl211wrn2iJQOHV2i9YV//MPAEkCABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAsBIn04+f/785cvXk7er//7N9f9+/me/fjn9a6d/+frrJFwP63pa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ABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAAUQIAECUAAFECABAlAABRAgAQJQAASf/88z8iAKVPK0buKgAAAABJRU5ErkJggg==")));
        ContentObject tres = new ContentObject();
        tres.setContent("222222222");
        contenido.add(uno);
        contenido.add(dos);
        contenido.add(tres);
        test.setBodyObjects(contenido);

        //System.out.println(new TestQuestionParser(test).toJson());
    }

    @Test
    void generateQuestionFromJsonTest() {
        TestQuestionParser questionParser = new TestQuestionParser(questionJsonString);
        TestQuestion question = (TestQuestion) questionParser.parseQuestion();
        //System.out.println(new TestQuestionParser(question).toJson());
    }

    @Test
    void generateCompleteExam() {
        Exam testExam = getExam();
        List<ExamPart> parts = testExam.getParts();
        parts.get(0).addQuestion(getTestQuestion());
        //System.out.println(new ExamParser(testExam).toJson());
    }
}