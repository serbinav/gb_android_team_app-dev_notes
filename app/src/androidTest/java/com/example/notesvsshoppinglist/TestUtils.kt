package com.example.notesvsshoppinglist

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.hamcrest.Description
import java.text.SimpleDateFormat
import java.util.*


object EspressoUtils {
    fun hasCheckedItem(id: Int): BoundedMatcher<View?, BottomNavigationView> {
        return object :
            BoundedMatcher<View?, BottomNavigationView>(BottomNavigationView::class.java) {
            var checkedIds: MutableSet<Int> = HashSet()
            var itemFound = false
            var triedMatching = false
            override fun describeTo(description: Description) {
                if (!triedMatching) {
                    description.appendText("BottomNavigationView")
                    return
                }
                description.appendText("BottomNavigationView to have a checked item with id=")
                description.appendValue(id)
                if (itemFound) {
                    description.appendText(", but selection was=")
                    description.appendValue(checkedIds)
                } else {
                    description.appendText(", but it doesn't have an item with such id")
                }
            }

            override fun matchesSafely(navigationView: BottomNavigationView): Boolean {
                triedMatching = true
                val menu: Menu = navigationView.menu
                for (i in 0 until menu.size()) {
                    val item: MenuItem = menu.getItem(i)
                    if (item.isChecked) {
                        checkedIds.add(item.itemId)
                    }
                    if (item.itemId == id) {
                        itemFound = true
                    }
                }
                return checkedIds.contains(id)
            }
        }
    }
}

fun getCurrentDateTime_test(): String {
    val date = Calendar.getInstance()

    val format = "dd.MM.yyyy"
    val formatter = SimpleDateFormat(format)

    return formatter.format(date.time)
}

fun nameRandom(): String{
    return "Факт №" + (0..100000).shuffled().first()
}

fun descriptionRandom(): String{
    return "В среднем самые высокие люди – голландцы. Их рост " + (1000..2000).shuffled().first()
}

fun taskRandom(): String{
    return "Элемент списка №" + (0..1000).shuffled().first()
}