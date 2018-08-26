package u07a1;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * @author <Matthew D. Miller IT4749 Adv Java Programming>
 */
public class CourseRegistrationService {
    protected EntityManager em;
    
    public CourseRegistrationService(EntityManager em) {
        this.em = em;
    }
    
    public Course createCourse(String courseCode, int creditHours) {
        Course course = new Course(courseCode, creditHours);
        em.persist(course);
        return course;
    }
    
    public List<Course> getAllCourses() {
        // Query is written in Hibernate Query Language (HQL) not SQL
        // Unit 7 changed to Course object
        String hql = "SELECT crs FROM Course crs ORDER BY courseCode";
        TypedQuery<Course> query = em.createQuery(hql, Course.class);
        return query.getResultList();
    }
    
    public CourseRegistration createCourseRegistration(String learnerID, String courseCode) {
        CourseRegistration registration = new CourseRegistration(learnerID, courseCode);
        // Unit 7 notes, entity management and how to start transaction, persisting, flushing, committing
        em.getTransaction().begin();
        em.persist(registration);
        em.flush();
        em.getTransaction().commit();
        return registration;
    }
    // Unit 7 added string id for use with other users
    public List<CourseRegistration> getAllCourseRegistrations(String id) {
        // Query is written in Hibernate Query Language (HQL) not SQL
        String hql = "SELECT reg FROM CourseRegistration reg WHERE learnerID = :id";
        TypedQuery<CourseRegistration> query = em.createQuery(hql, CourseRegistration.class).setParameter("id", id);
        return query.getResultList();
    }
}
