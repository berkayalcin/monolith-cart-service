package com.yalcinberkay.cartservice.entities;

import com.yalcinberkay.cartservice.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "seq_carts", sequenceName = "seq_carts", allocationSize = 1)
public class Cart {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_carts")
    private Long id;
    private Date createdAt;
    private Date lastModifiedAt;

    @Builder.Default
    private Status status = Status.ACTIVE;
}
