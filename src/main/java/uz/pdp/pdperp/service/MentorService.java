package uz.pdp.pdperp.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.pdperp.DTOS.request.MentorCreateDto;
import uz.pdp.pdperp.entity.MentorEntity;
import uz.pdp.pdperp.entity.UserEntity;
import uz.pdp.pdperp.exception.DataNotFoundException;
import uz.pdp.pdperp.repository.MentorRepository;
import uz.pdp.pdperp.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MentorService {
    private final MentorRepository mentorRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public String create(MentorCreateDto dto, UUID ownerId) {
        UserEntity user = userRepository.findById(ownerId).orElseThrow(
                () -> new DataNotFoundException("User not found"));
        MentorEntity mentor = modelMapper.map(dto, MentorEntity.class);
        mentor.setUserEntity(user);
        mentorRepository.save(mentor);
        return "Successes";
    }
}
