package com.example.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "admin")
@PrimaryKeyJoinColumn(name = "admin_id",referencedColumnName = "user_id")
public class Admin extends User {

}
