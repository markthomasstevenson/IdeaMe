package uk.co.markthomasstevenson.ideame.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class SchemaTableLink : RealmObject() {
    @PrimaryKey
    var id = UUID.fromString("").toString()
    var name = ""
    var sourceTableId = UUID.fromString("").toString()
    var sourceHeadingId = UUID.fromString("").toString()
    var targetTableId = UUID.fromString("").toString()
    var targetHeadingId = UUID.fromString("").toString()
}
