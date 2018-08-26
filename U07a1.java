package u07a1;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 *
 * @author <Matthew D. Miller IT4749 Adv Java Programming>
 */
public class U07a1 extends Application {
    // *******************************************
    // Set up Hibernate access
    // *******************************************
    // Setup is already complete per Brad.
    // TODO Brad says theres other mistakes in the code that need to be fixed
    // TODO Might have to dump jar files for submission.
    // Unit 7 changed EM to CourseRegistrationService
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("CourseRegistrationService");
    EntityManager em = emf.createEntityManager();
    CourseRegistrationService service = new CourseRegistrationService(em);
   
   // Code for JavaFX application 
    GridPane grid = new GridPane();
    Label learnerIDLabel = new Label("Learner ID: ");
    TextField learnerIDField = new TextField();
    ComboBox<Course> coursesComboBox = new ComboBox<>();   
    Label confirmPromptLabel = new Label("");
    Label registeredCoursesPromptLabel = new Label("You are currently registered for");
    Label creditHourPromptLabel = new Label("Current total Credit Hours");            
    Label registeredCoursesLabel = new Label("");
    Label creditHoursLabel = new Label("0");
    Label dataFilePathLabel = new Label("");
    
    // Unit 7 adding submit button
    final Button submitButton = new Button("Submit");
    // Unit 7 adding Logger
    private static final Logger log = Logger.getLogger(U07a1.class.getName());
    
    Course choice;
    final int MAX_CREDIT_LOAD = 9;
    int totalCredit = 0;     
           
    @Override
    public void start(Stage primaryStage) {
        RowConstraints row0 = new RowConstraints();
        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        RowConstraints row5 = new RowConstraints();
        
        // Configure row heights
        row0.setPercentHeight(10);
        row1.setPercentHeight(5);
        row2.setPercentHeight(10);
        row3.setPercentHeight(30);
        row4.setPercentHeight(10);
        row5.setPercentHeight(35);
         
        grid.getRowConstraints().addAll(row0, row1,row2, row3, row4, row5);

        grid.setAlignment(Pos.CENTER);

        grid.setHgap(5);
        grid.setVgap(5);
        
        // Unit 7 creating submit button below learnerID Textfield.
        grid.add(submitButton, 1, 1);
         
        grid.add(learnerIDLabel, 0, 0);
        GridPane.setHalignment(learnerIDLabel, HPos.LEFT);
        grid.add(learnerIDField, 1, 0);
        // configure & add combobox for available courses
        coursesComboBox.setMaxWidth(Double.MAX_VALUE);
        grid.add(coursesComboBox, 0, 2);
        GridPane.setValignment(coursesComboBox, VPos.TOP);
        // hide combobox until user has selected data file to populate it
        // Unit 7 add .setVisible to coursesComboBox
        coursesComboBox.setVisible(false);

        grid.add(confirmPromptLabel, 1, 3);  
        GridPane.setHalignment(confirmPromptLabel, HPos.LEFT);
        GridPane.setValignment(confirmPromptLabel, VPos.TOP);
        
        grid.add(registeredCoursesPromptLabel, 0, 4);  
        GridPane.setHalignment(registeredCoursesPromptLabel, HPos.LEFT);
        GridPane.setValignment(registeredCoursesPromptLabel, VPos.TOP);
        
        grid.add(creditHourPromptLabel, 1, 4);  
        GridPane.setHalignment(creditHourPromptLabel, HPos.LEFT);   
        GridPane.setValignment(creditHourPromptLabel, VPos.TOP);
         
        grid.add(registeredCoursesLabel, 0, 5);
        GridPane.setHalignment(registeredCoursesLabel, HPos.LEFT);   
        GridPane.setValignment(registeredCoursesLabel, VPos.TOP);
        registeredCoursesLabel.setStyle("-fx-background-color: #fff600;");
  
        grid.add(creditHoursLabel, 1, 5); 
        GridPane.setHalignment(creditHoursLabel, HPos.LEFT);   
        GridPane.setValignment(creditHoursLabel, VPos.TOP);
        creditHoursLabel.setStyle("-fx-background-color: #fff600;");
         
        Scene scene = new Scene(grid, 500, 500, Color.RED);
        
        primaryStage.setTitle("JavaFX Register for Courses");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //*****************************************************
        //  Add code to load course data into ComboBox
        //*****************************************************
        List<Course> available = service.getAllCourses();
        coursesComboBox.getItems().addAll(available);
        
        // ****************************************************
        // Course combobox event handler 
        //  Add code to enroll learner in course & update 
        //  list of registered courses. 
        // ****************************************************
        // TODO add validation, exceptionhandling, logging
        coursesComboBox.setOnAction(e -> {
            if(totalCredit < MAX_CREDIT_LOAD)
            {
                choice = coursesComboBox.getValue();
        
                service.createCourseRegistration(learnerIDField.getText(),coursesComboBox.getValue().getCourseCode());
  
            }
            
            // TODO want to remove
            for(CourseRegistration cr :service.getAllCourseRegistrations(learnerIDField.getText()))
            {
                System.out.println(cr.getLearnerID() + ": " + cr.getCourseCode());
            }
    });    
    
    submitButton.setOnAction((ActionEvent e) -> 
    {     

        
            // Unit 7 marking comboBox and Label true after submit is pressed.
            coursesComboBox.setVisible(true);
            // Unit 7 changing label to LearnerID Label
            learnerIDLabel.setVisible(true);      
        }); 
    }
    
    

    public static void main(String[] args) 
    {
        log.setLevel(Level.INFO);
        log.log(Level.SEVERE, "org.hibernate");
        launch(args);
    } 
    // Unit 7 TODO add stop and logging Info
    @Override
    public void stop()
    {
        em.close();
        emf.close();
        log.log(Level.INFO, "Application closed.");
    }
}
