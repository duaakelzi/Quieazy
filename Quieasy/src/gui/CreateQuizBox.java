// create quiz wizard

package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

public class CreateQuizBox extends VBox {

	private static CreateQuizBox createQuizBox;
	private Button createButtom;
	private TextField textThreshold;
	private TextField textname;
	private ComboBox<String> courseComboBox;
	ComboBox<String> studyProgramComboBox;
	// constructor can only be accessed from within
	private CreateQuizBox() {
		
		super();
		ObservableList<String> studyProgramHS = FXCollections.observableArrayList("Computational Science and Engineering",
				"Computer Science",
				"Data Science in der Medizin",
				"Digital Media",
				"Digital Media",
				"Elektrotechnik und Informationstechnik",
				"Energie-Informationsmanagement",
				"Energietechnik",
				"Informationsmanagement im Gesundheitswesen");

		// study program selection
		HBox studyProgram = initiateStudyProgram(studyProgramHS);
		//courses selection
		ObservableList<String> course = FXCollections.observableArrayList("SOFE", "Calculus", "C++", "WebEngineering");
		HBox courses = initiateCourse(course);
		//give a name to Quiz
		HBox nameQuiz = initiateNameQuiz();

		// give a passed grade to Quiz
		HBox thresholdQuiz = initiateTreshold();
		HBox descriptionQuiz = initiateDescription();
		HBox createButton = initiateBotton();
		this.getChildren().addAll(studyProgram, courses, nameQuiz, thresholdQuiz, descriptionQuiz, createButton);


		
	}
	
	// Gets the current instance -> Singleton
	public static CreateQuizBox getCreateQuizBox() {
		
		if (createQuizBox == null) createQuizBox = new CreateQuizBox();
		
		return createQuizBox;
		
	}

	public void showSuccessful(){
		// let the user know that the server has successfully saved a list of quizes to persistence
	}
	public void showFailed(){
		// let the user know server has failed saving the list of quizes to persistence
	}
	public void createNewQuiz(){
		//1 create Quiz object
		//2.Quiz newQuiz = new Quiz; take from the Gui
		// quiz= newQuiz;
	}

	public void createNewQuestion(){
		//3.ask user to enter question for new Quiz
		//4.for each question create a Question object
		//5 add all question to quiz
		//Question q = new Question();

		//quiz.addQuestion(q);
	}

	public void saveNreQuiz(){
		//6 call QuizC.createNewQuiz with the new Quiz object
		//QuizC.createNewQuiz(quiz);
	}


	public HBox initiateStudyProgram(ObservableList<String>studyProgramHS) {
		HBox studyProgram = new HBox(30);
		studyProgram.setPadding(new Insets(30));
		Label labelStudyProgram = new Label("Study Program*");
		labelStudyProgram.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
		studyProgramComboBox = new ComboBox<>();
		studyProgramComboBox.setPromptText("Select the Study Program");
		studyProgramComboBox.setItems(studyProgramHS);
		//set the size of text of item in the buttom cell
		settingsComboBox(studyProgramComboBox);

		studyProgramComboBox.setMinHeight(30);
		studyProgram.getChildren().addAll(labelStudyProgram, studyProgramComboBox);
		return studyProgram;

	}
	public HBox initiateCourse(ObservableList<String> courseHS){
		HBox course = new HBox(100);
		course.setPadding(new Insets(0, 30, 0, 30));
		Label labelcourse = new Label("Course*");
		labelcourse.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
		courseComboBox = new ComboBox<>();
		courseComboBox.setPromptText("Select the course*");


		courseComboBox.setItems(courseHS);

		settingsComboBox(courseComboBox);
		courseComboBox.setMinHeight(30);
		courseComboBox.setMinWidth(400);
		course.getChildren().addAll(labelcourse, courseComboBox);
		return course;
	}

	private void settingsComboBox(ComboBox<String> combobox){
		combobox.setButtonCell(new ListCell<String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (item != null) {
					setText(item);
					setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
					Background background = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));
					setBackground(background);
				} else {
					setText(null);
				}
			}
		});
		//set the size and font for CumboBox List of item
		combobox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> stringListView) {

				return new ListCell<>(){
					@Override
					protected void updateItem(String s, boolean b) {
						super.updateItem(s, b);
						if(s== null){
							setText(null);
						}else {
							setText(s);
							setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
							setTextFill(Color.BLACK);
						}

					}
				};
			}
		});


	}

	public HBox initiateNameQuiz(){
		HBox nameQuiz = new HBox(110);
		nameQuiz.setPadding(new Insets(30));
		Label labelName = new Label("Name*");
		labelName.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
		textname = new TextField();
		textname.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
		textname.setPromptText("Enter the name of your Quiz*");
		textname.setMinWidth(350);
		textname.setMinHeight(30);
		nameQuiz.getChildren().addAll(labelName, textname);

		return nameQuiz;	
	}
	public HBox initiateTreshold(){
		HBox thresholdQuiz = new HBox(75);
		thresholdQuiz.setPadding(new Insets(0, 30, 0, 30));
		Label labelThreshold = new Label("Threshold*");
		labelThreshold.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
		textThreshold = new TextField();
		textThreshold.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
		textThreshold.setPromptText("Enter the Quiz's threshold*");
		textThreshold.setMinWidth(250);
		textThreshold.setMinHeight(30);
		thresholdQuiz.getChildren().addAll(labelThreshold, textThreshold);
		return thresholdQuiz;

	}
	public HBox initiateDescription(){
		HBox descriptionQuiz = new HBox(80);
		descriptionQuiz.setPadding(new Insets(30, 30, 0, 30));
		Label labelDescription = new Label("Description");
		labelDescription.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 20));
		TextArea textDescription = new TextArea();
		textDescription.setPromptText("Description of the Quiz");
		textDescription.setPrefColumnCount(7);
		textDescription.setPrefHeight(100);
		textDescription.setPrefWidth(400);
		descriptionQuiz.getChildren().addAll(labelDescription, textDescription);

		return descriptionQuiz;
	}

	public HBox initiateBotton(){
		HBox buttonsubmit = new HBox();
		buttonsubmit.setPadding(new Insets(30, 30, 0, 530));
		createButtom = new Button("Create Quiz");
		createButtom.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
		buttonsubmit.getChildren().addAll(createButtom);
		createButtom.setOnAction(eventCreatQuiz);
		return buttonsubmit;

	}

	EventHandler<ActionEvent> eventCreatQuiz = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent actionEvent) {
			CreateQuizTab newtab = new CreateQuizTab("Add Questions", CreateQuizTab.getCreateQuizTab());
			MainPane.getMainPane().getTabs().add(newtab);


		}
	};

}

