package uk.co.markthomasstevenson.ideame.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Wireframe : RealmObject() {
    @PrimaryKey
    var id = UUID.fromString("").toString()
    var title = ""
    var order = 0
    //todo link to picture or canvas here
}
