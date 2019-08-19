package uk.co.markthomasstevenson.ideame.repos

import androidx.lifecycle.LiveData
import io.realm.Realm
import io.realm.RealmResults
import uk.co.markthomasstevenson.ideame.data.asLiveData
import uk.co.markthomasstevenson.ideame.model.Functionality
import uk.co.markthomasstevenson.ideame.model.Idea

class IdeaRepository(val realm: Realm) {
    fun getIdeas(): LiveData<RealmResults<Idea>> {
        return realm.where(Idea::class.java).findAllAsync().asLiveData()
    }

    fun getIdeaCount() : Int {
        return realm.where(Idea::class.java).findAll().count()
    }

    fun getOrCreateIdea(id: String): Idea {
        var idea = realm.where(Idea::class.java).equalTo("id", id).findFirst()
        if(idea == null) {
            createIdea(id)
            idea = realm.where(Idea::class.java).equalTo("id", id).findFirst()
        }
        return idea!!
    }

    private fun createIdea(id: String) {
        realm.executeTransaction {
            val idea = Idea()
            idea.id = id
            it.copyToRealmOrUpdate(idea)
        }
    }

    fun getFunctionalities(ideaId: String): LiveData<RealmResults<Functionality>> {
        return realm.where(Functionality::class.java).equalTo("ideaId", ideaId).findAllAsync().asLiveData()
    }

    fun addFunctionality(ideaId: String, id: String, version: String, name: String) {
        realm.executeTransaction {
            val functionality = Functionality()
            functionality.id = id
            functionality.ideaId = ideaId
            functionality.version = version
            functionality.name = name
            it.copyToRealmOrUpdate(functionality)
            val idea = it.where(Idea::class.java).equalTo("id", ideaId).findFirst()
            idea?.coreFunctionality?.add(functionality)
            it.copyToRealmOrUpdate(idea!!)
        }
    }

    fun getFunctionality(id: String): Functionality? {
        return realm.where(Functionality::class.java).equalTo("id", id).findFirst()
    }

    fun updateFunctionality(id: String, version: String, name: String) {
        realm.executeTransaction { realm1 ->
            val functionality = realm1.where(Functionality::class.java).equalTo("id", id).findFirst()
            functionality?.let {
                functionality.name = name
                functionality.version = version
                realm1.copyToRealmOrUpdate(functionality)
            }
        }
    }

    fun updateIdea(id: String, title: String, elevatorPitch: String) {
        realm.executeTransaction { realm1 ->
            val idea = realm1.where(Idea::class.java).equalTo("id", id).findFirst()
            idea?.let {
                idea.title= title
                idea.elevatorPitch = elevatorPitch
                realm1.copyToRealmOrUpdate(idea)
            }
        }
    }

    fun deleteIdea(ideaId: String) {
        realm.executeTransaction { realm1 ->
            val idea = realm1.where(Idea::class.java).equalTo("id", ideaId).findFirst()
            idea?.deleteFromRealm()
        }
    }

    fun deleteFunctionality(functionalityId: String) {
        realm.executeTransaction { realm1 ->
            val functionality = realm1.where(Functionality::class.java).equalTo("id", functionalityId).findFirst()
            functionality?.deleteFromRealm()
        }
    }
}