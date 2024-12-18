package dataaccess;

import domain.Course;
import domain.CourseType;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

public interface MyCourseRepository  extends BaseRepository<Course> {
    List<Course> findAllCoursesByName(String name);

    List<Course> findAllCoursesByDescription(String description);

    List<Course> findAllCoursesByNameOrDescription(String searchText);

    List<Course> findAllCoursesByCourseType(CourseType courseType);

    List<Course> findAllCoursesByBeginDate(Date startDate);

    List<Course> findAllRunningCourses();

    void deletebyId(Long id);
}
