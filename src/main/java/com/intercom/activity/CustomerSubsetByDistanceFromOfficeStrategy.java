package com.intercom.activity;

import com.intercom.models.Customer;
import com.intercom.models.Location;
import lombok.AllArgsConstructor;

import java.util.List;;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * A strategy implementation to get a subset of customer in a given radius from given office.
 */
@AllArgsConstructor
public class CustomerSubsetByDistanceFromOfficeStrategy implements CustomerSubsetFilterStrategy {
    private final Location officeLocation;
    private final double distanceCircle;

    private double milesToKm(double dist) {
        return dist * 1.60934;
    }

    private boolean isCustomerInDistanceCircle(Customer customer) {
        double angle = Math.acos(Math.sin(customer.getLocation().getLatitude()) * Math.sin(officeLocation.getLatitude())
                + Math.cos(customer.getLocation().getLatitude())
                * Math.cos(officeLocation.getLatitude())
                * Math.cos(customer.getLocation().getLongitude() - officeLocation.getLongitude()));

        angle = Math.toDegrees(angle);
        double distance = milesToKm(60 * angle);

        return distance <= distanceCircle;
    }

    /**
     * Applying filter has an overall complexity of O(nLogn).
     * going through the list of linear, and inserting required ones into the treeSet is O(Logn).
     * <p>
     * similar complexity could be achieved if the list of customer is sorted before [O(nLogn)]
     * and then required customers will be added to list in O(n). giving overall complixity of O(nLogn).
     */
    @Override
    public Set<Customer> applyFilter(List<Customer> customers) {
        return customers.stream()
                .filter(this::isCustomerInDistanceCircle)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
