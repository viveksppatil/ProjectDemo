package com.Entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.Enum.KycStatusEnum;
import com.Enum.UserStatusEnum;
import com.Security.CustomeUserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Slf4j
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements CustomeUserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String middleName;

	@Column(nullable = false)
	private String lastName;

	@Column(name = "umobileno", nullable = false)
	private String mobileNumber;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private KycStatusEnum kycstatus;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserStatusEnum userstatus;

	@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Role role;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updatedAt;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		log.debug("getAuthorities()");
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getName().toString());
		return Collections.singletonList(authority);
	}

	@Override
	public boolean isAccountNonExpired() {
		log.debug("isAccountNonExpired()");
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		log.debug("isAccountNonLocked()");
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		log.debug("isCredentialsNonExpired()");
		return true;
	}

	@Override
	public boolean isEnabled() {
		log.debug("isEnabled()");
		return true;
	}

}


