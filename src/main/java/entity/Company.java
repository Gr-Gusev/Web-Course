package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company  implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "company_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "company_name")
    private String name;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy="company", fetch=FetchType.LAZY)
    private Set<Teacher> teachers = new HashSet<Teacher>(10);

    @OneToMany(mappedBy="company", fetch=FetchType.LAZY)
    private Set<Course> courses = new HashSet<Course>(10);


    public Company() { }

    public Company(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Company(String name) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers.clear();
        this.teachers = teachers;
    }

    public boolean addTeacher(Teacher teacher) {
        return teachers.add(teacher);
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        courses.clear();
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Company that = (Company) o;
        return Objects.equals(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean fullyEquals(Company co) {
        boolean idEq = co.getId().equals(this.id);

        boolean nameEq = (co.getName() == null && this.name == null)
                        || (co.getName() != null && this.name != null && co.getName().equals(this.name));

        boolean addressEq = (co.getAddress() == null && this.address == null)
                || (co.getAddress() != null && this.address != null && co.getAddress().equals(this.address));

        return idEq && nameEq && addressEq;
    }
}
