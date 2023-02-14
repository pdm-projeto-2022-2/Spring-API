package io.github.marcondesnjr.api.controller;

import io.github.marcondesnjr.api.controller.requestDto.SignInRequest;
import io.github.marcondesnjr.api.model.BloodType;
import io.github.marcondesnjr.api.service.DonorService;
import io.github.marcondesnjr.api.service.dto.DonorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequiredArgsConstructor
public class DonorController {

    private final DonorService donorService;
    private final FileStorageService fileStorageService;

    @PostMapping("/donors")
    public ResponseEntity<?> createDonor(@ModelAttribute SignInRequest signInRequest){
        fileStorageService.init();
        String filename = fileStorageService.save(signInRequest.getImage());
        DonorDTO donorDTO = new DonorDTO();
        BeanUtils.copyProperties(signInRequest, donorDTO);
        donorDTO.setImage(filename);
        var donorResponse = donorService.create(donorDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath().path("{id}")
                .buildAndExpand(donorResponse.getId()).toUri()).body(donorResponse);
    }

    @GetMapping("/donors")
    public ResponseEntity<?> createDonor(@RequestParam BloodType bloodType){
        var list = donorService.findAllByBloodType(bloodType);
        return ResponseEntity.ok(list);
    }

}
