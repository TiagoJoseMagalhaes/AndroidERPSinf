package com.example.david.sinfapplication.WebAPI.Parsers;

import com.example.david.sinfapplication.CommonDataClasses.Customer;

public class CustomerParser
{

    public static Customer parseViewCustomerRequestResponse(String viewCustomerRequestResponse)
    {
        return new Customer("ldfjls", "---fjsdlkfs", null, null,
                null, null, null, null, null, null, null,
                null, null,  null, null, null);
    }
}