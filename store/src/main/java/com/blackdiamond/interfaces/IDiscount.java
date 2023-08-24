package com.blackdiamond.interfaces;



public interface IDiscount {
    public void setDiscountPer(float discount);

    public float getDiscountPer();

    public float getDiscountPrice();

    public boolean validDiscount(float discountPer);

}
