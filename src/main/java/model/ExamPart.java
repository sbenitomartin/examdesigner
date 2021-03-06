package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Model class for an ExamPart
 **/

public class ExamPart {

    private StringProperty title;
    private IntegerProperty weight;
    private IntegerProperty duration;
    private StringProperty instructions;
    private ObservableList<Question> questions;

    /**
     * ----- GETTERS -----
     **/
    public String getTitle() {
        return title.getValue();
    }

    public Integer getWeigh() {
        return weight.getValue();
    }

    public Integer getDuration() {
        return duration.getValue();
    }

    public String getInstructions() {
        return instructions.getValue();
    }

    public List<Question> getQuestions() {
        return questions;
    }
    /** ------------------- **/

    /**
     * ----- SETTERS -----
     **/
    public void setTitle(String title) {
        this.title.setValue(title);
    }

    public void setWeigh(Integer weigh) {
        this.weight.setValue(weigh);
    }

    public void setDuration(Integer duration) {
        this.duration.setValue(duration);
    }

    public void setInstructions(String instructions) {
        this.instructions.setValue(instructions);
    }

    public void setQuestions(List<Question> questions) {
        this.questions = FXCollections.observableList(questions);
    }

    /**
     * -------------------
     **/

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public ExamPart() {
        this.title = new SimpleStringProperty("");
        this.weight = new SimpleIntegerProperty();
        this.duration = new SimpleIntegerProperty(0);
        this.instructions = new SimpleStringProperty("");
        this.questions = FXCollections.observableArrayList();
    }

    //TODO implement
    public ExamPart copy() {
        return this;
    }
}
