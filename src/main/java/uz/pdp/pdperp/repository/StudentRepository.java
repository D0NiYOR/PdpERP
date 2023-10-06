package uz.pdp.pdperp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.pdperp.entity.StudentEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    List<StudentEntity> findStudentEntitiesByGroup_Id(UUID groupId);
}
