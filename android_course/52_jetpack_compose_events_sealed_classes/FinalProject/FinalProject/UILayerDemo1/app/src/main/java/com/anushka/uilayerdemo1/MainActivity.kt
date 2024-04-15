package com.anushka.uilayerdemo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.anushka.uilayerdemo1.ui.theme.UILayerDemo1Theme
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UILayerDemo1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                  MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    counterViewModel: CounterViewModel = viewModel()
){

    // recomposition takes care of updated values
  val screenState = counterViewModel.screenState.value
  val scaffoldState = rememberScaffoldState()

    //one time execution , for re-execution update key
   LaunchedEffect(key1 = true ){

       // Flow of event , use of event class
       // listener for event 2 - ui event
        counterViewModel.uiEventFlow.collectLatest {event->
             when(event){
                 // for event 2 ui event action
                 is UIEvent.ShowMessage -> {
                   scaffoldState.snackbarHostState.showSnackbar(
                       message = event.message
                   )
                 }
             }
        }
   }



  Scaffold(scaffoldState = scaffoldState) {
      Column(
        modifier = modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.SpaceEvenly
      ) {
         Text(
             modifier = modifier.fillMaxWidth(),
             text = screenState.displayingResult,
             fontWeight = FontWeight.Bold,
             fontSize = 30.sp,
             color = Color.DarkGray
         )
          OutlinedTextField(
              value = screenState.inputValue,
              onValueChange = {
                  // trigger for value enter
                  counterViewModel.onEvent(CounterEvent.ValueEntered(it))
              },
              modifier = modifier.fillMaxWidth(),
              keyboardOptions = KeyboardOptions(
                  keyboardType = KeyboardType.Number
              ),
              textStyle = TextStyle(
                  color = Color.LightGray,
                  fontSize = 30.sp,
                  fontWeight = FontWeight.Bold
              ),
              label = { Text(text = "New Count")}
          )
          if(screenState.isCountButtonVisible){
              Button(
                  onClick = {
                      // trigger for count button click
                      counterViewModel.onEvent(CounterEvent.CountButtonClicked)
                  },
                  modifier = modifier.fillMaxWidth()
              )
              {
               Text(
                   text = "Count",
                   fontSize = 30.sp,
                   fontWeight = FontWeight.Bold
               )
              }
          }
          Button(
              onClick = {
                  // call event method , pass event , method will check and emit value
                  // so basically - on click event to view model state update - view model state observed in main activity - main activity will update up accordingly

                  counterViewModel.onEvent(CounterEvent.ResetButtonClicked)
              },
              modifier = modifier.fillMaxWidth()
          ) {
              Text(
                  text = "Reset",
                  fontSize = 30.sp,
                  fontWeight = FontWeight.Bold
              )
          }

      }
  }

}



