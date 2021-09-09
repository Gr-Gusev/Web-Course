package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "day_of_start")
    private java.sql.Date dayOfStart;

    @Column(name = "day_of_finish")
    private java.sql.Date dayOfFinish;

    @Column(name = "hours_per_day")
    private Short hoursPerDay;

    @Column(name = "course_name")
    private String name;

    @OneToMany(mappedBy = "course", fetch=FetchType.LAZY)
    private Set<Lesson> lessons = new HashSet<Lesson>(10);

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private Set<Student> students = new HashSet<Student>(10);


    public Course() { }

    public Course(Company co, java.sql.Date start, java.sql.Date finish, Short hours, String name) {
        this.company = co;
        this.dayOfStart = start;
        this.dayOfFinish = finish;
        this.hoursPerDay = hours;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public java.sql.Date getDayOfStart() {
        return dayOfStart;
    }

    public void setDayOfStart(java.sql.Date dayOfStart) {
        this.dayOfStart = dayOfStart;
    }

    public java.sql.Date getDayOfFinish() {
        return dayOfFinish;
    }

    public void setDayOfFinish(java.sql.Date duration) {
        this.dayOfFinish = duration;
    }

    public Short getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(Short hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons.clear();
        this.lessons = lessons;
    }

    public Set<Student> getStudents() {
        return this.students;
    }

    public void setStudents(Set<Student> students) {
        this.students.clear();
        this.students = students;
    }

    public boolean isActive() {
        java.util.Date date = new java.util.Date();
        java.sql.Date curDate = new java.sql.Date(date.getTime());

        java.sql.Date start = new java.sql.Date(curDate.getTime());
        java.sql.Date finish = new java.sql.Date(curDate.getTime());
        start.setTime(this.dayOfStart.getTime());
        finish.setTime(this.dayOfFinish.getTime());
        long dayInMs = 24 * 60 * 60 * 1000;
        finish.setTime(finish.getTime() + dayInMs);

        return curDate.after(start) && curDate.before(finish);
    }

    public boolean isFinished() {
        java.util.Date date = new java.util.Date();
        java.sql.Date curDate = new java.sql.Date(date.getTime());

        java.sql.Date finish = new java.sql.Date(date.getTime());
        finish.setTime(this.dayOfFinish.getTime());
        long dayInMs = 24 * 60 * 60 * 1000;
        finish.setTime(finish.getTime() - (finish.getTime() % dayInMs) + dayInMs);

        return curDate.after(finish);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Course that = (Course) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean fullyEquals(Course cu) {
        boolean idEq = cu.getId().equals(this.id);

        boolean companyEq = cu.getCompany().fullyEquals(this.company);

        boolean startEq = cu.getDayOfStart().toString().equals(this.dayOfStart.toString());

        boolean finishEq = cu.getDayOfFinish().toString().equals(this.dayOfFinish.toString());

        boolean hoursEq = (cu.getHoursPerDay() == null && this.hoursPerDay == null)
                || (cu.getHoursPerDay() != null && this.hoursPerDay != null
                    && cu.getHoursPerDay().equals(this.hoursPerDay));

        boolean nameEq = (cu.getName() == null && this.name == null)
                || (cu.getName() != null && this.name != null && cu.getName().equals(this.name));

        return idEq && companyEq && startEq && finishEq && hoursEq && nameEq;
    }
}
