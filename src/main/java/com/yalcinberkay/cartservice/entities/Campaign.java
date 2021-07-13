package com.yalcinberkay.cartservice.entities;

import com.yalcinberkay.cartservice.enums.DiscountType;
import com.yalcinberkay.cartservice.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "campaigns_id_seq", sequenceName = "campaigns_id_seq", allocationSize = 1)
@Entity
@Table(name = "campaigns")
public class Campaign {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "campaigns_id_seq")
    private Long id;
    private String categoryId;
    private Double amount;
    private Integer minimumCartItem;
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
    private Date expiresAt;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;
}
