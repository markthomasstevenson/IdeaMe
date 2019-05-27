package uk.co.markthomasstevenson.ideame

import android.app.Application
import androidx.multidex.MultiDexApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import uk.co.markthomasstevenson.ideame.data.Migrations

class IdeaApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().modules(IdeaDBSchema())
                .name("idea-me.realm")
                .schemaVersion(0)
                .migration(Migrations())
                .build()
        Realm.setDefaultConfiguration(config)
    }
}