package org.example.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Installer;
import org.example.Order;
import org.junit.Assert;

public class Email {
Order order ;
Installer installer;
    public Email() {
        order = new Order();
        installer = new Installer();
    }


    @Given("Order status changed {string}")
    public void order_status_changed(String status) {
        order.ifEmailSending(status);
    }

    @Then("Customer should have an order notification about the shipped order")
    public void customer_should_have_an_order_notification_about_the_shipped_order() {
        Assert.assertTrue(order.isSendEmailConfirmation());

    }

    @Then("Customer should have an order notification about the delivered order")
    public void customer_should_have_an_order_notification_about_the_delivered_order() {
        Assert.assertTrue(order.isSendEmailReceiving());

    }

    @Given("Admin cancel customer order {string}")
    public void admin_cancel_customer_order(String status) {
        order.ifEmailSending(status);


    }

    @Then("Customer should have an order notification about the Canceled order")
    public void customer_should_have_an_order_notification_about_the_canceled_order() {
        Assert.assertTrue(order.isSendEmailCancel());

    }
    }
