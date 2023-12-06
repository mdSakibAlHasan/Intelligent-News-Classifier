package com.example.newspaperclassification;

import com.example.testing.NaiveBayes;
import com.example.testing.NewDataAdd;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.concurrent.atomic.AtomicBoolean;

public class HelloController {

    @FXML
    private Label welcomeText;
    @FXML
    private TextArea textArea;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Label classificationLabel;

    private boolean checkButtonClick=false;

    @FXML
    private ChoiceBox<String> choiceId;
    ObservableList<String> options = FXCollections.observableArrayList("sport","business","tech","politics","entertainment");

    @FXML
    public void checkboxController(){

        choiceId.setItems(options);
        //choiceId.setValue(type);
        System.out.println("Click on checkbox"+choiceId.getValue());
        choiceId.setValue("");
    }



    @FXML
    protected void onClassifyButtonClick() {
       String inputText =  textArea.getText();
        System.out.println("Input text is: "+inputText);

        NaiveBayes naiveBayes = new NaiveBayes();
        String classification =  naiveBayes.returnClassification(inputText);

        classificationLabel.setText(classification);
        checkButtonClick = false;
        checkboxController();
        choiceId.setValue(classification);
    }

    @FXML
    protected void textFieldController(){
        classificationLabel.setText("");
        checkBox.setSelected(false);

    }

    @FXML
    protected void addTrainingSectionCheckBox(){
        System.out.println("Check box clicked");
        System.out.println("Choice list array are: "+choiceId.getValue());
        if(classificationLabel.getText().length()>3 && textArea.getText().length()>200 && !checkButtonClick){
            NewDataAdd newDataAdd = new NewDataAdd();
            newDataAdd.writeData(textArea.getText(),choiceId.getValue());
            checkButtonClick = true;
        }
        else{
            System.out.println("Length is very small");
        }

    }

    public void conformationBox(){

    }
}