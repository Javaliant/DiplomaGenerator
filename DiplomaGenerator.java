/* Author: Luigi Vincent

*/

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DiplomaGenerator extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		BorderPane layout = new BorderPane();

		TextArea result = new TextArea();
		result.setEditable(false);
		layout.setBottom(result);

		Label name = new Label("Name:");
		GridPane.setConstraints(name, 0, 0);
		TextField nameField = new TextField();
		GridPane.setConstraints(nameField, 1, 0);

		Label hobbies = new Label("Hobbies:");
		GridPane.setConstraints(hobbies, 0, 1);
		TextField hobbyField = new TextField();
		GridPane.setConstraints(hobbyField, 1, 1);

		Label achievements = new Label("Achievements: ");
		GridPane.setConstraints(achievements, 0, 2);
		TextField achievementField = new TextField();
		GridPane.setConstraints(achievementField, 1, 2);

		Button submit = new Button("Submit");
		submit.setOnAction(e -> {
			result.setText(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
				"\nMaecenas et fermentum est, eget aliquam dolor." +
				"\nQuisque condimentum condimentum est, eu cursus odio mattis quis."
				+"\nNam consequat dui non sodales ultrices."
				+"Lorem ipsum dolor sit amet, consectetur adipiscing elit."
			);
		});
		GridPane.setConstraints(submit, 0, 3);

		GridPane fields = new GridPane();
		fields.getChildren().addAll(name, nameField, hobbies, hobbyField, achievements, achievementField, submit);
		layout.setCenter(fields);

		Scene scene = new Scene(layout);
		scene.getStylesheets().add("Assets/DiplomaGenerator.css");

		stage.setScene(scene);
		stage.setTitle("Diploma Generator");
		stage.getIcons().add(
			new Image(getClass().getResourceAsStream("Assets/Icon.jpeg")));
		stage.show();
	}
}