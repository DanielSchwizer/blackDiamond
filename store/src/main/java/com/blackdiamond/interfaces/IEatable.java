package com.blackdiamond.interfaces;

public interface IEatable {
    /**
     * Establece la fecha de vencimiento del producto comestible.
     * 
     * @param date La fecha de vencimiento en Stringo.
     */
    public void setExpiredDate(String date);

    /**
     * Obtiene la fecha de vencimiento del producto comestible.
     * 
     * @return La fecha de vencimiento en String.
     */
    public String getExpiredDate();

    /**
     * Establece el valor calórico del producto comestible.
     * 
     * @param kcal El valor calórico del producto.
     */
    public void setKcal(int kcal);

    /**
     * Obtiene el valor calórico del producto comestible.
     * 
     * @return El valor calórico del producto.
     */
    public int getKcal();

}
