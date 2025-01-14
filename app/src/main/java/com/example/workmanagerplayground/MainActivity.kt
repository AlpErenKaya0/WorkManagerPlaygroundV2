package com.example.workmanagerplayground

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workmanagerplayground.ui.theme.WorkManagerPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WorkManagerPlaygroundTheme {
                Column(Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Button(onClick = {
                        Intent(
                            this@MainActivity,CounterService::class.java
                        ).also {
                            it.action= CounterService.CounterAction.START.name
                            startService(it)
                            //Şu anki konumda Service durdurulup tekrar başlatıldığında
                            //durduğu yerden devam etmek yerine service baştan başlatılıyor.
                        }
                    }){
                        Text(text="Start Counter")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        Intent(
                            this@MainActivity,CounterService::class.java
                        ).also {
                            //startService yazıp it ile içine stop vererek durduruyoruz
                            //stopservice diye bir fonksiyonumuz olmadığı için
                            //service'i durdurmak için it'i veriyoruz.
                            it.action= CounterService.CounterAction.STOP.name
                            startService(it)
                        }
                    }
                    ){
                        Text(text="Stop Counter")
                    }
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
    WorkManagerPlaygroundTheme {
        Greeting("Android")
    }
}