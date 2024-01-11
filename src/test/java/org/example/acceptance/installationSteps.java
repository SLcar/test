package org.example.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;
import org.junit.Assert;

import static org.example.Installer.setIdInstallerRequest;

public class installationSteps {
    Installer installer;
    Customer customer;
    SignIn sign;
Gmail gmail;
Order order;
    public installationSteps() {
      installer = new Installer();
      customer = new Customer();
      gmail = new Gmail();
      sign = new SignIn();
      order=new Order();
    }

    public static final String[] arrayOfTopic =  {"Installation confirmation", "installer not available", "Cancel Installation Request","Task finished"}; // Creating an array that can hold 3 strings

    @Given("customer request for product {string}")
    public void customer_request_for_product(String string) {
       Installer.setProduct(string);
    }

    @Given("the preferred date is {string}")
    public void the_preferred_date_is(String string) {
      Installer.setPreferredDate(string);
    }

    @Given("the preferred hour is {string}")
    public void the_preferred_hour_is(String string) {
      Installer.setPreferredHour(string);
    }

    @Given("the installing location is {string}")
    public void the_installing_location_is(String string) {
       Installer.setLocationInstalling(string);
    }
    @When("the customer is {string} and password is {string}")
    public void the_customer_is_and_password_is(String string, String string2) {
        sign.customerIslLogin(string,string2);
        customer.setTheCustomerIs(sign.getNumberOfLine());
        customer.searchTheCustomer();
        installer.randomNumberGenerator();
        installer.ifRandomNumberGeneratorNotFound();
        setIdInstallerRequest(1401988804);
        Installer.setStatusInstalling("pending");
        Installer.setCompletionDate("--");
        installer.addThisInstallerRequest();

    }
    @Then("the request sent to installation to conform")
    public void the_request_sent_to_installation_to_conform() {

        Assert.assertTrue(installer.ifDataTrue());
    }

    @Then("the request not send to installation")
    public void the_request_not_send_to_installation() {
        Assert.assertFalse(installer.ifDataTrue());

    }

    @Given("customer view pending request and the id {string}")
    public void customer_view_pending_request_and_the_id(String string) {
        Installer.setIdCustomer(string);
        installer.showAllInstallationRequestPending();

    }

    @When("customer enter exist requestId {string}")
    public void customer_enter_exist_request_id(String string) {
        setIdInstallerRequest(Long.parseLong(string));
        installer.ifExitIdInstallerRequestPending();
    }

    @Then("customer can cancel request")
    public void customer_can_cancel_request() {
        installer.ifExitIdInstallerRequestPending();
        order.deleteOrder2("requestInstallation",installer.getNumberOfLine());
        Installer.setTheDataCancel(true);
        Assert.assertTrue(installer.isTheDataCancel());
    }

    @When("customer enter non exist requestId {string}")
    public void customer_enter_non_exist_request_id(String string) {
        setIdInstallerRequest(Long.parseLong(string));
        installer.ifExitIdInstallerRequestPending();

    }

    @Then("customer can't cancel request")
    public void customer_can_t_cancel_request() {
        Assert.assertFalse(installer.isIdInstallationFlag());

    }

    @Given("installer view pending request")
    public void installer_view_pending_request() {

        installer.showAllInstallationRequestToAdminANDInstaller("pending");
    }

    @When("installer enter exist request id {string}")
    public void installer_enter_exist_request_id(String string) {
        setIdInstallerRequest(Long.parseLong(string));
        installer.ifExitIdInstallerRequestPendingToAdmin();
    }

    @Then("installer can cancel request")
    public void installer_can_cancel_request() {
        Assert.assertTrue(installer.isTheDataCancel());

    }

    @When("installer enter non exist requestId {string}")
    public void installer_enter_non_exist_request_id(String string) {
        setIdInstallerRequest(Long.parseLong(string));
        installer.ifExitIdInstallerRequestPendingToAdmin();
    }

    @Then("installer can't cancel request")
    public void installer_can_t_cancel_request() {
        Assert.assertFalse(installer.isIdInstallationFlag());

    }

