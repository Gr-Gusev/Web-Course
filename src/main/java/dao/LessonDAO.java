package dao;

import entity.Lesson;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface LessonDAO {

    public void addLesson(Lesson lesson) throws SQLException;

    public void updateLesson(Lesson lesson) throws SQLException;

    public Lesson getLessonById(Long lesson_id) throws SQLException;

    public Collection<Lesson> getAllLessons() throws SQLException;

    public void deleteLesson(Lesson lesson) throws SQLException;

    public List<Lesson> getScheduleForStudentUntil(long student_id, java.util.Date untilDate)
            throws SQLException;

    public List<Lesson> getScheduleForTeacherUntil(long teacher_id, java.util.Date untilDate)
            throws SQLException;

}
