package model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table
public class Cohort {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int cohortId;
    private String cohortName;
    private String duration;

    public Cohort (String cohortName, String duration) {
        this.cohortName = cohortName;
        this.duration = duration;
    }

}
