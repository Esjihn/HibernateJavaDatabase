package u07a1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <Matthew D. Miller IT4749 Adv Java Programming>
 */
// Add Hibernate annotations for class to map it the course_registration table
@Entity
@Table(name="learner_registration")
public class CourseRegistration {
    // Add Hibernate @Column and @Id annotations
    @Column(name="registration_id")
    @Id
    @GeneratedValue
    private long registrationID;
    
    // Add Hibernate @Column annotation
    @Column(name="learner_id")
    private String learnerID;
    
    // Add Hibernate @Column annotation
    @Column(name="course_code")
    private String courseCode;

    public CourseRegistration() { }
    
    public CourseRegistration(String learnerID, String courseCode) {
        this.learnerID = learnerID;
        this.courseCode = courseCode;
    }

    public long getRegistrationID() {
        return registrationID;
    }

    public String getLearnerID() {
        return learnerID;
    }

    public void setLearnerID(String learnerID) {
        this.learnerID = learnerID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }    
}
