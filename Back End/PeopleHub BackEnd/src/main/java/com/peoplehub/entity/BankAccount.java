package com.peoplehub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

  @Id
  private Long accountNo;
  private String branchName;
  private String ifsc;
  private String bankName;
  @OneToOne
  private Employee e;
  
}
