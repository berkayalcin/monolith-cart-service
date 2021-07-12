package com.yalcinberkay.cartservice.entities;

import com.yalcinberkay.cartservice.enums.DiscountType;
import com.yalcinberkay.cartservice.enums.Status;
import com.yalcinberkay.cartservice.utils.UUIDUtils;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "seq_coupons", sequenceName = "seq_coupons", allocationSize = 1)
public class Coupon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_coupons")
    private Long id;
    private Double amount;
    private Double minimumCartAmount;
    private DiscountType discountType;
    private Date expiresAt;

    @Builder.Default
    private Status status = Status.ACTIVE;
}
