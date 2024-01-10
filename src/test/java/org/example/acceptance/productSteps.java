package org.example.acceptance;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.Product;
import org.junit.Assert;

import static org.junit.Assert.assertTrue;

public class productSteps {
    Product product;
    public productSteps() {
        product = new Product();
    }

    @Given("the productId is {string}")
    public void the_product_id_is(String id) {
        product.setID(Integer.parseInt(id));
    }
    @Given("the productName is {string}")
    public void the_product_name_is(String name) {
        product.setNameProduct(name);
    }
    @Given("the Description is {string}")
    public void the_description_is(String description) {
        product.setDescriptionProduct(description);
    }
    @Given("the price is {string}")
    public void the_price_is(String price) {
        product.setPriceProduct(price);
    }
    @Given("the availability is {string}")
    public void the_availability_is(String availability) {
        product.setAvailability(availability);
    }
    @Given("The img path is {string}")
    public void the_img_path_is(String imgPath) {
        product.setPriceProduct(imgPath);
    }
    @Given("the CategoryName is {string}")
    public void the_category_name_is(String categoryName) {
        product.setCategoryName(categoryName);
    }
    @Then("admin can add product")
    public void admin_can_add_product() {
        product.ifCategoryExist(product.getCategoryName());
        product.ifProductIdExist(product.getCategoryName(), String.valueOf(product.getID()));
        product.addNewProducts(product.categoryName,"added");
        assertTrue(product.isCategoryExistFlag());
        Assert.assertFalse(product.isiDExistFlag());
    }

    @Then("admin can't add product")
    public void admin_can_t_add_product() {
        product.ifProductIdExist(product.getCategoryName(), String.valueOf(product.getID()));
        assertTrue(product.isiDExistFlag());

    }

    @Then("the category not found")
    public void the_category_not_found() {
        product.ifCategoryExist(product.getCategoryName());
        Assert.assertFalse(product.isCategoryExistFlag());

    }

    @Then("admin can update product")
    public void admin_can_update_product() {
        product.ifCategoryExist(product.getCategoryName());
        product.ifProductIdExist(product.getCategoryName(), String.valueOf(product.getID()));
        assertTrue(product.isCategoryExistFlag());
        assertTrue(product.isiDExistFlag());
    }

    @Then("admin can't update")
    public void admin_can_t_update() {
        product.ifProductIdExist(product.getCategoryName(), String.valueOf(product.getID()));
        Assert.assertFalse(product.isiDExistFlag());
    }



    @Then("the product will deleted")
    public void the_product_will_deleted() {
        product.ifCategoryExist(product.getCategoryName());
        product.ifProductIdExist(product.getCategoryName(), String.valueOf(product.getID()));
        product.deleteThisProduct(product.getCategoryName());
        assertTrue(product.isCategoryExistFlag());
        assertTrue(product.isiDExistFlag());
    }

    @Then("the product not deleted")
    public void the_product_not_deleted() {
        product.ifProductIdExist(product.getCategoryName(), String.valueOf(product.getID()));
        Assert.assertFalse(product.isiDExistFlag());

    }

    @Then("all product category shown")
    public void all_product_category_shown() {
        product.ifCategoryExist(product.getCategoryName());
        product.printAllProductAndCategories(product.getCategoryName());
        assertTrue(product.isCategoryExistFlag());
    }

    @Then("the category not shown")
    public void the_category_not_shown() {
        product.ifCategoryExist(product.getCategoryName());
        Assert.assertFalse(product.isCategoryExistFlag());
    }

}
