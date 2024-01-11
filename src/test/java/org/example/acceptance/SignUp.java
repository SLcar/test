package org.example.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.Order;
import org.example.Registration;
import org.junit.Assert;

public class SignUp {
    Registration ob;
    Order order;
    public SignUp() {
        ob =new Registration();
        order = new Order();
    }

    @Given("the name is {string}")
    public void the_name_is(String name) {
        ob.setName(name);
    }
    @Given("The phone Number is {string}")
    public void the_phone_number_is(String string) {
      ob.setPhone(Integer.parseInt(string));
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

    @Given("The Address is {string}")
    public void the_address_is(String string) {
        ob.setAddress(string);
    }
    @Then("customer can sign up")
    public void customer_can_sign_up() {
        String dD =ob.getName()+"," +ob.getEmail() + "," + ob.getPassword() + "," + ob.getAddress() + "," + ob.getId()+","+ob.getPhone()+"\n";
        ob.storeDataToFile(dD);
        ob.isCustomerRegistrationCompleted(ob.getPassword(),ob.getComPassword());
        order.deleteOrder2("custumorData",4);
        Assert.assertTrue(ob.getCustomerRegistrationCompleted());

    }
    @Then("try again to sign up!")
    public void try_again_to_sign_up() {
        ob.isCustomerRegistrationCompleted(ob.getPassword(),ob.getComPassword());
        Assert.assertFalse(ob.getCustomerRegistrationCompleted());

    }

    @Then("try enter email to sign up!")
    public void try_enter_email_to_sign_up() {
        ob.searchIfEmailIsAlreadyExist(ob.getEmail());
        Assert.assertTrue(ob.isIfEmailFound());

    }

}
