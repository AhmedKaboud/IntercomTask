package com.intercom.activity;

import com.intercom.models.Customer;

import java.util.List;
import java.util.Set;

/**
 * An interface that is used to get a subset of customers based on a defined strategy.
 */
public interface CustomerSubsetFilterStrategy {
    Set<Customer> applyFilter(List<Customer> customers);
}
