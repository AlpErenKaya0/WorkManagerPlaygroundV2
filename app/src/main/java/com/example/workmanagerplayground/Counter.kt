package com.example.workmanagerplayground

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Counter:ViewModel(){
    private var counterValue: Int = 0
    private var isRunning: Boolean = true

    //keyfi olarak bildirimde kullanıcının girdiği ana ekranda ad soyad isim vs bilgisini alıp
    // onCreate'de notif iznini isteyip kullanıcı kaydet butonuna bastığında
    //WorkManager'ı başlatıp kullanıcının yazdığı veriyi
    // 15 dakkikada 1 artırarak  bildirim olarak 4 saatte bir verebiliriz.
    //15 dakikada bir de görünümü bir artırılmış haliyle güncelleriz mesela.
    //son yazdığım için SideEffect ya da coroutinescope gerekebilir.
    fun start(): Flow<Int> = flow {
        while (isRunning) {
        emit(counterValue)
            delay(1000)
            counterValue++
        }
    }
    fun stop(){
        isRunning = false
        counterValue = 0
    }
}