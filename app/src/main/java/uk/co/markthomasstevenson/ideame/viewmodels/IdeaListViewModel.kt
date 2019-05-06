package uk.co.markthomasstevenson.ideame.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.realm.Realm
import io.realm.RealmResults
import uk.co.markthomasstevenson.ideame.data.ideaDao
import uk.co.markthomasstevenson.ideame.model.Idea


class IdeaListViewModel : ViewModel() {
    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }

    fun getIdeas(): LiveData<RealmResults<Idea>> {
        return realm.ideaDao().getIdeas()
    }

    override fun onCleared() {
        realm.close()
        super.onCleared()
    }
}