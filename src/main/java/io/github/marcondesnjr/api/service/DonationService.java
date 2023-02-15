package io.github.marcondesnjr.api.service;

import io.github.marcondesnjr.api.model.Donation;
import io.github.marcondesnjr.api.model.Donor;
import io.github.marcondesnjr.api.repository.DonationRepository;
import io.github.marcondesnjr.api.repository.DonorRepository;
import io.github.marcondesnjr.api.service.dto.DonationDTO;
import io.github.marcondesnjr.api.service.dto.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonorRepository donorRepository;
    private final DonationRepository donationRepository;


    @Transactional
    public DonationDTO create(Long donorId, DonationDTO donationDTO){
        var donor = getDonorById(donorId);
        var donation = Mapper.toEntity(donationDTO);
        donation.setStatus(Donation.DonationStatus.SCHEDULED);
        donor.addDonation(donation);
        donorRepository.saveAndFlush(donor);
        return Mapper.toDTO(donation, donor);
    }

    public List<DonationDTO> readAllByDonor(Long donorId){
        var donor = getDonorById(donorId);
        return donor.getDonations().stream().map(item -> Mapper.toDTO(item, donor)).toList();
    }

    public List<DonationDTO> findAll(){
        var donorList = donorRepository.findAll();
        List<DonationDTO> allDonations = new ArrayList<>();
        donorList.forEach( donor -> {
            var list = donor.getDonations().stream().map(item -> Mapper.toDTO(item, donor)).toList();
            allDonations.addAll(list);
        });
        return allDonations;
    }

    public void doDonation(Long donationId){
        var donation = donationRepository.findById(donationId).orElseThrow();
        donation.setStatus(Donation.DonationStatus.DONE);
        donationRepository.save(donation);;
    }

    public void removeDonation(Long id){
        donationRepository.deleteById(id);
    }

    private Donor getDonorById(Long donorId){
        return donorRepository.findById(donorId)
                .orElseThrow(()-> new EntityNotFoundException("Donor not found with id: "+donorId));
    }
}
