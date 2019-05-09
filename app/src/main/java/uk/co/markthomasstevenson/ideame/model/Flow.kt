package uk.co.markthomasstevenson.ideame.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Flow : RealmObject() {
    @PrimaryKey
    var id = UUID.fromString("").toString()
    var name = ""
    var sourceWireframeId = UUID.fromString("").toString()
    var targetWireframeId = UUID.fromString("").toString()
}
