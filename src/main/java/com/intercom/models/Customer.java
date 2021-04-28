package com.intercom.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.intercom.constants.Constant;
import com.intercom.deserializers.CustomerDeserializer;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the customer data model.
 */
@Data
public class Customer implements Comparable {
    @SerializedName(Constant.USER_ID_KEY)
    @Expose
    final int id;

    @Expose
    final String name;

    final Location location;

    @Override
    public int compareTo(Object o) {
        if (o instanceof Customer) {
            Customer customer = (Customer) o;
            return Integer.compare(id, customer.getId());
        } else {
            throw new RuntimeException("The provided object isn't a customer instance.");
        }
    }

    public static List<Customer> ReadCustomerListFromJsonFile(JsonReader customersJson) {
        JsonDeserializer<Customer> deserializer = new CustomerDeserializer();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Customer.class, deserializer)
                .create();

        Type customersListType = new TypeToken<ArrayList<Customer>>() {
        }.getType();
        return gson.fromJson(customersJson, customersListType);
    }
}
