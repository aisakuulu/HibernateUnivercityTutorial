package org.univercity.dao;

import org.univercity.entity.Instructor;

import java.util.List;

public interface InstructorDao {

    void saveInstructor(Instructor instructor);

    void updateInstructor(Long id, Instructor newInstructor);

    Instructor getInstructorById(Long id);

    List<Instructor> getInstructorsByCourseId(Long id);

    void deleteInstructorById(Long id);

    void assignInstructorToCourse(Long instructorId, Long courseID);
}
