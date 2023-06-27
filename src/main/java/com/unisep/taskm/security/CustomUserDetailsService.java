// package com.unisep.taskm.security;

// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.List;
// import java.util.Set;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;

// import com.unisep.taskm.models.User;
// import com.unisep.taskm.repository.UserRepository;

// public class CustomUserDetailsService implements UserDetailsService {
    
//     @Autowired
//     private UserRepository userRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userRepository.findByNome(username)
//                 .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
//         return new org.springframework.security.core.userdetails.User(user.getEmail(),
//             user.getSenha(),
//             true,
//             true,
//             true,
//             true,
//             getAuthorities(user)
//         );
//     }

//     private Collection<? extends GrantedAuthority> getAuthorities(User user) {
//         Set<String> roles = user.getRoles();
//         List<GrantedAuthority> authorities = new ArrayList<>();

//         roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

//         return authorities;
//     }
// }
