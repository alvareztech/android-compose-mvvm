package tech.alvarez.employeedirectory

import android.app.Application
import tech.alvarez.employeedirectory.data.AppContainer
import tech.alvarez.employeedirectory.data.AppContainerImpl

class DirectoryApp : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }
}