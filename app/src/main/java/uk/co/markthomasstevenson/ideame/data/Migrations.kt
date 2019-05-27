package uk.co.markthomasstevenson.ideame.data

import io.realm.DynamicRealm
import io.realm.RealmMigration

class Migrations : RealmMigration {
    override fun migrate(realm: DynamicRealm, old: Long, new: Long) {
        var oldVersion = old

        // DynamicRealm exposes an editable schema
        val schema = realm.schema

        // Migrate to version 1: Add a new class.
        // Example:
        // public Person extends RealmObject {
        //     private String name;
        //     private int age;
        //     // getters and setters left out for brevity
        // }
        //if (oldVersion == 0L) {
        //    schema.create("Person")
        //            .addField("name", String::class.java)
        //            .addField("age", Int::class.java)
        //    oldVersion++
        //}


    }
}