package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "lessons")
public class Lesson  implements Serializable, Comparable<Lesson> {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "lesson_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Column(name = "date_and_time")
    private java.sql.Timestamp dateAndTime;


    public Lesson() { }

    public Lesson(Course course, Teacher teacher, java.sql.Timestamp time) {
        this.course = course;
        this.teacher = teacher;
        this.dateAndTime = time;
    }


    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public java.sql.Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(java.sql.Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public boolean isPassed() {
        java.util.Date curDate = new java.util.Date();
        java.util.Date lessonEndTime = new java.util.Date();
        lessonEndTime.setTime(this.dateAndTime.getTime());
        lessonEndTime.setTime(lessonEndTime.getTime() + this.course.getHoursPerDay() * 60 * 60 * 1000);
        return curDate.after(lessonEndTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Lesson that = (Lesson) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Lesson les) {
        if (this.getDateAndTime().getTime() - les.getDateAndTime().getTime() == 0)
            return 0;

        if (this.getDateAndTime().getTime() > les.getDateAndTime().getTime())
            return 1;
        else
            return -1;
    }

    public boolean fullyEquals(Lesson l) {
        boolean idEq = l.getId().equals(this.id);

        boolean courseEq = l.getCourse().fullyEquals(this.course);

        boolean teacherEq = l.getTeacher().fullyEquals(this.teacher);

        boolean timeEq = (l.getDateAndTime() == null && this.dateAndTime == null)
                || (l.getDateAndTime() != null && this.dateAndTime != null
                    && l.getDateAndTime().equals(this.dateAndTime));

        return idEq && courseEq && teacherEq && timeEq;
    }
}
