package com.tierrafantasia.utils;

import com.tierrafantasia.entidades.Bando;
import com.tierrafantasia.entidades.Raza;
import com.tierrafantasia.exceptions.ParseBandoException;
import com.tierrafantasia.exceptions.ParseRazaException;


public class ParsingUtils {
    public static Raza parseRaza(String raza) throws ParseRazaException {
        switch(raza.toUpperCase()){
            case "WRIVES" -> {
                return Raza.WRIVES;
            }
            case "RERALOPES" -> {
                return Raza.RERALOPES;
            }
            case "RADAITERAN" -> {
                return Raza.RADAITERAN;
            }
            case "NORTAICHIAN" -> {
                return Raza.NORTAICHIAN;
            }
            default -> throw new ParseRazaException(raza + " es una raza invalida");
        }
    }

    public static Bando parseBando(String bando) throws ParseBandoException {
        switch(bando.toUpperCase()){
            case "PROPIO" -> {
                return Bando.PROPIO;
            }
            case "ENEMIGO" -> {
                return Bando.ENEMIGO;
            }
            case "ALIADO" -> {
                return Bando.ALIADO;
            }
            default -> throw new ParseBandoException(bando + " es un bando invalido");
        }
    }


}


