package uk.co.markthomasstevenson.ideame.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import io.realm.Realm
import io.realm.RealmResults
import uk.co.markthomasstevenson.ideame.data.ideaDao
import uk.co.markthomasstevenson.ideame.model.Functionality
import uk.co.markthomasstevenson.ideame.model.Idea
import java.util.*


class IdeaViewModel : ViewModel() {
    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    fun getIdeas(): LiveData<RealmResults<Idea>> {
        return realm.ideaDao().getIdeas()
    }

    fun getOrCreateIdea(id: String): Idea {
        return realm.ideaDao().getOrCreateIdea(id)
    }

    fun getFunctionalities(id: String): LiveData<RealmResults<Functionality>> {
        return realm.ideaDao().getFunctionalities(id)
    }

    fun getFunctionality(id: String): Functionality? {
        return realm.ideaDao().getFunctionality(id)
    }

    fun addFunctionality(): String {
        val id = UUID.randomUUID().toString()
        realm.ideaDao().addFunctionality(id)
        return id
    }

    fun updateFunctionality(id: String, version: String, name: String) {
        realm.ideaDao().updateFunctionality(id, version, name)
    }

    override fun onCleared() {
        realm.close()
        super.onCleared()
    }

    fun updateFunctionalityVersion(id: String, version: String) {
        val functionality = getFunctionality(id)
        updateFunctionality(id, version, functionality!!.name)
    }

    fun updateFunctionalityName(id: String, name: String) {
        val functionality = getFunctionality(id)
        updateFunctionality(id, functionality!!.version, name)
    }

    fun updateIdea(id: String, title: String, elevatorPitch: String) {
        val idea = getOrCreateIdea(id)
        realm.ideaDao().updateIdea(id, title, elevatorPitch)
    }
}