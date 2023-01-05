package caffeinateme.model;

import java.util.*;

public class CoffeeShop {

    private final Queue<Order> orders = new LinkedList<>();
    private final Map<String, Customer> registeredCustomers = new HashMap<>();

    public void placeOrder(Order order, int distanceInMetres) {
        if (distanceInMetres <= 200) {
            order = order.withStatus(OrderStatus.Urgent);
        } else if (distanceInMetres > 5000) {
            order = order.withStatus(OrderStatus.Low);
        }
        orders.add(order);
    }

    public List<Order> getPendingOrders() {
        return new ArrayList<>(orders);
    }

    public Optional<Order> getOrderFor(Customer customer) {
        return orders.stream()
                .filter( order -> order.getCustomer().equals(customer))
                .findFirst();
    }

    public Customer registerNewCustomer(String customerName) {
        Customer newCustomer = Customer.named(customerName);
        registeredCustomers.put(customerName, newCustomer);
        return newCustomer;
    }
}
