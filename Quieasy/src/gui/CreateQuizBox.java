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
import requests.QuizC;
import requests.StudyProgramC;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CreateQuizBox extends VBox {

	private static CreateQuizBox createQuizBox;
	private Button createButton;
	private TextField textThreshold;
	private TextField textname;
	private ComboBox<String> courseComboBox;
	private ComboBox<String> studyProgramComboBox;
	private TextField textTime;
	private  Label warning;
	private ArrayList<StudyProgramData> studyProgramDataArrayList = new ArrayList<>();
	private QuizData quiz;
	private ObservableList<String> studyProgramHSObservList;
	// constructor can only be accessed from within
	private CreateQuizBox(){
		
		super();
		//call fetchStudyPrograms and present the data to user while waiting for their input
		initializeCreateQuizFields(); //return arraylist
	}
	//for testing the sequence of client-server message sending
	public void initializeCreateQuizFields(){
		studyProgramDataArrayList = StudyProgramC.fetchAllStudyPrograms();
		if(studyProgramDataArrayList != null) {
			ArrayList<String> studyProgramNames = studyProgramDataArrayList.stream()
					.map(StudyProgramData::getStudyprogram)
					.collect(Collectors.toCollection(ArrayList::new));
			studyProgramHSObservList = FXCollections.observableArrayList(studyProgramNames);

			// study program selection
			HBox studyProgram = initiateStudyProgram(studyProgramHSObservList);
			//courses selection
			//ObservableList<String> course = FXCollections.observableArrayList("SOFE", "Calculus", "C++", "WebEngineering");
			HBox courses = initiateCourse();
			//give a name to Quiz
			HBox nameQuiz = initiateNameQuiz();

			// give a passed grade to Quiz
			HBox thresholdQuiz = initiateTreshold();
			HBox timeLimit = initiateTimeLimit();
			HBox warningMessage = initiatewarning();
			HBox createButton = initiateBotton();
			this.getChildren().addAll(studyProgram, courses, nameQuiz, thresholdQuiz, timeLimit, warningMessage, createButton);
		}
	}
	public ArrayList<StudyProgramData> getStudyProgramDataArrayList() {
		return studyProgramDataArrayList;
	}

	public void setStudyProgramDataArrayList(ArrayList<StudyProgramData> studyProgramDataArrayList) {
		this.studyProgramDataArrayList = studyProgramDataArrayList;
	}


	// Gets the current instance -> Singleton
	public static CreateQuizBox getCreateQuizBox() {
		
		if (createQuizBox == null) createQuizBox = new CreateQuizBox();
		
		return createQuizBox;
		
	}

	public static void showSuccessful(){
		// let the user know that the server has successfully saved a list of quizes to persistence
		System.out.println("Quiz successfully created. "); //should be on UI, not console
	}
	public static void showFailed(){
		// let the user know server has failed saving the list of quizes to persistence
		System.out.println("Quiz creation failed. "); //should be on UI, not console
	}

	//this seems not to be needed
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
		studyProgramComboBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
					String selected = studyProgramComboBox.getValue();
					ArrayList<CourseData> courses = studyProgramDataArrayList.stream()
							.filter(studyProgramData1 -> studyProgramData1.getStudyprogram().equals(selected))
							.map(StudyProgramData::getCourses)
							.collect(Collectors.toCollection(ArrayList::new)).get(0);
					ArrayList<String> courseNames = courses.stream()
							.map(CourseData::getCourses)
							.collect(Collectors.toCollection(ArrayList::new));
					courseComboBox.setItems(FXCollections.observableArrayList(courseNames));
			}
		});

		studyProgramComboBox.setItems(studyProgramHS);
		//set the size of text of item in the buttom cell
		settingsComboBox(studyProgramComboBox);

		studyProgramComboBox.setMinHeight(30);
		studyProgramComboBox.setMinWidth(400);
		studyProgram.getChildren().addAll(labelStudyProgram, studyProgramComboBox);
		return studyProgram;

	}
	public HBox initiateCourse(){
		HBox course = new HBox(100);
		course.setPadding(new Insets(0, 30, 0, 30));
		Label labelcourse = new Label("Course*");
		labelcourse.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
		courseComboBox = new ComboBox<>();
		courseComboBox.setPromptText("Select the course");
		settingsComboBox(courseComboBox);
		courseComboBox.setMinHeight(30);
		courseComboBox.setMinWidth(400);
		course.getChildren().addAll(labelcourse, courseComboBox);
		return course;
	}

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
		//set the size and font for CumboBox List of item
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

	public HBox initiateNameQuiz(){
		HBox nameQuiz = new HBox(110);
		nameQuiz.setPadding(new Insets(30));
		Label labelName = new Label("Name*");
		labelName.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
		textname = new TextField();
		textname.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
		textname.setPromptText("Name of the Quiz*");
		textname.setMinWidth(400);
		textname.setMinHeight(30);
		nameQuiz.getChildren().addAll(labelName, textname);

		return nameQuiz;	
	}
	public HBox initiateTreshold(){
		HBox thresholdQuiz = new HBox(72);
		thresholdQuiz.setPadding(new Insets(0, 30, 0, 30));
		Label labelThreshold = new Label("Threshold*");
		labelThreshold.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
		textThreshold = new TextField();
		textThreshold.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
		textThreshold.setPromptText("80 %");
		textThreshold.setMinWidth(100);
		textThreshold.setMinHeight(30);
		textThreshold.textProperty().addListener((observableValue, oldValue, newValue) -> {
			if(!newValue.matches("\\d{0,2}([\\.]\\d{0,2})?")) {
				textThreshold.setText("80.00");
			}else{}
		});
		thresholdQuiz.getChildren().addAll(labelThreshold, textThreshold);
		return thresholdQuiz;

	}
	// add try catch error empty entry
	public HBox initiateTimeLimit(){
		HBox timeLimit = new HBox(70);
		Label labelTime = new Label("Time limit*");
		labelTime.setFont(Font.font("Times New Roman", FontWeight.EXTRA_BOLD, 20));
		timeLimit.setPadding(new Insets(30));
		textTime = new TextField();

		textTime.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 18));
		textTime.setMinWidth(100);
		textTime.setPromptText("15 minutes");

		textTime.textProperty().addListener((observableValue, oldValue, newValue) -> {
			if(!newValue.matches("\\d{0,3}")){
				textTime.setText("15");
			}else{

			}
		});
				timeLimit.getChildren().addAll(labelTime, textTime);

		return timeLimit;
	}
	public HBox initiatewarning(){
		HBox warningText = new HBox();
		warningText.setPadding(new Insets(0,0,0,200));
		warning = new Label();
		warning.setTextFill(Color.FIREBRICK);
		warningText.getChildren().add(warning);
		return warningText;
	}

	public HBox initiateBotton(){
		HBox buttonsubmit = new HBox();
		buttonsubmit.setPadding(new Insets(40, 30, 0, 500));
		createButton = new Button("➦ Create Quiz");
		createButton.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 16));
		buttonsubmit.getChildren().addAll(createButton);
		createButton.setOnAction(actionEvent -> {
			if(studyProgramComboBox.getValue()==null     || courseComboBox.getValue() == null
														 || textname.getText().isEmpty()
														 || textThreshold.getText().isEmpty()
														 || textTime.getText().isEmpty()) {


				warning.setText("Fill all the fields marked with *");

			}else{
				quiz = new QuizData(studyProgramComboBox.getValue(),
						courseComboBox.getValue(), textname.getText(),
						Double.parseDouble(textThreshold.getText()),
						Integer.parseInt(textTime.getText()),
						new ArrayList<QuestionData>());
				QuizC.createNewQuiz(quiz);

				MainPane.getMainPane().getTabs().add(CreateAddQuestionTab.getCreateAddQuestionTab());
				CreateQuizTab.getCreateQuizTab().closeTab();
				 sanitizeInputs();
				// create the object Quiz here



			}
		});



		return buttonsubmit;

	}

	public QuizData getQuiz() {
		return quiz;
	}

	private void sanitizeInputs(){
		textname.clear();
		textThreshold.clear();
		textTime.clear();

	}




}



