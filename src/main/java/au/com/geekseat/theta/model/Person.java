package au.com.geekseat.theta.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person extends BaseModel implements UserDetails {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 256)
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    private Boolean active = true;

    @JsonIgnore
    @Type(type = "text")
    protected String attachmentListData;

    @ManyToMany(fetch = EAGER)
    @JsonIgnoreProperties("persons")
    private Collection<Role> roles = new ArrayList<>();

    @Transient
    protected List<String> attachmentList = new ArrayList<>();

    public Person(Person person) {
        this.setId(person.getId());
        this.name = person.getName();
        this.email = person.getEmail();
        this.password = person.getPassword();
        this.active = person.getActive();
        this.roles = person.getRoles();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\": \"").append(getId()).append("\", ");
        sb.append("\"name\": \"").append(this.name).append("\", ");
        sb.append("\"email\": \"").append(this.email).append("\", ");
        sb.append("\"password\": \"[PROTECTED]\"").append("}");
        return sb.toString();
    }

    public Map<String, Object> toMap() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("id", getId());
        map.put("name", this.name);
        map.put("email", this.email);
        map.put("password", "[PROTECTED]");
        return map;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
