<<<<<<< HEAD
=======

>>>>>>> ece85e41568eaa38eacb1c2ccc45d7611ad79a3f
package u07a1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <Matthew D. Miller IT4749 Adv Java Programming>
 */

// Add Hibernate annotations for class to map it the course_offering table
@Entity
@Table(name="course_offerings")
public class Course {
    // Add Hibernate @Column and @Id annotations
    @Column(name="course_code")
    @Id
    private String courseCode;
    
    // Add Hibernate @Column annotation
    @Column(name="credit_hours")
    private int creditHours;
    
  
    public Course() { }
    
    public Course(String courseCode, int creditHours) {
        this.courseCode = courseCode;
        this.creditHours = creditHours;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }
    // Unit 7 add toString for comboBox, added override
    @Override
    public String toString()
    {   
        return this.courseCode;
    }
    
<<<<<<< HEAD
}
=======
}
>>>>>>> ece85e41568eaa38eacb1c2ccc45d7611ad79a3f
