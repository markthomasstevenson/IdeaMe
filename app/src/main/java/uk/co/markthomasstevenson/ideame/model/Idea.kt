package uk.co.markthomasstevenson.ideame.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Idea : RealmObject() {
    @PrimaryKey
    var id = UUID.fromString("").toString()
    var title = ""
    var elevatorPitch = ""
    var coreFunctionality = RealmList<CoreFunctionality>()
    var wireframes = RealmList<Wireframe>()
    var flows = RealmList<Flow>()
    var schema : IdeaSchema? = null
}