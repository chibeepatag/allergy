package com.chibee.allergy

import android.app.Application
import com.chibee.allergy.data.AllergyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AllergyApplication : Application(){
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AllergyDatabase.getInstance(this, applicationScope) }
}