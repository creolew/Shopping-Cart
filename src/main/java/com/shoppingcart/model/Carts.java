package com.shoppingcart.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Carts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	

	//Con
	// @OneToOne(fetch = FetchType.LAZY) // checked
	 
	
//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	 @JoinColumn(name = "user_id", referencedColumnName = "id")
//	 private User user;
	 
	 
	 
//	 @OneToOne(orphanRemoval = false)
//	 @MapsId
//	 @JoinColumn(name = "user_id")
//	 private User user;
	 

	
	//Con
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
	private User user;

	
//------------------------	

	 
	 
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<LineItem> cartRela = new ArrayList<LineItem>();
	

	
}
