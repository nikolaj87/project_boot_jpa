package de.telran.lesson3.service_layer.jpa;

import de.telran.lesson3.domain_layer.entity.jpa.JpaRole;
import de.telran.lesson3.domain_layer.entity.jpa.JpaUser;
import de.telran.lesson3.repository_layer.jpa.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JpaUserService implements UserDetailsService {

    @Autowired
    private JpaUserRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        JpaUser user = repository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public JpaUser saveUser(JpaUser user) {
        JpaUser foundUser = repository.findByUsername(user.getUsername());

        if (foundUser != null) {
            return null;
        }

        Set<JpaRole> roles = new HashSet<>();
        JpaRole userRole = new JpaRole(1, "USER");
        roles.add(userRole);
        user.setRoles(roles);

        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return repository.save(user);
    }

    public List<JpaUser> getAll() {
        return repository.findAll();
    }

}
