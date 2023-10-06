package uz.pdp.pdperp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.pdperp.repository.GroupRepository;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
}
