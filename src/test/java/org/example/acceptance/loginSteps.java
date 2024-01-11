package org.example.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.SignIn;
import org.junit.Assert;


public class loginSteps {
    SignIn ob1;
    public loginSteps() {
        ob1=new SignIn();
    }


    @Given(": The email is {string}")
    public void the_email_is(String email) {
        ob1.setEmail(email);
    }
    @Given(": The password is {string}")
    public void the_password_is(String password) {
        ob1.setPassword(password);
        ob1.setPassword(password);
    }

    @Then("customer login")
    public void customer_login() {
        ob1.setTheUser(Integer.parseInt(ob1.getPassword()));
        ob1.setName(ob1.getEmail());
        ob1.customerInInSystem();
        ob1.customerIslLogin(ob1.getName(), String.valueOf(ob1.getTheUser()));
        Assert.assertTrue(ob1.getCustomerLogin());
    }

    @Then("customer can not login")
    public void customer_can_not_login() {
        ob1.notFoundEmailCustomer(ob1.getEmail(),ob1.getPassword());
        ob1.customerIslLogin(ob1.getEmail(),ob1.getPassword());
        Assert.assertFalse(ob1.getCustomerLogin());    }
    @Then("customer enter false pass then can not login")
    public void customer_enter_false_pass_then_can_not_login() {
        ob1.notFoundPasswordCustomer(ob1.getEmail(),ob1.getPassword());
        ob1.customerIslLogin(ob1.getEmail(),ob1.getPassword());
        Assert.assertFalse(ob1.getCustomerLogin());
    }

    @Then("Admin can login")
    public void admin_can_login() {
        ob1.setAdminMenuName(ob1.getEmail());
        ob1.adminInInSystem();
        ob1.setId(Integer.parseInt(ob1.getPassword()));
        ob1.AdminLogin(ob1.getAdminMenuName(), String.valueOf(ob1.getId()));
        Assert.assertTrue(ob1.getAdminloged());

    }
    @Then("Admin cant login because email wrong")
    public void admin_cant_login_because_email_wrong() {
        ob1.AdminWrongEmail(ob1.getEmail(),ob1.getPassword());
        ob1.AdminLogin(ob1.getEmail(),ob1.getPassword());
        Assert.assertFalse(ob1.getAdminloged());
    }

    @Then("Admin cant login because pass wrong")
    public void admin_cant_login_because_pass_wrong() {
        ob1.AdminWrongPass(ob1.getEmail(),ob1.getPassword());
        ob1.AdminLogin(ob1.getEmail(),ob1.getPassword());
        Assert.assertFalse(ob1.getAdminloged());
    }


    @Then("Installer can login")
    public void installer_can_login() {
        ob1.installerInSystem();
        ob1.installerIsLogin(ob1.getEmail(),ob1.getPassword());
        Assert.assertTrue(ob1.getInstallerLogin());
    }
    @Then("Installer can't login")
    public void installer_can_t_login() {
        ob1.installerWrongEmail(ob1.getEmail(),ob1.getPassword());
        ob1.installerIsLogin(ob1.getEmail(),ob1.getPassword());
        Assert.assertFalse(ob1.getInstallerLogin());
    }
    @Then("Installer enter false pass then can't login")
    public void installer_enter_false_pass_then_can_t_login() {
        ob1.installerWrongPassword(ob1.getEmail(),ob1.getPassword());
        ob1.installerIsLogin(ob1.getEmail(),ob1.getPassword());
        Assert.assertFalse(ob1.getInstallerLogin());
    }


}
