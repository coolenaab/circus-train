package org.example.circustrain;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.circustrain.model.Wagon;
import org.example.circustrain.service.AnimalService;

import java.util.List;

/**
 * Main class for the application.
 * Builds the UI.
 */
public class Main extends Application {

    private AnimalService animalService;

    private TextField txtCarnivoresSmall;
    private TextField txtCarnivoresMedium;
    private TextField txtCarnivoresLarge;
    private TextField txtHerbivoresSmall;
    private TextField txtHerbivoresMedium;
    private TextField txtHerbivoresLarge;
    private ScrollPane scrollOutput;

    @Override
    public void start(Stage primaryStage) throws Exception{
        animalService= new AnimalService();

        Text textEmpty = new Text();
        Text textHerbivores = new Text("Herbivoren");
        Text textCarnivores = new Text("Carnivoren");
        Text textSmall = new Text("Small");
        Text textMedium = new Text("Medium");
        Text textLarge = new Text("Large");

        txtCarnivoresSmall = new TextField();
        txtCarnivoresMedium = new TextField();
        txtCarnivoresLarge = new TextField();
        txtHerbivoresSmall = new TextField();
        txtHerbivoresMedium = new TextField();
        txtHerbivoresLarge = new TextField();

        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(this::btnSubmitAction);

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(400, 200);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(textEmpty, 0, 0);
        gridPane.add(textHerbivores, 1, 0);
        gridPane.add(textCarnivores, 2, 0);
        gridPane.add(textSmall, 0, 1);
        gridPane.add(txtHerbivoresSmall, 1, 1);
        gridPane.add(txtCarnivoresSmall, 2, 1);
        gridPane.add(textMedium, 0, 2);
        gridPane.add(txtHerbivoresMedium, 1, 2);
        gridPane.add(txtCarnivoresMedium, 2, 2);
        gridPane.add(textLarge, 0, 3);
        gridPane.add(txtHerbivoresLarge, 1, 3);
        gridPane.add(txtCarnivoresLarge, 2, 3);
        gridPane.add(btnSubmit, 0, 4, 3, 1);

        scrollOutput = new ScrollPane();
        gridPane.add(scrollOutput, 0, 5, 3, 1);

        Scene scene = new Scene(gridPane);
        primaryStage.setTitle("Circus train");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void btnSubmitAction(ActionEvent actionEvent) {
        int smallCarnivores = parseInteger(txtCarnivoresSmall);
        int mediumCarnivores = parseInteger(txtCarnivoresMedium);
        int largeCarnivores = parseInteger(txtCarnivoresLarge);

        int smallHerbivores = parseInteger(txtHerbivoresSmall);
        int mediumHerbivores = parseInteger(txtHerbivoresMedium);
        int largeHerbivores = parseInteger(txtHerbivoresLarge);

        showOutput(animalService.sortAnimals(smallCarnivores, mediumCarnivores, largeCarnivores, smallHerbivores, mediumHerbivores, largeHerbivores));
    }

    /**
     * Show the list of wagons in a textual representation.
     *
     * @param wagons The {@link List} of wagons to be shown
     */
    private void showOutput(List<Wagon> wagons) {
        VBox vBoxWagons = new VBox();
        ObservableList<Node> txtFlowWagonsChildren = vBoxWagons.getChildren();
        String wagonsAmount = String.valueOf(wagons.size());
        txtFlowWagonsChildren.add(new Text(("Wagon amount: " + wagonsAmount)));
        wagons.forEach(wagon -> txtFlowWagonsChildren.add(new Text(wagon.toString())));
        scrollOutput.setContent(vBoxWagons);
    }

    /**
     * Show an error message
     *
     * @param error The error message
     */
    private void showError(String error) {
        Text txtError = new Text(error);
        scrollOutput.setContent(txtError);
    }

    /**
     * Parses a text field value to an integer
     *
     * @param field The {@link TextField} to be converted to an integer
     * @return a converted integer when possible otherwise throws an exception
     */
    private int parseInteger(TextField field) {
        try {
            return Integer.parseInt(field.getText());
        } catch (NumberFormatException nfe) {
            showError(field.getText() + " is not a valid number!");
            throw nfe;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
