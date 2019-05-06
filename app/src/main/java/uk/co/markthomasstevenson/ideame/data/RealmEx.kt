package uk.co.markthomasstevenson.ideame.data

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmResults
import uk.co.markthomasstevenson.ideame.repos.IdeaRepository

fun <T: RealmModel> RealmResults<T>.asLiveData() = RealmLiveData<T>(this)
fun Realm.ideaDao() : IdeaRepository = IdeaRepository(this)