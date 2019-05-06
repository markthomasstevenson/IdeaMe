package uk.co.markthomasstevenson.ideame.repos

import androidx.lifecycle.LiveData
import io.realm.Realm
import io.realm.RealmResults
import uk.co.markthomasstevenson.ideame.data.asLiveData
import uk.co.markthomasstevenson.ideame.model.Idea

class IdeaRepository(val realm: Realm) {
    fun getIdeas(): LiveData<RealmResults<Idea>> {
        return realm.where(Idea::class.java).findAllAsync().asLiveData()
    }
}