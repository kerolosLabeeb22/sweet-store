package com.example.online_shope.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.online_shope.domain.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainRepository {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadBanner(): LiveData<MutableList<SliderModel>> {

        val listData = MutableLiveData<MutableList<SliderModel>>()
        val ref = firebaseDatabase.getReference("Banner")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapshot in snapshot.children) {
                    val item = childSnapshot.getValue(SliderModel::class.java)
                    item?.let {
                        lists.add(it)
                    }
                }
                // update LiveData
                listData.value = lists


                Log.d("DEBUG_FIREBASE", "Fetched ${lists.size} banners from Firebase")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("DEBUG_FIREBASE", "Firebase load error: ${error.message}")
            }
        })

        return listData
    }
}
