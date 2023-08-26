package com.blackdiamond.interfaces;

import java.util.Date;

public interface IEatable {
    public void setExpiredDate(String date);

    public String getExpiredDate();

    public void setKcal(int kcal);

    public int getKcal();

}
