package tn.esprit.SkiStationProject.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.repositories.CourseRepository;
import tn.esprit.SkiStationProject.entities.enums.Support;
import tn.esprit.SkiStationProject.entities.enums.TypeCourse;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class CourseServicesImplTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServicesImpl courseService;

    @Test
     void testAddCourse() {
        // Arrange
        Course course = new Course();
        course.setSupport(Support.SKI);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN); // Using the correct enum constant
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        // Act
        Course result = courseService.addCourse(course);

        // Assert
        assertNotNull(result);
        assertEquals(Support.SKI, result.getSupport());
        assertEquals(TypeCourse.COLLECTIVE_CHILDREN, result.getTypeCourse()); // Verifying with the correct enum constant
        verify(courseRepository, times(1)).save(course);
    }

    @Test
     void testRetrieveCourseById() {
        // Arrange
        Long id = 1L;
        Course course = new Course();
        when(courseRepository.findById(id)).thenReturn(Optional.of(course));

        // Act
        Course result = courseService.retrieveCourse(id);

        // Assert
        assertNotNull(result);
        verify(courseRepository, times(1)).findById(id);
    }

    @Test
     void testGetAllCourses() {
        // Arrange
        List<Course> courses = Arrays.asList(new Course(), new Course());
        when(courseRepository.findAll()).thenReturn(courses);

        // Act
        List<Course> result = courseService.retrieveAllCourses();

        // Assert
        assertEquals(2, result.size());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
     void testUpdateCourse() {
        // Arrange
        Course course = new Course();
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        // Act
        Course result = courseService.updateCourse(course);

        // Assert
        assertEquals(course, result);
        verify(courseRepository, times(1)).save(course);
    }

    // Additional tests can be added here for other methods in CourseServicesImpl
}
