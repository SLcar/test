package org.example.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Admin;
import org.example.Customer;
import org.example.Installer;
import org.junit.Assert;

public class userRoles {
    Admin admin;
    Customer customer;
    Installer installer;
    public userRoles() {

        admin = new Admin();
        customer=new Customer();
        installer= new Installer();
    }

    @Given("The admin is logged into the system")
    public void the_admin_is_logged_into_the_system() {

        Assert.assertEquals(true, admin.isAdminLogin());
    }

    @When("The admin enter {string}")
    public void the_admin_enter(String string) {
        admin.whatAdminEnter(string);

    }

    @Then("The Admin able to add, edit, or delete products")
    public void the_admin_able_to_add_edit_or_delete_products() {

        Assert.assertEquals(true,admin.isProductsFlag());
    }


    @Then("The Admin should be able to create, edit, or delete categories")
    public void the_admin_should_be_able_to_create_edit_or_delete_categories() {
        Assert.assertEquals(true,admin.isCategoriesFlag());

    }

    @Then("The Admin able to create, edit, or delete user accounts")
    public void the_admin_able_to_create_edit_or_delete_user_accounts() {
        Assert.assertEquals(true,admin.isUserAccountsFlag());

    }
    @Then("The Admin should be enter one ,two,or three")
    public void the_admin_should_be_enter_one_two_or_three() {
        Assert.assertEquals(false,admin.isProductsFlag());
        Assert.assertEquals(false,admin.isCategoriesFlag());
        Assert.assertEquals(false,admin.isUserAccountsFlag());

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
        Assert.assertEquals(true,customer.isBrowseProductsFlag());

    }

    @Then("The Customer should be able to  make purchases")
    public void the_customer_should_be_able_to_make_purchases() {
        Assert.assertEquals(true,customer.isMakePurchasesFlag());
    }

    @Then("The Customer able to view orders")
    public void the_customer_able_to_view_orders() {
        Assert.assertEquals(true,customer.isViewOrdersFlag());

    }

    @Then("The Customer should be enter one ,two,or three")
    public void the_customer_should_be_enter_one_two_or_three() {
        Assert.assertEquals(false,customer.isBrowseProductsFlag());
        Assert.assertEquals(false,customer.isMakePurchasesFlag());
        Assert.assertEquals(false,customer.isViewOrdersFlag());

    }



}