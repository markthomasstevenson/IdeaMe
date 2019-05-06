package uk.co.markthomasstevenson.ideame.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class CoreFunctionality : RealmObject() {
    @PrimaryKey
    var id = UUID.fromString("").toString()
    var summary = ""
    var target = 0.0f
}
