package dataaccess;

import domain.Student;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface MyStudentRepository extends BaseRepository<Student> {
    void deletebyId(Long id);

    Optional<Student> findById(Long id);
    List<Student> findByName(String firstName, String lastName);
    List<Student> findByBirthYear(int year);
    List<Student> findByBirthDateRange(Date startDate, Date endDate);
    void deleteById(Long id);
}
