package org.example.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Admin;
import org.example.Customer;
import org.example.Installer;
import org.example.Product;
import org.junit.Assert;

public class userRoles {
    Admin admin;
    Customer customer;
    Installer installer;
    Product product;
    public userRoles() {

        admin = new Admin();
        customer=new Customer();
        installer= new Installer();
        product=new Product();
    }

    @Given("The admin is logged into the system")
    public void the_admin_is_logged_into_the_system() {

        Assert.assertTrue(admin.isAdminLogin());
    }

    @When("The admin enter {string}")
    public void the_admin_enter(String string) {
        admin.whatAdminEnter(string);

    }

    @Then("The Admin able to add, edit, or delete products")
    public void the_admin_able_to_add_edit_or_delete_products() {
        Assert.assertTrue(admin.isProductsFlag());
    }


    @Then("The Admin should be able to create, edit, or delete categories")
    public void the_admin_should_be_able_to_create_edit_or_delete_categories() {
        Assert.assertTrue(admin.isCategoriesFlag());

    }

    @Then("The Admin able to create, edit, or delete user accounts")
    public void the_admin_able_to_create_edit_or_delete_user_accounts() {
        Assert.assertTrue(admin.isUserAccountsFlag());

    }
    @Then("The Admin should be enter one ,two,or three")
    public void the_admin_should_be_enter_one_two_or_three() {
        Assert.assertFalse(admin.isProductsFlag());
        Assert.assertFalse(admin.isCategoriesFlag());
        Assert.assertFalse(admin.isUserAccountsFlag());

    }
    @Given("The Customer is logged into the system")
    public void the_customer_is_logged_into_the_system() {

        Assert.assertEquals(true,customer.isCustomerLogin());
    }

    @When("The Customer enter {string}")
    public void the_customer_enter(String string) {
        customer.whatCustomerEnter(string);

    }

    @Then("The Customer able to  Browse products")
    public void the_customer_able_to_browse_products() {
        Assert.assertTrue(customer.isBrowseProductsFlag());

    }

    @Then("The Customer should be able to  make purchases")
    public void the_customer_should_be_able_to_make_purchases() {
        product.PrintProductAvailabilityExist("Exterior","available");
        Assert.assertTrue(customer.isMakePurchasesFlag());
    }

    @Then("The Customer able to view orders")
    public void the_customer_able_to_view_orders() {

        Assert.assertTrue(customer.isViewOrdersFlag());

    }

    @Then("The Customer should be enter one ,two,or three")
    public void the_customer_should_be_enter_one_two_or_three() {
        Assert.assertFalse(customer.isBrowseProductsFlag());
        Assert.assertFalse(customer.isMakePurchasesFlag());
        Assert.assertFalse(customer.isViewOrdersFlag());

    }



}