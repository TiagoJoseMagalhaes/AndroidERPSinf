package com.example.david.sinfapplication.WebAPI;


import com.example.david.sinfapplication.CommonDataClasses.Customer;
import com.example.david.sinfapplication.WebAPI.Communication.ContentType;
import com.example.david.sinfapplication.WebAPI.Communication.RequestMethod;
import com.example.david.sinfapplication.CommonDataClasses.Product;
import com.example.david.sinfapplication.WebAPI.Communication.Route;
import com.example.david.sinfapplication.WebAPI.Communication.PrimaveraWebAPI;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.CustomerParserAndStringBuilder;
import com.example.david.sinfapplication.WebAPI.ParsersAndStringBuilders.ProductsListParser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class WebAPI
{
    public static ArrayList<Product> getProductsList() throws InterruptedException, ExecutionException, TimeoutException,
            JSONException
    {
        String query = "\""+ "SELECT A.Artigo, A.Descricao, A.Observacoes, A.StkActual, AM.PVP1, AM.PVP2, AM.PVP3, AM.PVP4, AM.PVP5, " +
                "AM.PVP6, AM.Moeda from Artigo A INNER JOIN ArtigoMoeda AM ON A.Artigo = AM.Artigo" + "\"";

        String listProductsRequestResponse = PrimaveraWebAPI.sendRequest(Route.ListProducts, RequestMethod.ListProducts,
                ContentType.ApplicationJson, query.getBytes());
        return ProductsListParser.parseListProductsRequestResponse(listProductsRequestResponse);
    }

    /**
     * Logs in to the webapi. Returns 0 on success; 1 on server error
     * @return
     */
    public static int login()
    {
        try
        {
            PrimaveraWebAPI.login("FEUP", "qualquer1", "BELAFLOR", "DEFAULT",
                    "password", "professional");
        } catch (UnsupportedEncodingException e)
        {
            return 1;
        }

        return 0;
    }

    public static Customer viewCustomer(String customerId) throws InterruptedException, ExecutionException, TimeoutException
    {
        String requestRoute = Route.viewCustomer + customerId;

        String viewCustomerRequestResponse = PrimaveraWebAPI.sendRequest(requestRoute, RequestMethod.ViewCustomer,
                ContentType.UrlEncoded, new byte[0]);
        try
        {
            return CustomerParserAndStringBuilder.parseViewCustomerRequestResponse(viewCustomerRequestResponse);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;

    }

    public static boolean addCustomer(Customer customer) throws InterruptedException,
            ExecutionException, TimeoutException, JSONException
    {
        String requestBody = CustomerParserAndStringBuilder.buildJsonWithCustomerNonNullAttributes(customer).toString();
        String addCustomerRequestResponse = PrimaveraWebAPI.sendRequest(Route.addCostumer, RequestMethod.AddCustomer,
                ContentType.ApplicationJson, requestBody.getBytes());
        return CustomerParserAndStringBuilder.parseAddCustomerRequestResponse(addCustomerRequestResponse);

    }
}
