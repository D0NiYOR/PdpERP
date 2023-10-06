package uz.pdp.pdperp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pdperp.entity.Group;

import java.util.List;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
    List<Group> findAllByMentorEntity_Id(UUID mentorId);
}
