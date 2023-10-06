package uz.pdp.pdperp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pdperp.entity.Group;
import uz.pdp.pdperp.entity.Lesson;

import java.util.List;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {
    List<Lesson> findLessonsByGroup_Id(UUID groupId);
}
