package com.intercom;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.intercom.activity.CustomerSubsetByDistanceFromOfficeStrategy;
import com.intercom.activity.CustomerSubsetFilterStrategy;
import com.intercom.constants.Constant;
import com.intercom.models.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DubOfficeTests {
    private final double circleRadius = 100.00;
    CustomerSubsetFilterStrategy customerSubsetFilterStrategy;

    @BeforeAll
    public void setup() {
        customerSubsetFilterStrategy
                = new CustomerSubsetByDistanceFromOfficeStrategy(Constant.DUBLIN_OFFICE, circleRadius);
    }

    @Test
    public void getCustomersInCircleFromOffice() throws IOException {
        List<Customer> customers = readDataFromJson();
        Set<Customer> filteredCustomers = customerSubsetFilterStrategy.applyFilter(customers);

        assertEquals(17, filteredCustomers.size());

        writeJsonToFile(filteredCustomers);
    }

    private List<Customer> readDataFromJson() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader("data.json"));
        return Customer.ReadCustomerListFromJsonFile(reader);
    }

    private void writeJsonToFile(Set<Customer> filteredCustomers) throws IOException {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
        Writer writer = new FileWriter("output.json");
        gson.toJson(filteredCustomers, writer);
        writer.flush();
        writer.close();
    }
}
