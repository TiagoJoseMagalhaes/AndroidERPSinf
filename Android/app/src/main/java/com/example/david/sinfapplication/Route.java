package com.example.david.sinfapplication;

public class Route
{
    private static String PrimaveraWebAPIDefaultRoute = "http://dservers.ddns.net:2018/WebApi/";
    private static String PrimaveraWebAPIDirectQuery = "Administrador/Consulta";
    public static String Authentication = PrimaveraWebAPIDefaultRoute + "token";
    public static String ListProducts = PrimaveraWebAPIDefaultRoute + PrimaveraWebAPIDirectQuery;

}
