package org.example.acceptance;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Product;
import org.junit.Assert;

public class ProductSearch {
    Product product;
    public ProductSearch() {
        product = new Product();
    }

    @Given("the CategoryName  {string}")
    public void the_category_name(String catName) {

        product.setCategoryName(catName);
    }
    @Then("The category not found")
    public void the_category_not_found() {
        product.ifCategoryExist(product.getCategoryName());
        Assert.assertEquals(false,product.isCategoryExistFlag());

    }

    @When("the user searches for a product with ID {string}")
    public void the_user_searches_for_a_product_with_id(String id) {
        product.ifProductIdExist(product.getCategoryName(),id);
        product.ifCategoryExist(product.getCategoryName());
    }


    @Then("the system should display the product with ID {string}")
    public void the_system_should_display_the_product_with_id(String string) {
        Assert.assertEquals(true,product.isCategoryExistFlag());
        Assert.assertEquals(true,product.isiDExistFlag());

    }


    @Then("the system should display the product with ID {string} not found")
    public void the_system_should_display_the_product_with_id_not_found(String string) {
        Assert.assertEquals(false,product.isiDExistFlag());
    }
    @When("the user searches for a product with name {string}")
    public void the_user_searches_for_a_product_with_name(String name) {
      product.ifProductNameExist(product.getCategoryName(),name);
    }
    @Then("the system should display products with the name {string}")
    public void the_system_should_display_products_with_the_name(String string) {
        Assert.assertEquals(true,product.ifProductNameExist2(product.getCount()));
    }
    @Then("the system should display products with the name {string} not found")
    public void the_system_should_display_products_with_the_name_not_found(String string) {
        Assert.assertEquals(false,product.ifProductNameExist2(product.getCount()));
    }

    @When("the user searches for a product with description {string}")
    public void the_user_searches_for_a_product_with_description(String des) {
        product.ifProductDescriptionsExist(product.getCategoryName(),des);
    }
    @Then("the system should display products with the description {string}")
    public void the_system_should_display_products_with_the_description(String string) {
        Assert.assertEquals(true,product.ifProductNameExist2(product.getCount()));
    }
    @Then("the system should display products with the description {string} not found")
    public void the_system_should_display_products_with_the_description_not_found(String string) {
        Assert.assertEquals(false,product.ifProductNameExist2(product.getCount()));

    }

    @When("the user searches for a product with availability {string}")
    public void the_user_searches_for_a_product_with_availability(String availability) {
        product.ifProductAvailabilityExist(product.getCategoryName(),availability);

    }
    @Then("the system should display products with the availability {string}")
    public void the_system_should_display_products_with_the_availability(String string) {
        Assert.assertEquals(true,product.ifProductNameExist2(product.getCount()));

    }

    @Then("the system should display products with the availability {string} not found")
    public void the_system_should_display_products_with_the_availability_not_found(String string) {
        Assert.assertEquals(false,product.ifProductNameExist2(product.getCount()));

    }





}
