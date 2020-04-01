package com.example.ecommerce.configuration;//package com.springbootcamp.springsecurity;


import com.example.ecommerce.entity.Customer;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.Seller;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.SellerRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Bootstrap implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (userRepository.count() < 0) {

            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            Seller seller = new Seller();
            seller.setUsername("ttn_user_1");
            seller.setEmail("ttnuser1@gmail.com");
            seller.setFirstName("ttnuser1");
            seller.setGstNumber("123x");
            seller.setContactNumber("09876561238");
            seller.setCompanyName("TTN");
            seller.setPassword(passwordEncoder.encode("qwertyQ!1"));
            List<Role> roles = new LinkedList<>();
            roles.add(new Role("ROLE_SELLER"));
            roles.add(new Role("ROLE_ADMIN"));
            seller.setRolesList(roles);
            sellerRepository.save(seller);

            Customer customer = new Customer();
            customer.setUsername("ttn_user_12");
            customer.setEmail("ttnuser2@gmail.com");
            customer.setFirstName("ttnuser2");
            customer.setContactNumber("09876561238");
            customer.setPassword(passwordEncoder.encode("qwertyQ!1"));
            List<Role> roles1 = new LinkedList<>();
            roles1.add(new Role("ROLE_CUSTOMER"));
            roles1.add(new Role("ROLE_ADMIN"));
            seller.setRolesList(roles1);
            customerRepository.save(customer);
            System.out.println("Total users saved::" + userRepository.count());
        }
    }
}
