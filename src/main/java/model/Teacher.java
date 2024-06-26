package model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;


//lombok annotation
@Getter
@Setter
@NoArgsConstructor

//jakarta annotation
@Entity
@Table
public class Teacher implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    private int teacherId;
    private String salary;
    private String teacherName;



    @ManyToMany (targetEntity = Cohort.class)
    private Set <Cohort> cohort;

    public Set<Cohort> getCohorts() {
        return cohort;
    }
    public void setCohort(Set<Cohort> cohort) {
        this.cohort = cohort;
    }


    //Lab 2
//    @OneToOne (cascade = CascadeType.ALL)
//    private Address address;
//    public Address getAddress() {
//        return address;
//    }
//    public void setAddress(Address address) {
//        this.address = address;
//    }

    public Teacher( String salary, String teacherName) {
        super();
        this.salary = salary;
        this.teacherName = teacherName;    }

    public Teacher(String salary, String teacherName, Department department) {
        this.salary = salary;
        this.teacherName = teacherName;
    }

    public Teacher(String salary, String teacherName, Set<Cohort> cohort) {
        this.salary = salary;
        this.teacherName = teacherName;
    }

}


//Example 1:

//@Entity
//@Table
//public class Teacher implements Serializable{
//    @Serial
//    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int teacherId;
//    private String salary;
//    private String TeacherName;
//
//    @ManyToOne
//    @JoinColumn (name = "deptId")
//    private Department department;
//
//    public Teacher(int teacherId, String salary, String teacherName){
//
//        super();
//        this.teacherId = teacherId;
//        this.salary = salary;
//        TeacherName = teacherName; }
//    public Teacher() {}
//
//    public Teacher(String s, String mHaseeb, Department dept1) {
//    }
//
//    public Department getDep() {
//        return department; }
//    public void setDep(Department department) {
//        this.department = department;
//    }
//    public int getTeacherId() {
//        return teacherId;
//    }
//    public void setTeacherId(int teacherId) {
//        this.teacherId = teacherId;
//    }
//    public String getSalary() {
//        return salary;
//    }
//    public void setSalary(String salary) {
//        this.salary = salary;
//    }
//    public String getTeacherName() {
//        return TeacherName;
//    }
//    public void setTeacherName(String teacherName) {
//        TeacherName = teacherName; }
//
//}
