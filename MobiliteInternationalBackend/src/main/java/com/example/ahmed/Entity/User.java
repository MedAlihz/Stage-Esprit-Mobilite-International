package com.example.ahmed.Entity;

import com.example.ahmed.token.Token;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Data
@Builder
@Table(name = "user")
public class User implements UserDetails {
    //ID*****************************
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    //ATTRIBUTES**************************
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    @Size(min = 4,max = 8)
    private String password;
//    private Integer phone;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "userCandidature")
    @JsonIgnore
    private Set<Candidature> candidatures;


    private Boolean locked = false;
    private Boolean enabled = false;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private  Role role;


    //RELATIONSHIPS***************************
//    @ManyToOne(cascade = CascadeType.ALL)
//    @ToString.Exclude
//    private Group group ;

    @OneToMany(mappedBy = "UserAuth" ,fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Autority> autority;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Token> tokens;


//    @ManyToMany
//    private List<Choice> choices;


    //OTHERS*****************************
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>() ;
        for (Autority authority : autority) {
            if (authority !=null)
                authorities.add(new SimpleGrantedAuthority(role.name()));
            else
                System.out.println("----- U have no AUtority Bro ----");
        }
        return authorities;
        // the first try *********************************************************
        //        SimpleGrantedAuthority authority =
//                new SimpleGrantedAuthority(role.name()) ;
//
//        return Collections.singletonList(authority);
    }

    public User(String firstname,
                String lastname,
                String email,
                String password,
                Role role
                ){
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.password = password;
        this.role = role;


    }
    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return email;
    }


    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return enabled;
    }


//    @OneToMany(mappedBy = "classe")
//    @JsonIgnore
//    @ToString.Exclude
//    private List<CoursClassroom> coursClassrooms;
}
