package uk.co.markthomasstevenson.ideame.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class SchemaTableHeadings : RealmObject() {
    @PrimaryKey
    var id = UUID.fromString("").toString()
    var name = ""
    var type = ""
}
