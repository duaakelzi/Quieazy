// create quiz wizard

package gui;

import data.CourseData;
import data.QuestionData;
import data.QuizData;
import data.StudyProgramData;
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
import requests.QuizRequests;
import requests.StudyProgramRequests;
import requests.UserRequests;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CreateQuizBox extends VBox {
	/**
	 * CreateQuizBox class holds the Study Programs, Course, Name of the Quiz, Threshold, Count Down Timer input field that are
	 * collected from User to create an empty Quiz. At this point an empty Quiz is created that belongs to the user without questions.
	 */
	private static CreateQuizBox createQuizBox;
	private TextField thresholdText;
	private TextField nameText;
	private ComboBox<String> courseComboBox;
	private ComboBox<String> studyProgramComboBox;
	private TextField timeText;
	private Label warningLabel;
	private ArrayList<StudyProgramData> studyProgramDataArrayList = new ArrayList<>();
	private QuizData quiz;
	private HBox studyProgram;
	private HBox courses;
	private HBox nameQuiz;
	private HBox thresholdQuiz;
	private HBox timeLimit;
	private HBox warningMessage;
	private HBox createButtons;

	/**
	 * Constructor of CreateQuizBox initialize the following member variable of the class:
	 * 1. First ComboBox dropdown the list of available Study Programs of THU
	 * 2. Second ComboBox lists the courses that belongs to the above chosen Study program
	 * 3. Name of the Quiz is inserted by user
	 * 4  Threshold - a field that accept a number input representing the percentage of correct answers to pass the Quiz
	 * 5. Timer - a time limit bound while the user can answers to the questions
	 */
	private CreateQuizBox(){
		
		super();
		//call fetchStudyPrograms and present the data to user while waiting for their input
		initializeCreateQuizFields(); //return arraylist
	}

	/**
	 * Initializes the JavaFX field to set up a new Quiz
	 */
	private void initializeCreateQuizFields(){
		studyProgramDataArrayList = StudyProgramRequests.fetchAllStudyPrograms();
		if(studyProgramDataArrayList != null) {
			ArrayList<String> studyProgramNames = studyProgramDataArrayList.stream()
					.map(StudyProgramData::getStudyprogram)
					.collect(Collectors.toCollection(ArrayList::new));
			ObservableList<String> studyProgramHSObservList = FXCollections.observableArrayList(studyProgramNames);
			initiateStudyProgram(studyProgramHSObservList);
			initiateCourse();
			initiateNameQuiz();
			initiateTreshold();
			initiateTimeLimit();
			initiatewarning();
			initiateBotton();
			this.getChildren().addAll(studyProgram, courses, nameQuiz, thresholdQuiz, timeLimit, warningMessage, createButtons);
		}
	}

	/**
	 * Getter for Study Program
	 * @return ArrayList of all study programs
	 */
	public ArrayList<StudyProgramData> getStudyProgramDataArrayList() {
		return studyProgramDataArrayList;
	}

	/**
	 * Singleton Object of CreateQuizBox Scene, is considered that the user can create a single Quiz at time
	 * @return the Window with all fields to create a Quiz
	 */
	public static CreateQuizBox getCreateQuizBox() {
		if (createQuizBox == null) createQuizBox = new CreateQuizBox();
		return createQuizBox;
	}

	/**
	 * Message displayed if the Quiz was successful created, representing that the newest created Quiz is saved on DB
	 */
	public static void showSuccessful(){
		QuizRequests.fetchAllQuizzes(UserRequests.getCurrentUser()); //for testing, to see if the method works
	}

	/**
	 * Failure Mesage on GUI when the Quiz was failed to be saved on DB
	 */
	public static void showFailed(){
		// let the user know server has failed saving the list of quizzes to persistence
		System.out.println("Quiz creation failed. "); //should be on UI, not console
	}

	/**
	 * Creating COmboBox of Study Program with Study Programs from DB
	 * @param studyProgramHS
	 */
	public void initiateStudyProgram(ObservableList<String>studyProgramHS) {
		studyProgram = new HBox(30);
		studyProgram.setPadding(new Insets(30));
		Label labelStudyProgram = new Label("Study Program*");
		labelStudyProgram.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
		studyProgramComboBox = new ComboBox<>();
		studyProgramComboBox.setPromptText("Select the Study Program");
		studyProgramComboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
					String selected = studyProgramComboBox.getValue();
					ArrayList<CourseData> courses = studyProgramDataArrayList.stream()
							.filter(studyProgramData1 -> studyProgramData1.getStudyprogram().equals(selected))
							.map(StudyProgramData::getCourses)
							.collect(Collectors.toCollection(ArrayList::new)).get(0);
					ArrayList<String> courseNames = courses.stream()
							.map(CourseData::getCourse)
							.collect(Collectors.toCollection(ArrayList::new));
					courseComboBox.setItems(FXCollections.observableArrayList(courseNames));
			}
		});

		studyProgramComboBox.setItems(studyProgramHS);
		//setting the appearance of text of item in the buttom cell
		settingsComboBox(studyProgramComboBox);
		studyProgramComboBox.setMinHeight(30);
		studyProgramComboBox.setMinWidth(400);
		studyProgram.getChildren().addAll(labelStudyProgram, studyProgramComboBox);
	}

	/**
	 * Creating ComboBox of Courses, the list of courses are dependent of Study Program selection
	 * Courses list from spinner are filled up in the moment of selection of a Study Program
	 */
	public void initiateCourse(){
		courses = new HBox(100);
		courses.setPadding(new Insets(0, 30, 0, 30));
		Label labelcourse = new Label("Course*");
		labelcourse.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
		courseComboBox = new ComboBox<>();
		courseComboBox.setPromptText("Select the course");
		settingsComboBox(courseComboBox);
		courseComboBox.setMinHeight(30);
		courseComboBox.setMinWidth(400);
		courses.getChildren().addAll(labelcourse, courseComboBox);
	}

	/**
	 * Settings for ComboBox appearance of the dropdown list using the design pattern Factory from java Library
	 * @param combobox is passed as parameter to change the design of the ComboBox
	 */
	private void settingsComboBox(ComboBox<String> combobox){
		combobox.setButtonCell(new ListCell<>() {
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
		//setting the size and font for CumboBox List of item
		combobox.setCellFactory(new Callback<>() {
			@Override
			public ListCell<String> call(ListView<String> stringListView) {

				return new ListCell<>() {
					@Override
					protected void updateItem(String s, boolean b) {
						super.updateItem(s, b);
						if (s == null) {
							setText(null);
						} else {
							setText(s);
							setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
							setTextFill(Color.BLACK);
						}

					}
				};
			}
		});


	}

	/**
	 * Create the input field text that represents for User the Name of the Quiz
	 */
	public void initiateNameQuiz(){
		nameQuiz = new HBox(110);
		nameQuiz.setPadding(new Insets(30));
		Label labelName = new Label("Name*");
		labelName.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
		nameText = new TextField();
		nameText.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
		nameText.setPromptText("Name of the Quiz*");
		nameText.setMinWidth(400);
		nameText.setMinHeight(30);
		nameQuiz.getChildren().addAll(labelName, nameText);
	}

	/**
	 * Create an input Text field that accepts only 2 digits units that represents the pass boundary for Quiz
	 * if the user doesn't introduce nothing 80% is selected as a default value when the any key from keyboard is pressed
	 */
	public void initiateTreshold(){
		thresholdQuiz = new HBox(72);
		thresholdQuiz.setPadding(new Insets(0, 30, 0, 30));
		Label labelThreshold = new Label("Threshold*");
		labelThreshold.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
		thresholdText = new TextField();
		thresholdText.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
		thresholdText.setPromptText("80 %");
		thresholdText.setMinWidth(100);
		thresholdText.setMinHeight(30);
		thresholdText.textProperty().addListener((observableValue, oldValue, newValue) -> {
			if(!newValue.matches("\\d{0,2}([\\.]\\d{0,2})?")) {
				thresholdText.setText("80.00");
			}
		});
		thresholdQuiz.getChildren().addAll(labelThreshold, thresholdText);

	}

	/**
	 * Create the field that accepts 3 digits, that represents the timer for the Quiz
	 * if the user doesn't insert a value a 15 min is settled as a default value on the press any key from the keyboard
	 */
	public void initiateTimeLimit(){
		timeLimit = new HBox(70);
		Label labelTime = new Label("Time limit*");
		labelTime.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
		timeLimit.setPadding(new Insets(30));
		timeText = new TextField();

		timeText.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
		timeText.setMinWidth(100);
		timeText.setPromptText("15 minutes");

		timeText.textProperty().addListener((observableValue, oldValue, newValue) -> {
			if(!newValue.matches("\\d{0,3}")){
				timeText.setText("15");
			}else{

			}
		});
				timeLimit.getChildren().addAll(labelTime, timeText);
	}

	/**
	 * Create the Warning message
	 * In the case if not all the mandatory fields with the sigh * not selected a warning message is displayed to the User
	 */
	public void initiatewarning(){
		warningMessage = new HBox();
		warningMessage.setPadding(new Insets(0,0,0,200));
		warningLabel = new Label();
		warningLabel.setTextFill(Color.FIREBRICK);
		warningMessage.getChildren().add(warningLabel);
	}

	/**
	 * Create Quiz Button that check if all the mandatory fields are filled in case of successfully check the Quiz is created
	 */
	public void initiateBotton(){
		createButtons = new HBox();
		createButtons.setPadding(new Insets(40, 30, 0, 500));
		Button createButton = new Button("➦ Create Quiz");
		createButton.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
		createButtons.getChildren().addAll(createButton);
		createButton.setOnAction(actionEvent -> {
			if(studyProgramComboBox.getValue()==null     || courseComboBox.getValue() == null
														 || nameText.getText().isEmpty()
														 || thresholdText.getText().isEmpty()

														 || timeText.getText().isEmpty()) {


				warningLabel.setText("Fill all the fields marked with *");

			}else{
				quiz = new QuizData(courseComboBox.getValue(), nameText.getText(),
						Double.parseDouble(thresholdText.getText()),
						Integer.parseInt(timeText.getText()),
						new ArrayList<QuestionData>());
				if(QuizRequests.createNewQuiz(quiz, UserRequests.getCurrentUser())) {
					CreateQuizBox.showSuccessful();
				} else {
					CreateQuizBox.showFailed();
				}

				MainPane.getMainPane().getTabs().add(CreateAddQuestionTab.getCreateAddQuestionTab());
				CreateQuizTab.getCreateQuizTab().closeTab();
				 sanitizeInputs();



			}
		});





	}

	public QuizData getQuiz() {
		return quiz;
	}

	/**
	 * Sanitizing the input field from the previous created Quiz
	 */
	private void sanitizeInputs(){
		nameText.clear();
		thresholdText.clear();
		timeText.clear();

	}




}



