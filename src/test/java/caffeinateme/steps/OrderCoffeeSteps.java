package caffeinateme.steps;

import caffeinateme.model.CoffeeShop;
import caffeinateme.model.Customer;
import caffeinateme.model.Order;
import caffeinateme.model.OrderStatus;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrderCoffeeSteps {
    Customer customer;
    CoffeeShop coffeeShop = new CoffeeShop();
    Order order;

    @Given("{} is a CaffeinateMe customer")
    public void a_caffeinate_me_customer_named(String customerName) {
        customer = coffeeShop.registerNewCustomer(customerName);
    }

    @Given("Cathy is {int} metres from the coffee shop")
    public void cathy_is_metres_from_the_coffee_shop(Integer distanceInMeters) {
        customer.setDistanceFromShop(distanceInMeters);
    }

    @When("Cathy orders a {string}")
    public void cathy_orders_a(String orderedProduct) {
        this.order = Order.of(1, orderedProduct).forCustomer(customer);
        customer.placesAnOrderFor(order).at(coffeeShop);
    }

    @When("Cathy orders a {string} with a comment {string}")
    public void cathy_orders_a_with_a_comment(String orderedProduct, String comment) {
        this.order = Order.of(1, orderedProduct).forCustomer(customer).withComment(comment);
        customer.placesAnOrderFor(order).at(coffeeShop);
    }

    @Then("Barry should receive the order")
    public void barry_should_receive_the_order() {
        assertThat(coffeeShop.getPendingOrders(), hasItem(order));
    }

    @Then("Barry should know that the order is {}")
    public void barry_should_know_that_the_order_is(OrderStatus expectedStatus) {
        Order customersOrder = coffeeShop.getOrderFor(customer).orElseThrow(() -> new AssertionError("No order found!"));
        assertThat(customersOrder.getStatus(), is(expectedStatus));
    }

    @Then("the order should have the comment {string}")
    public void the_order_should_have_the_comment(String comment) {
        Order customersOrder = coffeeShop.getOrderFor(customer).orElseThrow(() -> new AssertionError("No order found!"));
        assertThat(customersOrder.getComment(), is(comment));
    }
}
