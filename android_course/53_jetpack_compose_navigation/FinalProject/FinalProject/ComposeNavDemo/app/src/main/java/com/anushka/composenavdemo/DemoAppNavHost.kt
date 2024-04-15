package com.anushka.composenavdemo

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// composable called in main activity
@Composable
fun DemoAppNavHost(
    navController : NavHostController = rememberNavController() // get nav controller
){

    // create nav host with controller and start destination
    NavHost(navController = navController, startDestination ="home_screen" ){// route of start screen

        // list of screens
        // route is unique id as well as data place holder
        composable(route = "home_screen"){
            HomeScreen(
                // get string in lambda
                onNavigateToSecondScreen = {// pass it to second screen using complete path
                    // id imp here type can be anything
                    navController.navigate("second_screen/$it")
                }
            )
        }

        // home screen navigate here using complete path passing place holder id
        composable(
            route = "second_screen/{inputName}",// get string here
            arguments = listOf(// pass to arg
               navArgument("inputName"){
                    type = NavType.StringType
               }
            )
        ){
            SecondScreen(// get it from arguments
               textToDisplay = it.arguments?.getString("inputName").toString()
            )
        }

    }


}