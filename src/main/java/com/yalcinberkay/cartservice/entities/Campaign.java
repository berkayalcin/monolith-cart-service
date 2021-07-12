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
@SequenceGenerator(name = "seq_campaigns", sequenceName = "seq_campaigns", allocationSize = 1)
@Entity
public class Campaign {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_campaigns")
    private Long id;
    private String categoryId;
    private Double amount;
    private Integer minimumCartItem;
    private DiscountType discountType;
    private Date expiresAt;

    @Builder.Default
    private Status status = Status.ACTIVE;
}
