/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cris.mavenmultidepot.Models;

/**
 *
 * @author crist
 */
public class LocationCoordinates {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    public static double getEuclideanDistance(LocationCoordinates source, LocationCoordinates destination) {
        return Math.sqrt(Math.pow(source.x-destination.x, 2) + Math.pow(source.y-destination.y, 2));
    } 
}
