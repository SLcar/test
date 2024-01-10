package org.example.acceptance;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;
import org.junit.Assert;

public class OrderSteps {
    Product product;
    Customer customer;
    Order order;
    Category category;
    public static long orderNumber;
    SignIn sign;
    public OrderSteps() {
        product = new Product();
        customer = new Customer();
        order = new Order();
        category = new Category();
        sign = new SignIn();
    }

    @Given("customer view available products for exist categories example {string}")
    public void customer_view_available_products_for_exist_categories_example(String nameCato) {
        order.setCategoryName(nameCato);
        category.setCategoryName(nameCato);
        product.printAllProductAndCategories(nameCato);
        sign.customerIslLogin("sajaabosair@gmail.com","1111");
        customer.setTheCustomerIs(sign.getNumberOfLine());
        customer.searchTheCustomer();
    }

    @Given("The Customer Name is {string}")
    public void the_customer_name_is(String name) {

        order.setCustomerName(name);
    }
    @Given("The Customer Id is {string}")
    public void the_customer_id_is(String id) {

        order.setIdCustomer(id);
    }
    @When("customer enter exist productsId to order {string}")
    public void customer_enter_exist_products_id_to_order(String id) {
        product.setID(Integer.parseInt(id));
        order.setProductID(Integer.parseInt(id));
        product.ifProductIdExist(category.getCategoryName(), String.valueOf(product.getID()));
    }

    @When("the quantities is allowed {string}")
    public void the_quantities_is_allowed(String quantity) {
        order.makePurchasesMenu();
        order.setQuantitiesProduct(Integer.parseInt(quantity));
        order.ifQuantitiesAllowed(quantity);
        order.whatSetProductPrice(category.getCategoryName(), String.valueOf(order.getProductID()));
        order.fullProductPrice();
        orderNumber= order.getOrderNumber();
        String product =order.getCategoryName()+","+order.getOrderNumber()+","+order.getIdCustomer()+","+order.getProductID()+","+order.getQuantitiesProduct()+","+order.getTotalPriceProduct()+"\n";
        order.addNewOrderToCustomer(order.getCustomerName(),order.getIdCustomer(),product);
        order.extracted2();
    }

    @Then("the products should ordered")
    public void the_products_should_ordered() {

        Assert.assertTrue(product.isiDExistFlag());
        Assert.assertTrue(order.isQuantitiesAllowedFlag());
    }

    @Then("the order status is {string}")
    public void the_order_status_is(String status) {
        order.setStatusOrder(status);
    }

    @When("the quantities not allowed {string}")
    public void the_quantities_not_allowed(String quantities) {
        order.setQuantitiesProduct(Integer.parseInt(quantities));
        Assert.assertFalse(order.isQuantitiesAllowedFlag());
    }

    @Then("the customer should enter allowed quantity")
    public void the_customer_should_enter_allowed_quantity() {
        Assert.assertFalse(order.isQuantitiesAllowedFlag());

    }

    @When("customer enter not exist product id to order {string}")
    public void customer_enter_not_exist_product_id_to_order(String id) {
        product.ifProductIdExist(category.getCategoryName(),id);
    }

    @Then("the customer should add exist product")
    public void the_customer_should_add_exist_product() {
        Assert.assertFalse(product.isiDExistFlag());
    }

