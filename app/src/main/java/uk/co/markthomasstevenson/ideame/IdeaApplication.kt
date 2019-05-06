package uk.co.markthomasstevenson.ideame

import android.app.Application
import androidx.multidex.MultiDexApplication
import io.realm.Realm
import io.realm.RealmConfiguration

class IdeaApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().modules(IdeaDBSchema()).name("idea-me.realm").deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)
    }
}