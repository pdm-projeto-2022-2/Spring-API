package io.github.marcondesnjr.api.service.dto;

import io.github.marcondesnjr.api.model.Donation;
import io.github.marcondesnjr.api.model.Donor;
import io.github.marcondesnjr.api.model.Employee;
import io.github.marcondesnjr.api.model.Notification;
import org.springframework.beans.BeanUtils;

public class Mapper {

    public static Donor toEntity(DonorDTO donorDTO){
        var donor = new Donor();
        BeanUtils.copyProperties(donorDTO, donor);
        if(!donor.getDonations().isEmpty()) {
            var list = donorDTO.getDonations().stream().map(Mapper::toEntity).toList();
            list.forEach(donor::addDonation);
        }
        return donor;
    }

    public static DonorDTO toDTO(Donor entity){
        var dto = new DonorDTO();
        BeanUtils.copyProperties(entity, dto);
        if(!entity.getDonations().isEmpty()) {
            var list = entity.getDonations().stream().map( item -> toDTO(item, entity)).toList();
            dto.setDonations(list);
        }
        return dto;
    }

    public static Employee toEntity(EmployeeDTO employeeDTO){
        var employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    public static EmployeeDTO toDTO(Employee entity){
        var dto = new EmployeeDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static NotificationDTO toDTO(Notification entity){
        var dto = new NotificationDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public static Notification toEntity(NotificationDTO dto){
        var entity = new Notification();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public static DonationDTO toDTO(Donation entity, Donor donor){
        var dto = new DonationDTO();
        BeanUtils.copyProperties(entity, dto);
        var donorDto = new DonorDTO();
        donorDto.setId(donor.getId());
        donorDto.setName(donor.getName()); //FIXME: More fields
        dto.setDonor(donorDto);
        return dto;
    }

    public static Donation toEntity(DonationDTO dto){
        var entity = new Donation();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

}
