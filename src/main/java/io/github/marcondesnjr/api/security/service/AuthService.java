package io.github.marcondesnjr.api.security.service;

import io.github.marcondesnjr.api.repository.DonorRepository;
import io.github.marcondesnjr.api.repository.EmployeeRepository;
import io.github.marcondesnjr.api.security.JWTService;
import io.github.marcondesnjr.api.security.dao.AuthDAO;
import io.github.marcondesnjr.api.security.dao.TokenDao;
import io.github.marcondesnjr.api.service.dto.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final DonorRepository donorRepository;
    private final EmployeeRepository employeeRepository;
    private final JWTService jwtService;



    public TokenDao auth(AuthDAO authDAO){
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authDAO.getUsername(), authDAO.getPassword()
                )
        );
        var userDetails = (UserDetails) auth.getPrincipal();
        var donorOptional = donorRepository.findByEmail(authDAO.getUsername());
        var claims = new HashMap<String, Object>();
        if(donorOptional.isPresent()){
            claims.put("ROLE", "ROLE_USER");
            var donor =  Mapper.toDTO(donorOptional.get());
            donor.setPassword("");
            donor.setDonations(new ArrayList<>());
            claims.put("DETAILS", donor);
        }else{
            var employeeOptional = employeeRepository.findByEmail(authDAO.getUsername());
            if(employeeOptional.isPresent()){
                claims.put("ROLE", "ROLE_EMPLOYEE");
                var employee = Mapper.toDTO(employeeOptional.get());
                employee.setPassword("");
                employeeOptional.get().setPassword("");
                claims.put("DETAILS", employee);
            }
        }
        var token = new TokenDao();
        token.setToken(jwtService.generateToken(claims,userDetails));
        return token;
    }

}
