package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher  implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "teacher_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "teacher_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    @OneToMany(mappedBy="teacher", fetch=FetchType.LAZY)
    private Set<Lesson> lessons = new HashSet<Lesson>(10);


    public Teacher() { }

    public Teacher(String name, Company company) {
        this.name = name;
        this.company = company;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons.clear();
        this.lessons = lessons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher that = (Teacher) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean fullyEquals(Teacher t) {
        boolean idEq = t.getId().equals(this.id);

        boolean companyEq = t.getCompany().fullyEquals(this.company);

        boolean nameEq = (t.getName() == null && this.name == null)
                || (t.getName() != null && this.name != null && t.getName().equals(this.name));

        return idEq && companyEq && nameEq;
    }
}
