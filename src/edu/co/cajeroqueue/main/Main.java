/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.cajeroqueue.main;

import UI.Ventana;
import edu.co.cashierqueue.logic.Queue;
/**
 *
 * @author David Steven Santos Santos
 * @author Juan Camilo Sarmiento Reyes
 */
public class Main {
    public static void main(String[] args) {        
        //Ventana v = new Ventana();
        Queue q = new Queue();
        q.initValues();
        q.printAllStructure();
    }
}
