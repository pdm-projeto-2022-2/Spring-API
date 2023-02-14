package io.github.marcondesnjr.api.security;

import io.github.marcondesnjr.api.repository.DonorRepository;
import io.github.marcondesnjr.api.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final DonorRepository donorRepository;
    private final EmployeeRepository employeeRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var donorOptional = donorRepository.findByEmail(username);
        if(donorOptional.isPresent()){
            return  new UserDetailsImpl(donorOptional.get());
        }else {
            var employeeOptional = employeeRepository.findByEmail(username);
            if(employeeOptional.isPresent()){
                return new UserDetailsImpl(employeeOptional.get());
            }else{
                throw new UsernameNotFoundException("Username not found: "+username);
            }
        }
    }
}
