package com.blackdiamond.models;

import java.text.SimpleDateFormat;

import com.blackdiamond.interfaces.IEatable;
import com.blackdiamond.interfaces.ISalesMagnament;
import com.blackdiamond.types.PackagingType;

/**
 * Clase que representa un producto envasado.
 */
public class Packaged extends Product implements IEatable, ISalesMagnament {
    private PackagingType pType;
    private boolean isImported;
    private String expiredDate;
    private int kcal;

    /**
     * Constructor de la clase Packaged.
     *
     * @param id          Identificador del producto.
     * @param des         Descripción del producto.
     * @param unitPrice   Precio unitario(compra) del producto.
     * @param pType       Tipo de empaque del producto.
     * @param isImported  Indica si el producto es importado.
     * @param kcal        Valor calórico del producto.
     * @param expiredDate Fecha de caducidad del producto.
     */
    public Packaged(String id, String des, float unitPrice,
            PackagingType pType, boolean isImported, int kcal, String expiredDate) {
        super(des, unitPrice);
        setID(id);
        this.pType = pType;
        this.isImported = isImported;
        setKcal(kcal);
        setExpiredDate(expiredDate);
    }

    /**
     * Establece el identificador del producto.
     *
     * @param id Identificador del producto.
     * @throws IllegalArgumentException Si el identificador es inválido o está mal
     *                                  clasificado.
     */
    public void setID(String id) {
        checkID(id);
        if (!id.startsWith("AB")) {
            System.out.println("producto mal clasificado");
            throw new IllegalArgumentException("identicador de producto invalido");
        }
        this.ID = id;
    }

    /**
     * Establece la fecha de vencimiento del producto.
     *
     * @param date Fecha de caducidad en formato "dd/MM/yyyy".
     */
    public void setExpiredDate(String date) {
        try {
            this.expiredDate = new SimpleDateFormat("dd-MM-yyyy").parse(date).toString();
        } catch (Exception e) {
            System.err.println(e.getStackTrace());
        }
    }

    /**
     * Establece el precio de compra y aplica los impuestos correspondientes.
     *
     * @param gainPer Porcentaje de ganancia para el producto.
     * @throws IllegalArgumentException Si el porcentaje de ganancia es mayor al
     *                                  20%.
     */
    public void setStockPrice(float gainPer) {
        if (gainPer > 20) {
            throw new IllegalArgumentException("El porcentaje de ganancia para comestibles no puede superar el 20%.");
        }

        this.stockPrice = (getUnitPrice() * (1 + gainPer / 100));
        addTaxes();
    }

    /**
     * Agrega impuestos al precio de compra si el producto es importado.
     */
    public void addTaxes() {
        if (isImported) {
            stockPrice += stockPrice * 0.1;
            System.out.println("al producto se le aplicara un impuesto del 10% por ser importado");
            System.out.println("precio con impuestos :" + stockPrice);
        }
    }

    /**
     * Verifica si el descuento puede ser aplicado al producto.
     *
     * @param discount Descuento a aplicar en porcentaje.
     * @return `true` si el descuento es válido, `false` si no es válido.
     */
    public boolean checkDiscountPer(float discount) {
        if (discount > 20) {
            System.out.println("el descuento no pudo ser aplicado");
            return false;
        }
        if (!validDiscount(discount)) {
            System.out.println("el descuento no pudo ser aplicado porque es menor al precio de compra");
            return false;
        }
        return true;
    }

    /**
     * Establece el descuento para el producto.
     *
     * @param discount Descuento a aplicar en porcentaje.
     */
    public void setDiscount(float discount) {
        if (!checkDiscountPer(discount)) {
            return;
        }
        this.discountPer = discount;
        this.stockPrice = getDiscountPrice(discount);
    }

    public PackagingType getpType() {
        return pType;
    }

    public boolean getisImported() {
        return isImported;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getKcal() {
        return kcal;
    }

    @Override
    public float getDiscount() {
        return getDiscountPrice(this.discountPer);
    }

    @Override
    public float getDiscountPercent() {
        return this.discountPer;
    }

}
