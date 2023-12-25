package org.example.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.Registration;
import org.junit.Assert;


public class loginSteps {
    Registration ob;
    public loginSteps() {
        ob =new Registration();
    }


    @Given(": The email is {string}")
    public void the_email_is(String email) {
        ob.setEmail(email);
    }
    @Given(": The password is {string}")
    public void the_password_is(String password) {
        ob.setPassword(password);
    }

    @Then("customer login")
    public void customer_login() {
        ob.customerIslLogin(ob.getEmail(),ob.getPassword());
        Assert.assertTrue(ob.getCustomerLogin());
    }

    @Then("customer can not login")
    public void customer_can_not_login() {
        ob.customerIslLogin(ob.getEmail(),ob.getPassword());
        Assert.assertEquals(false,ob.getCustomerLogin());    }
    @Then("customer enter false pass then can not login")
    public void customer_enter_false_pass_then_can_not_login() {
        ob.customerIslLogin(ob.getEmail(),ob.getPassword());
        Assert.assertEquals(false,ob.getCustomerLogin());
    }
    @Then("Admin can login")
    public void admin_can_login() {
        ob.AdminLogin(ob.getEmail(),ob.getPassword());
        Assert.assertEquals(true,ob.getAdminloged());

    }
    @Then("Admin cant login because email wrong")
    public void admin_cant_login_because_email_wrong() {
        ob.AdminLogin(ob.getEmail(),ob.getPassword());
        Assert.assertEquals(false,ob.getAdminloged());
    }

    @Then("Admin cant login because pass wrong")
    public void admin_cant_login_because_pass_wrong() {
        ob.AdminLogin(ob.getEmail(),ob.getPassword());
        Assert.assertEquals(false,ob.getAdminloged());
    }


    @Then("Installer can login")
    public void installer_can_login() {
        ob.installerIsLogin(ob.getEmail(),ob.getPassword());
        Assert.assertEquals(true,ob.getInstallerLogin());
    }
    @Then("Installer can't login")
    public void installer_can_t_login() {
        ob.installerIsLogin(ob.getEmail(),ob.getPassword());
        Assert.assertEquals(false,ob.getInstallerLogin());
    }
    @Then("Installer enter false pass then can't login")
    public void installer_enter_false_pass_then_can_t_login() {
        ob.installerIsLogin(ob.getEmail(),ob.getPassword());
        Assert.assertEquals(false,ob.getInstallerLogin());
    }


}
