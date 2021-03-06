package uk.co.markthomasstevenson.ideame.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.realm.Realm
import io.realm.RealmResults
import uk.co.markthomasstevenson.ideame.data.ideaDao
import uk.co.markthomasstevenson.ideame.model.Functionality
import uk.co.markthomasstevenson.ideame.model.Idea
import java.util.*


class IdeaViewModel : ViewModel() {
    private var editableItem = MutableLiveData<String>()
    private var fabWasClickedToCreate = MutableLiveData<Boolean>()
    private var navigateWasEnabled = MutableLiveData<Boolean>()

    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    fun getIdeas(): LiveData<RealmResults<Idea>> {
        return realm.ideaDao().getIdeas()
    }

    fun getOrCreateIdea(id: String): Idea {
        return realm.ideaDao().getOrCreateIdea(id)
    }

    fun deleteIdea(ideaId: String) {
        realm.ideaDao().deleteIdea(ideaId)
    }

    fun getFunctionalities(ideaId: String): LiveData<RealmResults<Functionality>> {
        return realm.ideaDao().getFunctionalities(ideaId)
    }

    fun getFunctionality(id: String): Functionality? {
        return realm.ideaDao().getFunctionality(id)
    }

    fun addFunctionality(ideaId: String): String {
        val id = UUID.randomUUID().toString()
        realm.ideaDao().addFunctionality(ideaId, id)
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
        realm.ideaDao().updateIdea(id, title, elevatorPitch)
    }

    fun watchForNavigateEnabled() : LiveData<Boolean> {
        return navigateWasEnabled
    }

    fun enableNavigation() {
        val currentValue = navigateWasEnabled.value?: true
        navigateWasEnabled.value = !currentValue
    }

    fun watchFabWasClickedToCreate() : LiveData<Boolean> {
        return fabWasClickedToCreate
    }

    fun fabClicked(toCreate: Boolean) {
        fabWasClickedToCreate.value = toCreate
        if(toCreate) {
            navigateWasEnabled.value = true
            return
        }
    }

    fun itemClicked(ideaId: String) {
        editableItem.value = ideaId
        fabWasClickedToCreate.value = true
    }

    fun watchForEditableItemClicked() : LiveData<String> {
        return editableItem
    }

    fun deleteFunctionality(functionalityId: String) {
        realm.ideaDao().deleteFunctionality(functionalityId)
    }
}