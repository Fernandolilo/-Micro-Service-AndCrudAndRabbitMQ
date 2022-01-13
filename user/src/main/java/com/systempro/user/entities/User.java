package com.systempro.user.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
public class User implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_name", nullable = false, unique = true, length = 255)
	private String userName;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "accontNonExpired")
	private Boolean accontNonExpired;
	
	@Column(name = "accontNonLocked")
	private Boolean accontNonLocked;
	
	@Column(name = "credentialsNonExpired")
	private Boolean credentialsNonExpired;
	
	@Column(name = "enabled")
	private Boolean enabled;

	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_permission", joinColumns = {@JoinColumn(name = "id.user")}, 
	inverseJoinColumns = {@JoinColumn(name= "id.permissions")})
	private List<Permission> permissions;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}
	
	public List<String> getRoles(){
		List<String> roles = new ArrayList<>();
		this.permissions.stream()
			.forEach(p -> {
				roles.add(p.getDescription());
			});
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accontNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accontNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public User() {
	}

	public User(Long id, String userName, String password, Boolean accontNonExpired, Boolean accontNonLocked,
			Boolean credentialsNonExpired, Boolean enabled, List<Permission> permissions) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.accontNonExpired = accontNonExpired;
		this.accontNonLocked = accontNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.permissions = permissions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getAccontNonExpired() {
		return accontNonExpired;
	}

	public void setAccontNonExpired(Boolean accontNonExpired) {
		this.accontNonExpired = accontNonExpired;
	}

	public Boolean getAccontNonLocked() {
		return accontNonLocked;
	}

	public void setAccontNonLocked(Boolean accontNonLocked) {
		this.accontNonLocked = accontNonLocked;
	}

	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=")
			   .append(id)
			   .append(", userName=")
			   .append(userName)
			   .append(", password=")
			   .append(password)
			   .append(", accontNonExpired=")
			   .append(accontNonExpired)
			   .append(", accontNonLocked=")
			   .append(accontNonLocked)
			   .append(", credentialsNonExpired=")
			   .append(credentialsNonExpired)
			   .append(", enabled=")
			   .append(enabled)
			   .append(", permissions=")
			   .append(permissions)
			   .append("]");
		return builder.toString();
	}


}
