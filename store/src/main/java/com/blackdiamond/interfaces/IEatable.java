package com.blackdiamond.interfaces;

import java.util.Date;

public interface IEatable {
    public void setExpiredDate(Date date);

    public Date getExpiredDate();

    public void setKcal(int kcal);

    public int getKcal();

}
