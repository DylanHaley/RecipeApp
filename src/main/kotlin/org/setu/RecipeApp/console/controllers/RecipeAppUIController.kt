package org.setu.RecipeApp.console.controllers

import javafx.application.Platform.runLater
import mu.KotlinLogging
import org.setu.RecipeApp.console.models.RecipeJSONStore
import org.setu.RecipeApp.console.models.RecipeAppModel
import org.setu.RecipeApp.console.views.*
import tornadofx.*

class RecipeAppUIController : Controller() {

    val recipes = RecipeJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Recipe App TornadoFX UI App, store your own recipes" }
    }


    //Recipe Functions
    // Add new recipe to the JSON file
    fun addRecipe(_title : String, _ingredients : String, _description: String){

        var aRecipe = RecipeAppModel(title = _title, ingredients = _ingredients, description = _description)
        recipes.create(aRecipe)
        logger.info("Added")
    }

    //Load the recipe JSON file
    fun loadListRecipeScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListRecipeScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        recipes.logAll()
    }

    //Load the add recipe screen
    fun loadAddRecipeScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddRecipeScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    //Load delete recipe screen
    fun loadDeleteRecipeScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(DeleteRecipeScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    //close the add recipe screen
    fun closeAddRecipe() {
        runLater {
            find(AddRecipeScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    //close the list recipe screen
    fun closeListRecipe() {
        runLater {
            find(ListRecipeScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    //Attempted a delete function which calls delete from RecipeJSONStore to clear JSON file however very buggy
    fun deleteRecipe(){
        recipes.delete()
        logger.info("Deleted")
    }

    //Attempted search function does not work as it errors out while trying to load search screen
    fun searchRecipes(id: Long) {
        recipes.findOne(id)
    }

    fun loadSearchRecipeScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(SearchRecipeScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun loadUpdateRecipeScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(UpdateRecipeScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeUpdateRecipe() {
        runLater{
            find(UpdateRecipeScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }

    }

    fun closeDeleteRecipe() {
        runLater {
            find(DeleteRecipeScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
}