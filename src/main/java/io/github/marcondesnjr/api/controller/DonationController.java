package io.github.marcondesnjr.api.controller;

import io.github.marcondesnjr.api.service.DonationService;
import io.github.marcondesnjr.api.service.dto.DonationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RequiredArgsConstructor
@RestController
public class DonationController {

    private final DonationService donationService;

    @PostMapping("/donors/{donorId}/donations")
    public ResponseEntity<?> createDonation(@PathVariable Long donorId, @RequestBody DonationDTO donationDTO){
        var response = donationService.create(donorId, donationDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("{id}").buildAndExpand(response.getId()).toUri()).body(response);
    }

    @GetMapping("/donors/{donorId}/donations")
    public ResponseEntity<?> getAllDonationsByDonor(@PathVariable Long donorId){
        var list = donationService.readAllByDonor(donorId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/donations")
    public ResponseEntity<?> listAllDonations(){
        var list = donationService.findAll();
        return ResponseEntity.ok(list);
    }

    @PatchMapping("/donations/{donationId}")
    public ResponseEntity<?> doDonation(@PathVariable Long donationId){
        donationService.doDonation(donationId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/donations/{donationId}")
    public ResponseEntity<?> removeDonation(@PathVariable Long donationId){
        donationService.removeDonation(donationId);
        return ResponseEntity.ok().build();
    }

}
