package com.example.tictactoe.modele;

import java.util.ArrayList;
import java.util.List;

// etat 0 : non initialisé
// etat 1 : joueur A
// etat 2 : joueur B
public class map {
    private static List<Integer> etats;

    public map(){
        etats = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++){
            etats.add(0);
        }
    }

    public static int getValue(int a, int b){

        return etats.get(a + 3 * b);
    }

    public static void setValue(int a, int b, int v){
        etats.set(a + 3 * b, v);
    }

    public static List<Integer> getEtats() {

        return etats;
    }

    public static void setEtats(List<Integer> etats) {

        map.etats = etats;
    }

    public static boolean win(){
        for(int i = 0; i < 3; i++){
            int val = getValue(i, 0);
            if(val != 0){
                for (int j = 0; j < 3; j++){
                    if(val != getValue(i, j)){
                        break;
                    }
                    if(j == 2 && val == getValue(i, j)){
                        return true;
                    }
                }
            }
        }

        for(int i = 0; i < 3; i++){
            int val = getValue(0, i);
            if(val != 0){
                for (int j = 0; j < 3; j++){
                    if(val != getValue(j, i)){
                        break;
                    }
                    if(j == 2 && val == getValue(j, i)){
                        return true;
                    }
                }
            }
        }

        if(getValue(0, 0) == getValue(1, 1) && getValue(1, 1) == getValue(2, 2) && getValue(0, 0) != 0){
            return true;
        }
        if(getValue(2, 0) == getValue( 1, 1) && getValue(1, 1) == getValue(0, 2) && getValue(2, 0) != 0){
            return true;
        }
        return false;
    }
}
