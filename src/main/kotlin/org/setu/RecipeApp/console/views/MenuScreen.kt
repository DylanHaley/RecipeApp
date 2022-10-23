package org.setu.RecipeApp.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.setu.RecipeApp.console.controllers.RecipeAppUIController
import tornadofx.*

class MenuScreen : View("Recipe App") {

    val recipeUIController: RecipeAppUIController by inject()

    //Simple screen to choose a button to take them to the next screen
    override val root = form {
        setPrefSize(600.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Recipe") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        recipeUIController.loadAddRecipeScreen()
                    }
                }
            }
            text("")
            button("List Recipes") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        recipeUIController.loadListRecipeScreen()
                    }
                }
            }
            text("")
            button("Delete Recipe") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        recipeUIController.loadDeleteRecipeScreen()
                    }
                }
            }
            text("")
            button("Search Recipes") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        recipeUIController.loadSearchRecipeScreen()
                    }
                }
            }
                text("")
                button("Update Recipes") {

                    isDefaultButton = true
                    useMaxWidth = true
                    action {
                        runAsyncWithProgress {
                            recipeUIController.loadUpdateRecipeScreen()
                        }
                    }
                }
                text("")
                button("Exit") {

                    isDefaultButton = true
                    useMaxWidth = true
                    action {
                        runAsyncWithProgress {
                            Platform.exit();
                            System.exit(0);
                        }
                    }
                }

            }

        }

    }