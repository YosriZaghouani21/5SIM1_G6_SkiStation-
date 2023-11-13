package tn.esprit.SkiStationProject.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity

public class Instructor extends BaseEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	Long numInstructor;

	String firstName;
	String lastName;
	LocalDate dateOfHire;
	@OneToMany
	Set<Course> courses;
	public Instructor(long i, String test1, Object o) {
	}

}
