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
@Entity
@SequenceGenerator(name = "coupons_id_seq", sequenceName = "coupons_id_seq", allocationSize = 1)
@Table(name = "coupons")
public class Coupon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupons_id_seq")
    private Long id;
    private Double amount;
    private Double minimumCartAmount;

    private String code;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
    private Date expiresAt;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
}
