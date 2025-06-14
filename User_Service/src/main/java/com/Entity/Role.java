package com.Entity;

import java.time.Instant;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.Enum.RolesEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RolesEnum name;
	
	@Column(nullable = false)
	private String description;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "role")
	private List<User> user;
	
	@CreationTimestamp
	private Instant createdAt;

	@UpdateTimestamp
	private Instant updateAt;
}
