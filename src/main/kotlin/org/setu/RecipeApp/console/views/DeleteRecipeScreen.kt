package org.setu.RecipeApp.console.views

import org.setu.RecipeApp.console.controllers.RecipeAppUIController
import tornadofx.*

class DeleteRecipeScreen : View("Delete Recipes") {

    val recipeUIController: RecipeAppUIController by inject()


    //Delete screen allows user to delete entire recipe JSON file however if ran and a user inputs a new recipe it cannot be seen in list recipes
    override val root = vbox {
        setPrefSize(600.0, 200.0)
        button("Delete Recipe JSON") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    recipeUIController.deleteRecipe()
                }
            }
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    recipeUIController.closeDeleteRecipe()
                }
            }
        }
    }

}