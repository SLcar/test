package org.example.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;
import org.junit.Assert;
public class userRoles {
    Admin admin;
    Customer customer;
    Installer installer;
    Product product;
    SignIn sign;
    public userRoles() {
        admin = new Admin();
        customer=new Customer();
        installer= new Installer();
        product=new Product();
         sign= new SignIn();
    }
    @Given("The admin is logged into the system")
    public void the_admin_is_logged_into_the_system() {
        admin.setAdminName("Sama Abosair");
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
    @When("The Admin edit the email,password,userName;")
    public void the_admin_edit_the_email_password_user_name() {
        admin. editeUserName(admin.getAdminName());
        admin.editePassword("120","120","120");
        admin.editeGmail("samaabusair12@gmail.com");
        customer.setEditData(true);
    }
    @Then("The Data of Admin edit")
    public void the_data_of_admin_edit() {
        Assert.assertTrue(customer.isEditData());
    }
    @Then("The Admin should be enter one ,two,or three")
    public void the_admin_should_be_enter_one_two_or_three() {
        Assert.assertFalse(admin.isProductsFlag());
        Assert.assertFalse(admin.isCategoriesFlag());
        Assert.assertFalse(admin.isUserAccountsFlag());

    }
    @Given("The Customer is logged into the system")
    public void the_customer_is_logged_into_the_system() {
        Assert.assertTrue(customer.isCustomerLogin());
    }

    @When("The Customer enter {string}")
    public void the_customer_enter(String string) {
        customer.whatCustomerEnter(string);
    }

    @Then("The Customer able to  Browse products")
    public void the_customer_able_to_browse_products() {
        customer.ShowTheProduct("Exterior");
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

    @When("The Customer edit the email,password,phone,address;")
    public void the_customer_edit_the_email_password_phone_address() {
        customer.setSettingFlag(true);
        sign.customerIslLogin("s12029704@stu.najah.edu","1111");
        customer.setTheCustomerIs(sign.getNumberOfLine());
        customer.editeGmail("s12029704@stu.najah.edu");
        customer.editePassword("1111","1111","1111");
        customer.editeAddress("Nablus/Palestine Street/Al Quds Bakery");
        customer. editeNumber("059422");
        customer.setEditData(true);
    }
    @Then("The data of customer edit")
    public void the_data_of_customer_edit() {
        Assert.assertTrue(customer.isSettingFlag());
        Assert.assertTrue(customer.isEditData());

    }


    @When("The Admin edit the email,password,phone of installer;")
    public void the_admin_edit_the_email_password_phone_of_installer() {
        installer.showInstallerAccount();
        installer.setInstallerName("lamees");
        installer.setGmail("lameesahmed257@gmail.com");
        installer.editeUserName(installer.getInstallerName());
        installer.editeGmail(installer.getGmail());
        installer.editePassword("121","121","121");
        customer.setEditData(true);
    }
    @Then("The data of installer edit")
    public void the_data_of_installer_edit() {
        Assert.assertTrue(customer.isEditData());

    }

}