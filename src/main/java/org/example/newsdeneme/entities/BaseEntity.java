package org.example.newsdeneme.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public abstract class BaseEntity {
	@Id
	String id;
	@Builder.Default
	Long createdDate=System.currentTimeMillis();
	@Builder.Default
	Integer state=1;
	@Builder.Default
	Long updatedAt= System.currentTimeMillis();
	
	@PreUpdate
	public void preUpdate() {
		updatedAt=System.currentTimeMillis();
	}
}