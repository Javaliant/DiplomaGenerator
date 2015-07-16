/* Author: Luigi Vincent

*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
		Random random = new Random();
		List<String> achievementList = new ArrayList<>();
		List<String> likeList = new ArrayList<>();
		List<String> openers = new ArrayList<>();
		List<String> closers = new ArrayList<>();
		List<String> buzzwords = new ArrayList<>();
		List<String> hobbyList = new ArrayList<>();

		scanAndAdd(achievementList, new File("Assets/achievement_comments.txt"));
		scanAndAdd(likeList, new File("Assets/like_comments.txt"));
		scanAndAdd(openers, new File("Assets/openers.txt"));
		scanAndAdd(closers, new File("Assets/closers.txt"));
		scanAndAdd(buzzwords, new File("Assets/buzzwords.txt"));
		scanAndAdd(hobbyList, new File("Assets/hobby_comments.txt"));

		BorderPane layout = new BorderPane();

		TextArea result = new TextArea();
		result.setEditable(false);
		result.setWrapText(true);
		layout.setBottom(result);

		Label name = new Label("Name:");
		GridPane.setConstraints(name, 0, 0);
		TextField nameField = new TextField();
		GridPane.setConstraints(nameField, 1, 0);

		Label hobbies = new Label("Hobbies:");
		GridPane.setConstraints(hobbies, 0, 1);
		TextField hobbyField = new TextField();
		GridPane.setConstraints(hobbyField, 1, 1);

		Label likes = new Label("Likes:");
		GridPane.setConstraints(likes, 0, 2);
		TextField likeField = new TextField();
		GridPane.setConstraints(likeField, 1, 2);

		Label achievements = new Label("Achievements: ");
		GridPane.setConstraints(achievements, 0, 3);
		TextField achievementField = new TextField();
		GridPane.setConstraints(achievementField, 1, 3);

		Button submit = new Button("Submit");
		submit.setOnAction(e -> {
			result.setText(nameField.getText() + ", " +
				openers.get(random.nextInt(openers.size())) + "\n" +
				hobbyList.get(random.nextInt(hobbyList.size())).replaceAll("x", hobbyField.getText()) + "\n" +
				likeList.get(random.nextInt(likeList.size()))
					.replaceAll("x", likeField.getText())
					.replaceAll("z", buzzwords.get(random.nextInt(buzzwords.size()))) + "\n" +
				achievementList.get(random.nextInt(achievementList.size()))
					.replaceAll("x", achievementField.getText())
					.replaceAll("z", buzzwords.get(random.nextInt(buzzwords.size()))) + "\n" +
				closers.get(random.nextInt(closers.size()))
			);
			submit.setText("Regenerate");
		});
		GridPane.setConstraints(submit, 0, 4);

		GridPane fields = new GridPane();
		fields.getChildren().addAll(name, nameField, hobbies, hobbyField, submit, likes, likeField, achievements, achievementField);
		layout.setCenter(fields);

		Scene scene = new Scene(layout);
		scene.getStylesheets().add("Assets/DiplomaGenerator.css");

		stage.setScene(scene);
		stage.setTitle("Diploma Generator");
		stage.getIcons().add(
			new Image(getClass().getResourceAsStream("Assets/Icon.jpeg")));
		stage.show();
	}

	private static void scanAndAdd(List<String> list, File file) {
		try(Scanner input = new Scanner(file) ) {
			while (input.hasNextLine()) {
				list.add(input.nextLine());
			}
		} catch (FileNotFoundException notFound) {
			System.out.println("Cannot find: " + notFound);
		}
	}
}