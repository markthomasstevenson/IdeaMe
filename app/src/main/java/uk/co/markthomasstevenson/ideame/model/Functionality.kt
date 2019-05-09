package uk.co.markthomasstevenson.ideame.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Functionality : RealmObject() {
    @PrimaryKey
    var id = UUID.randomUUID().toString()
    var name = ""
    var version = ""
    var ideaId = ""
}
