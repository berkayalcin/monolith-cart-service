package com.yalcinberkay.cartservice.services;

import com.yalcinberkay.cartservice.converters.CartItemEntityToDTOConverter;
import com.yalcinberkay.cartservice.entities.Cart;
import com.yalcinberkay.cartservice.entities.CartItem;
import com.yalcinberkay.cartservice.exceptions.BusinessException;
import com.yalcinberkay.cartservice.models.DTOs.CartDTO;
import com.yalcinberkay.cartservice.models.DTOs.CartItemDTO;
import com.yalcinberkay.cartservice.models.requests.ApplyCouponDiscountRequest;
import com.yalcinberkay.cartservice.models.requests.CalculateCampaignDiscountRequest;
import com.yalcinberkay.cartservice.models.requests.CartItemAddRequest;
import com.yalcinberkay.cartservice.repositories.CartItemRepository;
import com.yalcinberkay.cartservice.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;

    @Lazy
    private final DiscountService discountService;
    private final CartItemEntityToDTOConverter cartItemEntityToDTOConverter;

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Synchronized
    @Transactional
    public CartDTO addItem(final CartItemAddRequest cartItemAddRequest) {
        final var cart = getCart(cartItemAddRequest.getCartId());
        saveCartItem(cartItemAddRequest);
        reCalculateCartAmounts(cart);
        return buildCart(cart);
    }

    @Synchronized
    @Transactional
    public CartDTO applyCoupon(final ApplyCouponDiscountRequest applyCouponDiscountRequest) {
        final var cart = getCart(applyCouponDiscountRequest.getCartId());
        final var couponDiscount = discountService.calculateCouponDiscount(applyCouponDiscountRequest);
        final var totalDiscount = couponDiscount + cart.getTotalCampaignDiscount();
        cart.setAppliedCouponCode(applyCouponDiscountRequest.getCouponCode());
        cart.setTotalCouponDiscount(couponDiscount);
        cart.setTotalDiscount(totalDiscount);
        cartRepository.save(cart);
        return buildCart(cart);
    }

    public CartDTO getById(final Long id) {
        final var cart = getCart(id);
        return buildCart(cart);
    }

    private Cart getCart(final Long cartId) {
        final var cart = cartRepository.findById(cartId);
        if (cart.isEmpty()) {
            throw new BusinessException("cart.is.not.exists");
        }
        return cart.get();
    }

    private void saveCartItem(CartItemAddRequest cartItemAddRequest) {
        final var cartItem = buildCartItem(cartItemAddRequest);
        cartItemRepository.save(cartItem);
    }

    private CartItem buildCartItem(final CartItemAddRequest cartItemAddRequest) {
        final var product = productService.get(cartItemAddRequest.getCartId());
        final var discount = discountService.calculateCampaignDiscount(buildCalculateDiscountRequest(cartItemAddRequest)) * cartItemAddRequest.getQuantity();
        return CartItem.builder()
                .cartId(cartItemAddRequest.getCartId())
                .amount(calculateAmount(product.getPrice(), cartItemAddRequest.getQuantity()))
                .quantity(cartItemAddRequest.getQuantity())
                .unitPrice(product.getPrice())
                .discount(discount)
                .productId(cartItemAddRequest.getProductId())
                .build();
    }

    private Double calculateAmount(final Double unitPrice, final Integer quantity) {
        return unitPrice * quantity;
    }

    private CalculateCampaignDiscountRequest buildCalculateDiscountRequest(final CartItemAddRequest cartItemAddRequest) {
        return CalculateCampaignDiscountRequest.builder()
                .productId(cartItemAddRequest.getProductId())
                .quantity(cartItemAddRequest.getQuantity())
                .build();
    }

    private List<CartItemDTO> getItems(final Long id) {
        final var cartItems = cartItemRepository.findAllByCartId(id);
        return cartItems.stream()
                .map(cartItemEntityToDTOConverter)
                .map(this::setProduct)
                .collect(Collectors.toList());
    }

    private CartItemDTO setProduct(final CartItemDTO cartItemDTO) {
        final var product = productService.getById(cartItemDTO.getProductId());
        cartItemDTO.setProduct(product);
        return cartItemDTO;
    }

    private void reCalculateCartAmounts(final Cart cart) {
        final var cartItems = cartItemRepository.findAllByCartId(cart.getId());
        final Double totalCampaignDiscount = cartItems.stream().mapToDouble(CartItem::getDiscount).sum();
        final Double totalAmount = cartItems.stream().mapToDouble(CartItem::getAmount).sum();

        cart.setAmount(totalAmount);
        cart.setTotalCampaignDiscount(totalCampaignDiscount);
        cartRepository.save(cart);
    }

    private CartDTO buildCart(final Cart cart) {
        return CartDTO.builder()
                .id(cart.getId())
                .deliveryCost(cart.getDeliveryCost())
                .status(cart.getStatus())
                .amount(cart.getAmount())
                .totalCampaignDiscount(cart.getTotalCampaignDiscount())
                .totalCouponDiscount(cart.getTotalCouponDiscount())
                .totalDiscount(cart.getTotalDiscount())
                .items(getItems(cart.getId()))
                .build();
    }

}
