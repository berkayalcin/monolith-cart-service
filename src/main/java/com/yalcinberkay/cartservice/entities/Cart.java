package com.yalcinberkay.cartservice.entities;

import com.yalcinberkay.cartservice.enums.Status;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "carts_id_seq", sequenceName = "carts_id_seq", allocationSize = 1)
@Table(name = "carts")
public class Cart {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carts_id_seq")
    private Long id;
    private Double amount;
    private Double totalDiscount;
    private Double totalCampaignDiscount;
    private Double totalCouponDiscount;
    private Double deliveryCost;
    private String appliedCouponCode;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
}
