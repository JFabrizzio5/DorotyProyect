package com.example.intento300

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.intento300.ui.theme.Intento300Theme
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.analytics.FirebaseAnalytics
import com.example.intento300.MyReadingDatabaseRealTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        // Instancia de MyReadingDatabaseRealTime
        val myDb = MyReadingDatabaseRealTime()
        myDb.addMessage()  // Enviar mensaje al iniciar la app

        // Leer todos los mensajes de la base de datos
        myDb.readAllMessages()
        enableEdgeToEdge() // Ensure that you are using the latest Compose version
        setContent {
            Intento300Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Intento300Theme {
        Greeting("Android")
    }
}
