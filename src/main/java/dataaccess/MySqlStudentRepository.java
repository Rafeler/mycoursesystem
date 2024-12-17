package dataaccess;

import domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlStudentRepository implements MyStudentRepository {
    private static final String INSERT_SQL = "INSERT INTO students (first_name, last_name, birth_date) VALUES (?, ?, ?)";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM students WHERE id = ?";
    private static final String FIND_BY_NAME_SQL = "SELECT * FROM students WHERE first_name LIKE ? AND last_name LIKE ?";
    private static final String FIND_BY_BIRTHYEAR_SQL = "SELECT * FROM students WHERE YEAR(birth_date) = ?";
    private static final String FIND_BY_BIRTHDATE_RANGE_SQL = "SELECT * FROM students WHERE birth_date BETWEEN ? AND ?";

    private final MysqlDatabaseConnection connection;

    public MySqlStudentRepository(MysqlDatabaseConnection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Student> getById(Long id) {
        try (Connection con = connection.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_ID_SQL)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Student(
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("birth_date")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Student> getAll() {
        // Not needed, but could be added
        return new ArrayList<>();
    }

    @Override
    public Optional<Student> insert(Student student) {
        try (Connection con = connection.getConnection();
             PreparedStatement ps = con.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setDate(3, student.getBirthDate());
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        student.setId(generatedKeys.getLong(1));
                        return Optional.of(student);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Student> update(Student student) {
        // Not implemented, can be added if needed
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deletebyId(Long id) {
        // Not implemented, can be added if needed
    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Student> findByName(String firstName, String lastName) {
        List<Student> students = new ArrayList<>();
        try (Connection con = connection.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_NAME_SQL)) {
            ps.setString(1, "%" + firstName + "%");
            ps.setString(2, "%" + lastName + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("birth_date")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Student> findByBirthYear(int year) {
        List<Student> students = new ArrayList<>();
        try (Connection con = connection.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_BIRTHYEAR_SQL)) {
            ps.setInt(1, year);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("birth_date")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Student> findByBirthDateRange(Date startDate, Date endDate) {
        List<Student> students = new ArrayList<>();
        try (Connection con = connection.getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_BY_BIRTHDATE_RANGE_SQL)) {
            ps.setDate(1, startDate);
            ps.setDate(2, endDate);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getDate("birth_date")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
