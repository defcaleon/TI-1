
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


import java.net.URL;
import java.util.ResourceBundle;

public class FormController implements Initializable {


    public TextArea resultText;
    public AnchorPane panel1;
    public AnchorPane panel2;
    public TableView matrixTable;

    private ObservableList list = FXCollections.observableArrayList();

    @FXML
    private ChoiceBox<String> choiceBox;
    @FXML
    private ChoiceBox<String> langBox;

    @FXML
    private RadioButton rBtn1;
    @FXML
    private RadioButton rBtn2;
    @FXML
    private TextField text;
    @FXML
    private TextField key;

    private void loadData() {
        list.removeAll(list);
        String a = "columnur cipher";
        String b = "rail fence cipher";
        String c = "play fair cipher";
        String d = "cipher rotary grid";

        list.addAll(a, b, c, d);
        choiceBox.getItems().addAll(list);
        choiceBox.setValue(a);


    }

    private void langBoxload() {
        list.removeAll(list);
        String a = "en";
        String b = "ru";
        list.addAll(a, b);
        langBox.getItems().addAll(list);
        langBox.setValue(a);
        langBox.setVisible(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        langBoxload();
        ToggleGroup group = new ToggleGroup();
        rBtn1.setToggleGroup(group);
        rBtn2.setToggleGroup(group);
        rBtn1.fire();
        panel1.setVisible(false);
        panel2.setVisible(false);
        matrixInit();
    }

    public void mainBtn(MouseEvent mouseEvent) {

        switch (choiceBox.getValue()) {

            case "rail fence cipher": {
                int intKey = isInt(key.getText());
                if (intKey != -1) {
                    RailFenceCipher ob = new RailFenceCipher(intKey, text.getText());
                    RailFenceCipherLogic logic = new RailFenceCipherLogic();
                    if (rBtn1.isSelected()) {
                        logic.encode(ob);
                    } else {
                        logic.decode(ob);
                    }
                    resultText.setText(ob.getCipher());
                }

                break;
            }
            case "columnur cipher": {
                ColumnarTranspositionCipher ob = new ColumnarTranspositionCipher(key.getText(), text.getText());
                ColumnarTranspositionCipherLogic logic = new ColumnarTranspositionCipherLogic();
                if (rBtn1.isSelected()) {
                    logic.encode(ob);
                } else {
                    logic.decode(ob);
                }
                resultText.setText(ob.getCipher());
                break;
            }
            case "play fair cipher": {
                PlayFairCipher.Lang lang;
                if (langBox.getValue() == "en") {
                    lang = PlayFairCipher.Lang.en;
                } else {
                    lang = PlayFairCipher.Lang.ru;
                }

                if (correctEnRusWord(text.getText(), lang)) {
                    PlayFairCipher ob = new PlayFairCipher(key.getText(), text.getText(), lang);
                    PlayFairCipherLogic logic = new PlayFairCipherLogic();
                    if (rBtn1.isSelected()) {
                        logic.encode(ob);
                    } else {
                        logic.decode(ob);
                    }
                    resultText.setText(ob.getCipher());

                }

                break;
            }
            case "cipher rotary grid": {
                break;
            }
        }


    }


    private int isInt(String word) {
        try {
            int intValue = Integer.parseInt(word);
            if (intValue > 0) {
                return intValue;
            } else {
                generateAndShowAlert("Number should be more than 0");
            }
        } catch (NumberFormatException e) {
            generateAndShowAlert("Input String cannot be parsed to Integer");
        }
        return -1;
    }

    private void generateAndShowAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.showAndWait();
    }


    private boolean correctEnRusWord(String word, PlayFairCipher.Lang lang) {
        if (lang == PlayFairCipher.Lang.en) {
            if (word.matches("^[a-zA-Z\\s]*$")) {
                return true;
            }
        } else {
            if (word.matches("^[а-яА-Я\\s]*$")) {
                return true;
            }
        }
        generateAndShowAlert("Input word can't contain these symbols");
        return false;
    }

    public void BtnOK(MouseEvent mouseEvent) {

        switch (choiceBox.getValue()) {
            case "rail fence cipher":
            case "columnur cipher": {
                panel1.setVisible(true);
                panel2.setVisible(false);
                langBox.setVisible(false);
                break;
            }
            case "play fair cipher": {

                panel1.setVisible(true);
                panel2.setVisible(false);
                langBox.setVisible(true);
                break;
            }
            case "cipher rotary grid": {
                panel1.setVisible(false);
                panel2.setVisible(true);
                break;
            }
        }

    }

    public void matrixInit() {
        String[][] dataArray =
                {{"1", "2", "3", "4", "5", "6"},
                        {"1", "2", "3", "4", "5",},
                        {"6", "7", "8", "9", "10",},
                        {"11", "12", "13", "14", "15"}};

        for (int i = 0; i < 6; i++) {
            TableColumn<String, String> col = new TableColumn<>(dataArray[0][i]);
            matrixTable.getColumns().addAll(col);
        }
    }
}
