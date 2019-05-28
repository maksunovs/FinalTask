package com.epam.final_task.model.entity;


public class CartTrack extends Entity {
    private Integer id;
    private Integer cartId;
    private Integer trackId;

    public CartTrack(Integer cartId, Integer trackId) {
        this.cartId = cartId;
        this.trackId = trackId;
    }

    public CartTrack(Integer id, Integer cartId, Integer trackId) {
        this.id = id;
        this.cartId = cartId;
        this.trackId = trackId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCartId() {
        return cartId;
    }

    public Integer getTrackId() {
        return trackId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        CartTrack cartTrack = (CartTrack) object;
        return this.id.equals(cartTrack.id) &&
                this.cartId.equals(cartTrack.cartId) &&
                this.trackId.equals(cartTrack.trackId);
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 1;
        hash = hash * prime + (id == null ? 0 : id);
        hash = hash * prime + (cartId == null ? 0 : cartId);
        hash = hash * prime + (trackId == null ? 0 : trackId);
        return hash * prime;
    }

    @Override
    public String toString() {
        return "CartTrack{" +
                "id=" + id +
                ", cartId=" + cartId +
                ", trackId=" + trackId +
                '}';
    }
}