    @When("customer enter exist  productId {string}")
    public void customer_enter_exist_product_id(String string) {
        order.setProductID(Integer.parseInt(string));
        System.out.println(order.getProductID());
        order.foundNumber2Line(order.getProductID(),orderNumber);
        int newQuantity = 2;
        order.ifQuantitiesAllowed(String.valueOf(newQuantity));
        order.ifQuantitiesGraterThan(newQuantity);

    }
    @Then("customer can edit Quantity To The Product")
    public void customer_can_edit_quantity_to_the_product() {
        order.ifProductExitToEdit(order.getProductID(),order.getOrderNumber(),order.getCustomerName(),order.getIdCustomer());
        Assert.assertTrue(order.isProductExitFlag());
    }
    @Then("customer can't edit  Quantity")
    public void customer_can_t_edit_quantity() {
        order.ifProductExitToEdit(order.getProductID(),order.getOrderNumber(),order.getCustomerName(),order.getIdCustomer());
        Assert.assertFalse(order.isProductExitFlag());
    }
    @Then("customer can't edit The Quantity of productId {int}")
    public void customer_can_t_edit_the_quantity_of_product_id(Integer int1) {
        order.ifQuantitiesAllowed(String.valueOf( int1));
        Assert.assertFalse(order.isQuantitiesAllowedFlag());

    }
    @Given("admin choose view customer order")
    public void admin_choose_view_customer_order() {
        order.viewAllOrderToAdmin();
    }


    @When("enter exist order {string}")
    public void enter_exist_order(String string) {
        order.setOrderNumber(Long.parseLong(string));

    }
    @Then("admin can edit order status,shipped date")
    public void admin_can_edit_order_status_shipped_date() {
        order.setOrderNumber(orderNumber);
        order.ifEnterOrderExitToChangeSt(order.getOrderNumber());
        order.searchAboutCustomer("orderToAdmin",orderNumber);
        order.setStatusOrder("shipped");
        order.setGmailIs("sajaabosair@gmail.com");
        order.editTheOrder();

    }

    @Then("admin can edit order status,delivered date")
    public void admin_can_edit_order_status_delivered_date() {
        order.setOrderNumber(orderNumber);
        order.ifEnterOrderExitToChangeSt(order.getOrderNumber());
        order.searchAboutCustomer("orderToAdmin",orderNumber);
        order.setStatusOrder(Admin.getDelivered);
        order.setGmailIs("sajaabosair@gmail.com");
        order.searchAboutGmail();
        order.editTheOrder();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Given("customer view {string} orders")
    public void customer_view_orders(String pending) {
        order.setStatusOrder(pending);
    }
    @When("customer enter exist order {string}")
    public void customer_enter_exist_order(String id) {

        order.setOrderNumber(orderNumber);
        String name = order.getCustomerName() + "-" + order.getIdCustomer();
       order.deleteOrder(name);
        order.setOrderNumber(Long.parseLong(id));

    }

    @Then("customer can cancel order")
    public void customer_can_cancel_order() {
        order.viewPendingOrder(order.getStatusOrder(),order.getIdCustomer());
        order.viewPendingOrder1(Order.pending,order.getIdCustomer());
        order.ifEnterOrderExitToCancelPending(String.valueOf(order.getOrderNumber()));
       Assert.assertTrue(order.isIfCustomerShowPendingOrder());
       Assert.assertTrue(order.isIfOrderExist());
        Assert.assertTrue(order.isIfCustomerCancelPendingOrder());
    }

    @Then("customer can't cancel order")
    public void customer_can_t_cancel_order() {
        order.viewPendingOrder(order.getStatusOrder(),order.getIdCustomer());
        order.ifEnterOrderExitToCancelPending(String.valueOf(order.getOrderNumber()));
        Assert.assertTrue(order.isIfCustomerShowPendingOrder());
        Assert.assertFalse(order.isIfOrderExist());
        Assert.assertFalse(order.isIfCustomerCancelPendingOrder());
    }

    @Then("customer view The {string} orders")
    public void customer_view_the_orders(String string) {
        order.viewDeliveredOrder(order.delivered,order.getIdCustomer());
    }

    @Then("Admin view {string} orders")
    public void admin_view_orders(String string) {
        order.viewAllOrderToAdmin();
        order.ifFileOfCustomerOrderNoItem("Saja");
        order.ifOrderExitDelivered("0");
        order.setCountOfLine(3);
        order.setIfCustomerShowDeliveredOrder(true);
        order.setDateOfReceiptOfTheOrder("Ddd");
    }

}
