package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for an Exam
 **/

public class Exam {

    public StringProperty title;
    public StringProperty subject;
    public StringProperty modality;

    public IntegerProperty duration;
    public IntegerProperty weight;
    public IntegerProperty numQuestions;

    public Image logo;

    public ObjectProperty<LocalDate> examDate;
    public ObjectProperty<LocalDate> publicationDate;
    public ObjectProperty<LocalDate> reviewDate;

    public BooleanProperty nameField;
    public BooleanProperty surnameField;
    public BooleanProperty idNumberField;
    public BooleanProperty groupField;

    public StringProperty instructionDetails;

    public ObservableList<ExamPart> parts;

    /**
     * ----- GETTERS -----
     **/
    public String getTitle() {
        return title.getValue();
    }

    public String getSubject() {
        return subject.getValue();
    }

    public String getModality() {
        return modality.getValue();
    }

    public Integer getDuration() {
        return duration.getValue();
    }

    public int getWeight() {
        return weight.get();
    }

    public Integer getNumQuestions() {
        return numQuestions.getValue();
    }

    public Image getLogo() {
        return logo;
    }

    public String getExamDate() {
        return DateUtil.format(examDate.getValue());
    }

    public String getPublicationDate() {
        return DateUtil.format(publicationDate.getValue());
    }

    public String getReviewDate() {
        return DateUtil.format(reviewDate.getValue());
    }

    public Boolean getNameField() {
        return nameField.getValue();
    }

    public Boolean getSurnameField() {
        return surnameField.getValue();
    }

    public Boolean getIdNumberField() {
        return idNumberField.getValue();
    }

    public Boolean getGroupField() {
        return groupField.getValue();
    }

    public String getInstructionDetails() {
        return instructionDetails.getValue();
    }

    public List<ExamPart> getParts() {
        return parts;
    }
    /** ------------------- **/

    /**
     * ----- SETTERS -----
     **/
    public void setTitle(String title) {
        this.title.setValue(title);
    }

    public void setSubject(String subject) {
        this.subject.setValue(subject);
    }

    public void setModality(String modality) {
        this.modality.setValue(modality);
    }

    public void setDuration(Integer duration) {
        this.duration.setValue(duration);
    }

    public void setWeight(Integer weigh) {
        this.weight.setValue(weigh);
    }

    public void setNumQuestions(Integer numQuestions) {
        this.numQuestions.setValue(numQuestions);
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public void setLogo(String url) {
        this.logo = new Image(url);
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate.setValue(examDate);
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate.setValue(publicationDate);
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate.setValue(reviewDate);
    }

    public void setNameField(Boolean nameField) {
        this.nameField.setValue(nameField);
    }

    public void setSurnameField(Boolean surnameField) {
        this.surnameField.setValue(surnameField);
    }

    public void setIdNumberField(Boolean idNumberField) {
        this.idNumberField.setValue(idNumberField);
    }

    public void setGroupField(Boolean groupField) {
        this.groupField.setValue(groupField);
    }

    public void setInstructionDetails(String instructionDetails) {
        this.instructionDetails.set(instructionDetails);
    }

    public void setParts(List<ExamPart> parts) {
        this.parts = FXCollections.observableList(parts);
    }

    /**
     * -------------------
     **/

    public Exam() {
        this.title = new SimpleStringProperty("");
        this.subject = new SimpleStringProperty("");
        this.modality = new SimpleStringProperty("");

        this.duration = new SimpleIntegerProperty(60);
        this.weight = new SimpleIntegerProperty();
        this.numQuestions = new SimpleIntegerProperty(0);

        this.logo = new Image("images/logo_default.png");

        this.examDate = new SimpleObjectProperty<>(LocalDate.now());
        this.publicationDate = new SimpleObjectProperty<>(LocalDate.now());
        this.reviewDate = new SimpleObjectProperty<>(LocalDate.now());

        this.nameField = new SimpleBooleanProperty(false);
        this.surnameField = new SimpleBooleanProperty(false);
        this.idNumberField = new SimpleBooleanProperty(false);
        this.groupField = new SimpleBooleanProperty(false);

        this.instructionDetails = new SimpleStringProperty("");

        this.parts = FXCollections.observableArrayList();
    }

    @Override
    public boolean equals(Object other) {

        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Exam)) return false;

        Exam exam = (Exam) other;

        if (this.title.getValue() != exam.title.getValue())
            return false;
        if (this.subject.getValue() != exam.subject.getValue())
            return false;
        if (this.modality.getValue() != exam.modality.getValue())
            return false;
        if (this.duration.getValue() != exam.duration.getValue())
            return false;
        if (this.weight.getValue() != exam.weight.getValue())
            return false;
        if (this.numQuestions.getValue() != exam.numQuestions.getValue())
            return false;
        //TODO fix logo
        /*
        if(ImageUtil.getBase64(this.logo) != ImageUtil.getBase64(exam.logo))
            return false;
        */
        if (this.examDate.getValue() != exam.examDate.getValue())
            return false;
        if (this.publicationDate.getValue() != exam.publicationDate.getValue())
            return false;
        if (this.reviewDate.getValue() != exam.reviewDate.getValue())
            return false;
        if (this.nameField.getValue() != exam.nameField.getValue())
            return false;
        if (this.surnameField.getValue() != exam.surnameField.getValue())
            return false;
        if (this.idNumberField.getValue() != exam.idNumberField.getValue())
            return false;
        if (this.groupField.getValue() != exam.groupField.getValue())
            return false;
        if (this.instructionDetails.getValue() != exam.instructionDetails.getValue())
            return false;

        //TODO implement parts.equals()
        return this.parts.equals(exam.parts);
    }

    public Exam copy() {

        Exam aux = new Exam();

        aux.setTitle(this.title.getValue());
        aux.setSubject(this.subject.getValue());
        aux.setModality(this.modality.getValue());

        aux.setDuration(this.duration.getValue());
        aux.setWeight(this.weight.getValue());
        aux.setNumQuestions(this.numQuestions.getValue());

        aux.setLogo(this.logo);

        aux.setExamDate(this.examDate.getValue());
        aux.setPublicationDate(this.publicationDate.getValue());
        aux.setReviewDate(this.reviewDate.getValue());

        aux.setNameField(this.nameField.getValue());
        aux.setSurnameField(this.surnameField.getValue());
        aux.setIdNumberField(this.idNumberField.getValue());
        aux.setGroupField(this.groupField.getValue());

        aux.setInstructionDetails(this.instructionDetails.getValue());

        List<ExamPart> auxList = new ArrayList<>();
        for (ExamPart part : this.parts) {
            auxList.add(part.copy());
        }
        aux.setParts(auxList);

        return aux;
    }
}
