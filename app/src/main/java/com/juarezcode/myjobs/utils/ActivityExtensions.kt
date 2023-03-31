package com.juarezcode.myjobs.utils

import android.app.Activity
import android.widget.Toast

fun Activity.mostrarToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}
