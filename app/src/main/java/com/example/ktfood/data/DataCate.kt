package com.example.ktfood.data

import com.example.ktfood.R
import com.example.ktfood.model.Affirmation

class DataCate(){
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.drawable.main_logo),
            Affirmation(R.string.affirmation2, R.drawable.item_cate),
            Affirmation(R.string.affirmation1, R.drawable.main_logo),
        Affirmation(R.string.affirmation2, R.drawable.item_cate))
    }
}
