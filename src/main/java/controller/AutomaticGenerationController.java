package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import model.Exam;
import model.Question;
import util.ExamGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AutomaticGenerationController {

    @FXML
    AnchorPane pane;
    @FXML
    ChoiceBox<String> difficulty;
    @FXML
    ListView<TopicChoiceHBox> testTopics;
    @FXML
    ListView<TopicChoiceHBox> essayTopics;
    @FXML
    Slider percentageSlider;

    Exam exam;

    @FXML
    public void initialize() {

        DatabaseManager db = DatabaseManager.getInstance();
        List<String> testTopicList = db.getTopics(Question.Type.TEST);
        List<String> essayTopicList = db.getTopics(Question.Type.ESSAY);
        testTopics.setItems(FXCollections.observableArrayList());
        essayTopics.setItems(FXCollections.observableArrayList());

        for(String topic : testTopicList) {
            testTopics.getItems().add(new TopicChoiceHBox(topic));
        }

        for(String topic : essayTopicList) {
            essayTopics.getItems().add(new TopicChoiceHBox(topic));
        }

        difficulty.getItems().add(ResourceBundle.getBundle(MainApp.LABELS).getString("lbl.notApplicable"));
        difficulty.getItems().add(ResourceBundle.getBundle(MainApp.LABELS).getString("lbl.low"));
        difficulty.getItems().add(ResourceBundle.getBundle(MainApp.LABELS).getString("lbl.medium"));
        difficulty.getItems().add(ResourceBundle.getBundle(MainApp.LABELS).getString("lbl.high"));
        difficulty.getItems().add(ResourceBundle.getBundle(MainApp.LABELS).getString("lbl.veryHigh"));

        difficulty.setValue(ResourceBundle.getBundle(MainApp.LABELS).getString("lbl.notApplicable"));
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    @FXML
    public void handleGenerate() {
        List<String> testSelectedTopics = new ArrayList<>();
        for(TopicChoiceHBox testTopicChoice : testTopics.getItems()) {
            if(testTopicChoice.isChecked()) {
                testSelectedTopics.add(testTopicChoice.getTopic());
            }
        }
        List<String> essaySelectedTopics = new ArrayList<>();
        for(TopicChoiceHBox essayTopicChoice : essayTopics.getItems()) {
            if(essayTopicChoice.isChecked()) {
                essaySelectedTopics.add(essayTopicChoice.getTopic());
            }
        }

        SceneManager sceneManager = SceneManager.getInstance();
        int aux = -1;

        if(difficulty.getValue().equals(ResourceBundle.getBundle(MainApp.LABELS).getString("lbl.low"))) {
            aux = 0;
        } else if(difficulty.getValue().equals(ResourceBundle.getBundle(MainApp.LABELS).getString("lbl.medium"))) {
            aux = 1;
        } else if(difficulty.getValue().equals(ResourceBundle.getBundle(MainApp.LABELS).getString("lbl.high"))) {
            aux = 2;
        } else if(difficulty.getValue().equals(ResourceBundle.getBundle(MainApp.LABELS).getString("lbl.veryHigh"))) {
            aux = 3;
        }

        final int difficultyValue = aux;

        sceneManager.showWorkIndicator(this.exam, (exam) -> {

            try{
            ExamGenerator.generateExam(this.exam, Integer.valueOf(difficulty.getValue()), Integer.valueOf(this.exam.durationProperty().getValue()), (int) (100-percentageSlider.getValue()),essaySelectedTopics ,testSelectedTopics);}
            catch (Exception e){
                //TODO: Implement the ui
            }

            return true;
        });
    }

    @FXML
    public void handleBack() {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.back();
    }
}
