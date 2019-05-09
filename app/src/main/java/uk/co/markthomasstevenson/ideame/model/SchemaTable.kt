package uk.co.markthomasstevenson.ideame.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class SchemaTable : RealmObject() {
    @PrimaryKey
    var id = UUID.fromString("").toString()
    var headings = RealmList<SchemaTableHeadings>()
}
