package org.example.acceptance;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.Category;
import org.junit.Assert;

public class CategorySteps {
    Category category;
    public CategorySteps() {
        category = new Category();
    }
    @Given("The Name of Category is {string}")
    public void the_name_of_category_is(String name) {
        category.setCategoryName(name);
    }

    @Then("The Admin can add the Category")
    public void the_admin_can_add_the_category() {
        category.addNewCategory(category.getCategoryName());
        category.addThisCategory(category.getCategoryName());
        Assert.assertTrue(category.isAddNewCategoryFlag());
    }


    @Then("Admin can't add Category")
    public void admin_can_t_add_category() {
        category.addNewCategory(category.getCategoryName());
        Assert.assertFalse(category.isAddNewCategoryFlag());

    }
    @Then("The Admin can edit name of Category")
    public void the_admin_can_edit_name_of_category() {
        category.printAllCategory();
        category.editTheName(category.getCategoryName());
        category.extractedEditCat(category.getCategoryName(),"New");
        Assert.assertTrue(category.isUpdateCategoryFlag());

    }
    @Then("The Admin can't edit name of Category")
    public void the_admin_can_t_edit_name_of_category() {
        category.editTheName(category.getCategoryName());
        Assert.assertFalse(category.isUpdateCategoryFlag());

    }
    @Then("The Admin can delete this Category")
    public void Admin_can_delete_category() {
        category.deleteTheCategory(category.getCategoryName());
        Assert.assertTrue(category.isDeleteCategoryFlag());
        category.extractedDeleteCat(category.getCategoryName());
    }

    @Then("the Admin can't delete Category")
    public void  Admin_enter_category_not_exist_to_delete() {
        category.deleteTheCategory(category.getCategoryName());
        Assert.assertFalse(category.isDeleteCategoryFlag());
    }



}
