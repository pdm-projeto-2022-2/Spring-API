package io.github.marcondesnjr.api.service;

import io.github.marcondesnjr.api.model.BloodType;
import io.github.marcondesnjr.api.repository.DonorRepository;
import io.github.marcondesnjr.api.service.dto.DonorDTO;
import io.github.marcondesnjr.api.service.dto.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DonorService {
    private final DonorRepository donorRepository;
    private final PasswordEncoder passwordEncoder;


    public DonorDTO create(DonorDTO donorDTO){
        var donor = Mapper.toEntity(donorDTO);
        donor.setPassword(passwordEncoder.encode(donor.getPassword()));
        donorRepository.save(donor);
        donor.setPassword(null);
        return Mapper.toDTO(donor);
    }

    public List<DonorDTO> findAllByBloodType(BloodType bloodType){
        if (bloodType == BloodType.ALL){
            return donorRepository.findAll().stream().map(Mapper::toDTO).toList();
        }
        var list = donorRepository.findAllByBloodType(bloodType.name());
        return list.stream().map(Mapper::toDTO).toList();
    }

}
