package com.yalcinberkay.cartservice.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SequenceGenerator(name = "cart_items_id_seq", sequenceName = "cart_items_id_seq", allocationSize = 1)
@Table(name = "cart_items")
public class CartItem {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_items_id_seq")
    private Long id;
    private Long productId;
    private Integer quantity;
    private Double amount;
    private Double unitPrice;
    private Double discount;
    private Long cartId;
}
