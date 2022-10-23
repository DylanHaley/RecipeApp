package org.setu.RecipeApp.console.views

import javafx.beans.property.SimpleLongProperty
import javafx.geometry.Orientation
import org.setu.RecipeApp.console.controllers.RecipeAppUIController
import tornadofx.*

class SearchRecipeScreen : View("Search Recipes") {

    val model = ViewModel()
    val recipeUIController: RecipeAppUIController by inject()
    val id =  model.bind { SimpleLongProperty() }


    //Attempted screen to allow user to search recipes id in order to find specific recipes however errors out on load
    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Recipe ID") {
                textfield(id).required()
            }
            button("Search") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                    action {
                        runAsyncWithProgress {
                            recipeUIController.searchRecipes(id.toLong())
                        }
                    }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        recipeUIController.closeListRecipe()
                    }
                }
            }
        }
    }
}