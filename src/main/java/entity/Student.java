package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "student_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "student_name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_courses", joinColumns = {
            @JoinColumn(name = "student_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "course_id", nullable = false, updatable = false) })
    private Set<Course> courses = new HashSet<Course>(10);


    public Student() { }

    public Student(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses.clear();
        this.courses = courses;
    }

    public boolean addToCourse(Course course) {
        return courses.add(course);
    }

    public boolean deleteFromCourse(Course course) {
        return courses.remove(course);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Student that = (Student) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean fullyEquals(Student s) {
        boolean idEq = s.getId().equals(this.id);

        boolean nameEq = (s.getName() == null && this.name == null)
                || (s.getName() != null && this.name != null && s.getName().equals(this.name));

        return idEq && nameEq;
    }
}
