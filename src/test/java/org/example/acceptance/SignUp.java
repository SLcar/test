package org.example.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.Registration;
import org.junit.Assert;

public class SignUp {
    Registration ob;
    public SignUp() {
        ob =new Registration();
    }

    @Given("the name is {string}")
    public void the_name_is(String name) {
        ob.setName(name);
    }
    @Given("the email {string}")
    public void the_email(String email) {
        ob.setEmail(email);
    }
    @Given("the  password {string}")
    public void the_password(String password) {
        ob.setPassword(password);
    }
    @Given("the confirmPassword is {string}")
    public void the_confirm_password_is(String confirmPass) {
        ob.setComPassword(confirmPass);
    }
    @Given("the id is {string}")
    public void the_id_is(String id) {
        ob.setId(Integer.parseInt(id));
    }
    @Then("customer can sign up")
    public void customer_can_sign_up() {
        ob.isCustomerRegistrationCompleted(ob,ob.getPassword(),ob.getComPassword());
        Assert.assertEquals(true,ob.getCustomerRegistrationCompleted());

    }
    @Then("try again to sign up!")
    public void try_again_to_sign_up() {
        ob.isCustomerRegistrationCompleted(ob,ob.getPassword(),ob.getComPassword());
        Assert.assertEquals(false,ob.getCustomerRegistrationCompleted());

    }

    @Then("try enter email to sign up!")
    public void try_enter_email_to_sign_up() {
        ob.searchIfEmailIsAlreadyExist(ob.getEmail());
        Assert.assertEquals(true,ob.isIfEmailFound());

    }

}
