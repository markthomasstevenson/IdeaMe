package uk.co.markthomasstevenson.ideame

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class IdeaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().modules(IdeaDBSchema()).name("idea-me.realm").deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)
    }
}