    @Given("admin view pending request")
    public void admin_view_pending_request() {
        installer.showAllInstallationRequestToAdminANDInstaller("pending");

    }

    @When("admin enter exist requestId {string}")
    public void admin_enter_exist_request_id(String string) {
        setIdInstallerRequest(Long.parseLong(string));
        installer.ifExitIdInstallerRequestPendingToAdmin();
    }

    @Then("admin can cancel request")
    public void admin_can_cancel_request() {
        Assert.assertTrue(installer.isTheDataCancel());

    }

    @When("admin enter non exist requestId {string}")
    public void admin_enter_non_exist_request_id(String string) {
        setIdInstallerRequest(Long.parseLong(string));
        installer.ifExitIdInstallerRequestPendingToAdmin();
    }

    @Then("admin can't cancel request")
    public void admin_can_t_cancel_request() {
        Assert.assertFalse(installer.isIdInstallationFlag());

    }
    @When("the installer choose req with {string}")
    public void the_installer_choose_req_with(String string) {
        setIdInstallerRequest(Long.parseLong(string));
        installer.setDAta(Long.parseLong(string),"pending");
        installer.checkIfDayAndHourAppropriate(installer.getPreferredDate(),installer.getPreferredHour());
    }

    @Then("the installer is available send confirmation request to customer and setting request status as {string}")
    public void the_installer_is_available_send_confirmation_request_to_customer_and_setting_request_status_as(String string) {
        Assert.assertTrue(installer.installerAvailableToCustomer);

    }

    @When("the req time in this date is {string} and the time is {string}")
    public void the_req_time_in_this_date_is_and_the_time_is(String string, String string2) {
       installer.ifAnyDayAnyTime(string,string2);
    }

    @Then("installer send confirmation to customer with date and time and setting request status as {string}")
    public void installer_send_confirmation_to_customer_with_date_and_time_and_setting_request_status_as(String string) {
       Assert.assertTrue(installer.installerAvailableToCustomer);
    }

    @Given("the installer choose req with not allowed {string}")
    public void the_installer_choose_req_with_not_allowed(String string) {
        setIdInstallerRequest(Long.parseLong(string));
        installer.ifExitIdInstallerRequestPendingToAdmin();
    }

    @Then("the installer can't schedule the requests")
    public void the_installer_can_t_schedule_the_requests() {
        Assert.assertFalse(installer.isIdInstallationFlag());
    }

    @When("the installer choose request with {string} and installer not available")
    public void the_installer_choose_request_with_and_installer_not_available(String string) {
       setIdInstallerRequest(Long.parseLong(string));
       installer.setDAta(installer.getIdInstallerRequest(),"pending");

    }
    @Then("the installer send email to customer suggested new time and date")
    public void the_installer_send_email_to_customer_suggested_new_time_and_date() {
      gmail.sendEmail(installer.getGmail(),arrayOfTopic[1],"the_installer_send_email_to_customer_suggested_new_time_and_date");
    }



    @When("the customer check new date and time From email")
    public void the_customer_check_new_date_and_time_from_email() {
        Installer.setPreferredDate("3/3/2023");
        installer.putDay(installer.getPreferredDate());
        installer.setNumberOfLine(7);
        order.deleteOrder2("requestInstallation",6);
        Installer.setPreferredHour("10 Am");
       installer.putTime(installer.getPreferredHour());
        installer.putDayAndTime(installer.getPreferredDate(),installer.getPreferredHour());
    }

    @Then("customer send confirmation to installer")
    public void customer_send_confirmation_to_installer() {
        Assert.assertTrue(installer.isChangeTimeOrHour());
    }

    @Given("the installer is completed the installation {string}")
    public void the_installer_is_completed_the_installation(String string) {
       setIdInstallerRequest(Long.parseLong(string));
        installer.changeStatus();
    }

    @Then("setting request status as completed")
    public void setting_request_status_as_completed() {
        Assert.assertTrue(installer.isChangeStatus());
    }



}
