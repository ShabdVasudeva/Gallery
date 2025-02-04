package apw.codebase.gallery;

import android.app.Application
import com.google.android.material.color.DynamicColors

open class AppName: Application(){
    override fun onCreate(){
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this@AppName)
    }
}